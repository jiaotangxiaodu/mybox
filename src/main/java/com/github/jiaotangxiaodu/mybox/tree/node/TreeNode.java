package com.github.jiaotangxiaodu.mybox.tree.node;

import java.io.Serializable;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-24
 * 树的节点
 */
public interface TreeNode<E> extends Serializable {
    /**
     * 返回节点中的元素
     */
    E getElement();

    /**
     * 设置节点中的元素
     *
     * @param e
     */
    void setElement(E e);

}
