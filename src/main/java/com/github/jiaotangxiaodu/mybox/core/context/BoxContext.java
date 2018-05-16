package com.github.jiaotangxiaodu.mybox.core.context;
/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 * 容器上下文,内部维护容器接口字节码和实现类的映射关系
 */
public interface BoxContext {

    /**
     * 通过接口字节码获取实例
     * @param boxType
     * @return
     */
    <T> Class<? extends T> get(Class<T> boxType, Object... args);
}
