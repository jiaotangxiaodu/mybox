package com.github.jiaotangxiaodu.mybox.tree.impl;

import com.github.jiaotangxiaodu.mybox.core.exception.DuplicateElementException;
import com.github.jiaotangxiaodu.mybox.tree.AVL;
import com.github.jiaotangxiaodu.mybox.tree.node.AVLNode;
import com.github.jiaotangxiaodu.mybox.tree.node.BSTNode;

import java.util.NoSuchElementException;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-25
 */
public class AVLImpl<E> extends BSTImpl<E> implements AVL<E> {

    public static final int ALLOWED_IMBALANCE = 1;//最大允许的不平衡高度差

    @Override
    public AVLNode<E> getRoot() {
        return (AVLNode<E>) super.getRoot();
    }

    @Override
    protected void setRoot(BSTNode<E> root) {
        if (root != null && !(root instanceof AVLNode)) {
            throw new IllegalArgumentException();
        }
        super.setRoot(root);
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
        try {
            remove((E) o, getRoot());
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private AVLNode<E> add(E x, AVLNode<E> t) {
        if (t == null) return new AVLNode<>(x, null, null);
        int compareStatus = compare(x, t.getElement());
        switch (compareStatus) {
            case COMPARE_LESS:
                t.setLeft(add(x, t.getLeft()));
                break;
            case COMPARE_GRARTER:
                t.setRight(add(x, t.getRight()));
                break;
            case COMPARE_EQUALS:
                //do nothing
        }
        return balance(t);
    }

    private AVLNode<E> balance(AVLNode<E> t) {
        //操作节点使之平衡
        if (t == null) return t;
        if (height(t.getLeft()) - height(t.getRight()) > ALLOWED_IMBALANCE) {
            if (height(t.getLeft().getLeft()) >= height(t.getLeft().getRight())) {
                //solution1
                t = rotateWithLeftChild(t);
            } else {
                //solution2
                t = doubleWithLeftChild(t);
            }
        } else if (height(t.getRight()) - height(t.getLeft()) > ALLOWED_IMBALANCE) {
            if (height(t.getRight().getRight()) >= height(t.getRight().getLeft())) {
                //solution3
                t = rotateWithRightChild(t);
            } else {
                //solution4
                t = doubleWithRightChild(t);
            }
        }
        t.setHeight(Math.max(height(t.getLeft()), height(t.getRight())) + 1);
        return t;
    }


    private AVLNode<E> rotateWithRightChild(AVLNode<E> k2) {
        AVLNode<E> k1 = k2.getRight();
        k2.setRight(k1.getLeft());
        k1.setLeft(k2);
        k2.setHeight(Math.max(height(k2.getRight()), height(k2.getLeft())) + 1);
        return k1;
    }

    private AVLNode<E> rotateWithLeftChild(AVLNode<E> k2) {
        AVLNode<E> k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);
        k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
        return k1;
    }

    private AVLNode<E> doubleWithRightChild(AVLNode<E> k3) {
        k3.setRight(rotateWithLeftChild(k3.getRight()));
        return rotateWithRightChild(k3);
    }

    private AVLNode<E> doubleWithLeftChild(AVLNode<E> k3) {
        k3.setLeft(rotateWithRightChild(k3.getLeft()));
        return rotateWithLeftChild(k3);
    }

    private int height(AVLNode<E> node) {
        //计算某一结点的高度
        return node == null ? -1 : node.getHeight();
    }

    private AVLNode<E> remove(E x, AVLNode<E> t) {
        return balance((AVLNode<E>) super.remove(x, t));
    }
}
