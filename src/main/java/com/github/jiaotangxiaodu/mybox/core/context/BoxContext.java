package com.github.jiaotangxiaodu.mybox.core.context;

import java.util.Collection;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 * 容器上下文,内部维护容器接口字节码和实现类的映射关系
 */
public interface BoxContext {

    /**
     * 通过接口字节码获取实例
     *
     * @param boxType
     * @return
     */
    <T> Class<? extends T> get(Class<T> boxType, Object... args);

    /**
     * 注册新的容器
     * @param boxType
     * @param implType
     */
    void put(Class<? extends Collection> boxType, Class<?> implType);
}
