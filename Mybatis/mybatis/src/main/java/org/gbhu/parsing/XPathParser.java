package org.gbhu.parsing;


import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

public class XPathParser {
    private Document document;
    private XPath xPath;
    private EntityResolver entityResolver;

    public XPathParser(InputStream inputStream, EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
        this.xPath = XPathFactory.newInstance().newXPath();
        this.document = createDocument(inputStream);
    }

    private Document createDocument(InputStream inputStream) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(inputStream);
        } catch (Exception e) {

        }
        return null;
    }
}
