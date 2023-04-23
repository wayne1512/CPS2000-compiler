package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class BooleanLiteralAstNode extends ASTNode{
    final boolean val;

    public BooleanLiteralAstNode(long sourceStart, long sourceEnd, boolean val){
        super(sourceStart, sourceEnd);
        this.val = val;
    }

    public boolean getVal(){
        return val;
    }


    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitBooleanLiteralAstNode(this);
    }
}
