package com.github.jiaotangxiaodu.mybox.core.factory;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 * myBox自用Factory,使用框架时勿用
 */
public abstract class CoreFactory {

    private static BoxFactory instance = new SimpleBoxFactory();

    public static <T> T create(Class<T> boxType, Object... args) {
        return instance.create(boxType, args);
    }

}
