package com.github.jiaotangxiaodu.mybox.set.impl;

import com.github.jiaotangxiaodu.mybox.set.SortedSet;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * SortedSet的默认实现
 *
 * @param <E>
 */
public class SortedSetImpl<E> implements SortedSet<E> {


    public SortedSetImpl() {
        this.proxy = new TreeSet<>();
    }

    private SortedSetImpl(java.util.SortedSet<E> proxy) {
        this.proxy = proxy;
    }

    /**
     * 被代理对象
     */
    private java.util.SortedSet<E> proxy;

    @Override
    public Comparator<? super E> comparator() {
        return proxy.comparator();
    }


    @Override
    public E first() {
        return proxy.first();
    }

    @Override
    public E last() {
        return proxy.last();
    }

    @Override
    public int size() {
        return proxy.size();
    }

    @Override
    public boolean isEmpty() {
        return proxy.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return proxy.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return proxy.iterator();
    }

    @Override
    public Object[] toArray() {
        return proxy.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return proxy.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return proxy.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return proxy.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return proxy.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return proxy.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return proxy.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return proxy.removeAll(c);
    }

    @Override
    public void clear() {
        proxy.clear();
    }


    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        java.util.SortedSet<E> es = proxy.subSet(fromElement, toElement);
        return new SortedSetImpl<>(es);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        java.util.SortedSet<E> es = proxy.headSet(toElement);
        return new SortedSetImpl<>(es);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        java.util.SortedSet<E> es = proxy.tailSet(fromElement);
        return new SortedSetImpl<>(es);
    }
}
