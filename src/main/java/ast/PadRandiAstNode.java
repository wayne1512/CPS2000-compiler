package ast;

import backend.Visitor;

public class PadRandiAstNode extends ASTNode{


    public ASTNode x;

    public PadRandiAstNode(long sourceStart, long sourceEnd, ASTNode x){
        super(sourceStart, sourceEnd);
        this.x = x;
    }

    @Override
    public String toString(){
        return String.format("<PadRandI>%s</PadRandI>", x);
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitPadRandiAstNode(this);
    }
}
