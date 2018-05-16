package com.github.jiaotangxiaodu.mybox.set.impl;

import com.github.jiaotangxiaodu.mybox.set.HashSet;

import java.util.Collection;
import java.util.Iterator;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 * HashSet的默认实现
 *
 * @param <E>
 */
public class HashSetImpl<E> implements HashSet<E> {
    /**
     * 被代理类
     */
    private java.util.Set<E> proxy = new java.util.HashSet<>();

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
    public boolean add(E o) {
        return proxy.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return proxy.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return proxy.addAll(c);
    }

    @Override
    public void clear() {
        proxy.clear();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return proxy.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection c) {
        return proxy.retainAll(c);
    }

    @Override
    public boolean containsAll(Collection c) {
        return proxy.containsAll(c);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return proxy.toArray(a);
    }

    @Override
    public String toString() {
        return proxy.toString();
    }
}
