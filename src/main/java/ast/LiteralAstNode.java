package ast;

import backend.Visitor;
import frontend.CompilerSettings;

public class LiteralAstNode extends ASTNode{

    ASTNode child;

    public LiteralAstNode(long sourceStart, long sourceEnd, ASTNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public String toString(){
        if (CompilerSettings.getInstance().verboseASTTree){
            return String.format("<Literal>%s</Literal>", child);
        } else
            return child.toString();
    }

    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitLiteralAstNode(this);
    }
}
