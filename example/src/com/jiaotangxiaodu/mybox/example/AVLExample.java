package com.jiaotangxiaodu.mybox.example;

import com.github.jiaotangxiaodu.mybox.core.factory.BoxFactory;
import com.github.jiaotangxiaodu.mybox.core.factory.SimpleBoxFactory;
import com.github.jiaotangxiaodu.mybox.tree.AVL;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-27
 * 示例：创建一个平衡二叉树容器
 */
public class AVLExample {

    public static void main(String[] args) {
        //创建BoxFactory工厂，项目中使用时建议将这个工厂设计为单例
        BoxFactory factory = new SimpleBoxFactory();
        //通过工厂构建平衡二叉树（AVL）容器
        AVL<String> avl = factory.create(AVL.class);
        //向容器中添加数据
        avl.add("MySQL从删库到跑路");
        avl.add("Java从精通到陌生");
        avl.add("Android从入门到放弃");
        avl.add("C语言从看懂到看开");
        avl.add("DreamWeaver从安装到卸载");

        //平衡二叉树添加元素时会自动保持从小到大顺序
        for (String s : avl) {
            System.out.println(s);
        }
    }

}
