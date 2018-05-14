package com.github.jiaotangxiaodu.mybox.core.factory;

/**
 * 构建容器的核心工厂
 */
public interface BoxFactory {

    /**
     * 构建容器
     * @param boxType 需要构建的容器的字节码,可以从BoxTypeEnum中获取
     * @param args 保留参数,暂时没有作用
     * @see BoxTypeEnum
     */
    <T> T create(Class<T> boxType,Object...args);
}
