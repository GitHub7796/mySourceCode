package org.gbhu.builder.xml;

import org.gbhu.builder.BaseBuilder;
import org.gbhu.builder.MapperBuilderAssistant;
import org.gbhu.mapping.SqlCommandType;
import org.gbhu.mapping.SqlSource;
import org.gbhu.mapping.StatementType;
import org.gbhu.parsing.XNode;
import org.gbhu.scripting.LanguageDriver;
import org.gbhu.scripting.xmltag.XMLScriptBuilder;
import org.gbhu.session.Configuration;

import java.util.Locale;

public class XMLStatementBuilder extends BaseBuilder {

    private final MapperBuilderAssistant builderAssistant;
    private final XNode context;


    public XMLStatementBuilder(Configuration configuration, MapperBuilderAssistant mapperBuilderAssistant, XNode context) {
        super(configuration);
        this.builderAssistant = mapperBuilderAssistant;
        this.context = context;
    }

    public void parseStatementNode() {
        // listUser
        String id = context.getStringAttribute("id");
        // SELECT
        String nodeName = context.getNode().getNodeName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
        // PREPARED TODO 先省略
        StatementType statementType = StatementType.PREPARED;

        // select * from user
        // 此处进行了省略，
//        String lang = context.getStringAttribute("lang");
//        SqlSource sqlSource = new XMLScriptBuilder(configuration,context).parseScriptNode();
        // TODO 源代码过于复杂，略
        // 获取xml文本内容
        String sqlSource = "SELECT * FROM USER";
        builderAssistant.addMappedStatement(id, sqlCommandType, statementType, sqlSource );
    }
}
