package ast;

import backend.Visitor;

public class PrintAstNode extends ASTNode{


    public ASTNode x;

    public PrintAstNode(long sourceStart, long sourceEnd, ASTNode x){
        super(sourceStart, sourceEnd);
        this.x = x;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitPrintAstNode(this);
    }
}
