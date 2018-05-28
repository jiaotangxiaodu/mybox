package com.jiaotangxiaodu.mybox.example;


import com.github.jiaotangxiaodu.mybox.core.factory.BoxFactory;
import com.github.jiaotangxiaodu.mybox.core.factory.SimpleBoxFactory;
import com.github.jiaotangxiaodu.mybox.linear.Queue;
import com.github.jiaotangxiaodu.mybox.linear.Stack;
import com.github.jiaotangxiaodu.mybox.linear.impl.LinkedStackImpl;


/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-28
 * 实例：扩展Stack（栈）容器，使其具有“反转”功能
 */
public class MyStackExample {

    //1.定义一个接口继承Stack接口
    interface MyStack<E> extends Stack<E> {
        void reverse();
    }

    /**
     * 2.写一个MyStack的实现类
     *
     * @param <E>
     */
    static class MyStackImpl<E> extends LinkedStackImpl<E> implements MyStack<E> {

        private BoxFactory factory = new SimpleBoxFactory();

        @Override
        public void reverse() {
            Queue<E> queue = factory.create(Queue.class);

            while (!isEmpty()) {
                queue.offer(this.pop());
            }
            while (!queue.isEmpty()) {
                this.push(queue.poll());
            }

        }
    }

    public static void main(String[] args) {
        BoxFactory factory = new SimpleBoxFactory();
        //3.将MyStack及其默认实现的映射注册到MyBox的工厂中
        factory.register(MyStack.class, MyStackImpl.class);
        MyStack<Integer> myStack = factory.create(MyStack.class);

        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);

        myStack.reverse();

        for (Integer integer : myStack) {
            System.out.println(integer);
        }
    }
}
