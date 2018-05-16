package com.github.jiaotangxiaodu.mybox.linear.impl;

import com.github.jiaotangxiaodu.mybox.linear.LinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 * LinkedList的默认实现类
 * @param <E>
 */
public class LinkedListImpl<E> implements LinkedList<E> {

    private List<E> proxy = new java.util.LinkedList<>();

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
    public boolean addAll(int index, Collection<? extends E> c) {
        return proxy.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return proxy.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return proxy.retainAll(c);
    }

    @Override
    public void clear() {
        proxy.clear();
    }

    @Override
    public E get(int index) {
        return proxy.get(index);
    }

    @Override
    public E set(int index, E element) {
        return proxy.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        proxy.add(index, element);
    }

    @Override
    public E remove(int index) {
        return proxy.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return proxy.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return proxy.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return proxy.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return proxy.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return proxy.subList(fromIndex, toIndex);
    }
}
