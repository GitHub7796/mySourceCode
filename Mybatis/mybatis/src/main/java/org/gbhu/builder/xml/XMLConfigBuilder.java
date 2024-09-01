package org.gbhu.builder.xml;

import org.gbhu.builder.BaseBuilder;
import org.gbhu.io.Resources;
import org.gbhu.parsing.XNode;
import org.gbhu.parsing.XPathParser;
import org.gbhu.session.Configuration;

import java.io.IOException;
import java.io.InputStream;

public class XMLConfigBuilder extends BaseBuilder {

    private XPathParser parser;

    public XMLConfigBuilder(XPathParser parser) {
        super(new Configuration());
        this.parser = parser;
    }

    public XMLConfigBuilder(InputStream inputStream) {
        this(new XPathParser(inputStream, new XMLMapperEntityResolver()));
    }

    public Configuration parse() {
        // insert this.configuration
        /***
         * mybatis-config.xml
         * root element 为 <configuration>
         */
        parseConfiguration(parser.evalNode("/configuration"));
        return configuration;
    }

    private void parseConfiguration(XNode root) {
        try {
            mapperElement(root.evalNode("mappers"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void mapperElement(XNode parent) throws IOException {
        for (XNode child : parent.getChildren()) {
            String resource = child.getStringAttribute("resource");
            InputStream inputStream = Resources.getResourceAsStream(resource);
            XMLMapperBuilder mapperBuilder = new XMLMapperBuilder(inputStream, configuration, resource);
            mapperBuilder.parse();
        }
    }

}
