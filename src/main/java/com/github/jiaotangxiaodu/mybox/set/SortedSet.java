package com.github.jiaotangxiaodu.mybox.set;

import java.util.Comparator;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 */
public interface SortedSet<E> extends java.util.SortedSet<E> {

    @Override
    SortedSet<E> subSet(E fromElement, E toElement);

    @Override
    SortedSet<E> headSet(E toElement);

    @Override
    SortedSet<E> tailSet(E fromElement);

    /**
     * 设置比较器
     * 如果容器中需要放入未实现Comparable接口的POJO,必须在放入容器之前调用此方法
     * @param comparator
     */
    void setComparator(Comparator<E>comparator);

}
