package com.github.jiaotangxiaodu.mybox.mytest;

import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.tree.BST;
import com.github.jiaotangxiaodu.mybox.tree.node.TreeNode;
import org.junit.Test;

import java.util.TreeSet;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-24
 */
public class MyTest {

    @Test
    public void test1() {
        double d1 = 1.4d;
        float f1 = 1.4f;
        double d2 = 1.5d;
        float f2 = 1.5f;

        System.out.println(Integer.toBinaryString(Float.floatToIntBits(f2)));
    }

    @Test
    public void test2() {
        BST<Integer> BST = CoreFactory.create(BST.class);
        BST.add(2);
        BST.add(1);
        BST.add(3);
        TreeNode<Integer> root = BST.getRoot();
        System.out.println("1");
    }

    @Test
    public void test3() {
        BST<Integer> bst = CoreFactory.create(BST.class);
        bst.add(1);
        bst.add(2);
        bst.add(3);
        System.out.println(bst);
    }

    @Test
    public void test4() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(7);
        set.add(8);
        set.add(4);
        set.add(9);
        set.add(2);

        System.out.println(set.first());
        System.out.println(set.last());
    }

}
