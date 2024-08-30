package org.gbhu.builder.xml;

import org.gbhu.builder.BaseBuilder;
import org.gbhu.parsing.XPathParser;
import org.gbhu.session.Configuration;

import java.io.InputStream;

public class XMLConfigBuilder extends BaseBuilder {

    private XPathParser parser;

    public XMLConfigBuilder(XPathParser parser) {
        super(new Configuration());
        this.parser = parser;
    }

    public XMLConfigBuilder(InputStream inputStream) {
        this(new XPathParser(inputStream,new XMLMapperEntityResolver()));
    }

    public Configuration parse() {
        return configuration;
    }

}
