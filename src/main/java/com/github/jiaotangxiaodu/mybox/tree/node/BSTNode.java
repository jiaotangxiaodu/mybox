package com.github.jiaotangxiaodu.mybox.tree.node;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-25
 */
public class BSTNode<E> implements BinaryTreeNode<E> {
    private E element;
    protected BSTNode<E> left;
    protected BSTNode<E> right;


    public BSTNode(E element) {
        this(element, null, null);
    }

    public BSTNode(E element, BSTNode<E> left, BSTNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    @Override
    public BSTNode<E> getLeft() {
        return left;
    }

    @Override
    public BSTNode<E> getRight() {
        return right;
    }

    @Override
    public void setLeft(BinaryTreeNode<E> e) {
        this.setLeft((BSTNode<E>) e);
    }

    @Override
    public void setRight(BinaryTreeNode<E> e) {
        this.setRight((BSTNode<E>) e);
    }


    private void setLeft(BSTNode<E> e) {
        this.left = e;
    }

    private void setRight(BSTNode<E> e) {
        this.right = e;
    }

    public E getElement() {
        return element;
    }

    @Override
    public void setElement(E e) {
        this.element = e;
    }


}
