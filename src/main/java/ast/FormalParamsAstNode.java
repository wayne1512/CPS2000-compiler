package ast;

import backend.Visitor;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FormalParamsAstNode extends ASTNode{


    public FormalParameterAstNode[] children;

    public FormalParamsAstNode(long sourceStart, long sourceEnd, FormalParameterAstNode[] children){
        super(sourceStart, sourceEnd);
        this.children = children;
    }

    @Override
    public String toString(){
        return "<FormalParams>"+Arrays.stream(children).map(ASTNode::toString).collect(Collectors.joining())+"</FormalParams>";
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitFormalParamsAstNode(this);
    }
}
