package com.jiaotangxiaodu.mybox.example;

import com.github.jiaotangxiaodu.mybox.core.factory.BoxFactory;
import com.github.jiaotangxiaodu.mybox.core.factory.SimpleBoxFactory;
import com.github.jiaotangxiaodu.mybox.tree.AVL;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-27
 */
public class AVLExample {

    public static void main(String[] args) {
        BoxFactory factory = new SimpleBoxFactory();
        AVL<String> avl = factory.create(AVL.class);
        avl.add("MySQL从删库到跑路");
        avl.add("Java从精通到陌生");
        avl.add("Android从入门到放弃");
        avl.add("C语言从看懂到看开");
        avl.add("DreamWeaver从安装到卸载");

        for (String s : avl) {
            System.out.println(s);
        }
    }

}
