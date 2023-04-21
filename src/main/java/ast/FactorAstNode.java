package ast;

import frontend.CompilerSettings;

public class FactorAstNode extends ASTNode{
    ASTNode child;

    public FactorAstNode(long sourceStart, long sourceEnd, ASTNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    public String toString(){
        if (CompilerSettings.getInstance().verboseASTTree){
            return String.format("<Factor>%s</Factor>", child);
        } else
            return child.toString();
    }

}
