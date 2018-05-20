package com.github.jiaotangxiaodu.mybox.linear;

import com.github.jiaotangxiaodu.mybox.base.CollectionTest;
import com.github.jiaotangxiaodu.mybox.base.StudentTest;
import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.pojo.Student;
import org.junit.Assert;
import org.junit.Test;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 */
public class QueueTest extends CollectionTest implements StudentTest {
    @Override
    protected Queue<Student> newBox() {
        return CoreFactory.create(Queue.class);
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
    public void testQueue(){
        Queue<Student> queue = newBox();
        Student s1 = newRandomElement();
        Student s2 = newRandomElement();
        queue.offer(s1);
        queue.offer(s2);


        Assert.assertEquals(queue.peek(),s1);
        Assert.assertEquals(queue.poll(),s1);
        Assert.assertEquals(queue.poll(),s2);
    }

}
