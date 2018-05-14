package com.github.jiaotangxiaodu.mybox.core.factory;

import com.github.jiaotangxiaodu.mybox.core.context.BoxContext;
import com.github.jiaotangxiaodu.mybox.core.context.XMLConfigurationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class SimpleBoxFactory implements BoxFactory {

    /**
     * 容器上下文,内部维护容器接口字节码和实现类的映射关系
     */
    private BoxContext boxContext;

    public void setBoxContext(BoxContext boxContext) {
        this.boxContext = boxContext;
    }

    public void initDefaultContext() {
        this.boxContext = new XMLConfigurationContext("boxApplication.xml");
    }

    @Override
    public <T> T create(Class<T> boxType, Object... args) {
        if (boxContext == null) {
            initDefaultContext();
        }
        Class<? extends T> targetClass = boxContext.get(boxType);
        try {
            return targetClass.getConstructor().newInstance();
        } catch (Exception e) {
            if (targetClass == null) {
                throw new RuntimeException("class=" + boxType.getName() + ",args=" + Arrays.toString(args) + ",未注册到boxContext中", e);
            }
            throw new RuntimeException(targetClass.getName() + "不存在空参构造方法", e);
        }
    }
}
