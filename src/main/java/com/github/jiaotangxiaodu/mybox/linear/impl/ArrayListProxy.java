package com.github.jiaotangxiaodu.mybox.linear.impl;

import com.github.jiaotangxiaodu.mybox.inf.ProxyImpl;
import com.github.jiaotangxiaodu.mybox.linear.ArrayList;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 */
public class ArrayListProxy implements ProxyImpl<ArrayList> {

    @Override
    public Object getProxy() {
        return new java.util.ArrayList<>();
    }

    @Override
    public Class<?>[] getInterfaces() {
        return new Class[]{ArrayList.class};
    }
}
