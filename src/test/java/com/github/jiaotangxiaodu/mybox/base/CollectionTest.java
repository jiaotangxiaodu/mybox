package com.github.jiaotangxiaodu.mybox.base;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 */
public abstract class CollectionTest<E> {

    /**
     * 返回测试用容器
     *
     * @return
     */
    protected abstract Collection<E> newBox();

    /**
     * 返回一个随机生成的元素,任意两次生成的element之间调用equals都应该返回false
     * 不能返回null
     */
    protected abstract E newRandomElement();

    /**
     * 返回一个特定的元素,任意两次返回的Element之间调用Equals都应该返回true
     * 不能返回null
     */
    protected abstract E getSpecialElement();

    protected boolean testAddNull() {
        return true;
    }

    @Test
    public void testSize() {
        int expectedSize = 0;
        Collection<E> box = newBox();
        Assert.assertEquals(expectedSize, box.size());

        box.add(newRandomElement());
        expectedSize++;
        Assert.assertEquals(expectedSize, box.size());

        box.add(newRandomElement());
        expectedSize++;
        Assert.assertEquals(expectedSize, box.size());

        if (testAddNull()) {
            box.add(null);
            expectedSize++;
            Assert.assertEquals(expectedSize, box.size());
        }


    }

}
