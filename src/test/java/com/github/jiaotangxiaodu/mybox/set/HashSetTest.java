package com.github.jiaotangxiaodu.mybox.set;

import com.github.jiaotangxiaodu.mybox.base.SetTest;
import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.pojo.Student;

import java.util.Collection;
import java.util.Random;
import java.util.UUID;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 */
public class HashSetTest extends SetTest<Student> {

    private Random random = new Random();
    private Random getRandom() {
        return random;
    }

    @Override
    protected Collection<Student> newBox() {
        return CoreFactory.create(HashSet.class);
    }

    @Override
    protected Student newRandomElement() {
        return new Student(UUID.randomUUID().toString().trim(), getRandom().nextInt(100),getRandom().nextBoolean()?"male":"female");
    }


    @Override
    protected Student getSpecialElement() {
        return new Student("nglszs",6,"male");
    }
}
