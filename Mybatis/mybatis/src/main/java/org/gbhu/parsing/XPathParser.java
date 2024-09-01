package org.gbhu.parsing;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * XML 解析器
 * 使用方法 XNode evalNode(xpath表达式)
 * 返回XNode，封装的 xpath结果
 */
public class XPathParser {

    //XML文档
    private Document document;
    // javax.xml.xpath.XPath 用来通过表达式 读取 document
    private XPath xpath;

    private EntityResolver entityResolver;
    private Properties variables;

    public XPathParser(InputStream inputStream, EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
        this.document = createDocument(inputStream);
        this.xpath = XPathFactory.newInstance().newXPath();
    }

    private Document createDocument(InputStream inputStream) {
        try {
            /**
             * 原生 java.xml 读取文档
             *        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
             *         DocumentBuilder builder = factory.newDocumentBuilder();
             *         Document document = builder.parse("src/test/java/demo.xml");
             */
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(inputStream);
        } catch (Exception e) {

        }
        return null;
    }

    public XNode evalNode(String expression) {
        return evalNode(expression, document, XPathConstants.NODE);
    }

    public XNode evalNode( String expression,Object root) {
        return evalNode(expression,root,XPathConstants.NODE);
    }

    public XNode evalNode(String expression, Object root, QName returnType) {
        try {
            // TODO 核心-xpath表达式
            Node node = (Node) xpath.evaluate(expression, root, returnType);
            return new XNode(node, this, variables);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }


    public List<XNode> evalNodes(String expression, Object root) {
        List<XNode> xNodes = new ArrayList<>();
        NodeList nodes = null;
        try {
            nodes = (NodeList) xpath.evaluate(expression, root, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < nodes.getLength(); i++) {
            xNodes.add(new XNode(nodes.item(i),this, variables));
        }
        return xNodes;
    }
}
