package com.github.jiaotangxiaodu.mybox.set;

import com.github.jiaotangxiaodu.mybox.base.SetTest;
import com.github.jiaotangxiaodu.mybox.base.StudentTest;
import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.pojo.Student;

import java.util.Collection;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 */
public class HashSetTest extends SetTest<Student> implements StudentTest {


    @Override
    protected Collection<Student> newBox() {
        return CoreFactory.create(HashSet.class);
    }

    @Override
    public Student newRandomElement() {
        return StudentTest.super.newRandomElement();
    }


    @Override
    public Student getSpecialElement() {
        return StudentTest.super.getSpecialElement();
    }
}
