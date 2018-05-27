package com.github.jiaotangxiaodu.mybox.tree.node;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-24
 * 二叉树结点
 */
public interface BinaryTreeNode<E> extends TreeNode<E>,Cloneable {
    /**
     * 返回左子节点
     */
    BinaryTreeNode<E> getLeft();

    /**
     * 返回右子节点
     */
    BinaryTreeNode<E> getRight();

    /**
     * 设置左子节点
     *
     * @param e
     */
    void setLeft(BinaryTreeNode<E> e);

    /**
     * 设置右子节点
     *
     * @param e
     */
    void setRight(BinaryTreeNode<E> e);

}
