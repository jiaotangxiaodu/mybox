package com.github.jiaotangxiaodu.mybox.tree;

import com.github.jiaotangxiaodu.mybox.base.StudentTest;
import com.github.jiaotangxiaodu.mybox.base.TreeTest;
import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.pojo.Student;
import com.github.jiaotangxiaodu.mybox.tree.node.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-25
 * 测试ADT使用Comparator的情形
 */
public class ADT2Test extends TreeTest<Student> implements StudentTest {
    @Override
    public ADT<Student> newBox() {
        ADT adt = CoreFactory.create(ADT.class);
        Comparator<Student> comparator = Comparator.comparing(Student::getName);
        adt.setComparator(comparator);
        return adt;
    }

    @Override
    public void testPreIterator() {
        //这个我也不知道怎么测。。。

        StringBuffer sb = new StringBuffer();
        sb.append("先序遍历:[");
        TempWrapper wrapper = initDefault();
        Iterator<Student> preItr = wrapper.adt.preIterator();
        while (preItr.hasNext()) {
            Student next = preItr.next();
            sb.append(next + ",");
        }
        sb.subSequence(0, sb.length() - 1);
        sb.append("]");
        System.out.println(sb.toString());
    }

    @Override
    public void testInIterator() {

        TempWrapper tempWrapper = initDefault();
        Student[] is = tempWrapper.is;
        ADT<Student> adt = tempWrapper.adt;
        TreeNode<Student> root = adt.root();
        Iterator<Student> inItr = adt.inIterator();
        int index = 0;
        while (inItr.hasNext()) {
            Student eInADT = inItr.next();
            Student eInArray = is[index];
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
        Iterator<Student> preItr = wrapper.adt.postIterator();
        while (preItr.hasNext()) {
            Student next = preItr.next();
            sb.append(next).append(",");
        }
        System.out.println(sb.subSequence(0, sb.length() - 1) + "]");
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
    public Student newRandomElement() {
        return StudentTest.super.newRandomElement();
    }

    @Override
    public Student getSpecialElement() {
        return StudentTest.super.getSpecialElement();
    }

    private TempWrapper initDefault() {
        TempWrapper tempWrapper = new TempWrapper();
        tempWrapper.adt = newBox();

        tempWrapper.is = new Student[100];
        for (int i = 0; i < tempWrapper.is.length; i++) {
            Student e = newRandomElement();
            tempWrapper.is[i] = e;
            tempWrapper.adt.add(e);
        }
        Arrays.sort(tempWrapper.is, Comparator.comparing(Student::getName));
        return tempWrapper;
    }

    private static class TempWrapper {
        //已排序的数组
        Student[] is;
        //树
        ADT<Student> adt;
    }
}
