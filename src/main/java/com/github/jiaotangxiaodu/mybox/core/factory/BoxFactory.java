package com.github.jiaotangxiaodu.mybox.core.factory;

import java.util.Collection;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 * 构建容器的核心工厂,建议做成单例模式
 */
public interface BoxFactory {

    /**
     * 构建容器
     *
     * @param boxType 需要构建的容器的字节码,可以从BoxTypeEnum中获取
     * @param args    目前不可用,如果容器构造函数需要参数,可以将参数传入
     * @see BoxTypeEnum
     */
    <T> T create(Class<T> boxType, Object... args);

    /**
     * 注册一个容器
     *
     * @param boxType
     * @param implType
     */
    void register(Class<? extends Collection> boxType, Class<?> implType);
}
