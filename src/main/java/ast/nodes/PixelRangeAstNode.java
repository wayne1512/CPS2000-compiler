package ast.nodes;

import ast.ASTNode;
import backend.Visitor;

public class PixelRangeAstNode extends ASTNode{


    public ASTNode x;
    public ASTNode y;
    public ASTNode width;
    public ASTNode height;
    public ASTNode colour;

    public PixelRangeAstNode(long sourceStart, long sourceEnd, ASTNode x, ASTNode y, ASTNode width, ASTNode height, ASTNode colour) {
        super(sourceStart, sourceEnd);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colour = colour;
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitPixelRangeAstNode(this);
    }
}
