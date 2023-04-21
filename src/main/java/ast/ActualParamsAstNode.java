package ast;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ActualParamsAstNode extends ASTNode{


    public ASTNode[] children;

    public ActualParamsAstNode(long sourceStart, long sourceEnd, ASTNode[] children){
        super(sourceStart, sourceEnd);
        this.children = children;
    }

    @Override
    public String toString(){
        return "<ActualParams>"+Arrays.stream(children).map(ASTNode::toString).collect(Collectors.joining())+"</ActualParams>";
    }
}
