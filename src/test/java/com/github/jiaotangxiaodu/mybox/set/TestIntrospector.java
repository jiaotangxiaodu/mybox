package com.github.jiaotangxiaodu.mybox.set;

import com.github.jiaotangxiaodu.mybox.pojo.Guy;
import com.github.jiaotangxiaodu.mybox.pojo.MissForture;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class TestIntrospector {

    @Test
    public void testMissFortune() throws Exception {
        MissForture missForture = new MissForture();
        Guy leftGuy = new Guy();
        leftGuy.setName("she");
        missForture.setLeftGuy(leftGuy);
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("leftGuy.name",MissForture.class);
        Method readMethod = propertyDescriptor.getReadMethod();
        Object invoke = readMethod.invoke(missForture);
        System.out.println("invoke = " + invoke);
    }

}
