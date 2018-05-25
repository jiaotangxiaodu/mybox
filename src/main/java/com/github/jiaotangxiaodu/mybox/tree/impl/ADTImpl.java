package com.github.jiaotangxiaodu.mybox.tree.impl;

import com.github.jiaotangxiaodu.mybox.tree.ADT;
import com.github.jiaotangxiaodu.mybox.tree.node.BinaryTreeNode;

import java.util.AbstractCollection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-24
 * 二叉查找树的默认实现
 */
public class ADTImpl<E> extends AbstractCollection<E> implements ADT<E> {

    private Comparator<E> comparator;

    private ADTNode<E> root;

    @Override
    public void setComparator(Comparator<E> comparator) {
        if (size() > 0) {
            throw new IllegalStateException("树中已经有元素了");
        }
        this.comparator = comparator;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException();
    }
        return contains(o, root());
    }

    @Override
    public E findMin() {
        if (root() == null) return null;
        return findMin(root());
    }

    @Override
    public E findMax() {
        if (root() == null) return null;
        return findMax(root());
    }

    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new ADTNode<>(e);
            return true;
        }
        return add(e, root());
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) throw new NullPointerException();
        if (root() == null) return false;
        if (o.equals(root())) {
            clear();
            return true;
        }
        return remove((E) o, root()) != null;
    }

    @Override
    public int size() {
        int size = 0;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            size++;
            iterator.next();
        }
        return size;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public Iterator<E> iterator() {
        return ADT.super.iterator();
    }

    @Override
    public ADTNode<E> root() {
        return root;
    }
    //----------------private method---------------

    private static final int COMPARE_LESS = -1;
    private static final int COMPARE_EQUALS = 0;
    private static final int COMPARE_GRARTER = 1;

    protected boolean contains(Object o, ADTNode<E> node) {
        return findNode(o) != null;
    }


    protected E findMin(ADTNode<E> node) {
        ADTNode<E> left = node.left();
        return left != null ? findMin(left) : node.element();
    }

    protected E findMax(ADTNode<E> node) {
        ADTNode<E> right = node.right();
        return right != null ? findMax(right) : node.element();
    }

    protected boolean add(E e, ADTNode<E> node) {
        if (e == null) throw new IllegalArgumentException("ADT不能储存null");
        int compare = compare(e, node.element());
        switch (compare) {
            case COMPARE_LESS:
                ADTNode<E> left = node.left();
                if (left == null) {
                    node.left(new ADTNode<>(e));
                    return true;
                } else {
                    return add(e, left);
                }
            case COMPARE_GRARTER:
                ADTNode<E> right = node.right();
                if (right == null) {
                    node.right(new ADTNode<>(e));
                    return true;
                } else {
                    return add(e, right);
                }
            default:
                return false;
        }

    }

    protected ADTNode<E> remove(E o, ADTNode<E> node) {
        if (node == null) return null;//o没有找到
        int compare = compare(node.element, o);
        switch (compare) {
            case COMPARE_LESS:
                node.left(remove(o, node.left));
            case COMPARE_GRARTER:
                node.right(remove(o, node.right()));
            case COMPARE_EQUALS:
                if (node.left() != null && node.right != null) {
                    //被删除的节点两边子节点都存在
                    node.element(findMin(node.right()));
                    node.right(remove(node.element(), node.right()));
                } else
                    node = (node.left() != null) ? node.left() : node.right();
        }
        return node;
    }

    protected ADTNode findNode(Object o) {
        return findNode((E) o, root());
    }

    protected ADTNode findNode(E o, ADTNode<E> node) {
        if (node == null) return null;
        int compareResult = compare(node.element(), o);
        switch (compareResult) {
            case COMPARE_LESS:
                return findNode(o, node.right());
            case COMPARE_GRARTER:
                return findNode(o, node.left());
            default:
                return node;
        }
    }

    /**
     * 对比两个元素大小
     *
     * @return 如果o1小于o2, 返回{@link #COMPARE_LESS},如果o1大于o2，返回${@link #COMPARE_GRARTER}，否则返回${{@link #COMPARE_EQUALS}}
     */
    protected int compare(E o1, E o2) {
        int comparedResult;
        if (comparator == null) {
            Comparable<E> c1 = (Comparable<E>) o1;
            Comparable<E> c2 = (Comparable<E>) o2;
            comparedResult = c1.compareTo(o2);
        } else {
            comparedResult = comparator.compare(o1, o2);
        }
        return comparedResult < 0 ? COMPARE_LESS : (comparedResult > 0 ? COMPARE_GRARTER : COMPARE_EQUALS);
    }

    protected static class ADTNode<E> implements BinaryTreeNode<E> {
        private E element;
        private ADTNode<E> left;
        private ADTNode<E> right;


        public ADTNode() {
            this(null);
        }

        public ADTNode(E element) {
            this(element, null, null);
        }

        public ADTNode(E element, ADTNode<E> left, ADTNode<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        @Override
        public ADTNode<E> left() {
            return left;
        }

        @Override
        public ADTNode<E> right() {
            return right;
        }

        @Override
        public void left(BinaryTreeNode<E> e) {
            left((ADTNode<E>) e);
        }

        @Override
        public void right(BinaryTreeNode<E> e) {
            right((ADTNode<E>) e);
        }

        public void left(ADTNode<E> e) {
            this.left = e;
        }

        public void right(ADTNode<E> e) {
            this.right = e;
        }

        @Override
        public E element() {
            return element;
        }

        @Override
        public void element(E e) {
            this.element = e;
        }
    }
}
