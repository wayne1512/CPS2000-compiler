package ast;

import backend.Visitor;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ActualParamsAstNode extends ASTNode{


    public ASTNode[] children;

    public ActualParamsAstNode(long sourceStart, long sourceEnd, ASTNode[] children){
        super(sourceStart, sourceEnd);
        this.children = children;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitActualParamsAstNode(this);
    }
}
