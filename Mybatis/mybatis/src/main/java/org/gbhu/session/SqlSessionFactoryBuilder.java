package org.gbhu.session;

import org.gbhu.builder.xml.XMLConfigBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) throws IOException {
        try {
            XMLConfigBuilder parser = new XMLConfigBuilder(inputStream);
            return build(parser.parse());
        } catch (Exception e) {

        }finally {
            //TODO
            inputStream.close();
        }
        return null;
    }

    public SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}
