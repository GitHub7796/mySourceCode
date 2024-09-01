import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class XMLTest {

    @Test
    public void testJDBC() throws SQLException {
        // JDBC连接的URL, 不同数据库有不同的格式:
        String JDBC_URL = "jdbc:mysql://localhost:3306/xdtk";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "123456";
        // 获取连接:
        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        // 访问数据库
        try (Statement stmt = conn.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM user")) {
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    System.out.println(rs.getString(2));
                    System.out.println(rs.getString(3));
                    System.out.println(rs.getString(4));
                    System.out.println(rs.getString(5));
                }
            }
        }
        // 关闭连接:
        conn.close();

    }

    @Test
    public void testXmlDom() throws ParserConfigurationException, IOException, SAXException {
        /**
         * XOM DOM 将整个XOM当作一个树，内部
         * <hello age="10">123</hello>
         */
        InputStream in = XMLTest.class.getResourceAsStream("mybatis-config.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        /**
         * interface Document extends Node
         */
        Document document = builder.parse(in);
        printNode(document, 5);
    }

    private void printNode(Node n, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print(' ');
        }
        switch (n.getNodeType()) {
            case Node.DOCUMENT_NODE://doucument节点
                System.out.println("Document:" + n.getNodeName());
                break;
            case Node.ELEMENT_NODE: // 元素节点
                //Node.ATTRIBUTE_NODE 不是单独存在，而是 元素节点（Node.ELEMENT_NODE）的一部分，需要在元素节点内部进行操作
                System.out.print("Element:" + n.getNodeName());
                if (n.hasAttributes()) {
                    NamedNodeMap attributes = n.getAttributes();
                    for (int i = 0; i < attributes.getLength(); i++) {
                        Node attr = attributes.item(i);
                        System.out.println(",Attr:"+attr.getNodeName() + "=" + attr.getNodeValue());
                    }
                }
                break;
            case Node.TEXT_NODE: // 文本
                System.out.println("Text:" + n.getNodeName() + "=" +n.getNodeValue());
                break;
            // 非解析文本
            case Node.CDATA_SECTION_NODE :
                System.out.println(  "CDATA:" + n.getNodeValue().trim());
                break;
            default://doucument节点
                /**
                 *  ELEMENT_NODE              = 1;
                 *  ATTRIBUTE_NODE            = 2;
                 *   TEXT_NODE                 = 3;
                 *   CDATA_SECTION_NODE        = 4;
                 *   ENTITY_REFERENCE_NODE     = 5;
                 *   ENTITY_NODE               = 6;
                 *   PROCESSING_INSTRUCTION_NODE = 7;
                 *    COMMENT_NODE              = 8;
                 *    DOCUMENT_NODE             = 9;
                 *    DOCUMENT_TYPE_NODE        = 10;
                 *    DOCUMENT_FRAGMENT_NODE    = 11;
                 *    NOTATION_NODE             = 12;
                 */
                System.out.println("getNodeType:" + n.getNodeType()+ ",getNodeName:"+n.getNodeName()+ ",value:" + n.getNodeValue());
                break;
        }
        for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling()) {
            printNode(child,indent+1);
        }
    }
    @Test
    public  void testXPath() throws Exception {
        // 读取并解析XML文档
        InputStream in = XMLTest.class.getResourceAsStream("mybatis-config.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(in);
        // 使用XPATH查询，查询后通过 DOM API 拿取属性
        XPath xpath = XPathFactory.newInstance().newXPath();
        /**
         *  <environments default="development">
         */
        String expression = "/configuration";
        Node node = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);
        printNode(node,5);
    }


    @Test
    public void testClassLoader() {
        /**
         * 因为有3种方法得到ClassLoader，对应有如下3种ClassLoader方法读取文件
         * 使用的路径是相对于这个ClassLoader的那个点的相对路径，此处只能使用相对路径
         */
        InputStream is = null;
        // 使用当前类的ClassLoader
        is = XMLTest.class.getClassLoader().getResourceAsStream("demo.xml");
        is = this.getClass().getClassLoader().getResourceAsStream("demo.xml");
        // 使用当前线程的ClassLoader
        is = Thread.currentThread().getContextClassLoader().getResourceAsStream( "demo.xml");
        // 使用系统ClassLoader，即系统的入口点所使用的ClassLoader
        is = ClassLoader.getSystemResourceAsStream("demo.xml");
    }

    @Test
    public void testSAX() throws ParserConfigurationException, SAXException, IOException {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("demo.xml");
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        saxParser.parse(input, new MyHandler());

    }
}
