package com.github.jiaotangxiaodu.mybox.linear;

import java.util.Collection;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 * LIFO表,栈,允许存入null
 */
public interface Stack<E> extends Collection<E> {
    /**
     * 弹栈(删除栈顶的元素)并返回
     */
    E pop();

    /**
     * 压栈(将一个元素放入栈顶)
     */
    void push(E e);

    /**
     * 查看栈顶的元素但不删除
     */
    E top();

}
