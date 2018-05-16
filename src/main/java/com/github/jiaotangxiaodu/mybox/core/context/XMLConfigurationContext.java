package com.github.jiaotangxiaodu.mybox.core.context;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * github.com/jiaotangxiaodu/mybox
 * 2018-5-16
 * 通过XML配置文件初始化的容器上下文
 */
public class XMLConfigurationContext implements BoxContext {

    private Map<Class, Class> mappers = new HashMap<>();
    private Map<String, Object> config = new HashMap<>();

    public XMLConfigurationContext(String applicationPath) {
        parse(applicationPath);

    }

    private void parse(String applicationPath) {
        //1.获取jaxp工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            //2.获取解析器
            DocumentBuilder builder = factory.newDocumentBuilder();
            //3.用解析器加载xml文档--->Document
            Document document = builder.parse(XMLConfigurationContext.class.getClassLoader().getResourceAsStream(applicationPath));
            //4.获取根元素
            Node boxContext = document.getDocumentElement();
            NodeList childNodes = boxContext.getChildNodes();
            int childNodesLength = childNodes.getLength();
            for (int i = 0; i < childNodesLength; i++) {
                Node item = childNodes.item(i);
                String nodeName = item.getNodeName();
                switch (nodeName) {
                    case "config":
                        parseConfig(item);
                        break;
                    case "mappers":
                        parseMappers(item);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void parseConfig(Node configNode) {
        //nothing to do;
    }

    private void parseMappers(Node mappersNode) throws ClassNotFoundException {
        NodeList childNodes = mappersNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);

            switch (item.getNodeName()) {
                case "mapper":
                    String keyClassName = item.getAttributes().getNamedItem("name").getNodeValue().trim();
                    String valueClassName = item.getTextContent().trim();
                    mappers.put(Class.forName(keyClassName), Class.forName(valueClassName));
                    break;
            }
        }
    }

    @Override
    public <T> Class<? extends T> get(Class<T> boxType, Object... args) {
        Class targetClass = mappers.get(boxType);
        if (targetClass != null) {
            return targetClass;
        }
        if (!Modifier.isAbstract(boxType.getModifiers())) {
            return boxType;
        }
        return null;
    }
}
