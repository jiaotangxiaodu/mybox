package com.github.jiaotangxiaodu.mybox.tree;

import com.github.jiaotangxiaodu.mybox.tree.node.TreeNode;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 * <br />
 * 树的基本实现<br />
 * 树中不能出现两个相同的元素<br />
 * 树中的元素必须是可比较的，比较性有两种实现方式<br />
 * 1. 保证树中所有元素都实现了{@link Comparable}接口<br />
 * 2. 在向树种添加元素之前，调用{@link Tree#setComparator(Comparator)}方法
 *
 * @param <E> 元素类型
 */
public interface Tree<E> extends Collection<E> {

    /**
     * 设置树的比较器
     *
     * @param comparator
     */
    void setComparator(Comparator<E> comparator);


    /**
     * 先序遍历（根左右）
     */
    default Iterator<E> preIterator() {
        return preIterator(getRoot());
    }

    /**
     * 在传入的结点上进行先序遍历（根左右）
     */
    Iterator<E> preIterator(TreeNode<E> node);

    /**
     * 中序遍历(左根右)
     */
    default Iterator<E> inIterator() {
        return inIterator(getRoot());
    }
    /**
     * 在传入的结点上进行中序遍历(左根右)
     */
    Iterator<E> inIterator(TreeNode<E> node);

    /**
     * 后序遍历（左右根）
     */
    default Iterator<E> postIterator() {
        return postIterator(getRoot());
    }
    /**
     * 在传入的结点上进行后序遍历（左右根）
     */
    Iterator<E> postIterator(TreeNode<E> node);

    /**
     * 返回根节点
     */
    TreeNode<E> getRoot();

    /**
     * 默认遍历方式是中序遍历
     */
    @Override
    default Iterator<E> iterator() {
        return inIterator();
    }

    //-----------暂时不做---------------
    //计算某一节点的深度depth（到根的唯一长度）
    //计算某一结点的高height(到树叶的最长长度)
    //计算树的高度（调用计算根节点的高）
    //isAncestor判断n1是不是n2的祖先
}
