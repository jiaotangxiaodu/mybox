package com.github.jiaotangxiaodu.mybox.set;

public interface SortedSet<E> extends java.util.SortedSet<E> {

    @Override
    SortedSet<E> subSet(E fromElement, E toElement);

    @Override
    SortedSet<E> headSet(E toElement);

    @Override
    SortedSet<E> tailSet(E fromElement);

}
