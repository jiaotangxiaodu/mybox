package com.github.jiaotangxiaodu.mybox.linear.impl;

import com.github.jiaotangxiaodu.mybox.linear.LinkedQueue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 * Queue的默认实现
 */
public class LinkedQueueImpl<E> extends AbstractQueue<E> implements LinkedQueue<E> {

    private int size = 0;
    private Node<E> front;//队列头
    private Node<E> rear;//队列尾

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedItr(front);
    }


    @Override
    public boolean offer(E e) {
        Node<E> newRear = new Node<>(null, e);
        if (isEmpty()) {
            front = rear = newRear;
            size++;
            return true;
        }
        rear = rear.next = newRear;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        E frontElement = front.element;
        front = front.next;
        size--;
        return frontElement;
    }

    @Override
    public E peek() {
        if (front == null) return null;
        return front.element;
    }

    /**
     * 队列中的节点
     *
     * @param <E>
     */
    static class Node<E> {
        Node<E> next;
        E element;

        public Node(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }
    }

    /**
     * 队列的迭代器
     */
    static class LinkedItr<E> implements Iterator<E> {
        private Node<E> lastReturned;
        private Node<E> next;

        LinkedItr(Node front) {
            next = front;
        }

        public boolean hasNext() {
            return next != null;
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException("迭代器中没有下一个元素了");

            lastReturned = next;
            next = next.next;
            return lastReturned.element;
        }

    }
}
