//package com.deepblue.inaction_100_source_algorithm;
//
///*
// * Copyright (c) 1997, 2017, Oracle and/or its affiliates. All rights reserved.
// * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//
//import java.io.IOException;
//import java.io.InvalidObjectException;
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.function.BiConsumer;
//import java.util.function.BiFunction;
//import java.util.function.Consumer;
//import java.util.function.Function;
//import sun.misc.SharedSecrets;
//
///**
// * <tt>Map</tt> 接口的基于哈希表的实现。
// * Hash table based implementation of the <tt>Map</tt> interface.
// *
// * 此实现提供所有可选的映射操作，并允许 <tt>null</tt> 值和 <tt>null</tt> 键。
// * This implementation provides all of the optional map operations, and permits <tt>null</tt> values and the <tt>null</tt> key.
// *
// * (<tt>HashMap</tt> 类大致等价于 <tt>Hashtable</tt>，除了它是不同步的并且允许空值。)
// * (The <tt>HashMap</tt> class is roughly equivalent to <tt>Hashtable</tt>, except that it is unsynchronized and permits nulls.)
// *
// * 此类不保证地图的顺序；特别是，它不保证订单会随着时间的推移保持不变。
// * This class makes no guarantees as to the order of the map; in particular, it does not guarantee that the order will remain constant over time.
// *
// * <p>此实现为基本操作（<tt>get</tt> 和 <tt>put</tt>）提供恒定时间性能，
// * <p>This implementation provides constant-time performance for the basic operations (<tt>get</tt> and <tt>put</tt>),
// *
// * 假设散列函数将元素正确地分散在桶中。
// * assuming the hash function disperses the elements properly among the buckets.
// *
// * 集合视图的迭代需要的时间与 <tt>HashMap</tt> 实例的“容量”（桶的数量）加上它的大小（键值映射的数量）成正比。
// * Iteration over collection views requires time proportional to the "capacity" of the <tt>HashMap</tt> instance (the number of buckets) plus its size (the number of key-value mappings).
// *
// * 因此，如果迭代性能很重要，则不要将初始容量设置得太高（或负载因子太低），这一点非常重要。 TODO 如果经常遍历, 则将初始容量设置小一些 或者 负载因子设置相对大一些!
// * Thus, it's very important not to set the initial capacity too high (or the load factor too low) if iteration performance is important.
// *
// * TODO 核心: <p><tt>HashMap</tt> 的实例有两个影响其性能的参数：<i>初始容量</i> 和<i>负载因子</i>。
// * <p>An instance of <tt>HashMap</tt> has two parameters that affect its performance: <i>initial capacity</i> and <i>load factor</i>.
// *
// * <i>容量</i>是哈希表中桶的数量，初始容量就是哈希表创建时的容量。 TODO 初始容量 = 初始桶的数量!
// * The <i>capacity</i> is the number of buckets in the hash table, and the initial capacity is simply the capacity at the time the hash table is created.
// *
// * <i>负载因子</i> 是哈希表在其容量自动增加之前允许达到的程度的度量。 TODO 目前 有数据的桶数 / 总桶数
// * The <i>load factor</i> is a measure of how full the hash table is allowed to get before its capacity is automatically increased.
// *
// * 当哈希表中的条目数超过负载因子和当前容量的乘积时，
// * When the number of entries in the hash table exceeds the product of the load factor and the current capacity,
// *
// * 散列表被<i>重新散列</i>（也就是说，内部数据结构被重建），因此散列表的桶数大约是两倍。 TODO 扩容策略: 扩容为 原始的两倍!
// * the hash table is <i>rehashed</i> (that is, internal data structures are rebuilt) so that the hash table has approximately twice the number of buckets.
// *
// * <p>作为一般规则，默认负载系数 (.75) 在时间和空间成本之间提供了良好的折衷。
// * <p>As a general rule, the default load factor (.75) offers a good tradeoff between time and space costs.
// *
// * TODO 负载因子较高的值会减少空间开销，但会增加查找成本（反映在 <tt>HashMap</tt> 类的大多数操作中，包括 <tt>get</tt> 和 <tt>put</tt>）。
// * Higher values decrease the space overhead but increase the lookup cost (reflected in most of the operations of the <tt>HashMap</tt> class, including <tt>get</tt> and <tt>put</tt>).
// *
// * 在设置其初始容量时，应考虑映射中的预期条目数及其负载因子，以尽量减少重新哈希操作的次数。TODO 预估桶的个数, 减少rehashed 次数!
// * The expected number of entries in the map and its load factor should be taken into account when setting its initial capacity, so as to minimize the number of rehash operations.
// *
// * 如果初始容量大于最大条目数除以负载因子，则不会发生重新哈希操作。  TODO 原则上: capacity * factor >= items
// * If the initial capacity is greater than the maximum number of entries divided by the load factor, no rehash operations will ever occur.
// *
// * <p>如果要在一个 <tt>HashMap</tt> 实例中存储许多映射，则创建具有足够大容量的实例将使映射存储效率更高，而不是让它根据需要执行自动重新散列以增长表.
// * <p>If many mappings are to be stored in a <tt>HashMap</tt> instance, creating it with a sufficiently large capacity will allow the mappings to be stored more efficiently than letting it perform automatic rehashing as needed to grow the table.
// *
// * 请注意，使用很多具有相同 hashCode 的key时, 会降低 hashtable 的性能!
// * Note that using many keys with the same {@code hashCode()} is a sure way to slow down performance of any hash table.
// *
// * 为了改善影响，当键是 {@link Comparable} 时，此类可以使用键之间的比较顺序来帮助打破平局。
// * To ameliorate impact, when keys are {@link Comparable}, this class may use comparison order among keys to help break ties.
// *
// * <p><strong>请注意，TODO 此实现不同步。</strong>
// * <p><strong>Note that this implementation is not synchronized.</strong>
// *
// * 如果多个线程同时访问哈希映射，并且至少有一个线程在结构上修改映射，则它<i>必须</i>在外部同步。
// * If multiple threads access a hash map concurrently, and at least one of the threads modifies the map structurally, it <i>must</i> be synchronized externally.
// *
// * (结构修改是添加或删除一个或多个映射的任何操作；仅更改与实例已包含的键关联的值不是结构修改。)
// * (A structural modification is any operation that adds or deletes one or more mappings; merely changing the value associated with a key that an instance already contains is not a structural modification.)
// *
// * 这通常是通过同步一些自然封装地图的对象来完成的。
// * This is typically accomplished by synchronizing on some object that naturally encapsulates the map.
// *
// * TODO 如果不存在这样的对象，则应使用 {@link Collections#synchronizedMap Collections.synchronizedMap} 方法“包装”地图。
// * If no such object exists, the map should be "wrapped" using the {@link Collections#synchronizedMap Collections.synchronizedMap} method.
// *
// * 这最好在创建时完成，以防止对地图的意外不同步访问：<pre>
// * This is best done at creation time, to prevent accidental unsynchronized access to the map:<pre>
// *
// *   TODO 如果使用封装的同步HashMap 使用方式如下:
// *   Map m = Collections.synchronizedMap(new HashMap(...));</pre>
// *
// * <p>这个类的所有“集合视图方法”返回的迭代器都是<i>fail-fast</i>：
// * <p>The iterators returned by all of this class's "collection view methods" are <i>fail-fast</i>:
// *
// * 如果在创建迭代器后的任何时候对映射进行了结构修改，除了通过迭代器自己的 <tt>remove</tt> 方法之外的任何方式，迭代器都会抛出 {@link ConcurrentModificationException}。
// * if the map is structurally modified at any time after the iterator is created, in any way except through the iterator's own <tt>remove</tt> method, the iterator will throw a {@link ConcurrentModificationException}.
// *
// * 因此，面对并发修改，迭代器快速而干净地失败，而不是在未来不确定的时间冒任意的、非确定性的行为。
// * Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than risking arbitrary, non-deterministic behavior at an undetermined time in the future.
// *
// * <p>请注意，无法保证迭代器的快速失败行为，因为一般来说，在存在不同步的并发修改的情况下无法做出任何硬保证。
// * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed as it is, generally speaking, impossible to make any hard guarantees in the presence of unsynchronized concurrent modification.
// *
// * 快速失败的迭代器会尽最大努力抛出 <tt>ConcurrentModificationException</tt>。
// * Fail-fast iterators throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
// *
// * 因此，编写一个抛出此异常来确保其正确性的程序是错误的：<i>迭代器的快速失败行为应仅用于检测错误。</i>
// * Therefore, it would be wrong to write a program that depended on this exception for its correctness: <i>the fail-fast behavior of iterators should be used only to detect bugs.</i>
// *
// * <p>这个类是<a href="{@docRoot}/../technotes/guides/collections/index.html">Java Collections Framework</a>的成员。
// * <p>This class is a member of the <a href="{@docRoot}/../technotes/guides/collections/index.html"> Java Collections Framework</a>.
// *
// * @param <K> the type of keys maintained by this map
// * @param <V> the type of mapped values
// *
// * @author  Doug Lea
// * @author  Josh Bloch
// * @author  Arthur van Hoff
// * @author  Neal Gafter
// * @see     Object#hashCode()
// * @see     Collection
// * @see     Map
// * @see     TreeMap
// * @see     Hashtable
// * @since   1.2
// */
//public class HashMap<K,V> extends AbstractMap<K,V>
//        implements Map<K,V>, Cloneable, Serializable {
//
//    private static final long serialVersionUID = 362498820763181265L;
//
//    /**
//     * 实施说明。
//     * Implementation notes.
//     *
//     * 此映射通常充当分桶（分桶）哈希表，但当桶变得太大时，它们将转换为 TreeNode 的桶，每个结构类似于 java.util.TreeMap 中的结构。
//     * This map usually acts as a binned (bucketed) hash table, but when bins get too large, they are transformed into bins of TreeNodes, each structured similarly to those in java.util.TreeMap.
//     *
//     * 大多数方法尝试使用正常的 bin，但在适用时中继到 TreeNode 方法（只需检查节点的实例）。
//     * Most methods try to use normal bins, but relay to TreeNode methods when applicable (simply by checking instanceof a node).
//     *
//     * TreeNode 的 bin 可以像任何其他 bin 一样被遍历和使用，但在填充过多时还支持更快的查找。
//     * Bins of TreeNodes may be traversed and used like any others, but additionally support faster lookup when overpopulated.
//     *
//     * 然而，由于绝大多数正常使用的 bin 并没有被过度填充，因此在 table 方法的过程中检查树 bin 的存在可能会被延迟。
//     * However, since the vast majority of bins in normal use are not overpopulated, checking for existence of tree bins may be delayed in the course of table methods.
//     *
//     * 树桶（即元素都是 TreeNodes 的桶）主要按 hashCode 排序，但在 ties 的情况下，如果两个元素属于相同的“C 类实现 Comparable<C>”，则 type 然后它们的 compareTo 方法用于订购。
//     * Tree bins (i.e., bins whose elements are all TreeNodes) are ordered primarily by hashCode, but in the case of ties, if two elements are of the same "class C implements Comparable<C>", type then their compareTo method is used for ordering.
//     *
//     * (我们保守地通过反射检查泛型类型来验证这一点——参见方法 compatibleClassFor)。
//     * (We conservatively check generic types via reflection to validate this -- see method comparableClassFor).
//     *
//     * 当键具有不同的散列或可排序时，树桶增加的复杂性在提供最坏情况 O(log n) 操作时是值得的，
//     * The added complexity of tree bins is worthwhile in providing worst-case O(log n) operations when keys either have distinct hashes or are orderable,
//     *
//     * 因此，在 hashCode() 方法返回分布不佳的值的意外或恶意使用下，性能会优雅地下降，
//     * Thus, performance degrades gracefully under accidental or malicious usages in which hashCode() methods return values that are poorly distributed,
//     *
//     * 以及许多键共享一个 hashCode 的那些，只要它们也是 Comparable 的。
//     * as well as those in which many keys share a hashCode, so long as they are also Comparable.
//     *
//     * (如果这些都不适用，与不采取预防措施相比，我们可能会浪费大约两倍的时间和空间。
//     * (If neither of these apply, we may waste about a factor of two in time and space compared to taking no precautions.
//     *
//     * 但唯一已知的案例源于糟糕的用户编程实践，这些实践已经很慢，以至于几乎没有什么区别。)
//     * But the only known cases stem from poor user programming practices that are already so slow that this makes little difference.)
//     *
//     * 因为 TreeNode 的大小大约是常规节点的两倍，所以我们仅在 bin 包含足够的节点以保证使用时才使用它们（请参阅 TREEIFY_THRESHOLD）。
//     * Because TreeNodes are about twice the size of regular nodes, we use them only when bins contain enough nodes to warrant use (see TREEIFY_THRESHOLD).
//     *
//     * 当它们变得太小（由于移除或调整大小）时，它们会被转换回普通垃圾桶。
//     * And when they become too small (due to removal or resizing) they are converted back to plain bins.
//     *
//     * 在具有良好分布的用户哈希码的使用中，很少使用树桶。
//     * In usages with well-distributed user hashCodes, tree bins are rarely used.
//     *
//     * 理想情况下，在随机哈希码下，bin 中节点的频率遵循泊松分布 (http://en.wikipedia.org/wiki/Poisson_distribution)，默认调整大小阈值为 0.75，参数平均约为 0.5，尽管具有由于调整粒度而导致的较大差异。
//     * Ideally, under random hashCodes, the frequency of nodes in bins follows a Poisson distribution (http://en.wikipedia.org/wiki/Poisson_distribution) with a parameter of about 0.5 on average for the default resizing threshold of 0.75, although with a large variance because of resizing granularity.
//     *
//     * 忽略方差，列表大小 k 的期望 是 (exp(-0.5) * pow(0.5, k) / factorial(k))。
//     * Ignoring variance, the expected occurrences of list size k are (exp(-0.5) * pow(0.5, k) / factorial(k)).
//     *
//     * 第一个值是：
//     * The first values are:
//     *
//     * 0:    0.60653066
//     * 1:    0.30326533
//     * 2:    0.07581633
//     * 3:    0.01263606
//     * 4:    0.00157952
//     * 5:    0.00015795
//     * 6:    0.00001316
//     * 7:    0.00000094
//     * 8:    0.00000006
//     *
//     * 更多：不到千万分之一
//     * more: less than 1 in ten million
//     *
//     * 树箱的根通常是它的第一个节点。
//     * The root of a tree bin is normally its first node.
//     *
//     * 然而，有时（目前仅在 Iterator.remove 上），根可能在其他地方，但可以在父链接之后恢复（方法 TreeNode.root()）。
//     * However, sometimes (currently only upon Iterator.remove), the root might be elsewhere, but can be recovered following parent links (method TreeNode.root()).
//     *
//     * 所有适用的内部方法都接受哈希码作为参数（通常由公共方法提供），允许它们相互调用而无需重新计算用户哈希码。
//     * All applicable internal methods accept a hash code as an argument (as normally supplied from a public method), allowing them to call each other without recomputing user hashCodes.
//     *
//     * 大多数内部方法还接受“tab”参数，通常是当前表，但在调整大小或转换时可能是新表或旧表。
//     * Most internal methods also accept a "tab" argument, that is normally the current table, but may be a new or old one when resizing or converting.
//     *
//     * 当 bin 列表被树化、拆分或未树化时，我们将它们保持在相同的相对访问/遍历顺序（即字段 Node.next）中，以更好地保留局部性，并稍微简化调用 iterator.remove 的拆分和遍历的处理。
//     * When bin lists are treeified, split, or untreeified, we keep them in the same relative access/traversal order (i.e., field Node.next) to better preserve locality, and to slightly simplify handling of splits and traversals that invoke iterator.remove.
//     *
//     * 在插入时使用比较器时，为了在重新平衡之间保持总排序（或此处要求的接近），我们将类和 identityHashCodes 比较为决胜局。
//     * When using comparators on insertion, to keep a total ordering (or as close as is required here) across rebalancings, we compare classes and identityHashCodes as tie-breakers.
//     *
//     * 由于子类 LinkedHashMap 的存在，普通模式与树模式之间的使用和转换变得复杂。
//     * The use and transitions among plain vs tree modes is complicated by the existence of subclass LinkedHashMap.
//     *
//     * 请参阅下面定义为在插入、删除和访问时调用的钩子方法，这些方法允许 LinkedHashMap 内部保持独立于这些机制。
//     * See below for hook methods defined to be invoked upon insertion, removal and access that allow LinkedHashMap internals to otherwise remain independent of these mechanics.
//     *
//     * (这还需要将地图实例传递给一些可能创建新节点的实用程序方法。)
//     * (This also requires that a map instance be passed to some utility methods that may create new nodes.)
//     *
//     * 类似并发编程的基于 SSA 的编码风格有助于避免在所有曲折的指针操作中出现别名错误。
//     * The concurrent-programming-like SSA-based coding style helps avoid aliasing errors amid all of the twisty pointer operations.
//     *
//     */
//
//    /**
//     * 默认初始容量 - 必须是 2 的幂。
//     * The default initial capacity - MUST be a power of two.
//     */
//    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
//
//    /**
//     * 最大容量，如果一个更高的值由任何一个带参数的构造函数隐式指定时使用。
//     * The maximum capacity, used if a higher value is implicitly specified by either of the constructors with arguments.
//     *
//     * 最大容量必须是 小于 1<<30 的幂。
//     * MUST be a power of two <= 1<<30.
//     */
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//
//    /**
//     * 构造函数中未指定时使用的负载因子。
//     * The load factor used when none specified in constructor.
//     */
//    static final float DEFAULT_LOAD_FACTOR = 0.75f;
//
//    /**
//     * 使用树而不是列表的 bin 计数阈值。
//     * The bin count threshold for using a tree rather than list for a bin.
//     *
//     * 将元素添加到至少具有这么多节点的 bin 时，bin 将转换为树。
//     * Bins are converted to trees when adding an element to a bin with at least this many nodes.
//     *
//     * 该值必须大于 2 并且应该至少为 8，以便与树移除中关于在收缩时转换回普通 bin 的假设相吻合。
//     * The value must be greater than 2 and should be at least 8 to mesh with assumptions in tree removal about conversion back to plain bins upon shrinkage.
//     */
//    static final int TREEIFY_THRESHOLD = 8;
//
//    /**
//     * 在调整大小操作期间 untreeifying（拆分）bin 的 bin 计数阈值。
//     * The bin count threshold for untreeifying a (split) bin during a resize operation.
//     *
//     * 应小于 TREEIFY_THRESHOLD，并且最多 6 以在移除时进行收缩检测。
//     * Should be less than TREEIFY_THRESHOLD, and at most 6 to mesh with shrinkage detection under removal.
//     */
//    static final int UNTREEIFY_THRESHOLD = 6;
//
//    /**
//     * 可对其进行树化的 bin 的最小表容量。
//     * The smallest table capacity for which bins may be treeified.
//     *
//     * (否则，如果 bin 中有太多节点，则会调整表的大小。)
//     * (Otherwise the table is resized if too many nodes in a bin.)
//     *
//     * 应至少为 4 * TREEIFY_THRESHOLD 以避免调整大小和树化阈值之间的冲突。
//     * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts between resizing and treeification thresholds.
//     */
//    static final int MIN_TREEIFY_CAPACITY = 64;
//
//    /**
//     * Basic hash bin node, used for most entries.
//     *
//     * (See below for TreeNode subclass, and in LinkedHashMap for its Entry subclass.)
//     */
//    static class Node<K,V> implements Map.Entry<K,V> {
//        final int hash;
//        final K key;
//        V value;
//        Node<K,V> next;
//
//        Node(int hash, K key, V value, Node<K,V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//        public final K getKey()        { return key; }
//        public final V getValue()      { return value; }
//        public final String toString() { return key + "=" + value; }
//
//        public final int hashCode() {
//            return Objects.hashCode(key) ^ Objects.hashCode(value);
//        }
//
//        public final V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//        public final boolean equals(Object o) {
//            if (o == this)
//                return true;
//            if (o instanceof Map.Entry) {
//                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
//                if (Objects.equals(key, e.getKey()) &&
//                        Objects.equals(value, e.getValue()))
//                    return true;
//            }
//            return false;
//        }
//    }
//
//    /* ---------------- Static utilities -------------- */
//
//    /**
//     * Computes key.hashCode() and spreads (XORs) higher bits of hash
//     * to lower.  Because the table uses power-of-two masking, sets of
//     * hashes that vary only in bits above the current mask will
//     * always collide. (Among known examples are sets of Float keys
//     * holding consecutive whole numbers in small tables.)  So we
//     * apply a transform that spreads the impact of higher bits
//     * downward. There is a tradeoff between speed, utility, and
//     * quality of bit-spreading. Because many common sets of hashes
//     * are already reasonably distributed (so don't benefit from
//     * spreading), and because we use trees to handle large sets of
//     * collisions in bins, we just XOR some shifted bits in the
//     * cheapest possible way to reduce systematic lossage, as well as
//     * to incorporate impact of the highest bits that would otherwise
//     * never be used in index calculations because of table bounds.
//     */
//    static final int hash(Object key) {
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//    }
//
//    /**
//     * Returns x's Class if it is of the form "class C implements
//     * Comparable<C>", else null.
//     */
//    static Class<?> comparableClassFor(Object x) {
//        if (x instanceof Comparable) {
//            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
//            if ((c = x.getClass()) == String.class) // bypass checks
//                return c;
//            if ((ts = c.getGenericInterfaces()) != null) {
//                for (int i = 0; i < ts.length; ++i) {
//                    if (((t = ts[i]) instanceof ParameterizedType) &&
//                            ((p = (ParameterizedType)t).getRawType() ==
//                                    Comparable.class) &&
//                            (as = p.getActualTypeArguments()) != null &&
//                            as.length == 1 && as[0] == c) // type arg is c
//                        return c;
//                }
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Returns k.compareTo(x) if x matches kc (k's screened comparable
//     * class), else 0.
//     */
//    @SuppressWarnings({"rawtypes","unchecked"}) // for cast to Comparable
//    static int compareComparables(Class<?> kc, Object k, Object x) {
//        return (x == null || x.getClass() != kc ? 0 :
//                ((Comparable)k).compareTo(x));
//    }
//
//    /**
//     * Returns a power of two size for the given target capacity.
//     */
//    static final int tableSizeFor(int cap) {
//        int n = cap - 1;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
//    }
//
//    /* ---------------- Fields -------------- */
//
//    /**
//     * The table, initialized on first use, and resized as
//     * necessary. When allocated, length is always a power of two.
//     * (We also tolerate length zero in some operations to allow
//     * bootstrapping mechanics that are currently not needed.)
//     */
//    transient Node<K,V>[] table;
//
//    /**
//     * Holds cached entrySet(). Note that AbstractMap fields are used
//     * for keySet() and values().
//     */
//    transient Set<Map.Entry<K,V>> entrySet;
//
//    /**
//     * The number of key-value mappings contained in this map.
//     */
//    transient int size;
//
//    /**
//     * The number of times this HashMap has been structurally modified
//     * Structural modifications are those that change the number of mappings in
//     * the HashMap or otherwise modify its internal structure (e.g.,
//     * rehash).  This field is used to make iterators on Collection-views of
//     * the HashMap fail-fast.  (See ConcurrentModificationException).
//     */
//    transient int modCount;
//
//    /**
//     * The next size value at which to resize (capacity * load factor).
//     *
//     * @serial
//     */
//    // (The javadoc description is true upon serialization.
//    // Additionally, if the table array has not been allocated, this
//    // field holds the initial array capacity, or zero signifying
//    // DEFAULT_INITIAL_CAPACITY.)
//    int threshold;
//
//    /**
//     * The load factor for the hash table.
//     *
//     * @serial
//     */
//    final float loadFactor;
//
//    /* ---------------- Public operations -------------- */
//
//    /**
//     * Constructs an empty <tt>HashMap</tt> with the specified initial
//     * capacity and load factor.
//     *
//     * @param  initialCapacity the initial capacity
//     * @param  loadFactor      the load factor
//     * @throws IllegalArgumentException if the initial capacity is negative
//     *         or the load factor is nonpositive
//     */
//    public HashMap(int initialCapacity, float loadFactor) {
//        if (initialCapacity < 0)
//            throw new IllegalArgumentException("Illegal initial capacity: " +
//                    initialCapacity);
//        if (initialCapacity > MAXIMUM_CAPACITY)
//            initialCapacity = MAXIMUM_CAPACITY;
//        if (loadFactor <= 0 || Float.isNaN(loadFactor))
//            throw new IllegalArgumentException("Illegal load factor: " +
//                    loadFactor);
//        this.loadFactor = loadFactor;
//        this.threshold = tableSizeFor(initialCapacity);
//    }
//
//    /**
//     * Constructs an empty <tt>HashMap</tt> with the specified initial
//     * capacity and the default load factor (0.75).
//     *
//     * @param  initialCapacity the initial capacity.
//     * @throws IllegalArgumentException if the initial capacity is negative.
//     */
//    public HashMap(int initialCapacity) {
//        this(initialCapacity, DEFAULT_LOAD_FACTOR);
//    }
//
//    /**
//     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
//     * (16) and the default load factor (0.75).
//     */
//    public HashMap() {
//        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
//    }
//
//    /**
//     * Constructs a new <tt>HashMap</tt> with the same mappings as the
//     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
//     * default load factor (0.75) and an initial capacity sufficient to
//     * hold the mappings in the specified <tt>Map</tt>.
//     *
//     * @param   m the map whose mappings are to be placed in this map
//     * @throws  NullPointerException if the specified map is null
//     */
//    public HashMap(Map<? extends K, ? extends V> m) {
//        this.loadFactor = DEFAULT_LOAD_FACTOR;
//        putMapEntries(m, false);
//    }
//
//    /**
//     * Implements Map.putAll and Map constructor.
//     *
//     * @param m the map
//     * @param evict false when initially constructing this map, else
//     * true (relayed to method afterNodeInsertion).
//     */
//    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
//        int s = m.size();
//        if (s > 0) {
//            if (table == null) { // pre-size
//                float ft = ((float)s / loadFactor) + 1.0F;
//                int t = ((ft < (float)MAXIMUM_CAPACITY) ?
//                        (int)ft : MAXIMUM_CAPACITY);
//                if (t > threshold)
//                    threshold = tableSizeFor(t);
//            }
//            else if (s > threshold)
//                resize();
//            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
//                K key = e.getKey();
//                V value = e.getValue();
//                putVal(hash(key), key, value, false, evict);
//            }
//        }
//    }
//
//    /**
//     * Returns the number of key-value mappings in this map.
//     *
//     * @return the number of key-value mappings in this map
//     */
//    public int size() {
//        return size;
//    }
//
//    /**
//     * Returns <tt>true</tt> if this map contains no key-value mappings.
//     *
//     * @return <tt>true</tt> if this map contains no key-value mappings
//     */
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    /**
//     * Returns the value to which the specified key is mapped,
//     * or {@code null} if this map contains no mapping for the key.
//     *
//     * <p>More formally, if this map contains a mapping from a key
//     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
//     * key.equals(k))}, then this method returns {@code v}; otherwise
//     * it returns {@code null}.  (There can be at most one such mapping.)
//     *
//     * <p>A return value of {@code null} does not <i>necessarily</i>
//     * indicate that the map contains no mapping for the key; it's also
//     * possible that the map explicitly maps the key to {@code null}.
//     * The {@link #containsKey containsKey} operation may be used to
//     * distinguish these two cases.
//     *
//     * @see #put(Object, Object)
//     */
//    public V get(Object key) {
//        Node<K,V> e;
//        return (e = getNode(hash(key), key)) == null ? null : e.value;
//    }
//
//    /**
//     * Implements Map.get and related methods.
//     *
//     * @param hash hash for key
//     * @param key the key
//     * @return the node, or null if none
//     */
//    final Node<K,V> getNode(int hash, Object key) {
//        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
//        if ((tab = table) != null && (n = tab.length) > 0 &&
//                (first = tab[(n - 1) & hash]) != null) {
//            if (first.hash == hash && // always check first node
//                    ((k = first.key) == key || (key != null && key.equals(k))))
//                return first;
//            if ((e = first.next) != null) {
//                if (first instanceof TreeNode)
//                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k))))
//                        return e;
//                } while ((e = e.next) != null);
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Returns <tt>true</tt> if this map contains a mapping for the
//     * specified key.
//     *
//     * @param   key   The key whose presence in this map is to be tested
//     * @return <tt>true</tt> if this map contains a mapping for the specified
//     * key.
//     */
//    public boolean containsKey(Object key) {
//        return getNode(hash(key), key) != null;
//    }
//
//    /**
//     * Associates the specified value with the specified key in this map.
//     * If the map previously contained a mapping for the key, the old
//     * value is replaced.
//     *
//     * @param key key with which the specified value is to be associated
//     * @param value value to be associated with the specified key
//     * @return the previous value associated with <tt>key</tt>, or
//     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
//     *         (A <tt>null</tt> return can also indicate that the map
//     *         previously associated <tt>null</tt> with <tt>key</tt>.)
//     */
//    public V put(K key, V value) {
//        return putVal(hash(key), key, value, false, true);
//    }
//
//    /**
//     * Implements Map.put and related methods.
//     *
//     * @param hash hash for key
//     * @param key the key
//     * @param value the value to put
//     * @param onlyIfAbsent if true, don't change existing value
//     * @param evict if false, the table is in creation mode.
//     * @return previous value, or null if none
//     */
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//                   boolean evict) {
//        Node<K,V>[] tab; Node<K,V> p; int n, i;
//        if ((tab = table) == null || (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        if ((p = tab[i = (n - 1) & hash]) == null)
//            tab[i] = newNode(hash, key, value, null);
//        else {
//            Node<K,V> e; K k;
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k))))
//                e = p;
//            else if (p instanceof TreeNode)
//                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
//            else {
//                for (int binCount = 0; ; ++binCount) {
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
//                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
//                            treeifyBin(tab, hash);
//                        break;
//                    }
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k))))
//                        break;
//                    p = e;
//                }
//            }
//            if (e != null) { // existing mapping for key
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null)
//                    e.value = value;
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        ++modCount;
//        if (++size > threshold)
//            resize();
//        afterNodeInsertion(evict);
//        return null;
//    }
//
//    /**
//     * Initializes or doubles table size.  If null, allocates in
//     * accord with initial capacity target held in field threshold.
//     * Otherwise, because we are using power-of-two expansion, the
//     * elements from each bin must either stay at same index, or move
//     * with a power of two offset in the new table.
//     *
//     * @return the table
//     */
//    final Node<K,V>[] resize() {
//        Node<K,V>[] oldTab = table;
//        int oldCap = (oldTab == null) ? 0 : oldTab.length;
//        int oldThr = threshold;
//        int newCap, newThr = 0;
//        if (oldCap > 0) {
//            if (oldCap >= MAXIMUM_CAPACITY) {
//                threshold = Integer.MAX_VALUE;
//                return oldTab;
//            }
//            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
//                    oldCap >= DEFAULT_INITIAL_CAPACITY)
//                newThr = oldThr << 1; // double threshold
//        }
//        else if (oldThr > 0) // initial capacity was placed in threshold
//            newCap = oldThr;
//        else {               // zero initial threshold signifies using defaults
//            newCap = DEFAULT_INITIAL_CAPACITY;
//            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
//        }
//        if (newThr == 0) {
//            float ft = (float)newCap * loadFactor;
//            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
//                    (int)ft : Integer.MAX_VALUE);
//        }
//        threshold = newThr;
//        @SuppressWarnings({"rawtypes","unchecked"})
//        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
//        table = newTab;
//        if (oldTab != null) {
//            for (int j = 0; j < oldCap; ++j) {
//                Node<K,V> e;
//                if ((e = oldTab[j]) != null) {
//                    oldTab[j] = null;
//                    if (e.next == null)
//                        newTab[e.hash & (newCap - 1)] = e;
//                    else if (e instanceof TreeNode)
//                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
//                    else { // preserve order
//                        Node<K,V> loHead = null, loTail = null;
//                        Node<K,V> hiHead = null, hiTail = null;
//                        Node<K,V> next;
//                        do {
//                            next = e.next;
//                            if ((e.hash & oldCap) == 0) {
//                                if (loTail == null)
//                                    loHead = e;
//                                else
//                                    loTail.next = e;
//                                loTail = e;
//                            }
//                            else {
//                                if (hiTail == null)
//                                    hiHead = e;
//                                else
//                                    hiTail.next = e;
//                                hiTail = e;
//                            }
//                        } while ((e = next) != null);
//                        if (loTail != null) {
//                            loTail.next = null;
//                            newTab[j] = loHead;
//                        }
//                        if (hiTail != null) {
//                            hiTail.next = null;
//                            newTab[j + oldCap] = hiHead;
//                        }
//                    }
//                }
//            }
//        }
//        return newTab;
//    }
//
//    /**
//     * Replaces all linked nodes in bin at index for given hash unless
//     * table is too small, in which case resizes instead.
//     */
//    final void treeifyBin(Node<K,V>[] tab, int hash) {
//        int n, index; Node<K,V> e;
//        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
//            resize();
//        else if ((e = tab[index = (n - 1) & hash]) != null) {
//            TreeNode<K,V> hd = null, tl = null;
//            do {
//                TreeNode<K,V> p = replacementTreeNode(e, null);
//                if (tl == null)
//                    hd = p;
//                else {
//                    p.prev = tl;
//                    tl.next = p;
//                }
//                tl = p;
//            } while ((e = e.next) != null);
//            if ((tab[index] = hd) != null)
//                hd.treeify(tab);
//        }
//    }
//
//    /**
//     * Copies all of the mappings from the specified map to this map.
//     * These mappings will replace any mappings that this map had for
//     * any of the keys currently in the specified map.
//     *
//     * @param m mappings to be stored in this map
//     * @throws NullPointerException if the specified map is null
//     */
//    public void putAll(Map<? extends K, ? extends V> m) {
//        putMapEntries(m, true);
//    }
//
//    /**
//     * Removes the mapping for the specified key from this map if present.
//     *
//     * @param  key key whose mapping is to be removed from the map
//     * @return the previous value associated with <tt>key</tt>, or
//     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
//     *         (A <tt>null</tt> return can also indicate that the map
//     *         previously associated <tt>null</tt> with <tt>key</tt>.)
//     */
//    public V remove(Object key) {
//        Node<K,V> e;
//        return (e = removeNode(hash(key), key, null, false, true)) == null ?
//                null : e.value;
//    }
//
//    /**
//     * Implements Map.remove and related methods.
//     *
//     * @param hash hash for key
//     * @param key the key
//     * @param value the value to match if matchValue, else ignored
//     * @param matchValue if true only remove if value is equal
//     * @param movable if false do not move other nodes while removing
//     * @return the node, or null if none
//     */
//    final Node<K,V> removeNode(int hash, Object key, Object value,
//                               boolean matchValue, boolean movable) {
//        Node<K,V>[] tab; Node<K,V> p; int n, index;
//        if ((tab = table) != null && (n = tab.length) > 0 &&
//                (p = tab[index = (n - 1) & hash]) != null) {
//            Node<K,V> node = null, e; K k; V v;
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k))))
//                node = p;
//            else if ((e = p.next) != null) {
//                if (p instanceof TreeNode)
//                    node = ((TreeNode<K,V>)p).getTreeNode(hash, key);
//                else {
//                    do {
//                        if (e.hash == hash &&
//                                ((k = e.key) == key ||
//                                        (key != null && key.equals(k)))) {
//                            node = e;
//                            break;
//                        }
//                        p = e;
//                    } while ((e = e.next) != null);
//                }
//            }
//            if (node != null && (!matchValue || (v = node.value) == value ||
//                    (value != null && value.equals(v)))) {
//                if (node instanceof TreeNode)
//                    ((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);
//                else if (node == p)
//                    tab[index] = node.next;
//                else
//                    p.next = node.next;
//                ++modCount;
//                --size;
//                afterNodeRemoval(node);
//                return node;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Removes all of the mappings from this map.
//     * The map will be empty after this call returns.
//     */
//    public void clear() {
//        Node<K,V>[] tab;
//        modCount++;
//        if ((tab = table) != null && size > 0) {
//            size = 0;
//            for (int i = 0; i < tab.length; ++i)
//                tab[i] = null;
//        }
//    }
//
//    /**
//     * Returns <tt>true</tt> if this map maps one or more keys to the
//     * specified value.
//     *
//     * @param value value whose presence in this map is to be tested
//     * @return <tt>true</tt> if this map maps one or more keys to the
//     *         specified value
//     */
//    public boolean containsValue(Object value) {
//        Node<K,V>[] tab; V v;
//        if ((tab = table) != null && size > 0) {
//            for (int i = 0; i < tab.length; ++i) {
//                for (Node<K,V> e = tab[i]; e != null; e = e.next) {
//                    if ((v = e.value) == value ||
//                            (value != null && value.equals(v)))
//                        return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Returns a {@link Set} view of the keys contained in this map.
//     * The set is backed by the map, so changes to the map are
//     * reflected in the set, and vice-versa.  If the map is modified
//     * while an iteration over the set is in progress (except through
//     * the iterator's own <tt>remove</tt> operation), the results of
//     * the iteration are undefined.  The set supports element removal,
//     * which removes the corresponding mapping from the map, via the
//     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
//     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
//     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
//     * operations.
//     *
//     * @return a set view of the keys contained in this map
//     */
//    public Set<K> keySet() {
//        Set<K> ks = keySet;
//        if (ks == null) {
//            ks = new KeySet();
//            keySet = ks;
//        }
//        return ks;
//    }
//
//    final class KeySet extends AbstractSet<K> {
//        public final int size()                 { return size; }
//        public final void clear()               { HashMap.this.clear(); }
//        public final Iterator<K> iterator()     { return new KeyIterator(); }
//        public final boolean contains(Object o) { return containsKey(o); }
//        public final boolean remove(Object key) {
//            return removeNode(hash(key), key, null, false, true) != null;
//        }
//        public final Spliterator<K> spliterator() {
//            return new KeySpliterator<>(HashMap.this, 0, -1, 0, 0);
//        }
//        public final void forEach(Consumer<? super K> action) {
//            Node<K,V>[] tab;
//            if (action == null)
//                throw new NullPointerException();
//            if (size > 0 && (tab = table) != null) {
//                int mc = modCount;
//                for (int i = 0; i < tab.length; ++i) {
//                    for (Node<K,V> e = tab[i]; e != null; e = e.next)
//                        action.accept(e.key);
//                }
//                if (modCount != mc)
//                    throw new ConcurrentModificationException();
//            }
//        }
//    }
//
//    /**
//     * Returns a {@link Collection} view of the values contained in this map.
//     * The collection is backed by the map, so changes to the map are
//     * reflected in the collection, and vice-versa.  If the map is
//     * modified while an iteration over the collection is in progress
//     * (except through the iterator's own <tt>remove</tt> operation),
//     * the results of the iteration are undefined.  The collection
//     * supports element removal, which removes the corresponding
//     * mapping from the map, via the <tt>Iterator.remove</tt>,
//     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
//     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
//     * support the <tt>add</tt> or <tt>addAll</tt> operations.
//     *
//     * @return a view of the values contained in this map
//     */
//    public Collection<V> values() {
//        Collection<V> vs = values;
//        if (vs == null) {
//            vs = new Values();
//            values = vs;
//        }
//        return vs;
//    }
//
//    final class Values extends AbstractCollection<V> {
//        public final int size()                 { return size; }
//        public final void clear()               { HashMap.this.clear(); }
//        public final Iterator<V> iterator()     { return new ValueIterator(); }
//        public final boolean contains(Object o) { return containsValue(o); }
//        public final Spliterator<V> spliterator() {
//            return new ValueSpliterator<>(HashMap.this, 0, -1, 0, 0);
//        }
//        public final void forEach(Consumer<? super V> action) {
//            Node<K,V>[] tab;
//            if (action == null)
//                throw new NullPointerException();
//            if (size > 0 && (tab = table) != null) {
//                int mc = modCount;
//                for (int i = 0; i < tab.length; ++i) {
//                    for (Node<K,V> e = tab[i]; e != null; e = e.next)
//                        action.accept(e.value);
//                }
//                if (modCount != mc)
//                    throw new ConcurrentModificationException();
//            }
//        }
//    }
//
//    /**
//     * Returns a {@link Set} view of the mappings contained in this map.
//     * The set is backed by the map, so changes to the map are
//     * reflected in the set, and vice-versa.  If the map is modified
//     * while an iteration over the set is in progress (except through
//     * the iterator's own <tt>remove</tt> operation, or through the
//     * <tt>setValue</tt> operation on a map entry returned by the
//     * iterator) the results of the iteration are undefined.  The set
//     * supports element removal, which removes the corresponding
//     * mapping from the map, via the <tt>Iterator.remove</tt>,
//     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
//     * <tt>clear</tt> operations.  It does not support the
//     * <tt>add</tt> or <tt>addAll</tt> operations.
//     *
//     * @return a set view of the mappings contained in this map
//     */
//    public Set<Map.Entry<K,V>> entrySet() {
//        Set<Map.Entry<K,V>> es;
//        return (es = entrySet) == null ? (entrySet = new EntrySet()) : es;
//    }
//
//    final class EntrySet extends AbstractSet<Map.Entry<K,V>> {
//        public final int size()                 { return size; }
//        public final void clear()               { HashMap.this.clear(); }
//        public final Iterator<Map.Entry<K,V>> iterator() {
//            return new EntryIterator();
//        }
//        public final boolean contains(Object o) {
//            if (!(o instanceof Map.Entry))
//                return false;
//            Map.Entry<?,?> e = (Map.Entry<?,?>) o;
//            Object key = e.getKey();
//            Node<K,V> candidate = getNode(hash(key), key);
//            return candidate != null && candidate.equals(e);
//        }
//        public final boolean remove(Object o) {
//            if (o instanceof Map.Entry) {
//                Map.Entry<?,?> e = (Map.Entry<?,?>) o;
//                Object key = e.getKey();
//                Object value = e.getValue();
//                return removeNode(hash(key), key, value, true, true) != null;
//            }
//            return false;
//        }
//        public final Spliterator<Map.Entry<K,V>> spliterator() {
//            return new EntrySpliterator<>(HashMap.this, 0, -1, 0, 0);
//        }
//        public final void forEach(Consumer<? super Map.Entry<K,V>> action) {
//            Node<K,V>[] tab;
//            if (action == null)
//                throw new NullPointerException();
//            if (size > 0 && (tab = table) != null) {
//                int mc = modCount;
//                for (int i = 0; i < tab.length; ++i) {
//                    for (Node<K,V> e = tab[i]; e != null; e = e.next)
//                        action.accept(e);
//                }
//                if (modCount != mc)
//                    throw new ConcurrentModificationException();
//            }
//        }
//    }
//
//    // Overrides of JDK8 Map extension methods
//
//    @Override
//    public V getOrDefault(Object key, V defaultValue) {
//        Node<K,V> e;
//        return (e = getNode(hash(key), key)) == null ? defaultValue : e.value;
//    }
//
//    @Override
//    public V putIfAbsent(K key, V value) {
//        return putVal(hash(key), key, value, true, true);
//    }
//
//    @Override
//    public boolean remove(Object key, Object value) {
//        return removeNode(hash(key), key, value, true, true) != null;
//    }
//
//    @Override
//    public boolean replace(K key, V oldValue, V newValue) {
//        Node<K,V> e; V v;
//        if ((e = getNode(hash(key), key)) != null &&
//                ((v = e.value) == oldValue || (v != null && v.equals(oldValue)))) {
//            e.value = newValue;
//            afterNodeAccess(e);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public V replace(K key, V value) {
//        Node<K,V> e;
//        if ((e = getNode(hash(key), key)) != null) {
//            V oldValue = e.value;
//            e.value = value;
//            afterNodeAccess(e);
//            return oldValue;
//        }
//        return null;
//    }
//
//    @Override
//    public V computeIfAbsent(K key,
//                             Function<? super K, ? extends V> mappingFunction) {
//        if (mappingFunction == null)
//            throw new NullPointerException();
//        int hash = hash(key);
//        Node<K,V>[] tab; Node<K,V> first; int n, i;
//        int binCount = 0;
//        TreeNode<K,V> t = null;
//        Node<K,V> old = null;
//        if (size > threshold || (tab = table) == null ||
//                (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        if ((first = tab[i = (n - 1) & hash]) != null) {
//            if (first instanceof TreeNode)
//                old = (t = (TreeNode<K,V>)first).getTreeNode(hash, key);
//            else {
//                Node<K,V> e = first; K k;
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k)))) {
//                        old = e;
//                        break;
//                    }
//                    ++binCount;
//                } while ((e = e.next) != null);
//            }
//            V oldValue;
//            if (old != null && (oldValue = old.value) != null) {
//                afterNodeAccess(old);
//                return oldValue;
//            }
//        }
//        V v = mappingFunction.apply(key);
//        if (v == null) {
//            return null;
//        } else if (old != null) {
//            old.value = v;
//            afterNodeAccess(old);
//            return v;
//        }
//        else if (t != null)
//            t.putTreeVal(this, tab, hash, key, v);
//        else {
//            tab[i] = newNode(hash, key, v, first);
//            if (binCount >= TREEIFY_THRESHOLD - 1)
//                treeifyBin(tab, hash);
//        }
//        ++modCount;
//        ++size;
//        afterNodeInsertion(true);
//        return v;
//    }
//
//    public V computeIfPresent(K key,
//                              BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
//        if (remappingFunction == null)
//            throw new NullPointerException();
//        Node<K,V> e; V oldValue;
//        int hash = hash(key);
//        if ((e = getNode(hash, key)) != null &&
//                (oldValue = e.value) != null) {
//            V v = remappingFunction.apply(key, oldValue);
//            if (v != null) {
//                e.value = v;
//                afterNodeAccess(e);
//                return v;
//            }
//            else
//                removeNode(hash, key, null, false, true);
//        }
//        return null;
//    }
//
//    @Override
//    public V compute(K key,
//                     BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
//        if (remappingFunction == null)
//            throw new NullPointerException();
//        int hash = hash(key);
//        Node<K,V>[] tab; Node<K,V> first; int n, i;
//        int binCount = 0;
//        TreeNode<K,V> t = null;
//        Node<K,V> old = null;
//        if (size > threshold || (tab = table) == null ||
//                (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        if ((first = tab[i = (n - 1) & hash]) != null) {
//            if (first instanceof TreeNode)
//                old = (t = (TreeNode<K,V>)first).getTreeNode(hash, key);
//            else {
//                Node<K,V> e = first; K k;
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k)))) {
//                        old = e;
//                        break;
//                    }
//                    ++binCount;
//                } while ((e = e.next) != null);
//            }
//        }
//        V oldValue = (old == null) ? null : old.value;
//        V v = remappingFunction.apply(key, oldValue);
//        if (old != null) {
//            if (v != null) {
//                old.value = v;
//                afterNodeAccess(old);
//            }
//            else
//                removeNode(hash, key, null, false, true);
//        }
//        else if (v != null) {
//            if (t != null)
//                t.putTreeVal(this, tab, hash, key, v);
//            else {
//                tab[i] = newNode(hash, key, v, first);
//                if (binCount >= TREEIFY_THRESHOLD - 1)
//                    treeifyBin(tab, hash);
//            }
//            ++modCount;
//            ++size;
//            afterNodeInsertion(true);
//        }
//        return v;
//    }
//
//    @Override
//    public V merge(K key, V value,
//                   BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
//        if (value == null)
//            throw new NullPointerException();
//        if (remappingFunction == null)
//            throw new NullPointerException();
//        int hash = hash(key);
//        Node<K,V>[] tab; Node<K,V> first; int n, i;
//        int binCount = 0;
//        TreeNode<K,V> t = null;
//        Node<K,V> old = null;
//        if (size > threshold || (tab = table) == null ||
//                (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        if ((first = tab[i = (n - 1) & hash]) != null) {
//            if (first instanceof TreeNode)
//                old = (t = (TreeNode<K,V>)first).getTreeNode(hash, key);
//            else {
//                Node<K,V> e = first; K k;
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k)))) {
//                        old = e;
//                        break;
//                    }
//                    ++binCount;
//                } while ((e = e.next) != null);
//            }
//        }
//        if (old != null) {
//            V v;
//            if (old.value != null)
//                v = remappingFunction.apply(old.value, value);
//            else
//                v = value;
//            if (v != null) {
//                old.value = v;
//                afterNodeAccess(old);
//            }
//            else
//                removeNode(hash, key, null, false, true);
//            return v;
//        }
//        if (value != null) {
//            if (t != null)
//                t.putTreeVal(this, tab, hash, key, value);
//            else {
//                tab[i] = newNode(hash, key, value, first);
//                if (binCount >= TREEIFY_THRESHOLD - 1)
//                    treeifyBin(tab, hash);
//            }
//            ++modCount;
//            ++size;
//            afterNodeInsertion(true);
//        }
//        return value;
//    }
//
//    @Override
//    public void forEach(BiConsumer<? super K, ? super V> action) {
//        Node<K,V>[] tab;
//        if (action == null)
//            throw new NullPointerException();
//        if (size > 0 && (tab = table) != null) {
//            int mc = modCount;
//            for (int i = 0; i < tab.length; ++i) {
//                for (Node<K,V> e = tab[i]; e != null; e = e.next)
//                    action.accept(e.key, e.value);
//            }
//            if (modCount != mc)
//                throw new ConcurrentModificationException();
//        }
//    }
//
//    @Override
//    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
//        Node<K,V>[] tab;
//        if (function == null)
//            throw new NullPointerException();
//        if (size > 0 && (tab = table) != null) {
//            int mc = modCount;
//            for (int i = 0; i < tab.length; ++i) {
//                for (Node<K,V> e = tab[i]; e != null; e = e.next) {
//                    e.value = function.apply(e.key, e.value);
//                }
//            }
//            if (modCount != mc)
//                throw new ConcurrentModificationException();
//        }
//    }
//
//    /* ------------------------------------------------------------ */
//    // Cloning and serialization
//
//    /**
//     * Returns a shallow copy of this <tt>HashMap</tt> instance: the keys and
//     * values themselves are not cloned.
//     *
//     * @return a shallow copy of this map
//     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public Object clone() {
//        HashMap<K,V> result;
//        try {
//            result = (HashMap<K,V>)super.clone();
//        } catch (CloneNotSupportedException e) {
//            // this shouldn't happen, since we are Cloneable
//            throw new InternalError(e);
//        }
//        result.reinitialize();
//        result.putMapEntries(this, false);
//        return result;
//    }
//
//    // These methods are also used when serializing HashSets
//    final float loadFactor() { return loadFactor; }
//    final int capacity() {
//        return (table != null) ? table.length :
//                (threshold > 0) ? threshold :
//                        DEFAULT_INITIAL_CAPACITY;
//    }
//
//    /**
//     * Save the state of the <tt>HashMap</tt> instance to a stream (i.e.,
//     * serialize it).
//     *
//     * @serialData The <i>capacity</i> of the HashMap (the length of the
//     *             bucket array) is emitted (int), followed by the
//     *             <i>size</i> (an int, the number of key-value
//     *             mappings), followed by the key (Object) and value (Object)
//     *             for each key-value mapping.  The key-value mappings are
//     *             emitted in no particular order.
//     */
//    private void writeObject(java.io.ObjectOutputStream s)
//            throws IOException {
//        int buckets = capacity();
//        // Write out the threshold, loadfactor, and any hidden stuff
//        s.defaultWriteObject();
//        s.writeInt(buckets);
//        s.writeInt(size);
//        internalWriteEntries(s);
//    }
//
//    /**
//     * Reconstitutes this map from a stream (that is, deserializes it).
//     * @param s the stream
//     * @throws ClassNotFoundException if the class of a serialized object
//     *         could not be found
//     * @throws IOException if an I/O error occurs
//     */
//    private void readObject(java.io.ObjectInputStream s)
//            throws IOException, ClassNotFoundException {
//        // Read in the threshold (ignored), loadfactor, and any hidden stuff
//        s.defaultReadObject();
//        reinitialize();
//        if (loadFactor <= 0 || Float.isNaN(loadFactor))
//            throw new InvalidObjectException("Illegal load factor: " +
//                    loadFactor);
//        s.readInt();                // Read and ignore number of buckets
//        int mappings = s.readInt(); // Read number of mappings (size)
//        if (mappings < 0)
//            throw new InvalidObjectException("Illegal mappings count: " +
//                    mappings);
//        else if (mappings > 0) { // (if zero, use defaults)
//            // Size the table using given load factor only if within
//            // range of 0.25...4.0
//            float lf = Math.min(Math.max(0.25f, loadFactor), 4.0f);
//            float fc = (float)mappings / lf + 1.0f;
//            int cap = ((fc < DEFAULT_INITIAL_CAPACITY) ?
//                    DEFAULT_INITIAL_CAPACITY :
//                    (fc >= MAXIMUM_CAPACITY) ?
//                            MAXIMUM_CAPACITY :
//                            tableSizeFor((int)fc));
//            float ft = (float)cap * lf;
//            threshold = ((cap < MAXIMUM_CAPACITY && ft < MAXIMUM_CAPACITY) ?
//                    (int)ft : Integer.MAX_VALUE);
//
//            // Check Map.Entry[].class since it's the nearest public type to
//            // what we're actually creating.
//            SharedSecrets.getJavaOISAccess().checkArray(s, Map.Entry[].class, cap);
//            @SuppressWarnings({"rawtypes","unchecked"})
//            Node<K,V>[] tab = (Node<K,V>[])new Node[cap];
//            table = tab;
//
//            // Read the keys and values, and put the mappings in the HashMap
//            for (int i = 0; i < mappings; i++) {
//                @SuppressWarnings("unchecked")
//                K key = (K) s.readObject();
//                @SuppressWarnings("unchecked")
//                V value = (V) s.readObject();
//                putVal(hash(key), key, value, false, false);
//            }
//        }
//    }
//
//    /* ------------------------------------------------------------ */
//    // iterators
//
//    abstract class HashIterator {
//        Node<K,V> next;        // next entry to return
//        Node<K,V> current;     // current entry
//        int expectedModCount;  // for fast-fail
//        int index;             // current slot
//
//        HashIterator() {
//            expectedModCount = modCount;
//            Node<K,V>[] t = table;
//            current = next = null;
//            index = 0;
//            if (t != null && size > 0) { // advance to first entry
//                do {} while (index < t.length && (next = t[index++]) == null);
//            }
//        }
//
//        public final boolean hasNext() {
//            return next != null;
//        }
//
//        final Node<K,V> nextNode() {
//            Node<K,V>[] t;
//            Node<K,V> e = next;
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//            if (e == null)
//                throw new NoSuchElementException();
//            if ((next = (current = e).next) == null && (t = table) != null) {
//                do {} while (index < t.length && (next = t[index++]) == null);
//            }
//            return e;
//        }
//
//        public final void remove() {
//            Node<K,V> p = current;
//            if (p == null)
//                throw new IllegalStateException();
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//            current = null;
//            K key = p.key;
//            removeNode(hash(key), key, null, false, false);
//            expectedModCount = modCount;
//        }
//    }
//
//    final class KeyIterator extends HashIterator
//            implements Iterator<K> {
//        public final K next() { return nextNode().key; }
//    }
//
//    final class ValueIterator extends HashIterator
//            implements Iterator<V> {
//        public final V next() { return nextNode().value; }
//    }
//
//    final class EntryIterator extends HashIterator
//            implements Iterator<Map.Entry<K,V>> {
//        public final Map.Entry<K,V> next() { return nextNode(); }
//    }
//
//    /* ------------------------------------------------------------ */
//    // spliterators
//
//    static class HashMapSpliterator<K,V> {
//        final HashMap<K,V> map;
//        Node<K,V> current;          // current node
//        int index;                  // current index, modified on advance/split
//        int fence;                  // one past last index
//        int est;                    // size estimate
//        int expectedModCount;       // for comodification checks
//
//        HashMapSpliterator(HashMap<K,V> m, int origin,
//                           int fence, int est,
//                           int expectedModCount) {
//            this.map = m;
//            this.index = origin;
//            this.fence = fence;
//            this.est = est;
//            this.expectedModCount = expectedModCount;
//        }
//
//        final int getFence() { // initialize fence and size on first use
//            int hi;
//            if ((hi = fence) < 0) {
//                HashMap<K,V> m = map;
//                est = m.size;
//                expectedModCount = m.modCount;
//                Node<K,V>[] tab = m.table;
//                hi = fence = (tab == null) ? 0 : tab.length;
//            }
//            return hi;
//        }
//
//        public final long estimateSize() {
//            getFence(); // force init
//            return (long) est;
//        }
//    }
//
//    static final class KeySpliterator<K,V>
//            extends HashMapSpliterator<K,V>
//            implements Spliterator<K> {
//        KeySpliterator(HashMap<K,V> m, int origin, int fence, int est,
//                       int expectedModCount) {
//            super(m, origin, fence, est, expectedModCount);
//        }
//
//        public KeySpliterator<K,V> trySplit() {
//            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
//            return (lo >= mid || current != null) ? null :
//                    new KeySpliterator<>(map, lo, index = mid, est >>>= 1,
//                            expectedModCount);
//        }
//
//        public void forEachRemaining(Consumer<? super K> action) {
//            int i, hi, mc;
//            if (action == null)
//                throw new NullPointerException();
//            HashMap<K,V> m = map;
//            Node<K,V>[] tab = m.table;
//            if ((hi = fence) < 0) {
//                mc = expectedModCount = m.modCount;
//                hi = fence = (tab == null) ? 0 : tab.length;
//            }
//            else
//                mc = expectedModCount;
//            if (tab != null && tab.length >= hi &&
//                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
//                Node<K,V> p = current;
//                current = null;
//                do {
//                    if (p == null)
//                        p = tab[i++];
//                    else {
//                        action.accept(p.key);
//                        p = p.next;
//                    }
//                } while (p != null || i < hi);
//                if (m.modCount != mc)
//                    throw new ConcurrentModificationException();
//            }
//        }
//
//        public boolean tryAdvance(Consumer<? super K> action) {
//            int hi;
//            if (action == null)
//                throw new NullPointerException();
//            Node<K,V>[] tab = map.table;
//            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
//                while (current != null || index < hi) {
//                    if (current == null)
//                        current = tab[index++];
//                    else {
//                        K k = current.key;
//                        current = current.next;
//                        action.accept(k);
//                        if (map.modCount != expectedModCount)
//                            throw new ConcurrentModificationException();
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }
//
//        public int characteristics() {
//            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
//                    Spliterator.DISTINCT;
//        }
//    }
//
//    static final class ValueSpliterator<K,V>
//            extends HashMapSpliterator<K,V>
//            implements Spliterator<V> {
//        ValueSpliterator(HashMap<K,V> m, int origin, int fence, int est,
//                         int expectedModCount) {
//            super(m, origin, fence, est, expectedModCount);
//        }
//
//        public ValueSpliterator<K,V> trySplit() {
//            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
//            return (lo >= mid || current != null) ? null :
//                    new ValueSpliterator<>(map, lo, index = mid, est >>>= 1,
//                            expectedModCount);
//        }
//
//        public void forEachRemaining(Consumer<? super V> action) {
//            int i, hi, mc;
//            if (action == null)
//                throw new NullPointerException();
//            HashMap<K,V> m = map;
//            Node<K,V>[] tab = m.table;
//            if ((hi = fence) < 0) {
//                mc = expectedModCount = m.modCount;
//                hi = fence = (tab == null) ? 0 : tab.length;
//            }
//            else
//                mc = expectedModCount;
//            if (tab != null && tab.length >= hi &&
//                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
//                Node<K,V> p = current;
//                current = null;
//                do {
//                    if (p == null)
//                        p = tab[i++];
//                    else {
//                        action.accept(p.value);
//                        p = p.next;
//                    }
//                } while (p != null || i < hi);
//                if (m.modCount != mc)
//                    throw new ConcurrentModificationException();
//            }
//        }
//
//        public boolean tryAdvance(Consumer<? super V> action) {
//            int hi;
//            if (action == null)
//                throw new NullPointerException();
//            Node<K,V>[] tab = map.table;
//            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
//                while (current != null || index < hi) {
//                    if (current == null)
//                        current = tab[index++];
//                    else {
//                        V v = current.value;
//                        current = current.next;
//                        action.accept(v);
//                        if (map.modCount != expectedModCount)
//                            throw new ConcurrentModificationException();
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }
//
//        public int characteristics() {
//            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0);
//        }
//    }
//
//    static final class EntrySpliterator<K,V>
//            extends HashMapSpliterator<K,V>
//            implements Spliterator<Map.Entry<K,V>> {
//        EntrySpliterator(HashMap<K,V> m, int origin, int fence, int est,
//                         int expectedModCount) {
//            super(m, origin, fence, est, expectedModCount);
//        }
//
//        public EntrySpliterator<K,V> trySplit() {
//            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
//            return (lo >= mid || current != null) ? null :
//                    new EntrySpliterator<>(map, lo, index = mid, est >>>= 1,
//                            expectedModCount);
//        }
//
//        public void forEachRemaining(Consumer<? super Map.Entry<K,V>> action) {
//            int i, hi, mc;
//            if (action == null)
//                throw new NullPointerException();
//            HashMap<K,V> m = map;
//            Node<K,V>[] tab = m.table;
//            if ((hi = fence) < 0) {
//                mc = expectedModCount = m.modCount;
//                hi = fence = (tab == null) ? 0 : tab.length;
//            }
//            else
//                mc = expectedModCount;
//            if (tab != null && tab.length >= hi &&
//                    (i = index) >= 0 && (i < (index = hi) || current != null)) {
//                Node<K,V> p = current;
//                current = null;
//                do {
//                    if (p == null)
//                        p = tab[i++];
//                    else {
//                        action.accept(p);
//                        p = p.next;
//                    }
//                } while (p != null || i < hi);
//                if (m.modCount != mc)
//                    throw new ConcurrentModificationException();
//            }
//        }
//
//        public boolean tryAdvance(Consumer<? super Map.Entry<K,V>> action) {
//            int hi;
//            if (action == null)
//                throw new NullPointerException();
//            Node<K,V>[] tab = map.table;
//            if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
//                while (current != null || index < hi) {
//                    if (current == null)
//                        current = tab[index++];
//                    else {
//                        Node<K,V> e = current;
//                        current = current.next;
//                        action.accept(e);
//                        if (map.modCount != expectedModCount)
//                            throw new ConcurrentModificationException();
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }
//
//        public int characteristics() {
//            return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
//                    Spliterator.DISTINCT;
//        }
//    }
//
//    /* ------------------------------------------------------------ */
//    // LinkedHashMap support
//
//
//    /*
//     * The following package-protected methods are designed to be
//     * overridden by LinkedHashMap, but not by any other subclass.
//     * Nearly all other internal methods are also package-protected
//     * but are declared final, so can be used by LinkedHashMap, view
//     * classes, and HashSet.
//     */
//
//    // Create a regular (non-tree) node
//    Node<K,V> newNode(int hash, K key, V value, Node<K,V> next) {
//        return new Node<>(hash, key, value, next);
//    }
//
//    // For conversion from TreeNodes to plain nodes
//    Node<K,V> replacementNode(Node<K,V> p, Node<K,V> next) {
//        return new Node<>(p.hash, p.key, p.value, next);
//    }
//
//    // Create a tree bin node
//    TreeNode<K,V> newTreeNode(int hash, K key, V value, Node<K,V> next) {
//        return new TreeNode<>(hash, key, value, next);
//    }
//
//    // For treeifyBin
//    TreeNode<K,V> replacementTreeNode(Node<K,V> p, Node<K,V> next) {
//        return new TreeNode<>(p.hash, p.key, p.value, next);
//    }
//
//    /**
//     * Reset to initial default state.  Called by clone and readObject.
//     */
//    void reinitialize() {
//        table = null;
//        entrySet = null;
//        keySet = null;
//        values = null;
//        modCount = 0;
//        threshold = 0;
//        size = 0;
//    }
//
//    // Callbacks to allow LinkedHashMap post-actions
//    void afterNodeAccess(Node<K,V> p) { }
//    void afterNodeInsertion(boolean evict) { }
//    void afterNodeRemoval(Node<K,V> p) { }
//
//    // Called only from writeObject, to ensure compatible ordering.
//    void internalWriteEntries(java.io.ObjectOutputStream s) throws IOException {
//        Node<K,V>[] tab;
//        if (size > 0 && (tab = table) != null) {
//            for (int i = 0; i < tab.length; ++i) {
//                for (Node<K,V> e = tab[i]; e != null; e = e.next) {
//                    s.writeObject(e.key);
//                    s.writeObject(e.value);
//                }
//            }
//        }
//    }
//
//    /* ------------------------------------------------------------ */
//    // Tree bins
//
//    /**
//     * Entry for Tree bins. Extends LinkedHashMap.Entry (which in turn
//     * extends Node) so can be used as extension of either regular or
//     * linked node.
//     */
//    static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
//        TreeNode<K,V> parent;  // red-black tree links
//        TreeNode<K,V> left;
//        TreeNode<K,V> right;
//        TreeNode<K,V> prev;    // needed to unlink next upon deletion
//        boolean red;
//        TreeNode(int hash, K key, V val, Node<K,V> next) {
//            super(hash, key, val, next);
//        }
//
//        /**
//         * Returns root of tree containing this node.
//         */
//        final TreeNode<K,V> root() {
//            for (TreeNode<K,V> r = this, p;;) {
//                if ((p = r.parent) == null)
//                    return r;
//                r = p;
//            }
//        }
//
//        /**
//         * Ensures that the given root is the first node of its bin.
//         */
//        static <K,V> void moveRootToFront(Node<K,V>[] tab, TreeNode<K,V> root) {
//            int n;
//            if (root != null && tab != null && (n = tab.length) > 0) {
//                int index = (n - 1) & root.hash;
//                TreeNode<K,V> first = (TreeNode<K,V>)tab[index];
//                if (root != first) {
//                    Node<K,V> rn;
//                    tab[index] = root;
//                    TreeNode<K,V> rp = root.prev;
//                    if ((rn = root.next) != null)
//                        ((TreeNode<K,V>)rn).prev = rp;
//                    if (rp != null)
//                        rp.next = rn;
//                    if (first != null)
//                        first.prev = root;
//                    root.next = first;
//                    root.prev = null;
//                }
//                assert checkInvariants(root);
//            }
//        }
//
//        /**
//         * Finds the node starting at root p with the given hash and key.
//         * The kc argument caches comparableClassFor(key) upon first use
//         * comparing keys.
//         */
//        final TreeNode<K,V> find(int h, Object k, Class<?> kc) {
//            TreeNode<K,V> p = this;
//            do {
//                int ph, dir; K pk;
//                TreeNode<K,V> pl = p.left, pr = p.right, q;
//                if ((ph = p.hash) > h)
//                    p = pl;
//                else if (ph < h)
//                    p = pr;
//                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
//                    return p;
//                else if (pl == null)
//                    p = pr;
//                else if (pr == null)
//                    p = pl;
//                else if ((kc != null ||
//                        (kc = comparableClassFor(k)) != null) &&
//                        (dir = compareComparables(kc, k, pk)) != 0)
//                    p = (dir < 0) ? pl : pr;
//                else if ((q = pr.find(h, k, kc)) != null)
//                    return q;
//                else
//                    p = pl;
//            } while (p != null);
//            return null;
//        }
//
//        /**
//         * Calls find for root node.
//         */
//        final TreeNode<K,V> getTreeNode(int h, Object k) {
//            return ((parent != null) ? root() : this).find(h, k, null);
//        }
//
//        /**
//         * Tie-breaking utility for ordering insertions when equal
//         * hashCodes and non-comparable. We don't require a total
//         * order, just a consistent insertion rule to maintain
//         * equivalence across rebalancings. Tie-breaking further than
//         * necessary simplifies testing a bit.
//         */
//        static int tieBreakOrder(Object a, Object b) {
//            int d;
//            if (a == null || b == null ||
//                    (d = a.getClass().getName().
//                            compareTo(b.getClass().getName())) == 0)
//                d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
//                        -1 : 1);
//            return d;
//        }
//
//        /**
//         * Forms tree of the nodes linked from this node.
//         */
//        final void treeify(Node<K,V>[] tab) {
//            TreeNode<K,V> root = null;
//            for (TreeNode<K,V> x = this, next; x != null; x = next) {
//                next = (TreeNode<K,V>)x.next;
//                x.left = x.right = null;
//                if (root == null) {
//                    x.parent = null;
//                    x.red = false;
//                    root = x;
//                }
//                else {
//                    K k = x.key;
//                    int h = x.hash;
//                    Class<?> kc = null;
//                    for (TreeNode<K,V> p = root;;) {
//                        int dir, ph;
//                        K pk = p.key;
//                        if ((ph = p.hash) > h)
//                            dir = -1;
//                        else if (ph < h)
//                            dir = 1;
//                        else if ((kc == null &&
//                                (kc = comparableClassFor(k)) == null) ||
//                                (dir = compareComparables(kc, k, pk)) == 0)
//                            dir = tieBreakOrder(k, pk);
//
//                        TreeNode<K,V> xp = p;
//                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                            x.parent = xp;
//                            if (dir <= 0)
//                                xp.left = x;
//                            else
//                                xp.right = x;
//                            root = balanceInsertion(root, x);
//                            break;
//                        }
//                    }
//                }
//            }
//            moveRootToFront(tab, root);
//        }
//
//        /**
//         * Returns a list of non-TreeNodes replacing those linked from
//         * this node.
//         */
//        final Node<K,V> untreeify(HashMap<K,V> map) {
//            Node<K,V> hd = null, tl = null;
//            for (Node<K,V> q = this; q != null; q = q.next) {
//                Node<K,V> p = map.replacementNode(q, null);
//                if (tl == null)
//                    hd = p;
//                else
//                    tl.next = p;
//                tl = p;
//            }
//            return hd;
//        }
//
//        /**
//         * Tree version of putVal.
//         */
//        final TreeNode<K,V> putTreeVal(HashMap<K,V> map, Node<K,V>[] tab,
//                                       int h, K k, V v) {
//            Class<?> kc = null;
//            boolean searched = false;
//            TreeNode<K,V> root = (parent != null) ? root() : this;
//            for (TreeNode<K,V> p = root;;) {
//                int dir, ph; K pk;
//                if ((ph = p.hash) > h)
//                    dir = -1;
//                else if (ph < h)
//                    dir = 1;
//                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
//                    return p;
//                else if ((kc == null &&
//                        (kc = comparableClassFor(k)) == null) ||
//                        (dir = compareComparables(kc, k, pk)) == 0) {
//                    if (!searched) {
//                        TreeNode<K,V> q, ch;
//                        searched = true;
//                        if (((ch = p.left) != null &&
//                                (q = ch.find(h, k, kc)) != null) ||
//                                ((ch = p.right) != null &&
//                                        (q = ch.find(h, k, kc)) != null))
//                            return q;
//                    }
//                    dir = tieBreakOrder(k, pk);
//                }
//
//                TreeNode<K,V> xp = p;
//                if ((p = (dir <= 0) ? p.left : p.right) == null) {
//                    Node<K,V> xpn = xp.next;
//                    TreeNode<K,V> x = map.newTreeNode(h, k, v, xpn);
//                    if (dir <= 0)
//                        xp.left = x;
//                    else
//                        xp.right = x;
//                    xp.next = x;
//                    x.parent = x.prev = xp;
//                    if (xpn != null)
//                        ((TreeNode<K,V>)xpn).prev = x;
//                    moveRootToFront(tab, balanceInsertion(root, x));
//                    return null;
//                }
//            }
//        }
//
//        /**
//         * Removes the given node, that must be present before this call.
//         * This is messier than typical red-black deletion code because we
//         * cannot swap the contents of an interior node with a leaf
//         * successor that is pinned by "next" pointers that are accessible
//         * independently during traversal. So instead we swap the tree
//         * linkages. If the current tree appears to have too few nodes,
//         * the bin is converted back to a plain bin. (The test triggers
//         * somewhere between 2 and 6 nodes, depending on tree structure).
//         */
//        final void removeTreeNode(HashMap<K,V> map, Node<K,V>[] tab,
//                                  boolean movable) {
//            int n;
//            if (tab == null || (n = tab.length) == 0)
//                return;
//            int index = (n - 1) & hash;
//            TreeNode<K,V> first = (TreeNode<K,V>)tab[index], root = first, rl;
//            TreeNode<K,V> succ = (TreeNode<K,V>)next, pred = prev;
//            if (pred == null)
//                tab[index] = first = succ;
//            else
//                pred.next = succ;
//            if (succ != null)
//                succ.prev = pred;
//            if (first == null)
//                return;
//            if (root.parent != null)
//                root = root.root();
//            if (root == null
//                    || (movable
//                    && (root.right == null
//                    || (rl = root.left) == null
//                    || rl.left == null))) {
//                tab[index] = first.untreeify(map);  // too small
//                return;
//            }
//            TreeNode<K,V> p = this, pl = left, pr = right, replacement;
//            if (pl != null && pr != null) {
//                TreeNode<K,V> s = pr, sl;
//                while ((sl = s.left) != null) // find successor
//                    s = sl;
//                boolean c = s.red; s.red = p.red; p.red = c; // swap colors
//                TreeNode<K,V> sr = s.right;
//                TreeNode<K,V> pp = p.parent;
//                if (s == pr) { // p was s's direct parent
//                    p.parent = s;
//                    s.right = p;
//                }
//                else {
//                    TreeNode<K,V> sp = s.parent;
//                    if ((p.parent = sp) != null) {
//                        if (s == sp.left)
//                            sp.left = p;
//                        else
//                            sp.right = p;
//                    }
//                    if ((s.right = pr) != null)
//                        pr.parent = s;
//                }
//                p.left = null;
//                if ((p.right = sr) != null)
//                    sr.parent = p;
//                if ((s.left = pl) != null)
//                    pl.parent = s;
//                if ((s.parent = pp) == null)
//                    root = s;
//                else if (p == pp.left)
//                    pp.left = s;
//                else
//                    pp.right = s;
//                if (sr != null)
//                    replacement = sr;
//                else
//                    replacement = p;
//            }
//            else if (pl != null)
//                replacement = pl;
//            else if (pr != null)
//                replacement = pr;
//            else
//                replacement = p;
//            if (replacement != p) {
//                TreeNode<K,V> pp = replacement.parent = p.parent;
//                if (pp == null)
//                    root = replacement;
//                else if (p == pp.left)
//                    pp.left = replacement;
//                else
//                    pp.right = replacement;
//                p.left = p.right = p.parent = null;
//            }
//
//            TreeNode<K,V> r = p.red ? root : balanceDeletion(root, replacement);
//
//            if (replacement == p) {  // detach
//                TreeNode<K,V> pp = p.parent;
//                p.parent = null;
//                if (pp != null) {
//                    if (p == pp.left)
//                        pp.left = null;
//                    else if (p == pp.right)
//                        pp.right = null;
//                }
//            }
//            if (movable)
//                moveRootToFront(tab, r);
//        }
//
//        /**
//         * Splits nodes in a tree bin into lower and upper tree bins,
//         * or untreeifies if now too small. Called only from resize;
//         * see above discussion about split bits and indices.
//         *
//         * @param map the map
//         * @param tab the table for recording bin heads
//         * @param index the index of the table being split
//         * @param bit the bit of hash to split on
//         */
//        final void split(HashMap<K,V> map, Node<K,V>[] tab, int index, int bit) {
//            TreeNode<K,V> b = this;
//            // Relink into lo and hi lists, preserving order
//            TreeNode<K,V> loHead = null, loTail = null;
//            TreeNode<K,V> hiHead = null, hiTail = null;
//            int lc = 0, hc = 0;
//            for (TreeNode<K,V> e = b, next; e != null; e = next) {
//                next = (TreeNode<K,V>)e.next;
//                e.next = null;
//                if ((e.hash & bit) == 0) {
//                    if ((e.prev = loTail) == null)
//                        loHead = e;
//                    else
//                        loTail.next = e;
//                    loTail = e;
//                    ++lc;
//                }
//                else {
//                    if ((e.prev = hiTail) == null)
//                        hiHead = e;
//                    else
//                        hiTail.next = e;
//                    hiTail = e;
//                    ++hc;
//                }
//            }
//
//            if (loHead != null) {
//                if (lc <= UNTREEIFY_THRESHOLD)
//                    tab[index] = loHead.untreeify(map);
//                else {
//                    tab[index] = loHead;
//                    if (hiHead != null) // (else is already treeified)
//                        loHead.treeify(tab);
//                }
//            }
//            if (hiHead != null) {
//                if (hc <= UNTREEIFY_THRESHOLD)
//                    tab[index + bit] = hiHead.untreeify(map);
//                else {
//                    tab[index + bit] = hiHead;
//                    if (loHead != null)
//                        hiHead.treeify(tab);
//                }
//            }
//        }
//
//        /* ------------------------------------------------------------ */
//        // Red-black tree methods, all adapted from CLR
//
//        static <K,V> TreeNode<K,V> rotateLeft(TreeNode<K,V> root,
//                                              TreeNode<K,V> p) {
//            TreeNode<K,V> r, pp, rl;
//            if (p != null && (r = p.right) != null) {
//                if ((rl = p.right = r.left) != null)
//                    rl.parent = p;
//                if ((pp = r.parent = p.parent) == null)
//                    (root = r).red = false;
//                else if (pp.left == p)
//                    pp.left = r;
//                else
//                    pp.right = r;
//                r.left = p;
//                p.parent = r;
//            }
//            return root;
//        }
//
//        static <K,V> TreeNode<K,V> rotateRight(TreeNode<K,V> root,
//                                               TreeNode<K,V> p) {
//            TreeNode<K,V> l, pp, lr;
//            if (p != null && (l = p.left) != null) {
//                if ((lr = p.left = l.right) != null)
//                    lr.parent = p;
//                if ((pp = l.parent = p.parent) == null)
//                    (root = l).red = false;
//                else if (pp.right == p)
//                    pp.right = l;
//                else
//                    pp.left = l;
//                l.right = p;
//                p.parent = l;
//            }
//            return root;
//        }
//
//        static <K,V> TreeNode<K,V> balanceInsertion(TreeNode<K,V> root,
//                                                    TreeNode<K,V> x) {
//            x.red = true;
//            for (TreeNode<K,V> xp, xpp, xppl, xppr;;) {
//                if ((xp = x.parent) == null) {
//                    x.red = false;
//                    return x;
//                }
//                else if (!xp.red || (xpp = xp.parent) == null)
//                    return root;
//                if (xp == (xppl = xpp.left)) {
//                    if ((xppr = xpp.right) != null && xppr.red) {
//                        xppr.red = false;
//                        xp.red = false;
//                        xpp.red = true;
//                        x = xpp;
//                    }
//                    else {
//                        if (x == xp.right) {
//                            root = rotateLeft(root, x = xp);
//                            xpp = (xp = x.parent) == null ? null : xp.parent;
//                        }
//                        if (xp != null) {
//                            xp.red = false;
//                            if (xpp != null) {
//                                xpp.red = true;
//                                root = rotateRight(root, xpp);
//                            }
//                        }
//                    }
//                }
//                else {
//                    if (xppl != null && xppl.red) {
//                        xppl.red = false;
//                        xp.red = false;
//                        xpp.red = true;
//                        x = xpp;
//                    }
//                    else {
//                        if (x == xp.left) {
//                            root = rotateRight(root, x = xp);
//                            xpp = (xp = x.parent) == null ? null : xp.parent;
//                        }
//                        if (xp != null) {
//                            xp.red = false;
//                            if (xpp != null) {
//                                xpp.red = true;
//                                root = rotateLeft(root, xpp);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        static <K,V> TreeNode<K,V> balanceDeletion(TreeNode<K,V> root,
//                                                   TreeNode<K,V> x) {
//            for (TreeNode<K,V> xp, xpl, xpr;;) {
//                if (x == null || x == root)
//                    return root;
//                else if ((xp = x.parent) == null) {
//                    x.red = false;
//                    return x;
//                }
//                else if (x.red) {
//                    x.red = false;
//                    return root;
//                }
//                else if ((xpl = xp.left) == x) {
//                    if ((xpr = xp.right) != null && xpr.red) {
//                        xpr.red = false;
//                        xp.red = true;
//                        root = rotateLeft(root, xp);
//                        xpr = (xp = x.parent) == null ? null : xp.right;
//                    }
//                    if (xpr == null)
//                        x = xp;
//                    else {
//                        TreeNode<K,V> sl = xpr.left, sr = xpr.right;
//                        if ((sr == null || !sr.red) &&
//                                (sl == null || !sl.red)) {
//                            xpr.red = true;
//                            x = xp;
//                        }
//                        else {
//                            if (sr == null || !sr.red) {
//                                if (sl != null)
//                                    sl.red = false;
//                                xpr.red = true;
//                                root = rotateRight(root, xpr);
//                                xpr = (xp = x.parent) == null ?
//                                        null : xp.right;
//                            }
//                            if (xpr != null) {
//                                xpr.red = (xp == null) ? false : xp.red;
//                                if ((sr = xpr.right) != null)
//                                    sr.red = false;
//                            }
//                            if (xp != null) {
//                                xp.red = false;
//                                root = rotateLeft(root, xp);
//                            }
//                            x = root;
//                        }
//                    }
//                }
//                else { // symmetric
//                    if (xpl != null && xpl.red) {
//                        xpl.red = false;
//                        xp.red = true;
//                        root = rotateRight(root, xp);
//                        xpl = (xp = x.parent) == null ? null : xp.left;
//                    }
//                    if (xpl == null)
//                        x = xp;
//                    else {
//                        TreeNode<K,V> sl = xpl.left, sr = xpl.right;
//                        if ((sl == null || !sl.red) &&
//                                (sr == null || !sr.red)) {
//                            xpl.red = true;
//                            x = xp;
//                        }
//                        else {
//                            if (sl == null || !sl.red) {
//                                if (sr != null)
//                                    sr.red = false;
//                                xpl.red = true;
//                                root = rotateLeft(root, xpl);
//                                xpl = (xp = x.parent) == null ?
//                                        null : xp.left;
//                            }
//                            if (xpl != null) {
//                                xpl.red = (xp == null) ? false : xp.red;
//                                if ((sl = xpl.left) != null)
//                                    sl.red = false;
//                            }
//                            if (xp != null) {
//                                xp.red = false;
//                                root = rotateRight(root, xp);
//                            }
//                            x = root;
//                        }
//                    }
//                }
//            }
//        }
//
//        /**
//         * Recursive invariant check
//         */
//        static <K,V> boolean checkInvariants(TreeNode<K,V> t) {
//            TreeNode<K,V> tp = t.parent, tl = t.left, tr = t.right,
//                    tb = t.prev, tn = (TreeNode<K,V>)t.next;
//            if (tb != null && tb.next != t)
//                return false;
//            if (tn != null && tn.prev != t)
//                return false;
//            if (tp != null && t != tp.left && t != tp.right)
//                return false;
//            if (tl != null && (tl.parent != t || tl.hash > t.hash))
//                return false;
//            if (tr != null && (tr.parent != t || tr.hash < t.hash))
//                return false;
//            if (t.red && tl != null && tl.red && tr != null && tr.red)
//                return false;
//            if (tl != null && !checkInvariants(tl))
//                return false;
//            if (tr != null && !checkInvariants(tr))
//                return false;
//            return true;
//        }
//    }
//
//}
