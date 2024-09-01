package org.gbhu.scripting.xmltag;

import org.gbhu.builder.BaseBuilder;
import org.gbhu.mapping.SqlSource;
import org.gbhu.parsing.XNode;
import org.gbhu.scripting.defaults.RawSqlSource;
import org.gbhu.session.Configuration;

public class XMLScriptBuilder extends BaseBuilder {
    private final XNode context;
    public XMLScriptBuilder(Configuration configuration, XNode context) {
        super(configuration);
        this.context = context;

    }
    public SqlSource parseScriptNode() {
        MixedSqlNode rootSqlNode = parseDynamicTags(context);
        return null;
    }

    private MixedSqlNode parseDynamicTags(XNode node) {
        return null;
    }

}
