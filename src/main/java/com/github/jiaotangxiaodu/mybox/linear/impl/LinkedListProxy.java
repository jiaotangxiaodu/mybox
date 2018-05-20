package com.github.jiaotangxiaodu.mybox.linear.impl;

import com.github.jiaotangxiaodu.mybox.inf.ProxyImpl;
import com.github.jiaotangxiaodu.mybox.linear.LinkedList;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 */
public class LinkedListProxy implements ProxyImpl<LinkedList> {
    @Override
    public Object getProxy() {
        return new java.util.LinkedList<>();
    }

    @Override
    public Class<?>[] getInterfaces() {
        return new Class[]{LinkedList.class};
    }
}
