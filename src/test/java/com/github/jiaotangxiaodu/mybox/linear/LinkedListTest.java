package com.github.jiaotangxiaodu.mybox.linear;

import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.pojo.Student;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 */
public class LinkedListTest extends LinearListTest {
    @Override
    public LinkedList<Student> newBox() {
        return CoreFactory.create(LinkedList.class);
    }
}
