package com.github.jiaotangxiaodu.mybox.linear.impl;

import com.github.jiaotangxiaodu.mybox.inf.ProxyImpl;
import com.github.jiaotangxiaodu.mybox.linear.Deque;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 * Deque双向队列的默认实现
 */
public class LinkedDequeProxy implements ProxyImpl<Deque> {


    @Override
    public Object getProxy() {
        return new java.util.LinkedList<>();
    }

    @Override
    public Class<?>[] getInterfaces() {
        return new Class[]{Deque.class};
    }
}
