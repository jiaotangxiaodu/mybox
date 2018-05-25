package com.github.jiaotangxiaodu.mybox.tree;

import com.github.jiaotangxiaodu.mybox.base.IntegerTest;
import com.github.jiaotangxiaodu.mybox.base.TreeTest;
import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.tree.node.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-25
 * 测试ADT中装入实现Comparable接口的元素
 */
public class ADTTest extends TreeTest<Integer> implements IntegerTest {
    @Override
    public ADT<Integer> newBox() {
        return CoreFactory.create(ADT.class);
    }

    @Override
    public void testPreIterator() {
        //这个我也不知道怎么测。。。

        StringBuffer sb = new StringBuffer();
        sb.append("先序遍历:[");
        TempWrapper wrapper = initDefault();
        Iterator<Integer> preItr = wrapper.adt.preIterator();
        while (preItr.hasNext()) {
            Integer next = preItr.next();
            sb.append(next + ",");
        }
        sb.subSequence(0, sb.length() - 1);
        sb.append("]");
        System.out.println(sb.toString());
    }

    @Override
    public void testInIterator() {

        TempWrapper tempWrapper = initDefault();
        Integer[] is = tempWrapper.is;
        ADT<Integer> adt = tempWrapper.adt;
        TreeNode<Integer> root = adt.root();
        Iterator<Integer> inItr = adt.inIterator();
        int index = 0;
        while (inItr.hasNext()) {
            Integer eInADT = inItr.next();
            Integer eInArray = is[index];
            index++;
            Assert.assertEquals(eInArray, eInADT);
        }

    }

    @Override
    public void testPostIterator() {
        //这个我也不知道怎么测。。。

        StringBuffer sb = new StringBuffer();
        sb.append("后序遍历:[");
        TempWrapper wrapper = initDefault();
        Iterator<Integer> preItr = wrapper.adt.postIterator();
        while (preItr.hasNext()) {
            Integer next = preItr.next();
            sb.append(next + ",");
        }
        sb.subSequence(0, sb.length() - 1);
        sb.append("]");
        System.out.println(sb.toString());
    }

    @Test
    public void testFindMin() {
        TempWrapper wrap = initDefault();
        Assert.assertEquals(wrap.adt.findMin(), wrap.is[0]);
    }

    @Test
    public void testFindMax() {
        TempWrapper wrap = initDefault();
        Assert.assertEquals(wrap.is[wrap.is.length - 1], wrap.adt.findMax());
    }

    @Override
    public Integer newRandomElement() {
        return IntegerTest.super.newRandomElement();
    }

    @Override
    public Integer getSpecialElement() {
        return IntegerTest.super.getSpecialElement();
    }

    private TempWrapper initDefault() {
        TempWrapper tempWrapper = new TempWrapper();
        tempWrapper.adt = CoreFactory.create(ADT.class);
        tempWrapper.is = new Integer[100];
        for (int i = 0; i < tempWrapper.is.length; i++) {
            Integer e = newRandomElement();
            tempWrapper.is[i] = e;
            tempWrapper.adt.add(e);
        }
        Arrays.sort(tempWrapper.is);
        TreeNode<Integer> root = tempWrapper.adt.root();
        return tempWrapper;
    }

    private static class TempWrapper {
        //已排序的数组
        Integer[] is;
        //树
        ADT<Integer> adt;
    }
}
