package com.github.jiaotangxiaodu.mybox.linear.impl;

import com.github.jiaotangxiaodu.mybox.core.util.CommonUtil;
import com.github.jiaotangxiaodu.mybox.linear.LinkedStack;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 */
public class LinkedStackImpl<E> extends AbstractCollection<E> implements LinkedStack<E> {

    private int size = 0;
    /**
     * 栈顶节点
     */
    private Node<E> top;


    @Override
    public int size() {
        return this.size;
    }


    @Override
    public Iterator<E> iterator() {
        return new LinkedItr<>(top);
    }


    @Override
    public boolean add(E e) {
        top = new Node<>(top, e);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {

        Node<E> preNode = new Node<>(top, null);
        Node<E> curNode;

        while ((curNode = preNode.next) != null) {
            if (CommonUtil.equals(curNode.element, o)) {
                preNode.next = curNode.next;
                size--;
                return true;
            } else {
                preNode = curNode;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public E pop() {
        throwExceptionIfEmpty();
        E topElement = top.element;
        top = top.next;
        size--;
        return topElement;
    }


    @Override
    public void push(E e) {

        Node<E> newTop = new Node<>(top, e);
        top = newTop;
        size++;

    }

    @Override
    public E top() {
        throwExceptionIfEmpty();
        return top.element;
    }

    /**
     * 判断当前是否是空栈,如果是空则跑出异常
     */
    private void throwExceptionIfEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("当前栈为空");
        }
    }

    /**
     * 栈中存储的节点
     *
     * @param <E>
     */
    static class Node<E> {
        public Node(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }

        Node<E> next;//指向下一个节点
        E element;//指向储存的元素
    }

    private class LinkedItr<E> implements Iterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;

        LinkedItr(Node<E> front) {
            next = front;
        }

        public boolean hasNext() {
            return next != null;
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            return lastReturned.element;
        }

    }

}
