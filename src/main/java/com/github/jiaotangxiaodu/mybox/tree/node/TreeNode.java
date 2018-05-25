package com.github.jiaotangxiaodu.mybox.tree.node;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-24
 * 树的节点
 */
public interface TreeNode<E> {
    /**
     * 返回节点中的元素
     */
    E element();

    /**
     * 设置节点中的元素
     * @param e
     */
    void element(E e);

}
