package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class IntegerLiteralAstNode extends ASTNode{
    private final int val;

    public IntegerLiteralAstNode(long sourceStart, long sourceEnd, int val){
        super(sourceStart, sourceEnd);
        this.val = val;
    }

    public int getVal(){
        return val;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitIntegerLiteralAstNode(this);
    }
}
