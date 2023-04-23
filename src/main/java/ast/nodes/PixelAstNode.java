package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class PixelAstNode extends ASTNode{


    public ASTNode x;
    public ASTNode y;
    public ASTNode colour;

    public PixelAstNode(long sourceStart, long sourceEnd, ASTNode x, ASTNode y, ASTNode colour) {
        super(sourceStart, sourceEnd);
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitPixelAstNode(this);
    }
}
