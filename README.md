(V1.0)  
## 一个工厂模式快速构建数据容器的框架。    
专注高扩展性。容器继承自Collection,与Java原生List、Set框架完全兼容。  
支持索引,支持栈、队列、RB树等数据结构。  
非侵入性,容器支持所有类型的POJO,支持Spring注入。  


## 使用  

1. 依赖  
    jar包:https://github.com/jiaotangxiaodu/mybox/raw/master/out/artifacts/mybox_jar/mybox.jar   
    源码:https://github.com/jiaotangxiaodu/mybox/raw/master/out/artifacts/mybox_src_v1_0/mybox_src_v1.0.jar   
    maven://TODO
2. 创建工厂  
    myBox工厂属于轻量级对象,使用时直接new即可。  
    `BoxFactory factory = new SimpleBoxFactory();  `
    
    如果你的项目集成了Spring,也可以使用依赖注入的方式实例化工厂。  
    
    applicationContext.xml  
    `<bean id="boxFactory" 
    class="com.github.jiaotangxiaodu.mybox.core.factory.SimpleBoxFactory"/>`
    
    Java代码  
    `import com.github.jiaotangxiaodu.mybox.core.factory.SimpleBoxFactoryy`  
    
    `@Autowired`  
    `private BoxFactory boxFactory;`
    
3. 构建容器  
    示例:构建一个容纳String的AVL(平衡二叉树)容器  
    示例:使用IndexSet容器(能够创建索引并进行搜索的容器)并使用条件过滤
    示例:扩展Stack使其具有"翻转"的功能

## V1.0  
myBox1.0容器原生支持的数据结构  
![myBox1.0容器支持的数据结构](https://raw.githubusercontent.com/jiaotangxiaodu/imgReponsitory/master/myContainer/portal/support_structure.png)
