package org.gbhu.scripting.defaults;

import org.gbhu.builder.SqlSourceBuilder;
import org.gbhu.mapping.BoundSql;
import org.gbhu.mapping.SqlSource;
import org.gbhu.scripting.xmltag.MixedSqlNode;
import org.gbhu.scripting.xmltag.SqlNode;
import org.gbhu.session.Configuration;

public class RawSqlSource implements SqlSource {



    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return null;
    }
}
