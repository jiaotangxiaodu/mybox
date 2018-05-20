package com.github.jiaotangxiaodu.mybox.set;

import com.github.jiaotangxiaodu.mybox.base.SetTest;
import com.github.jiaotangxiaodu.mybox.base.StudentTest;
import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.pojo.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-20
 */
public class IndexSetTest extends SetTest<Student> implements StudentTest {


    @Override
    protected IndexSet<Student> newBox() {
        return CoreFactory.create(IndexSet.class);
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
    public void testCreateIndex() {
        IndexSet<Student> box = newBox();
        box.add(newRandomElement());
        box.add(newRandomElement());
        box.add(newRandomElement());
        Assert.assertEquals(box.showIndexs().size(), 0);
        box.createIndex("name");
        Set<String> indexs = box.showIndexs();
        Assert.assertEquals(indexs.size(), 1);
        Assert.assertEquals(indexs.iterator().next(), "name");
    }

    @Test
    public void testSelectByIndex() {
        IndexSet<Student> box = newBox();
        box.add(new Student("zs", 11, "male"));
        box.add(new Student("ls", 12, "male"));
        box.add(new Student("ww", 13, "female"));

        List<Student> students = box.selectByIndex("sex", "male");
        Assert.assertEquals(students.size(), 2);
    }

    @Test
    public void testSelectOneByIndex() {
        IndexSet<Student> box = newBox();
        Student zs = new Student("zs", 11, "male");
        box.add(zs);
        box.add(new Student("ls", 12, "male"));
        box.add(new Student("ww", 13, "female"));

        Student s = box.selectOneByIndex("name", "zs");
        Assert.assertEquals(s, zs);
    }
}
