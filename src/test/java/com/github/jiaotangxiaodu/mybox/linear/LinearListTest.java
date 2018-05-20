package com.github.jiaotangxiaodu.mybox.linear;

import com.github.jiaotangxiaodu.mybox.base.LinearTest;
import com.github.jiaotangxiaodu.mybox.base.StudentTest;
import com.github.jiaotangxiaodu.mybox.pojo.Student;
import org.junit.Assert;
import org.junit.Test;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 * 测试线性表
 */
public abstract class LinearListTest extends LinearTest<Student> implements StudentTest {

    public abstract LinearList<Student> newBox();

    @Override
    public Student newRandomElement() {
        return StudentTest.super.newRandomElement();
    }

    @Override
    public Student getSpecialElement() {
        return StudentTest.super.getSpecialElement();
    }

    @Test
    public void testGet(){
        LinearList<Student> box = newBox();
        Student s1 = newRandomElement();
        Student s2 = newRandomElement();
        box.add(s1);
        box.add(s2);

        Assert.assertEquals(s1,box.get(0));
    }

    @Test
    public void testSet(){
        LinearList<Student> box = newBox();
        Student s1 = newRandomElement();
        Student s2 = newRandomElement();
        box.add(s1);
        box.add(s2);
        box.set(0,s2);
        Assert.assertEquals(s2,box.get(0));
    }

    @Test
    public void testAddIndex(){
        LinearList<Student> box = newBox();
        Student s1 = newRandomElement();
        Student s2 = newRandomElement();
        box.add(s1);
        box.add(s2);
        box.add(0,s2);
        Assert.assertEquals(box.size(),3);
        Assert.assertEquals(box.get(1),s1);
    }

    @Test
    public void testIndexOf(){
        LinearList<Student> box = newBox();
        Student s1 = newRandomElement();
        Student s2 = newRandomElement();
        box.add(s1);
        box.add(s2);
        box.add(s1);
        Assert.assertEquals(box.indexOf(s1),0);
        Assert.assertEquals(box.lastIndexOf(s1),2);
    }


}
