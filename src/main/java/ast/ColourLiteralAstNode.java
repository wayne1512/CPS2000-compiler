package ast;

import backend.Visitor;

public class ColourLiteralAstNode extends ASTNode{
    private final String val;

    public ColourLiteralAstNode(long sourceStart, long sourceEnd, String val){
        super(sourceStart, sourceEnd);
        this.val = val;
    }

    public String getVal(){
        return val;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitColourLiteralAstNode(this);
    }
}
