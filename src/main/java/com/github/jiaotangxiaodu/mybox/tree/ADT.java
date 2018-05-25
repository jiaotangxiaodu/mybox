package com.github.jiaotangxiaodu.mybox.tree;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-24
 * 二叉查找树
 */
public interface ADT<E> extends BinaryTree<E> {

    /**
     * 找出最小元素，空树返回null
     */
    E findMin();

    /**
     * 找出最大元素，空树返回null
     */
    E findMax();
}
