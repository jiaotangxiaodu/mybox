package com.github.jiaotangxiaodu.mybox.tree.node;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-25
 */
public class AVLNode<E> extends BSTNode<E> {
    private int height;//节点的高度

    public AVLNode(E element) {
        this(element, 0);
    }

    public AVLNode(E element, int height) {
        this(element, null, null, height);
    }

    public AVLNode(E element, AVLNode<E> left, AVLNode<E> right) {
        this(element, left, right, 0);
    }

    public AVLNode(E element, AVLNode<E> left, AVLNode<E> right, int height) {
        super(element, left, right);
        this.height = height;
    }


    @Override
    public AVLNode<E> getLeft() {
        return (AVLNode<E>) super.getLeft();
    }

    @Override
    public AVLNode<E> getRight() {
        return (AVLNode<E>) super.getRight();
    }

    @Override
    public void setLeft(BinaryTreeNode<E> e) {
        super.setLeft((AVLNode<E>) e);
    }

    @Override
    public void setRight(BinaryTreeNode<E> e) {
        super.setRight((AVLNode<E>) e);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return getElement() + "";
    }

}
