import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
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
    public  void testJavaxXml() throws Exception {
        // 读取并解析XML文档
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("src/test/java/demo.xml");
        NodeList list = document.getElementsByTagName("name");

        // 获取XML内容
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            String name = node.getTextContent();
            System.out.println(name);
            // 张三 李四
        }

        // 使用XPATH查询
        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "/book/title";
        NodeList nodeList = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getNodeValue());
        }
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
