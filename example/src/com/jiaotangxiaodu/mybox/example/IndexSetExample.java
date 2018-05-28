package com.jiaotangxiaodu.mybox.example;


import com.github.jiaotangxiaodu.mybox.core.factory.BoxFactory;
import com.github.jiaotangxiaodu.mybox.core.factory.SimpleBoxFactory;
import com.github.jiaotangxiaodu.mybox.set.IndexSet;

import java.util.List;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-27
 * 示例：创建索引集合容器，这个容器会自动对其中元素的属性创建索引
 */
public class IndexSetExample {

    public static void main(String[] args) {
        BoxFactory factory = new SimpleBoxFactory();//构建MyBox工厂
        IndexSet<Employee> indexSet = factory.create(IndexSet.class);//用工厂创建一个可建立索引的容器
        indexSet.add(new Employee("张三", 22, 10000));
        indexSet.add(new Employee("王五", 33, 15000));
        indexSet.add(new Employee("尼古拉斯赵四", 33, 20000));

        //indexSet.createIndex("name");手动建立索引。这一步可以省略,查询的时候会自动建立索引
        Employee employee = indexSet.selectOneByIndex("name", "张三");//查询name为张三的元素
        System.out.println("zs:" + employee);

        List<Employee> employeeList = indexSet.selectByIndex("age", 33);//查询age为33的所有元素
        System.out.println("age33:" + employeeList);

    }

    public static class Employee {
        private String name;
        private int age;
        private long salary;

        public Employee() {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public long getSalary() {
            return salary;
        }

        public void setSalary(long salary) {
            this.salary = salary;
        }

        public Employee(String name, int age, long salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    '}';
        }
    }

}
