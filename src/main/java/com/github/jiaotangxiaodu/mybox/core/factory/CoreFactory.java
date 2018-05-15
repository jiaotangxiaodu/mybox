package com.github.jiaotangxiaodu.mybox.core.factory;

/**
 * myBox自用Factory,使用框架时勿用
 */
public abstract class CoreFactory {

    private static BoxFactory instance = new SimpleBoxFactory();

    public static <T> T create(Class<T> boxType, Object... args) {
        return instance.create(boxType, args);
    }

}
