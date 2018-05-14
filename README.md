(V1.0施工中)  
## 一个工厂模式快速构建数据容器的框架。    
专注高扩展性。容器继承自Collection,与Java原生List、Set框架完全兼容。  
支持索引,支持栈、队列、RB树等数据结构。  
非侵入性,容器支持所有类型的POJO,支持Spring注入,支持注解和XML两种扩展方式。  


## 使用  

1. 依赖  
    jar包://TODO  
    maven://TODO
2. 创建工厂  
    myContainer工厂属于轻量级对象,使用时直接new即可。  
    `ContainerFactory factory = new SimpleContainerFactory();  `
    
    如果你的项目集成了Spring,也可以使用依赖注入的方式实例化工厂。  
    
    applicationContext.xml  
    `<bean id="containerFactory" 
    class="com.github.jiaotangxiaodu.mycontainer.core.factory.SimpleContainerFactory"/>`
    
    Java代码  
    `import com.github.jiaotangxiaodu.mycontainer.core.factory.ContainerFactory`  
    
    `@Autowired`  
    `private ContainerFactory containerFactory;`
    
3. 构建容器  
    eg.构建一个AVL树容器  
    `import com.github.jiaotangxiaodu.mycontainer.core.strutype.Tree`  
    
    


## V1.0  
myContainer1.0容器支持的数据结构  
![myContainer1.0容器支持的数据结构](https://raw.githubusercontent.com/jiaotangxiaodu/imgReponsitory/master/myContainer/portal/support_structure.png )
