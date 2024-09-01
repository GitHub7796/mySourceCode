package org.gbhu.scripting.xmltag;

import java.util.List;

public class MixedSqlNode implements SqlNode {
    protected final List<SqlNode> contents;

    public MixedSqlNode(List<SqlNode> contents) {
        this.contents = contents;
    }

}
