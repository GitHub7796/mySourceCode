package org.gbhu.builder.xml;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;

public class XMLMapperEntityResolver implements EntityResolver {
    private static final String MYBATIS_CONFIG_DTD = "org/gbhu/builder/xml/mybatis-config.xsd";
    private static final String MYBATIS_MAPPER_DTD = "org/gbhu/builder/xml/mybatis-mapper.xsd";

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        return null;
    }
}
