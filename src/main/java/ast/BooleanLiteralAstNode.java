package ast;

import backend.Visitor;

public class BooleanLiteralAstNode extends ASTNode{
    private final boolean val;

    public BooleanLiteralAstNode(long sourceStart, long sourceEnd, boolean val){
        super(sourceStart, sourceEnd);
        this.val = val;
    }

    public boolean getVal(){
        return val;
    }

    @Override
    public String toString(){
        return String.format("<Bool>%b</Bool>", val);
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitBooleanLiteralAstNode(this);
    }
}
