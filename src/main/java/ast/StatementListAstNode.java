package ast;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StatementListAstNode extends ASTNode{


    public StatementAstNode[] children;

    public StatementListAstNode(long sourceStart, long sourceEnd, StatementAstNode[] children){
        super(sourceStart, sourceEnd);
        this.children = children;
    }

    @Override
    public String toString(){
        return "<Statements>"+Arrays.stream(children).map(ASTNode::toString).collect(Collectors.joining())+"</Statements>";
    }
}
