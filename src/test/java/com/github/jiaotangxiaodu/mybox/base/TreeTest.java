package com.github.jiaotangxiaodu.mybox.base;

import com.github.jiaotangxiaodu.mybox.tree.Tree;
import org.junit.Assert;
import org.junit.Test;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-25
 */
public abstract class TreeTest<E> extends CollectionTest<E> {

    @Override
    protected abstract Tree<E> newBox();

    @Test
    public void testRoot() {
        Tree<E> tree = newBox();
        Assert.assertEquals(null, tree.getRoot());
        E rootElement = newRandomElement();
        tree.add(rootElement);
        Assert.assertEquals(rootElement, tree.getRoot().getElement());
    }

    @Test
    public abstract void testPreIterator();

    @Test
    public abstract void testInIterator();

    @Test
    public abstract void testPostIterator();

    @Override
    protected boolean testAddNull() {
        return false;
    }
}
