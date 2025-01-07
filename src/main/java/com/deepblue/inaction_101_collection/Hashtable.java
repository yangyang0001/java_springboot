//package com.deepblue.inaction_100_source_algorithm;
//
///*
// * Copyright (c) 1994, 2017, Oracle and/or its affiliates. All rights reserved.
// * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//
//import java.io.*;
//import java.util.*;
//import java.util.function.BiConsumer;
//import java.util.function.Function;
//import java.util.function.BiFunction;
//import sun.misc.SharedSecrets;
//
///**
// * 这个类实现了一个哈希表，它将键映射到值。
// * This class implements a hash table, which maps keys to values.
// *
// * 任何非<code>null</code> 对象都可以用作键或值。 <p>
// * Any non-<code>null</code> object can be used as a key or as a value. <p>
// *
// * 要成功地从哈希表存储和检索对象，用作键的对象必须实现 <code>hashCode</code> 方法和 <code>equals</code> 方法。
// * To successfully store and retrieve objects from a hashtable, the objects used as keys must implement the <code>hashCode</code> method and the <code>equals</code> method. <p>
// *
// * Hashtable 的实例有两个影响其性能的参数：初始容量和负载因子。TODO 核心参数: 初始容量 和 负载因子!
// * An instance of <code>Hashtable</code> has two parameters that affect its performance: <i>initial capacity</i> and <i>load factor</i>.
// *
// * <i>容量</i>是哈希表中<i>桶</i>的个数，<i>初始容量</i>就是哈希表创建时的容量。TODO 容量 是指 桶的个数; 初始容量 是指 初始桶的个数!
// * The <i>capacity</i> is the number of <i>buckets</i> in the hash table, and the <i>initial capacity</i> is simply the capacity at the time the hash table is created.
// *
// * 注意哈希表是<i>open</i>的：在“哈希冲突”的情况下，单个桶存储多个条目，必须按顺序搜索。 TODO hash 碰撞时, 一旦一个桶中有多个元素, 则元素必须按顺序搜索!
// * Note that the hash table is <i>open</i>: in the case of a "hash collision", a single bucket stores multiple entries, which must be searched sequentially.
// *
// * <i>负载因子</i> 是哈希表在其容量自动增加之前允许达到的程度的度量。
// * The <i>load factor</i> is a measure of how full the hash table is allowed to get before its capacity is automatically increased.
// *
// * 初始容量和负载因子参数只是实现的提示。
// * The initial capacity and load factor parameters are merely hints to the implementation.
// *
// * 关于何时以及是否调用 rehash 方法的确切细节取决于实现。<p>
// * The exact details as to when and whether the rehash method is invoked are implementation-dependent.<p>
// *
// * 通常，默认负载因子 (.75) 在时间和空间成本之间提供了良好的折衷。
// * Generally, the default load factor (.75) offers a good tradeoff between time and space costs.
// *
// * 较高的值会减少空间开销，但会增加查找条目的时间成本（这反映在大多数 <tt>Hashtable</tt> 操作中，包括 <tt>get</tt> 和 <tt>put</tt> )。<p>
// * Higher values decrease the space overhead but increase the time cost to look up an entry (which is reflected in most <tt>Hashtable</tt> operations, including <tt>get</tt> and <tt>put</tt>).<p>
// *
// * 初始容量控制了浪费空间和需要耗时的 <code>rehash</code> 操作之间的权衡。
// * The initial capacity controls a tradeoff between wasted space and the need for <code>rehash</code> operations, which are time-consuming.
// *
// * 如果初始容量大于 <tt>Hashtable</tt> 将包含的最大条目数除以其负载因子，则不会发生任何 <code>rehash</code> 操作。 TODO 原则上: capacity * factor > items 是不会发生任 rehash 操作的!
// * No <code>rehash</code> operations will <i>ever</i> occur if the initial capacity is greater than the maximum number of entries the <tt>Hashtable</tt> will contain divided by its load factor.
// *
// * 但是，将初始容量设置得太高会浪费空间。<p>
// * However, setting the initial capacity too high can waste space.<p>
// *
// * 如果要在 <code>Hashtable</code> 中创建许多条目，则使用足够大的容量创建它可能比让它根据需要执行自动重新散列以增加表来更有效地插入条目。 <p>
// * If many entries are to be made into a <code>Hashtable</code>, creating it with a sufficiently large capacity may allow the entries to be inserted more efficiently than letting it perform automatic rehashing as needed to grow the table. <p>
// *
// * 此示例创建一个数字哈希表。它使用数字的名称作为键：
// * This example creates a hashtable of numbers. It uses the names of the numbers as keys:
// * <pre>   {@code
// *   Hashtable<String, Integer> numbers
// *     = new Hashtable<String, Integer>();
// *   numbers.put("one", 1);
// *   numbers.put("two", 2);
// *   numbers.put("three", 3);}</pre>
// *
// * <p>要检索数字，请使用以下代码：
// * <p>To retrieve a number, use the following code:
// * <pre>
// * {@code
// *   Integer n = numbers.get("two");
// *   if (n != null) {
// *     System.out.println("two = " + n);
// *   }
// * }</pre>
// *
// * <p>所有此类的“集合视图方法”返回的集合的 <tt>iterator</tt> 方法返回的迭代器是 <em>fail-fast</em>：如果 Hashtable 在结构上修改为在迭代器创建后的任何时候，除了通过迭代器自己的 <tt>remove</tt> 方法外，迭代器将抛出 {@link ConcurrentModificationException}。
// * <p>The iterators returned by the <tt>iterator</tt> method of the collections returned by all of this class's "collection view methods" are <em>fail-fast</em>: if the Hashtable is structurally modified at any time after the iterator is created, in any way except through the iterator's own <tt>remove</tt> method, the iterator will throw a {@link ConcurrentModificationException}.
// *
// * 因此，面对并发修改，迭代器快速而干净地失败，而不是在未来不确定的时间冒任意的、非确定性的行为。
// * Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than risking arbitrary, non-deterministic behavior at an undetermined time in the future.
// *
// * Hashtable 的键和元素方法返回的枚举<em>不是</em>快速失败的。
// * The Enumerations returned by Hashtable's keys and elements methods are <em>not</em> fail-fast.
// *
// * <p>请注意，无法保证迭代器的快速失败行为，因为一般来说，在存在不同步的并发修改的情况下无法做出任何硬保证。
// * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed as it is, generally speaking, impossible to make any hard guarantees in the presence of unsynchronized concurrent modification.
// *
// * 快速失败的迭代器会尽最大努力抛出 <tt>ConcurrentModificationException</tt>。
// * Fail-fast iterators throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
// *
// * 因此，编写一个依赖此异常来确保其正确性的程序是错误的：<i>迭代器的快速失败行为应仅用于检测错误。</i>
// * Therefore, it would be wrong to write a program that depended on this exception for its correctness: <i>the fail-fast behavior of iterators should be used only to detect bugs.</i>
// *
// * <p>从 Java 2 平台 v1.2 开始，该类被改进为实现 {@link Map} 接口，使其成为 <a href="{@docRoot}/../technotes/guides/ 的成员集合/index.html"> Java 集合框架</a>。
// * <p>As of the Java 2 platform v1.2, this class was retrofitted to implement the {@link Map} interface, making it a member of the <a href="{@docRoot}/../technotes/guides/collections/index.html"> Java Collections Framework</a>.
// *
// * 与新的集合实现不同，{@code Hashtable} 是同步的。
// * Unlike the new collection implementations, {@code Hashtable} is synchronized.
// *
// * 如果不需要线程安全实现，建议使用 {@link HashMap} 代替 {@code Hashtable}。
// * If a thread-safe implementation is not needed, it is recommended to use {@link HashMap} in place of {@code Hashtable}.
// *
// * 如果需要线程安全的高并发实现，则建议使用 {@link java.util.concurrent.ConcurrentHashMap} 代替 {@code Hashtable}。
// * If a thread-safe highly-concurrent implementation is desired, then it is recommended to use {@link java.util.concurrent.ConcurrentHashMap} in place of {@code Hashtable}.
// *
// * @author  Arthur van Hoff
// * @author  Josh Bloch
// * @author  Neal Gafter
// * @see     Object#equals(java.lang.Object)
// * @see     Object#hashCode()
// * @see     java.util.Hashtable#rehash()
// * @see     Collection
// * @see     Map
// * @see     HashMap
// * @see     TreeMap
// * @since JDK1.0
// */
//public class Hashtable<K,V>
//        extends Dictionary<K,V>
//        implements Map<K,V>, Cloneable, java.io.Serializable {
//
//    /**
//     * The hash table data.
//     */
//    private transient java.util.Hashtable.Entry<?,?>[] table;
//
//    /**
//     * The total number of entries in the hash table.
//     */
//    private transient int count;
//
//    /**
//     * The table is rehashed when its size exceeds this threshold.  (The
//     * value of this field is (int)(capacity * loadFactor).)
//     *
//     * @serial
//     */
//    private int threshold;
//
//    /**
//     * The load factor for the hashtable.
//     *
//     * @serial
//     */
//    private float loadFactor;
//
//    /**
//     * The number of times this Hashtable has been structurally modified
//     * Structural modifications are those that change the number of entries in
//     * the Hashtable or otherwise modify its internal structure (e.g.,
//     * rehash).  This field is used to make iterators on Collection-views of
//     * the Hashtable fail-fast.  (See ConcurrentModificationException).
//     */
//    private transient int modCount = 0;
//
//    /** use serialVersionUID from JDK 1.0.2 for interoperability */
//    private static final long serialVersionUID = 1421746759512286392L;
//
//    /**
//     * Constructs a new, empty hashtable with the specified initial
//     * capacity and the specified load factor.
//     *
//     * @param      initialCapacity   the initial capacity of the hashtable.
//     * @param      loadFactor        the load factor of the hashtable.
//     * @exception  IllegalArgumentException  if the initial capacity is less
//     *             than zero, or if the load factor is nonpositive.
//     */
//    public Hashtable(int initialCapacity, float loadFactor) {
//        if (initialCapacity < 0)
//            throw new IllegalArgumentException("Illegal Capacity: "+
//                    initialCapacity);
//        if (loadFactor <= 0 || Float.isNaN(loadFactor))
//            throw new IllegalArgumentException("Illegal Load: "+loadFactor);
//
//        if (initialCapacity==0)
//            initialCapacity = 1;
//        this.loadFactor = loadFactor;
//        table = new java.util.Hashtable.Entry<?,?>[initialCapacity];
//        threshold = (int)Math.min(initialCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
//    }
//
//    /**
//     * Constructs a new, empty hashtable with the specified initial capacity
//     * and default load factor (0.75).
//     *
//     * @param     initialCapacity   the initial capacity of the hashtable.
//     * @exception IllegalArgumentException if the initial capacity is less
//     *              than zero.
//     */
//    public Hashtable(int initialCapacity) {
//        this(initialCapacity, 0.75f);
//    }
//
//    /**
//     * Constructs a new, empty hashtable with a default initial capacity (11)
//     * and load factor (0.75).
//     */
//    public Hashtable() {
//        this(11, 0.75f);
//    }
//
//    /**
//     * Constructs a new hashtable with the same mappings as the given
//     * Map.  The hashtable is created with an initial capacity sufficient to
//     * hold the mappings in the given Map and a default load factor (0.75).
//     *
//     * @param t the map whose mappings are to be placed in this map.
//     * @throws NullPointerException if the specified map is null.
//     * @since   1.2
//     */
//    public Hashtable(Map<? extends K, ? extends V> t) {
//        this(Math.max(2*t.size(), 11), 0.75f);
//        putAll(t);
//    }
//
//    /**
//     * Returns the number of keys in this hashtable.
//     *
//     * @return  the number of keys in this hashtable.
//     */
//    public synchronized int size() {
//        return count;
//    }
//
//    /**
//     * Tests if this hashtable maps no keys to values.
//     *
//     * @return  <code>true</code> if this hashtable maps no keys to values;
//     *          <code>false</code> otherwise.
//     */
//    public synchronized boolean isEmpty() {
//        return count == 0;
//    }
//
//    /**
//     * Returns an enumeration of the keys in this hashtable.
//     *
//     * @return  an enumeration of the keys in this hashtable.
//     * @see     Enumeration
//     * @see     #elements()
//     * @see     #keySet()
//     * @see     Map
//     */
//    public synchronized Enumeration<K> keys() {
//        return this.<K>getEnumeration(KEYS);
//    }
//
//    /**
//     * Returns an enumeration of the values in this hashtable.
//     * Use the Enumeration methods on the returned object to fetch the elements
//     * sequentially.
//     *
//     * @return  an enumeration of the values in this hashtable.
//     * @see     java.util.Enumeration
//     * @see     #keys()
//     * @see     #values()
//     * @see     Map
//     */
//    public synchronized Enumeration<V> elements() {
//        return this.<V>getEnumeration(VALUES);
//    }
//
//    /**
//     * Tests if some key maps into the specified value in this hashtable.
//     * This operation is more expensive than the {@link #containsKey
//     * containsKey} method.
//     *
//     * <p>Note that this method is identical in functionality to
//     * {@link #containsValue containsValue}, (which is part of the
//     * {@link Map} interface in the collections framework).
//     *
//     * @param      value   a value to search for
//     * @return     <code>true</code> if and only if some key maps to the
//     *             <code>value</code> argument in this hashtable as
//     *             determined by the <tt>equals</tt> method;
//     *             <code>false</code> otherwise.
//     * @exception  NullPointerException  if the value is <code>null</code>
//     */
//    public synchronized boolean contains(Object value) {
//        if (value == null) {
//            throw new NullPointerException();
//        }
//
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        for (int i = tab.length ; i-- > 0 ;) {
//            for (java.util.Hashtable.Entry<?,?> e = tab[i]; e != null ; e = e.next) {
//                if (e.value.equals(value)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Returns true if this hashtable maps one or more keys to this value.
//     *
//     * <p>Note that this method is identical in functionality to {@link
//     * #contains contains} (which predates the {@link Map} interface).
//     *
//     * @param value value whose presence in this hashtable is to be tested
//     * @return <tt>true</tt> if this map maps one or more keys to the
//     *         specified value
//     * @throws NullPointerException  if the value is <code>null</code>
//     * @since 1.2
//     */
//    public boolean containsValue(Object value) {
//        return contains(value);
//    }
//
//    /**
//     * Tests if the specified object is a key in this hashtable.
//     *
//     * @param   key   possible key
//     * @return  <code>true</code> if and only if the specified object
//     *          is a key in this hashtable, as determined by the
//     *          <tt>equals</tt> method; <code>false</code> otherwise.
//     * @throws  NullPointerException  if the key is <code>null</code>
//     * @see     #contains(Object)
//     */
//    public synchronized boolean containsKey(Object key) {
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        for (java.util.Hashtable.Entry<?,?> e = tab[index]; e != null ; e = e.next) {
//            if ((e.hash == hash) && e.key.equals(key)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Returns the value to which the specified key is mapped,
//     * or {@code null} if this map contains no mapping for the key.
//     *
//     * <p>More formally, if this map contains a mapping from a key
//     * {@code k} to a value {@code v} such that {@code (key.equals(k))},
//     * then this method returns {@code v}; otherwise it returns
//     * {@code null}.  (There can be at most one such mapping.)
//     *
//     * @param key the key whose associated value is to be returned
//     * @return the value to which the specified key is mapped, or
//     *         {@code null} if this map contains no mapping for the key
//     * @throws NullPointerException if the specified key is null
//     * @see     #put(Object, Object)
//     */
//    @SuppressWarnings("unchecked")
//    public synchronized V get(Object key) {
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        for (java.util.Hashtable.Entry<?,?> e = tab[index]; e != null ; e = e.next) {
//            if ((e.hash == hash) && e.key.equals(key)) {
//                return (V)e.value;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * The maximum size of array to allocate.
//     * Some VMs reserve some header words in an array.
//     * Attempts to allocate larger arrays may result in
//     * OutOfMemoryError: Requested array size exceeds VM limit
//     */
//    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
//
//    /**
//     * Increases the capacity of and internally reorganizes this
//     * hashtable, in order to accommodate and access its entries more
//     * efficiently.  This method is called automatically when the
//     * number of keys in the hashtable exceeds this hashtable's capacity
//     * and load factor.
//     */
//    @SuppressWarnings("unchecked")
//    protected void rehash() {
//        int oldCapacity = table.length;
//        java.util.Hashtable.Entry<?,?>[] oldMap = table;
//
//        // overflow-conscious code
//        int newCapacity = (oldCapacity << 1) + 1;
//        if (newCapacity - MAX_ARRAY_SIZE > 0) {
//            if (oldCapacity == MAX_ARRAY_SIZE)
//                // Keep running with MAX_ARRAY_SIZE buckets
//                return;
//            newCapacity = MAX_ARRAY_SIZE;
//        }
//        java.util.Hashtable.Entry<?,?>[] newMap = new java.util.Hashtable.Entry<?,?>[newCapacity];
//
//        modCount++;
//        threshold = (int)Math.min(newCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
//        table = newMap;
//
//        for (int i = oldCapacity ; i-- > 0 ;) {
//            for (java.util.Hashtable.Entry<K,V> old = (java.util.Hashtable.Entry<K,V>)oldMap[i]; old != null ; ) {
//                java.util.Hashtable.Entry<K,V> e = old;
//                old = old.next;
//
//                int index = (e.hash & 0x7FFFFFFF) % newCapacity;
//                e.next = (java.util.Hashtable.Entry<K,V>)newMap[index];
//                newMap[index] = e;
//            }
//        }
//    }
//
//    private void addEntry(int hash, K key, V value, int index) {
//        modCount++;
//
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        if (count >= threshold) {
//            // Rehash the table if the threshold is exceeded
//            rehash();
//
//            tab = table;
//            hash = key.hashCode();
//            index = (hash & 0x7FFFFFFF) % tab.length;
//        }
//
//        // Creates the new entry.
//        @SuppressWarnings("unchecked")
//        java.util.Hashtable.Entry<K,V> e = (java.util.Hashtable.Entry<K,V>) tab[index];
//        tab[index] = new java.util.Hashtable.Entry<>(hash, key, value, e);
//        count++;
//    }
//
//    /**
//     * Maps the specified <code>key</code> to the specified
//     * <code>value</code> in this hashtable. Neither the key nor the
//     * value can be <code>null</code>. <p>
//     *
//     * The value can be retrieved by calling the <code>get</code> method
//     * with a key that is equal to the original key.
//     *
//     * @param      key     the hashtable key
//     * @param      value   the value
//     * @return     the previous value of the specified key in this hashtable,
//     *             or <code>null</code> if it did not have one
//     * @exception  NullPointerException  if the key or value is
//     *               <code>null</code>
//     * @see     Object#equals(Object)
//     * @see     #get(Object)
//     */
//    public synchronized V put(K key, V value) {
//        // Make sure the value is not null
//        if (value == null) {
//            throw new NullPointerException();
//        }
//
//        // Makes sure the key is not already in the hashtable.
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        @SuppressWarnings("unchecked")
//        java.util.Hashtable.Entry<K,V> entry = (java.util.Hashtable.Entry<K,V>)tab[index];
//        for(; entry != null ; entry = entry.next) {
//            if ((entry.hash == hash) && entry.key.equals(key)) {
//                V old = entry.value;
//                entry.value = value;
//                return old;
//            }
//        }
//
//        addEntry(hash, key, value, index);
//        return null;
//    }
//
//    /**
//     * Removes the key (and its corresponding value) from this
//     * hashtable. This method does nothing if the key is not in the hashtable.
//     *
//     * @param   key   the key that needs to be removed
//     * @return  the value to which the key had been mapped in this hashtable,
//     *          or <code>null</code> if the key did not have a mapping
//     * @throws  NullPointerException  if the key is <code>null</code>
//     */
//    public synchronized V remove(Object key) {
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        @SuppressWarnings("unchecked")
//        java.util.Hashtable.Entry<K,V> e = (java.util.Hashtable.Entry<K,V>)tab[index];
//        for(java.util.Hashtable.Entry<K,V> prev = null; e != null ; prev = e, e = e.next) {
//            if ((e.hash == hash) && e.key.equals(key)) {
//                modCount++;
//                if (prev != null) {
//                    prev.next = e.next;
//                } else {
//                    tab[index] = e.next;
//                }
//                count--;
//                V oldValue = e.value;
//                e.value = null;
//                return oldValue;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Copies all of the mappings from the specified map to this hashtable.
//     * These mappings will replace any mappings that this hashtable had for any
//     * of the keys currently in the specified map.
//     *
//     * @param t mappings to be stored in this map
//     * @throws NullPointerException if the specified map is null
//     * @since 1.2
//     */
//    public synchronized void putAll(Map<? extends K, ? extends V> t) {
//        for (Map.Entry<? extends K, ? extends V> e : t.entrySet())
//            put(e.getKey(), e.getValue());
//    }
//
//    /**
//     * Clears this hashtable so that it contains no keys.
//     */
//    public synchronized void clear() {
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        modCount++;
//        for (int index = tab.length; --index >= 0; )
//            tab[index] = null;
//        count = 0;
//    }
//
//    /**
//     * Creates a shallow copy of this hashtable. All the structure of the
//     * hashtable itself is copied, but the keys and values are not cloned.
//     * This is a relatively expensive operation.
//     *
//     * @return  a clone of the hashtable
//     */
//    public synchronized Object clone() {
//        try {
//            java.util.Hashtable<?,?> t = (java.util.Hashtable<?,?>)super.clone();
//            t.table = new java.util.Hashtable.Entry<?,?>[table.length];
//            for (int i = table.length ; i-- > 0 ; ) {
//                t.table[i] = (table[i] != null)
//                        ? (java.util.Hashtable.Entry<?,?>) table[i].clone() : null;
//            }
//            t.keySet = null;
//            t.entrySet = null;
//            t.values = null;
//            t.modCount = 0;
//            return t;
//        } catch (CloneNotSupportedException e) {
//            // this shouldn't happen, since we are Cloneable
//            throw new InternalError(e);
//        }
//    }
//
//    /**
//     * Returns a string representation of this <tt>Hashtable</tt> object
//     * in the form of a set of entries, enclosed in braces and separated
//     * by the ASCII characters "<tt>,&nbsp;</tt>" (comma and space). Each
//     * entry is rendered as the key, an equals sign <tt>=</tt>, and the
//     * associated element, where the <tt>toString</tt> method is used to
//     * convert the key and element to strings.
//     *
//     * @return  a string representation of this hashtable
//     */
//    public synchronized String toString() {
//        int max = size() - 1;
//        if (max == -1)
//            return "{}";
//
//        StringBuilder sb = new StringBuilder();
//        Iterator<Map.Entry<K,V>> it = entrySet().iterator();
//
//        sb.append('{');
//        for (int i = 0; ; i++) {
//            Map.Entry<K,V> e = it.next();
//            K key = e.getKey();
//            V value = e.getValue();
//            sb.append(key   == this ? "(this Map)" : key.toString());
//            sb.append('=');
//            sb.append(value == this ? "(this Map)" : value.toString());
//
//            if (i == max)
//                return sb.append('}').toString();
//            sb.append(", ");
//        }
//    }
//
//
//    private <T> Enumeration<T> getEnumeration(int type) {
//        if (count == 0) {
//            return Collections.emptyEnumeration();
//        } else {
//            return new java.util.Hashtable.Enumerator<>(type, false);
//        }
//    }
//
//    private <T> Iterator<T> getIterator(int type) {
//        if (count == 0) {
//            return Collections.emptyIterator();
//        } else {
//            return new java.util.Hashtable.Enumerator<>(type, true);
//        }
//    }
//
//    // Views
//
//    /**
//     * Each of these fields are initialized to contain an instance of the
//     * appropriate view the first time this view is requested.  The views are
//     * stateless, so there's no reason to create more than one of each.
//     */
//    private transient volatile Set<K> keySet;
//    private transient volatile Set<Map.Entry<K,V>> entrySet;
//    private transient volatile Collection<V> values;
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
//     * @since 1.2
//     */
//    public Set<K> keySet() {
//        if (keySet == null)
//            keySet = Collections.synchronizedSet(new java.util.Hashtable.KeySet(), this);
//        return keySet;
//    }
//
//    private class KeySet extends AbstractSet<K> {
//        public Iterator<K> iterator() {
//            return getIterator(KEYS);
//        }
//        public int size() {
//            return count;
//        }
//        public boolean contains(Object o) {
//            return containsKey(o);
//        }
//        public boolean remove(Object o) {
//            return java.util.Hashtable.this.remove(o) != null;
//        }
//        public void clear() {
//            java.util.Hashtable.this.clear();
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
//     * @since 1.2
//     */
//    public Set<Map.Entry<K,V>> entrySet() {
//        if (entrySet==null)
//            entrySet = Collections.synchronizedSet(new java.util.Hashtable.EntrySet(), this);
//        return entrySet;
//    }
//
//    private class EntrySet extends AbstractSet<Map.Entry<K,V>> {
//        public Iterator<Map.Entry<K,V>> iterator() {
//            return getIterator(ENTRIES);
//        }
//
//        public boolean add(Map.Entry<K,V> o) {
//            return super.add(o);
//        }
//
//        public boolean contains(Object o) {
//            if (!(o instanceof Map.Entry))
//                return false;
//            Map.Entry<?,?> entry = (Map.Entry<?,?>)o;
//            Object key = entry.getKey();
//            java.util.Hashtable.Entry<?,?>[] tab = table;
//            int hash = key.hashCode();
//            int index = (hash & 0x7FFFFFFF) % tab.length;
//
//            for (java.util.Hashtable.Entry<?,?> e = tab[index]; e != null; e = e.next)
//                if (e.hash==hash && e.equals(entry))
//                    return true;
//            return false;
//        }
//
//        public boolean remove(Object o) {
//            if (!(o instanceof Map.Entry))
//                return false;
//            Map.Entry<?,?> entry = (Map.Entry<?,?>) o;
//            Object key = entry.getKey();
//            java.util.Hashtable.Entry<?,?>[] tab = table;
//            int hash = key.hashCode();
//            int index = (hash & 0x7FFFFFFF) % tab.length;
//
//            @SuppressWarnings("unchecked")
//            java.util.Hashtable.Entry<K,V> e = (java.util.Hashtable.Entry<K,V>)tab[index];
//            for(java.util.Hashtable.Entry<K,V> prev = null; e != null; prev = e, e = e.next) {
//                if (e.hash==hash && e.equals(entry)) {
//                    modCount++;
//                    if (prev != null)
//                        prev.next = e.next;
//                    else
//                        tab[index] = e.next;
//
//                    count--;
//                    e.value = null;
//                    return true;
//                }
//            }
//            return false;
//        }
//
//        public int size() {
//            return count;
//        }
//
//        public void clear() {
//            java.util.Hashtable.this.clear();
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
//     * @since 1.2
//     */
//    public Collection<V> values() {
//        if (values==null)
//            values = Collections.synchronizedCollection(new java.util.Hashtable.ValueCollection(),
//                    this);
//        return values;
//    }
//
//    private class ValueCollection extends AbstractCollection<V> {
//        public Iterator<V> iterator() {
//            return getIterator(VALUES);
//        }
//        public int size() {
//            return count;
//        }
//        public boolean contains(Object o) {
//            return containsValue(o);
//        }
//        public void clear() {
//            java.util.Hashtable.this.clear();
//        }
//    }
//
//    // Comparison and hashing
//
//    /**
//     * Compares the specified Object with this Map for equality,
//     * as per the definition in the Map interface.
//     *
//     * @param  o object to be compared for equality with this hashtable
//     * @return true if the specified Object is equal to this Map
//     * @see Map#equals(Object)
//     * @since 1.2
//     */
//    public synchronized boolean equals(Object o) {
//        if (o == this)
//            return true;
//
//        if (!(o instanceof Map))
//            return false;
//        Map<?,?> t = (Map<?,?>) o;
//        if (t.size() != size())
//            return false;
//
//        try {
//            Iterator<Map.Entry<K,V>> i = entrySet().iterator();
//            while (i.hasNext()) {
//                Map.Entry<K,V> e = i.next();
//                K key = e.getKey();
//                V value = e.getValue();
//                if (value == null) {
//                    if (!(t.get(key)==null && t.containsKey(key)))
//                        return false;
//                } else {
//                    if (!value.equals(t.get(key)))
//                        return false;
//                }
//            }
//        } catch (ClassCastException unused)   {
//            return false;
//        } catch (NullPointerException unused) {
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * Returns the hash code value for this Map as per the definition in the
//     * Map interface.
//     *
//     * @see Map#hashCode()
//     * @since 1.2
//     */
//    public synchronized int hashCode() {
//        /*
//         * This code detects the recursion caused by computing the hash code
//         * of a self-referential hash table and prevents the stack overflow
//         * that would otherwise result.  This allows certain 1.1-era
//         * applets with self-referential hash tables to work.  This code
//         * abuses the loadFactor field to do double-duty as a hashCode
//         * in progress flag, so as not to worsen the space performance.
//         * A negative load factor indicates that hash code computation is
//         * in progress.
//         */
//        int h = 0;
//        if (count == 0 || loadFactor < 0)
//            return h;  // Returns zero
//
//        loadFactor = -loadFactor;  // Mark hashCode computation in progress
//        java.util.Hashtable.Entry<?,?>[] tab = table;
//        for (java.util.Hashtable.Entry<?,?> entry : tab) {
//            while (entry != null) {
//                h += entry.hashCode();
//                entry = entry.next;
//            }
//        }
//
//        loadFactor = -loadFactor;  // Mark hashCode computation complete
//
//        return h;
//    }
//
//    @Override
//    public synchronized V getOrDefault(Object key, V defaultValue) {
//        V result = get(key);
//        return (null == result) ? defaultValue : result;
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public synchronized void forEach(BiConsumer<? super K, ? super V> action) {
//        Objects.requireNonNull(action);     // explicit check required in case
//        // table is empty.
//        final int expectedModCount = modCount;
//
//        java.util.Hashtable.Entry<?, ?>[] tab = table;
//        for (java.util.Hashtable.Entry<?, ?> entry : tab) {
//            while (entry != null) {
//                action.accept((K)entry.key, (V)entry.value);
//                entry = entry.next;
//
//                if (expectedModCount != modCount) {
//                    throw new ConcurrentModificationException();
//                }
//            }
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public synchronized void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
//        Objects.requireNonNull(function);     // explicit check required in case
//        // table is empty.
//        final int expectedModCount = modCount;
//
//        java.util.Hashtable.Entry<K, V>[] tab = (java.util.Hashtable.Entry<K, V>[])table;
//        for (java.util.Hashtable.Entry<K, V> entry : tab) {
//            while (entry != null) {
//                entry.value = Objects.requireNonNull(
//                        function.apply(entry.key, entry.value));
//                entry = entry.next;
//
//                if (expectedModCount != modCount) {
//                    throw new ConcurrentModificationException();
//                }
//            }
//        }
//    }
//
//    @Override
//    public synchronized V putIfAbsent(K key, V value) {
//        Objects.requireNonNull(value);
//
//        // Makes sure the key is not already in the hashtable.
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        @SuppressWarnings("unchecked")
//        java.util.Hashtable.Entry<K,V> entry = (java.util.Hashtable.Entry<K,V>)tab[index];
//        for (; entry != null; entry = entry.next) {
//            if ((entry.hash == hash) && entry.key.equals(key)) {
//                V old = entry.value;
//                if (old == null) {
//                    entry.value = value;
//                }
//                return old;
//            }
//        }
//
//        addEntry(hash, key, value, index);
//        return null;
//    }
//
//    @Override
//    public synchronized boolean remove(Object key, Object value) {
//        Objects.requireNonNull(value);
//
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        @SuppressWarnings("unchecked")
//        java.util.Hashtable.Entry<K,V> e = (java.util.Hashtable.Entry<K,V>)tab[index];
//        for (java.util.Hashtable.Entry<K,V> prev = null; e != null; prev = e, e = e.next) {
//            if ((e.hash == hash) && e.key.equals(key) && e.value.equals(value)) {
//                modCount++;
//                if (prev != null) {
//                    prev.next = e.next;
//                } else {
//                    tab[index] = e.next;
//                }
//                count--;
//                e.value = null;
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public synchronized boolean replace(K key, V oldValue, V newValue) {
//        Objects.requireNonNull(oldValue);
//        Objects.requireNonNull(newValue);
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        @SuppressWarnings("unchecked")
//        java.util.Hashtable.Entry<K,V> e = (java.util.Hashtable.Entry<K,V>)tab[index];
//        for (; e != null; e = e.next) {
//            if ((e.hash == hash) && e.key.equals(key)) {
//                if (e.value.equals(oldValue)) {
//                    e.value = newValue;
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public synchronized V replace(K key, V value) {
//        Objects.requireNonNull(value);
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        @SuppressWarnings("unchecked")
//        java.util.Hashtable.Entry<K,V> e = (java.util.Hashtable.Entry<K,V>)tab[index];
//        for (; e != null; e = e.next) {
//            if ((e.hash == hash) && e.key.equals(key)) {
//                V oldValue = e.value;
//                e.value = value;
//                return oldValue;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public synchronized V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
//        Objects.requireNonNull(mappingFunction);
//
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        @SuppressWarnings("unchecked")
//        java.util.Hashtable.Entry<K,V> e = (java.util.Hashtable.Entry<K,V>)tab[index];
//        for (; e != null; e = e.next) {
//            if (e.hash == hash && e.key.equals(key)) {
//                // Hashtable not accept null value
//                return e.value;
//            }
//        }
//
//        V newValue = mappingFunction.apply(key);
//        if (newValue != null) {
//            addEntry(hash, key, newValue, index);
//        }
//
//        return newValue;
//    }
//
//    @Override
//    public synchronized V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
//        Objects.requireNonNull(remappingFunction);
//
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        @SuppressWarnings("unchecked")
//        java.util.Hashtable.Entry<K,V> e = (java.util.Hashtable.Entry<K,V>)tab[index];
//        for (java.util.Hashtable.Entry<K,V> prev = null; e != null; prev = e, e = e.next) {
//            if (e.hash == hash && e.key.equals(key)) {
//                V newValue = remappingFunction.apply(key, e.value);
//                if (newValue == null) {
//                    modCount++;
//                    if (prev != null) {
//                        prev.next = e.next;
//                    } else {
//                        tab[index] = e.next;
//                    }
//                    count--;
//                } else {
//                    e.value = newValue;
//                }
//                return newValue;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public synchronized V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
//        Objects.requireNonNull(remappingFunction);
//
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        @SuppressWarnings("unchecked")
//        java.util.Hashtable.Entry<K,V> e = (java.util.Hashtable.Entry<K,V>)tab[index];
//        for (java.util.Hashtable.Entry<K,V> prev = null; e != null; prev = e, e = e.next) {
//            if (e.hash == hash && Objects.equals(e.key, key)) {
//                V newValue = remappingFunction.apply(key, e.value);
//                if (newValue == null) {
//                    modCount++;
//                    if (prev != null) {
//                        prev.next = e.next;
//                    } else {
//                        tab[index] = e.next;
//                    }
//                    count--;
//                } else {
//                    e.value = newValue;
//                }
//                return newValue;
//            }
//        }
//
//        V newValue = remappingFunction.apply(key, null);
//        if (newValue != null) {
//            addEntry(hash, key, newValue, index);
//        }
//
//        return newValue;
//    }
//
//    @Override
//    public synchronized V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
//        Objects.requireNonNull(remappingFunction);
//
//        java.util.Hashtable.Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        @SuppressWarnings("unchecked")
//        java.util.Hashtable.Entry<K,V> e = (java.util.Hashtable.Entry<K,V>)tab[index];
//        for (java.util.Hashtable.Entry<K,V> prev = null; e != null; prev = e, e = e.next) {
//            if (e.hash == hash && e.key.equals(key)) {
//                V newValue = remappingFunction.apply(e.value, value);
//                if (newValue == null) {
//                    modCount++;
//                    if (prev != null) {
//                        prev.next = e.next;
//                    } else {
//                        tab[index] = e.next;
//                    }
//                    count--;
//                } else {
//                    e.value = newValue;
//                }
//                return newValue;
//            }
//        }
//
//        if (value != null) {
//            addEntry(hash, key, value, index);
//        }
//
//        return value;
//    }
//
//    /**
//     * Save the state of the Hashtable to a stream (i.e., serialize it).
//     *
//     * @serialData The <i>capacity</i> of the Hashtable (the length of the
//     *             bucket array) is emitted (int), followed by the
//     *             <i>size</i> of the Hashtable (the number of key-value
//     *             mappings), followed by the key (Object) and value (Object)
//     *             for each key-value mapping represented by the Hashtable
//     *             The key-value mappings are emitted in no particular order.
//     */
//    private void writeObject(java.io.ObjectOutputStream s)
//            throws IOException {
//        java.util.Hashtable.Entry<Object, Object> entryStack = null;
//
//        synchronized (this) {
//            // Write out the threshold and loadFactor
//            s.defaultWriteObject();
//
//            // Write out the length and count of elements
//            s.writeInt(table.length);
//            s.writeInt(count);
//
//            // Stack copies of the entries in the table
//            for (int index = 0; index < table.length; index++) {
//                java.util.Hashtable.Entry<?,?> entry = table[index];
//
//                while (entry != null) {
//                    entryStack =
//                            new java.util.Hashtable.Entry<>(0, entry.key, entry.value, entryStack);
//                    entry = entry.next;
//                }
//            }
//        }
//
//        // Write out the key/value objects from the stacked entries
//        while (entryStack != null) {
//            s.writeObject(entryStack.key);
//            s.writeObject(entryStack.value);
//            entryStack = entryStack.next;
//        }
//    }
//
//    /**
//     * Reconstitute the Hashtable from a stream (i.e., deserialize it).
//     */
//    private void readObject(java.io.ObjectInputStream s)
//            throws IOException, ClassNotFoundException
//    {
//        // Read in the threshold and loadFactor
//        s.defaultReadObject();
//
//        // Validate loadFactor (ignore threshold - it will be re-computed)
//        if (loadFactor <= 0 || Float.isNaN(loadFactor))
//            throw new StreamCorruptedException("Illegal Load: " + loadFactor);
//
//        // Read the original length of the array and number of elements
//        int origlength = s.readInt();
//        int elements = s.readInt();
//
//        // Validate # of elements
//        if (elements < 0)
//            throw new StreamCorruptedException("Illegal # of Elements: " + elements);
//
//        // Clamp original length to be more than elements / loadFactor
//        // (this is the invariant enforced with auto-growth)
//        origlength = Math.max(origlength, (int)(elements / loadFactor) + 1);
//
//        // Compute new length with a bit of room 5% + 3 to grow but
//        // no larger than the clamped original length.  Make the length
//        // odd if it's large enough, this helps distribute the entries.
//        // Guard against the length ending up zero, that's not valid.
//        int length = (int)((elements + elements / 20) / loadFactor) + 3;
//        if (length > elements && (length & 1) == 0)
//            length--;
//        length = Math.min(length, origlength);
//
//        if (length < 0) { // overflow
//            length = origlength;
//        }
//
//        // Check Map.Entry[].class since it's the nearest public type to
//        // what we're actually creating.
//        SharedSecrets.getJavaOISAccess().checkArray(s, Map.Entry[].class, length);
//        table = new java.util.Hashtable.Entry<?,?>[length];
//        threshold = (int)Math.min(length * loadFactor, MAX_ARRAY_SIZE + 1);
//        count = 0;
//
//        // Read the number of elements and then all the key/value objects
//        for (; elements > 0; elements--) {
//            @SuppressWarnings("unchecked")
//            K key = (K)s.readObject();
//            @SuppressWarnings("unchecked")
//            V value = (V)s.readObject();
//            // sync is eliminated for performance
//            reconstitutionPut(table, key, value);
//        }
//    }
//
//    /**
//     * The put method used by readObject. This is provided because put
//     * is overridable and should not be called in readObject since the
//     * subclass will not yet be initialized.
//     *
//     * <p>This differs from the regular put method in several ways. No
//     * checking for rehashing is necessary since the number of elements
//     * initially in the table is known. The modCount is not incremented and
//     * there's no synchronization because we are creating a new instance.
//     * Also, no return value is needed.
//     */
//    private void reconstitutionPut(java.util.Hashtable.Entry<?,?>[] tab, K key, V value)
//            throws StreamCorruptedException
//    {
//        if (value == null) {
//            throw new java.io.StreamCorruptedException();
//        }
//        // Makes sure the key is not already in the hashtable.
//        // This should not happen in deserialized version.
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        for (java.util.Hashtable.Entry<?,?> e = tab[index]; e != null ; e = e.next) {
//            if ((e.hash == hash) && e.key.equals(key)) {
//                throw new java.io.StreamCorruptedException();
//            }
//        }
//        // Creates the new entry.
//        @SuppressWarnings("unchecked")
//        java.util.Hashtable.Entry<K,V> e = (java.util.Hashtable.Entry<K,V>)tab[index];
//        tab[index] = new java.util.Hashtable.Entry<>(hash, key, value, e);
//        count++;
//    }
//
//    /**
//     * Hashtable bucket collision list entry
//     */
//    private static class Entry<K,V> implements Map.Entry<K,V> {
//        final int hash;
//        final K key;
//        V value;
//        java.util.Hashtable.Entry<K,V> next;
//
//        protected Entry(int hash, K key, V value, java.util.Hashtable.Entry<K,V> next) {
//            this.hash = hash;
//            this.key =  key;
//            this.value = value;
//            this.next = next;
//        }
//
//        @SuppressWarnings("unchecked")
//        protected Object clone() {
//            return new java.util.Hashtable.Entry<>(hash, key, value,
//                    (next==null ? null : (java.util.Hashtable.Entry<K,V>) next.clone()));
//        }
//
//        // Map.Entry Ops
//
//        public K getKey() {
//            return key;
//        }
//
//        public V getValue() {
//            return value;
//        }
//
//        public V setValue(V value) {
//            if (value == null)
//                throw new NullPointerException();
//
//            V oldValue = this.value;
//            this.value = value;
//            return oldValue;
//        }
//
//        public boolean equals(Object o) {
//            if (!(o instanceof Map.Entry))
//                return false;
//            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
//
//            return (key==null ? e.getKey()==null : key.equals(e.getKey())) &&
//                    (value==null ? e.getValue()==null : value.equals(e.getValue()));
//        }
//
//        public int hashCode() {
//            return hash ^ Objects.hashCode(value);
//        }
//
//        public String toString() {
//            return key.toString()+"="+value.toString();
//        }
//    }
//
//    // Types of Enumerations/Iterations
//    private static final int KEYS = 0;
//    private static final int VALUES = 1;
//    private static final int ENTRIES = 2;
//
//    /**
//     * A hashtable enumerator class.  This class implements both the
//     * Enumeration and Iterator interfaces, but individual instances
//     * can be created with the Iterator methods disabled.  This is necessary
//     * to avoid unintentionally increasing the capabilities granted a user
//     * by passing an Enumeration.
//     */
//    private class Enumerator<T> implements Enumeration<T>, Iterator<T> {
//        java.util.Hashtable.Entry<?,?>[] table = java.util.Hashtable.this.table;
//        int index = table.length;
//        java.util.Hashtable.Entry<?,?> entry;
//        java.util.Hashtable.Entry<?,?> lastReturned;
//        int type;
//
//        /**
//         * Indicates whether this Enumerator is serving as an Iterator
//         * or an Enumeration.  (true -> Iterator).
//         */
//        boolean iterator;
//
//        /**
//         * The modCount value that the iterator believes that the backing
//         * Hashtable should have.  If this expectation is violated, the iterator
//         * has detected concurrent modification.
//         */
//        protected int expectedModCount = modCount;
//
//        Enumerator(int type, boolean iterator) {
//            this.type = type;
//            this.iterator = iterator;
//        }
//
//        public boolean hasMoreElements() {
//            java.util.Hashtable.Entry<?,?> e = entry;
//            int i = index;
//            java.util.Hashtable.Entry<?,?>[] t = table;
//            /* Use locals for faster loop iteration */
//            while (e == null && i > 0) {
//                e = t[--i];
//            }
//            entry = e;
//            index = i;
//            return e != null;
//        }
//
//        @SuppressWarnings("unchecked")
//        public T nextElement() {
//            java.util.Hashtable.Entry<?,?> et = entry;
//            int i = index;
//            java.util.Hashtable.Entry<?,?>[] t = table;
//            /* Use locals for faster loop iteration */
//            while (et == null && i > 0) {
//                et = t[--i];
//            }
//            entry = et;
//            index = i;
//            if (et != null) {
//                java.util.Hashtable.Entry<?,?> e = lastReturned = entry;
//                entry = e.next;
//                return type == KEYS ? (T)e.key : (type == VALUES ? (T)e.value : (T)e);
//            }
//            throw new NoSuchElementException("Hashtable Enumerator");
//        }
//
//        // Iterator methods
//        public boolean hasNext() {
//            return hasMoreElements();
//        }
//
//        public T next() {
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//            return nextElement();
//        }
//
//        public void remove() {
//            if (!iterator)
//                throw new UnsupportedOperationException();
//            if (lastReturned == null)
//                throw new IllegalStateException("Hashtable Enumerator");
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//
//            synchronized(java.util.Hashtable.this) {
//                java.util.Hashtable.Entry<?,?>[] tab = java.util.Hashtable.this.table;
//                int index = (lastReturned.hash & 0x7FFFFFFF) % tab.length;
//
//                @SuppressWarnings("unchecked")
//                java.util.Hashtable.Entry<K,V> e = (java.util.Hashtable.Entry<K,V>)tab[index];
//                for(java.util.Hashtable.Entry<K,V> prev = null; e != null; prev = e, e = e.next) {
//                    if (e == lastReturned) {
//                        modCount++;
//                        expectedModCount++;
//                        if (prev == null)
//                            tab[index] = e.next;
//                        else
//                            prev.next = e.next;
//                        count--;
//                        lastReturned = null;
//                        return;
//                    }
//                }
//                throw new ConcurrentModificationException();
//            }
//        }
//    }
//}
