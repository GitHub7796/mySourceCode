package org.gbhu.session;

import org.gbhu.builder.xml.XMLConfigBuilder;

import java.io.IOException;
import java.io.InputStream;

public  class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) throws IOException {
        try {
            // 构造 XML config 构造器，内含解析器对象
            XMLConfigBuilder parser = new XMLConfigBuilder(inputStream);
            return build(parser.parse());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //TODO
            inputStream.close();
        }
        return null;
    }

    public SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}
