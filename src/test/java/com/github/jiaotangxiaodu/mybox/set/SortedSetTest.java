package com.github.jiaotangxiaodu.mybox.set;

import com.github.jiaotangxiaodu.mybox.base.SetTest;
import com.github.jiaotangxiaodu.mybox.core.factory.CoreFactory;
import com.github.jiaotangxiaodu.mybox.pojo.ExamScore;

import java.util.Collection;
import java.util.Random;
import java.util.UUID;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 */
public class SortedSetTest extends SetTest {

    @Override
    protected boolean testAddNull() {
        return false;
    }

    @Override
    protected Collection newBox() {
        return CoreFactory.create(SortedSet.class);
    }

    @Override
    protected Object newRandomElement() {
        return new ExamScore(new Random().nextInt(), UUID.randomUUID().toString().trim());
    }

    @Override
    protected Object getSpecialElement() {
        return new ExamScore(6, "王尼玛");
    }
}
