package com.github.jiaotangxiaodu.mybox.linear;


import java.util.Comparator;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 * 优先队列
 * 优先队列中需要用一个比较器Comparator来对元素进行排序,或者所有元素本身都实现了Comparable接口
 */
public interface PriorityQueue<E> extends Queue<E> {
    /**
     * 设置比较器。如果优先队列中需要使用比较器,此方法必须在所有的add和offer之前调用。
     * @param comparator
     */
    void setComparator(Comparator<? super E> comparator);
}
