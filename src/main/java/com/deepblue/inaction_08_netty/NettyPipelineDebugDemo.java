package com.deepblue.inaction_08_netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;

/**
 * 在 IDEA 中 Debug main 即可，不启动 Spring，不监听端口，也不需要客户端。
 * EmbeddedChannel 运行真正的 Netty Pipeline，但不模拟真实 socket 和线程调度。
 *
 * 添加顺序：Head -> InboundA -> OutboundA -> InboundB -> Business -> OutboundB -> Tail
 * 入站顺序：InboundA -> InboundB -> Business
 * ctx 出站：Business -> OutboundA -> Head
 * channel 出站：Tail -> OutboundB -> OutboundA -> Head
 *
 * 建议先在所有标记 BP 的位置打断点，通过 F7 进入 Netty 源码，F8 看业务顺序。
 */
public final class NettyPipelineDebugDemo {

    private enum Mode {
        CTX_WRITE, CHANNEL_WRITE, STOP_INBOUND
    }

    public static void main(String[] args) {
        // 单独调试一种模式时，注释另外两行即可。
        run(Mode.CTX_WRITE);
        run(Mode.CHANNEL_WRITE);
        run(Mode.STOP_INBOUND);
    }

    private static void run(Mode mode) {
        System.out.println("\n========== " + mode + " ==========");
        EmbeddedChannel channel = new EmbeddedChannel();
        try {
            // BP0：逐行添加，查看 pipeline 内各 Context 的 prev / next。
            channel.pipeline().addLast("inboundA", new InboundA(mode));
            channel.pipeline().addLast("outboundA", new OutboundA());
            channel.pipeline().addLast("inboundB", new InboundB());
            channel.pipeline().addLast("business", new Business(mode));
            channel.pipeline().addLast("outboundB", new OutboundB());

            // BP1：模拟 socket 读到一条消息；从 Pipeline 的入站方向注入。
            // 此例消息都是 String，用来专注观察责任链，不涉及 ByteBuf 所有权。
            channel.writeInbound("hello");
            channel.runPendingTasks();
            channel.checkException();

            String response = channel.readOutbound();
            System.out.println("[RESULT] " + response);
            String expected = mode == Mode.STOP_INBOUND ? null : "reply:HELLO";
            if (expected == null ? response != null : !expected.equals(response)) {
                throw new IllegalStateException("Unexpected outbound response: " + response);
            }
        } finally {
            channel.finishAndReleaseAll();
        }
    }

    private static final class InboundA extends ChannelInboundHandlerAdapter {
        private final Mode mode;

        private InboundA(Mode mode) {
            this.mode = mode;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            // BP2：ctx.name() = inboundA；查看 msg 和 ctx 的 prev / next。
            log("IN", ctx, msg);
            if (mode == Mode.STOP_INBOUND) {
                System.out.println("[STOP] 不调用 fireChannelRead，后续入站不再执行");
                return;
            }
            // BP3：F7 进入这里，观察 findContextInbound 跳过 OutboundA。
            ctx.fireChannelRead(msg);
        }
    }

    private static final class InboundB extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            // BP4：入站转换消息，再把新的 String 交给 Business。
            log("IN", ctx, msg);
            String request = ((String) msg).toUpperCase(java.util.Locale.ROOT);
            ctx.fireChannelRead(request);
        }
    }

    private static final class Business extends ChannelInboundHandlerAdapter {
        private final Mode mode;

        private Business(Mode mode) {
            this.mode = mode;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            // BP5：业务消费请求，不再 fireChannelRead，因此不会传到 Tail。
            log("IN", ctx, msg);
            String response = "reply:" + msg;
            ChannelFuture future;
            if (mode == Mode.CTX_WRITE) {
                // BP6：从 Business 的前方查找 Outbound，跳过 InboundB。
                // 路径：OutboundA；Business 后面的 OutboundB 不会执行。
                future = ctx.writeAndFlush(response);
            } else {
                // BP7：从 Tail 查找 Outbound，先执行 OutboundB，再执行 OutboundA。
                future = ctx.channel().writeAndFlush(response);
            }
            future.addListener(done -> {
                // BP10：EmbeddedChannel 可能已经同步完成，因此回调可能立即执行。
                // 真正网络 Channel 的完成时机不同；不要在 I/O 回调里 sync 等待。
                System.out.println("[FUTURE] success=" + done.isSuccess());
            });
        }
    }

    private static final class OutboundA extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
            // BP9：两种发送入口都会到达这里。
            log("OUT", ctx, msg);
            // 继续向前查找 Outbound，并传递原 Promise。
            ctx.write(msg, promise);
        }
    }

    private static final class OutboundB extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
            // BP8：只有 CHANNEL_WRITE 会到达这里。
            log("OUT", ctx, msg);
            // 不要换成 ctx.channel().write(...)，否则又从 Tail 开始，可能递归。
            ctx.write(msg, promise);
        }
    }

    private static void log(String direction, ChannelHandlerContext ctx, Object msg) {
        System.out.printf("[%s] %-10s msg=%s thread=%s%n",
                direction, ctx.name(), msg, Thread.currentThread().getName());
    }
}
