package ast;

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

    public PixelRangeAstNode(long sourceStart, long sourceEnd, ASTNode x, ASTNode y, ASTNode colour) {
        super(sourceStart, sourceEnd);
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    @Override
    public String toString(){
        return String.format("<PixelRange>%s%s%s%s%s<PixelRange>", x,y,width,height,colour);
    }
}
