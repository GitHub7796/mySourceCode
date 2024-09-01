package org.gbhu.builder.xml;

import org.gbhu.builder.BaseBuilder;
import org.gbhu.builder.MapperBuilderAssistant;
import org.gbhu.io.Resources;
import org.gbhu.parsing.XNode;
import org.gbhu.parsing.XPathParser;
import org.gbhu.session.Configuration;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder extends BaseBuilder {
    private final XPathParser parser;
    private final String resource;
    private final MapperBuilderAssistant builderAssistant;

    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource) {
        super(configuration);
        this.parser = new XPathParser(inputStream, new XMLMapperEntityResolver());
        this.resource = resource;
        this.builderAssistant = new MapperBuilderAssistant(configuration, resource);
    }

    public void parse() {
        configurationElement(parser.evalNode("/mapper"));
        bindMapperForNamespace();
    }

    private void bindMapperForNamespace() {
        String namespace = builderAssistant.getCurrentNamespace();
        Class<?> boundType = Resources.classForName(namespace);
        configuration.addMapper(boundType);
    }

    private void configurationElement(XNode context) {
        String namespace = context.getStringAttribute("namespace");
        builderAssistant.setCurrentNamespace(namespace);
        buildStatementFromContext(context.evalNodes("select|insert|update|delete"));
    }

    private void buildStatementFromContext(List<XNode> xNodes) {
        for (XNode context : xNodes) {
            final XMLStatementBuilder statementParser = new XMLStatementBuilder(configuration, builderAssistant, context);
            statementParser.parseStatementNode();
        }
    }
}
