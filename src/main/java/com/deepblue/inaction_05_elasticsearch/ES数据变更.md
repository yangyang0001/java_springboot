# ES 数据变更机制

## 1. ES 中没有"原地修改/删除"

ES 底层基于 Lucene，Lucene 的 segment 是**不可变（immutable）**的，所以：

### 更新（Update）
- ES 没有真正的"原地修改"。一次 update 实际上是：先把旧文档标记为删除，再写入一个新版本的文档（新的 `_seq_no`/`_version`）。
- 也就是说一次 update = 一次逻辑删除 + 一次新增。

### 删除（Delete）
- 删除也不是立即从磁盘物理擦除数据，而是给该文档打上一个"墓碑标记"（tombstone，`.del` 文件里标记该文档 ID 不可见）。
- 文档在查询时会被过滤掉（不可见），但底层数据依然占用磁盘空间。

### 真正的物理清除：Segment Merge
- ES/Lucene 后台会定期做 **segment merge**（合并小 segment 成大 segment）。
- 在 merge 过程中，被标记删除的文档才会被真正丢弃，不会被写入新的合并后 segment，此时磁盘空间才会被回收。
- 如果一直不触发 merge（比如索引很少写入），被删除/被更新覆盖的旧数据可能会长期占用磁盘。

### 实际影响
- 频繁 update/delete 会导致 segment 数量增多、磁盘占用膨胀（旧版本数据未被及时清理），也会影响查询性能（需要过滤更多已删除文档）。
- 可以用 `_forcemerge` API 强制触发合并、清理已删除文档，但这是重操作，一般只在低峰期对只读/冷索引执行。
- 也可以通过 `index.merge.policy` 相关参数调整合并策略。

一句话总结：ES 中没有"删除即释放空间"这回事，delete 和 update 本质上都是追加写 + 标记旧数据失效，真正的空间回收依赖后台的 segment merge。

## 2. Merge 触发规则

ES 默认使用 **TieredMergePolicy**，触发合并的规则大致如下：

### 分层（Tiered）合并触发条件
- Segment 按大小分层，同一层内的 segment 数量达到阈值（`index.merge.policy.segments_per_tier`，默认 **10**）时，就会触发一次合并，把这一层的 segment 合并成更大的 segment。

### 关键参数

| 参数 | 默认值 | 作用 |
|---|---|---|
| `index.merge.policy.max_merged_segment` | 5GB | 单个合并后 segment 的目标最大大小，超过此大小的 segment 不会再参与合并 |
| `index.merge.policy.segments_per_tier` | 10 | 每层允许的 segment 数量，超过就合并 |
| `index.merge.policy.floor_segment` | 2MB | 小于此大小的 segment 会被"拍平"到同一层，避免出现大量微小 segment |
| `index.merge.policy.deletes_pct_allowed` | 20% | 当 segment 中被删除文档占比超过这个百分比，即使不满足大小条件也会优先被选中合并，用来回收空间 |

### 选择合并对象的规则
- Merge policy 会打分（考虑 segment 大小、删除文档比例等），优先选择：
  - **删除文档占比高**的 segment（哪怕它本身不小），因为合并能清理更多"墓碑"数据，性价比高；
  - 大小相近的多个小 segment，合并性价比也高（减少查询时要打开的文件数）。

### 触发时机
- 每次 `refresh`（生成新 segment）、`flush`、后台 merge 线程调度时都会检查是否满足合并条件，不是定时任务，而是"事件驱动 + 策略判断"。

### 强制清理
- 如果想立刻回收被删除文档占用的空间，而不是等自然合并，可以调用：
  ```
  POST /your_index/_forcemerge?only_expunge_deletes=true
  ```
  这个只清理已删除文档，不做全量合并，相对轻量；不加参数的 `_forcemerge` 则是把 segment 数量强制减少到指定值（默认 1），开销较大，建议只在冷索引/低峰期执行。

一句话：merge 的核心规则是"按大小分层 + 删除比例优先"，目的是在减少 segment 数量和及时回收已删除文档空间之间做平衡。
