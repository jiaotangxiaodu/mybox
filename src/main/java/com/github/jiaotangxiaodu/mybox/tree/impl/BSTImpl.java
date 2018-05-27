package com.github.jiaotangxiaodu.mybox.tree.impl;

import com.github.jiaotangxiaodu.mybox.core.exception.DuplicateElementException;
import com.github.jiaotangxiaodu.mybox.tree.BST;
import com.github.jiaotangxiaodu.mybox.tree.node.BSTNode;

import java.util.AbstractCollection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-24
 * 二叉查找树的默认实现
 */
public class BSTImpl<E> extends AbstractCollection<E> implements BST<E> {

    private Comparator<E> comparator;

    private BSTNode<E> root;

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
        return findNode((E) o, getRoot()) != null;
    }

    @Override
    public E findMin() {
        if (getRoot() == null) return null;
        return findMin(getRoot());
    }

    @Override
    public E findMax() {
        if (getRoot() == null) return null;
        return findMax(getRoot());
    }

    @Override
    public boolean add(E e) {
        if (e == null)
            throw new NullPointerException();
        try {
            setRoot(add(e, getRoot()));
        } catch (DuplicateElementException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) throw new NullPointerException();
        if (getRoot() == null) return false;
        if (o.equals(getRoot())) {
            clear();
            return true;
        }
        return remove((E) o, getRoot()) != null;
    }

    @Override
    public int size() {
        int size = 0;
        for (E ignored : this) {
            size++;
        }
        return size;
    }

    @Override
    public void clear() {
        setRoot(null);
    }

    @Override
    public Iterator<E> iterator() {
        return BST.super.iterator();
    }

    @Override
    public BSTNode<E> getRoot() {
        return root;
    }

    protected void setRoot(BSTNode<E> root) {
        this.root = root;
    }

    //----------------private method---------------

    protected static final int COMPARE_LESS = -1;
    protected static final int COMPARE_EQUALS = 0;
    protected static final int COMPARE_GRARTER = 1;


    private E findMin(BSTNode<E> node) {
        BSTNode<E> left = node.getLeft();
        return left != null ? findMin(left) : node.getElement();
    }

    private E findMax(BSTNode<E> node) {
        BSTNode<E> right = node.getRight();
        return right != null ? findMax(right) : node.getElement();
    }

    private BSTNode<E> add(E e, BSTNode<E> node) {
        if (e == null) throw new IllegalArgumentException("ADT不能储存null");
        if (node == null) return new BSTNode<>(e);//第一次插入元素
        int compare = compare(e, node.getElement());
        switch (compare) {
            case COMPARE_LESS:
                node.setLeft(add(e, node.getLeft()));
                break;
            case COMPARE_GRARTER:
                node.setRight(add(e, node.getRight()));
                break;
            case COMPARE_EQUALS:
                throw new DuplicateElementException();
        }
        return node;

    }


    protected BSTNode<E> remove(E o, BSTNode<E> node) {
        if (node == null) return null;//o没有找到
        int compare = compare(node.getElement(), o);
        switch (compare) {
            case COMPARE_LESS:
                node.setLeft(remove(o, node.getLeft()));
            case COMPARE_GRARTER:
                node.setRight(remove(o, node.getRight()));
            case COMPARE_EQUALS:
                if (node.getLeft() != null && node.getRight() != null) {
                    //被删除的节点两边子节点都存在
                    node.setElement(findMin(node.getRight()));
                    node.setRight(remove(node.getElement(), node.getRight()));
                } else
                    node = (node.getLeft() != null) ? node.getLeft() : node.getRight();
        }
        return node;
    }

    private BSTNode findNode(E o, BSTNode<E> node) {
        if (node == null) return null;
        int compareResult = compare(node.getElement(), o);
        switch (compareResult) {
            case COMPARE_LESS:
                return findNode(o, node.getRight());
            case COMPARE_GRARTER:
                return findNode(o, node.getLeft());
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
        return Integer.compare(comparedResult, 0);
    }


}
