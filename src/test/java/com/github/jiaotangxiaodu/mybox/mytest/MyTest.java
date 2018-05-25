package com.github.jiaotangxiaodu.mybox.mytest;

import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.tree.ADT;
import com.github.jiaotangxiaodu.mybox.tree.node.TreeNode;
import org.junit.Test;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-24
 */
public class MyTest {

    @Test
    public void test1(){
        double d1 = 1.4d;
        float f1 = 1.4f;
        double d2 = 1.5d;
        float f2 = 1.5f;

        System.out.println("ans1 = " + (d1 == f1));
        System.out.println("ans1 = " + (d2 == f2));
    }

    @Test
    public void test2(){
        ADT<Integer> adt = CoreFactory.create(ADT.class);
        adt.add(2);
        adt.add(1);
        adt.add(3);
        TreeNode<Integer> root = adt.root();
        System.out.println("1");
    }

}
