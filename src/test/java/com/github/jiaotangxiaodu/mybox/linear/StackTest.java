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
public class StackTest extends CollectionTest<Student> implements StudentTest {
    @Override
    protected Stack<Student> newBox() {
        return CoreFactory.create(Stack.class);
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
    public void testStack(){
        Stack<Student> stack = newBox();
        Student s1 = newRandomElement();
        Student s2 = newRandomElement();
        stack.push(s1);
        stack.push(s2);

        Assert.assertEquals(stack.top(),s2);
        Assert.assertEquals(stack.pop(),s2);
        Assert.assertEquals(stack.pop(),s1);
    }
}
