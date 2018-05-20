package com.github.jiaotangxiaodu.mybox.linear;

import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.pojo.Student;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 */
public class ArrayListTest extends LinearListTest {
    @Override
    public ArrayList<Student> newBox() {
        return CoreFactory.create(ArrayList.class);
    }
}
