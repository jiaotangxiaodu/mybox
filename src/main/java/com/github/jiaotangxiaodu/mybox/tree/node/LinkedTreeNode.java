package com.github.jiaotangxiaodu.mybox.tree.node;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-24
 * 链式节点
 */
public interface LinkedTreeNode<E> extends TreeNode<E> {
    /**
     * 返回第一个子节点(叶子节点返回null)
     */
    TreeNode<E> firstChild();

    /**
     * 返回下一兄弟节点（不存在下一兄弟节点时返回null）
     */
    TreeNode<E> nextSibling();
}
