package ast;

import backend.Visitor;

public class TypeLiteralAstNode extends ASTNode{
    private final Type val;

    public TypeLiteralAstNode(long sourceStart, long sourceEnd, Type val){
        super(sourceStart, sourceEnd);
        this.val = val;
    }

    public Type getVal(){
        return val;
    }

    @Override
    public String toString(){
        return String.format("<Type>%s</Type>", val);
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitTypeLiteralAstNode(this);
    }

    public enum Type{
        Float, Int, Bool, Colour
    }
}
