package frontend.ast;

import frontend.CompilerSettings;

public class SubExprASTNode extends ASTNode{

    ASTNode child;

    public SubExprASTNode(long sourceStart, long sourceEnd, ASTNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public String toString(){
        if (CompilerSettings.getInstance().verboseASTTree){
            return String.format("<SubExpr>%s</SubExpr>",child);
        } else
            return child.toString();
    }
}
