package com.github.jiaotangxiaodu.mybox.tree.impl;

import com.github.jiaotangxiaodu.mybox.core.util.CommonUtil;
import com.github.jiaotangxiaodu.mybox.inf.ProxyImpl;
import com.github.jiaotangxiaodu.mybox.tree.RBTree;
import com.github.jiaotangxiaodu.mybox.tree.node.BinaryTreeNode;
import com.github.jiaotangxiaodu.mybox.tree.node.TreeNode;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-27
 */
public class RBTreeProxy<E> implements ProxyImpl<RBTree> {

    private TreeSet<E> nonComparatorTreeSet = new TreeSet$BinaryTree<>();
    private TreeSet<E> comparatorTreeSet = null;

    @Override
    public RBTree newProxy() {
        //这里使用2个被代理对象,一个处理使用Comparator的优先队列,一个处理不使用Comparator但是元素本身实现Comparable的优先队列
        return (RBTree) Proxy.newProxyInstance(RBTreeProxy.class.getClassLoader(), new Class<?>[]{RBTree.class}, (proxy, method, args) -> {
            if (CommonUtil.equals(method.getName(), "setComparator") && method.getParameterTypes() != null && method.getParameterTypes().length == 1 && CommonUtil.equals(method.getParameterTypes()[0], Comparator.class)) {
                //调用setComparator方法
                invokeSetComparator((Comparator) args[0]);
                return null;
            }

            return method.invoke(getCurrentProxy(), args);

        });
    }

    private TreeSet getCurrentProxy() {
        return comparatorTreeSet == null ? nonComparatorTreeSet : comparatorTreeSet;
    }

    /**
     * 设置比较器,调用这个方法时启用comparatorTreeSet代理
     *
     * @param comparator
     */
    private void invokeSetComparator(Comparator comparator) {
        if (comparatorTreeSet == null) {
            comparatorTreeSet = new TreeSet$BinaryTree<>(comparator);
            comparatorTreeSet.addAll(nonComparatorTreeSet);
            nonComparatorTreeSet = null;
        } else {
            TreeSet newQueue = new TreeSet$BinaryTree<>(comparator);
            newQueue.addAll(comparatorTreeSet);
            comparatorTreeSet = newQueue;
        }

    }


    ///////////////////////不使用
    @Override
    public Object getProxy() {
        //这里直接修改newProxy的逻辑,不再重写这个方法
        return null;
    }

    @Override
    public Class<?>[] getInterfaces() {
        //这里直接修改newProxy的逻辑,不再重写这个方法
        return new Class[0];
    }

    private static class TreeSet$BinaryTree<E> extends TreeSet<E> implements RBTree<E> {

        public TreeSet$BinaryTree() {
        }

        public TreeSet$BinaryTree(Comparator<? super E> comparator) {
            super(comparator);
        }

        public TreeSet$BinaryTree(Collection<? extends E> c) {
            super(c);
        }

        public TreeSet$BinaryTree(SortedSet<E> s) {
            super(s);
        }

        @Override
        public void setComparator(Comparator<E> comparator) {
            throw new UnsupportedOperationException();
        }

        @Override
        public TreeNode<E> getRoot() {
            //wtf.......
            try {
                if (size() == 0) return null;
                Field m = TreeSet.class.getDeclaredField("m");
                m.setAccessible(true);
                TreeMap map = (TreeMap) m.get(this);
                Field rootField = TreeMap.class.getDeclaredField("root");
                rootField.setAccessible(true);
                Object treeMap$Root = rootField.get(map);
                return new TreeMapEntryWrapper<>(treeMap$Root);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public Iterator<E> preIterator() {
            throw new UnsupportedOperationException("RB树不支持先序遍历");
        }

        @Override
        public Iterator<E> postIterator() {
            throw new UnsupportedOperationException("RB树不支持后序遍历");
        }

        @Override
        public Iterator<E> inIterator() {
            return super.iterator();
        }

        @Override
        public E findMin() {
            return first();
        }

        @Override
        public E findMax() {
            return last();
        }
    }

    private static class TreeMapEntryWrapper<E> implements BinaryTreeNode<E> {
        private Object entryProxy;

        public TreeMapEntryWrapper(Object entryProxy) {
            this.entryProxy = entryProxy;
        }

        @Override
        public BinaryTreeNode<E> getLeft() {
            try {
                Field leftField = entryProxy.getClass().getDeclaredField("left");
                leftField.setAccessible(true);
                Object left = leftField.get(entryProxy);
                return new TreeMapEntryWrapper<>(left);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public BinaryTreeNode<E> getRight() {
            try {
                Field rightField = entryProxy.getClass().getDeclaredField("right");
                rightField.setAccessible(true);
                Object right = rightField.get(entryProxy);
                return new TreeMapEntryWrapper<>(right);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void setLeft(BinaryTreeNode<E> e) {
            try {
                Field leftField = entryProxy.getClass().getDeclaredField("left");
                leftField.setAccessible(true);
                leftField.set(entryProxy, e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void setRight(BinaryTreeNode<E> e) {
            try {
                Field rightField = entryProxy.getClass().getDeclaredField("right");
                rightField.setAccessible(true);
                rightField.set(entryProxy, e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public E getElement() {
            try {
                Field keyField = entryProxy.getClass().getDeclaredField("key");
                keyField.setAccessible(true);
                Object left = keyField.get(entryProxy);
                return (E) left;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void setElement(E e) {
            try {
                Field keyField = entryProxy.getClass().getDeclaredField("key");
                keyField.setAccessible(true);
                keyField.set(entryProxy, e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


}
