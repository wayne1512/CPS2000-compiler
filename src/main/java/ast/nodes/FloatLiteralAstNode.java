package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class FloatLiteralAstNode extends ASTNode{
    private final float val;

    public FloatLiteralAstNode(long sourceStart, long sourceEnd, float val){
        super(sourceStart, sourceEnd);
        this.val = val;
    }

    public float getVal(){
        return val;
    }


    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitFloatLiteralAstNode(this);
    }
}
