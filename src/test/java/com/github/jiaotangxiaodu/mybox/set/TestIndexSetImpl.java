package com.github.jiaotangxiaodu.mybox.set;

import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.pojo.Student;
import com.github.jiaotangxiaodu.mybox.set.impl.IndexSetImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestIndexSetImpl {
    @Test
    public void testAdd() {
        initSet();
    }

    @Test
    public void testSelectOne() {
        IndexSet<Student> indexSet = initSet();
        indexSet.createIndex("name");
        Student o = indexSet.selectOneByIndex("name", "zs");
        System.out.println("o = " + o);
    }

    @Test
    public void testSelectList() {
        IndexSet<Student> indexSet = initSet();
        indexSet.createIndex("age");
        List<Student> ss = indexSet.selectByIndex("age", 12);
        System.out.println("ss = " + ss);
    }

    @Test
    public void testRemove() {
        IndexSet<Student> indexSet = initSet();
        indexSet.createIndex("name");
        Student zs = indexSet.selectOneByIndex("name", "zs");
        boolean remove = indexSet.remove(zs, "name");
        System.out.println("remove = " + remove);
        System.out.println(indexSet);
    }

    @Test
    public void testClear() {
        IndexSet<Student> indexSet = initSet();
        indexSet.clear();
        System.out.println(indexSet);
    }

    @Test
    public void testMulIndex() {
        IndexSet<Student> indexSet = initSet();
        indexSet.createIndex("name");
        indexSet.createIndex("age");

        List<Student> z = indexSet.selectByIndex("name", "z");
        System.out.println("z = " + z);

        List<Student> zs = indexSet.selectByIndex("name", "zs");
        System.out.println("zs = " + zs);

        List<Student> age12 = indexSet.selectByIndex("age", 12);
        System.out.println("age12 = " + age12);

    }


    private IndexSet<Student> initSet() {
        IndexSet indexSet = CoreFactory.create(IndexSet.class);
        indexSet.add(new Student("zs", 12, "male"));
        indexSet.add(new Student("ls", 12, "male"));
        indexSet.add(new Student("ww", 22, "female"));
        return indexSet;
    }


}
