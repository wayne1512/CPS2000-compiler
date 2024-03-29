package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class PadReadAstNode extends ASTNode{


    public ASTNode x;
    public ASTNode y;

    public PadReadAstNode(long sourceStart, long sourceEnd, ASTNode x, ASTNode y){
        super(sourceStart, sourceEnd);
        this.x = x;
        this.y = y;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitPadReadAstNode(this);
    }
}
