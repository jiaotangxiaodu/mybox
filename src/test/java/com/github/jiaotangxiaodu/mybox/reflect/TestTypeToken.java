package com.github.jiaotangxiaodu.mybox.reflect;

import com.github.jiaotangxiaodu.mybox.core.factory.BoxFactory;
import com.github.jiaotangxiaodu.mybox.core.factory.SimpleBoxFactory;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestTypeToken {

    @Test
    public void testTypeToken(){
        BoxFactory factory = new SimpleBoxFactory();
        Map<String,Object> map = factory.create(Map.class);
        System.out.println("map = " + map.getClass().getName());
    }

}
