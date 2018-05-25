package com.github.jiaotangxiaodu.mybox.tree.node;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-24
 * 二叉树结点
 */
public interface BinaryTreeNode<E> extends TreeNode<E> {
    /**
     * 返回左子节点
     */
    BinaryTreeNode<E> left();

    /**
     * 返回右子节点
     */
    BinaryTreeNode<E> right();

    /**
     * 设置左子节点
     *
     * @param e
     */
    void left(BinaryTreeNode<E> e);

    /**
     * 设置右子节点
     *
     * @param e
     */
    void right(BinaryTreeNode<E> e);

}
