package org.gbhu.parsing;


import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 对 xpath搜索结果的封装
 */
public class XNode {
    private final Node node;
    private final XPathParser xPathParser;
    private final Properties attributes;
    private final Properties variables;

    public Node getNode() {
        return node;
    }

    public XNode(Node node, XPathParser xPathParser, Properties variables) {
        this.node = node;
        this.xPathParser = xPathParser;
        this.attributes = parseAttributes(node);
        this.variables = variables;
    }

    public XNode evalNode(String expression) {
        return xPathParser.evalNode(expression,node);
    }

    public List<XNode> getChildren() {
        List<XNode> children = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        //TODO 可借鉴的for循环
        for (int i = 0, n = nodeList.getLength(); i < n; i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                children.add(new XNode(node, xPathParser, variables));
            }
        }
        return children;
    }

    public String getStringAttribute(String name) {
        return attributes.getProperty(name);
    }



    private Properties parseAttributes(Node n) {
        Properties properties = new Properties();
        if (n.hasAttributes()) {
            NamedNodeMap attributeNodes = n.getAttributes();
            for (int i = 0; i < attributeNodes.getLength(); i++) {
                Node attribute = attributeNodes.item(i);
                String value = attribute.getNodeValue();
                properties.put(attribute.getNodeName(), value);
            }
        }
        return properties;
    }

    public List<XNode> evalNodes(String expression) {
        return xPathParser.evalNodes(expression,node);
    }


}
