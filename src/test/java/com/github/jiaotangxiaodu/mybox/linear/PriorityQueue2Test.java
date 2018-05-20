package com.github.jiaotangxiaodu.mybox.linear;

import com.github.jiaotangxiaodu.mybox.base.CollectionTest;
import com.github.jiaotangxiaodu.mybox.base.StudentTest;
import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.pojo.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 * 使用Comparator
 */
public class PriorityQueue2Test extends CollectionTest<Student> implements StudentTest {


    @Override
    protected boolean testAddNull() {
        return false;
    }

    @Override
    protected PriorityQueue<Student> newBox() {
        PriorityQueue queue = CoreFactory.create(PriorityQueue.class);
        queue.setComparator(new StudentComparator());
        return queue;
    }

    @Override
    public Student newRandomElement() {
        return StudentTest.super.newRandomElement();
    }

    @Override
    public Student getSpecialElement() {
        return StudentTest.super.getSpecialElement();
    }

    @Test
    public void testPriorityQueueAll() {
        PriorityQueue<Student> box = newBox();
        Student[] targetArr = new Student[100];
        for (int i = 0; i < 100; i++) {
            targetArr[i] = newRandomElement();
            box.offer(targetArr[i]);
        }
        Arrays.sort(targetArr, new StudentComparator());

        for (int i = 0; i < 100; i++) {
            Student poll = box.poll();
            Assert.assertEquals(poll, targetArr[i]);
        }
    }


    private class StudentComparator implements java.util.Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
