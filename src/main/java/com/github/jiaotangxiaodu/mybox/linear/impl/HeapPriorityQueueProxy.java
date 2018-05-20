package com.github.jiaotangxiaodu.mybox.linear.impl;

import com.github.jiaotangxiaodu.mybox.core.util.CommonUtil;
import com.github.jiaotangxiaodu.mybox.inf.ProxyImpl;
import com.github.jiaotangxiaodu.mybox.linear.PriorityQueue;

import java.lang.reflect.Proxy;
import java.util.Comparator;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 */
public class HeapPriorityQueueProxy implements ProxyImpl<PriorityQueue> {

    private java.util.PriorityQueue nonComparatorPriorityQueue = new java.util.PriorityQueue<>();
    private java.util.PriorityQueue comparatorPriorityQueue = null;

    @Override
    public PriorityQueue newProxy() {
        //这里使用2个被代理对象,一个处理使用Comparator的优先队列,一个处理不使用Comparator但是元素本身实现Comparable的优先队列
        return (PriorityQueue) Proxy.newProxyInstance(HeapPriorityQueueProxy.class.getClassLoader(), new Class<?>[]{PriorityQueue.class}, (proxy, method, args) -> {
            if (CommonUtil.equals(method.getName(), "setComparator") && method.getParameterTypes() != null && method.getParameterTypes().length == 1 && CommonUtil.equals(method.getParameterTypes()[0], Comparator.class)) {
                //调用setComparator方法
                invokeSetComparator((Comparator) args[0]);
                return null;
            }
            if (comparatorPriorityQueue == null) {
                return method.invoke(nonComparatorPriorityQueue, args);
            } else {
                return method.invoke(comparatorPriorityQueue, args);
            }

        });
    }

    /**
     * 设置比较器,调用这个方法时启用comparatorPriorityQueue代理
     *
     * @param comparator
     */
    private void invokeSetComparator(Comparator comparator) {
        if (comparatorPriorityQueue == null) {
            comparatorPriorityQueue = new java.util.PriorityQueue<>(comparator);
            comparatorPriorityQueue.addAll(nonComparatorPriorityQueue);
            nonComparatorPriorityQueue = null;
        } else {
            java.util.PriorityQueue newQueue = new java.util.PriorityQueue<>(comparator);
            newQueue.addAll(comparatorPriorityQueue);
            comparatorPriorityQueue = newQueue;
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
}
