package com.github.jiaotangxiaodu.mybox.tree;

import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.linear.Queue;
import com.github.jiaotangxiaodu.mybox.tree.node.BinaryTreeNode;
import com.github.jiaotangxiaodu.mybox.tree.node.TreeNode;

import java.util.Iterator;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-24
 * 二叉树
 */
public interface BinaryTree<E> extends Tree<E> {

    /**
     * 二叉树的默认遍历顺序是中序遍历（左根右）
     */
    @Override
    default Iterator<E> iterator() {
        return inIterator();
    }

    @Override
    default Iterator<E> preIterator(TreeNode<E> node) {
        return new IteratorHelper().preIterator((BinaryTreeNode<E>) node);
    }

    @Override
    default Iterator<E> inIterator(TreeNode<E> node) {
        return new IteratorHelper().inIterator((BinaryTreeNode<E>) node);
    }

    @Override
    default Iterator<E> postIterator(TreeNode<E> node) {
        return new IteratorHelper().postIterator((BinaryTreeNode<E>) node);
    }

    class BinaryTreeIterator<E> implements Iterator<E> {
        private final Iterator<BinaryTreeNode<E>> itr;

        public BinaryTreeIterator(Queue<BinaryTreeNode<E>> queue) {
            this.itr = queue.iterator();
        }

        @Override
        public boolean hasNext() {
            return itr.hasNext();
        }

        @Override
        public E next() {
            return itr.next().getElement();
        }

    }

    //Java真的啰嗦到绝望...
    class IteratorHelper<E> {
        public void preIterator(BinaryTreeNode<E> node, Queue<BinaryTreeNode<E>> itrQueue) {

            if (node == null) {
                return;
            }
            itrQueue.offer(node);
            preIterator(node.getLeft(), itrQueue);
            preIterator(node.getRight(), itrQueue);

        }

        public void inIterator(BinaryTreeNode<E> node, Queue<BinaryTreeNode<E>> itrQueue) {
            if (node == null) {
                return;
            }
            inIterator(node.getLeft(), itrQueue);
            itrQueue.offer(node);
            inIterator(node.getRight(), itrQueue);
        }

        public void postIterator(BinaryTreeNode<E> node, Queue<BinaryTreeNode<E>> itrQueue) {
            if (node == null) {
                return;
            }
            postIterator(node.getRight(), itrQueue);
            postIterator(node.getLeft(), itrQueue);
            itrQueue.offer(node);
        }

        public Iterator<E> preIterator(BinaryTreeNode<E> node) {
            Queue itrQueue = CoreFactory.create(Queue.class);
            if (node != null) {
                preIterator(node, itrQueue);
            }
            return new BinaryTreeIterator<>(itrQueue);
        }

        public Iterator<E> inIterator(BinaryTreeNode<E> node) {
            Queue itrQueue = CoreFactory.create(Queue.class);
            if (node != null) {
                inIterator(node, itrQueue);
            }
            return new BinaryTreeIterator<>(itrQueue);
        }

        public Iterator<E> postIterator(BinaryTreeNode<E> node) {
            Queue itrQueue = CoreFactory.create(Queue.class);
            if (node != null) {
                postIterator(node, itrQueue);
            }
            return new BinaryTreeIterator<>(itrQueue);
        }
    }
}
