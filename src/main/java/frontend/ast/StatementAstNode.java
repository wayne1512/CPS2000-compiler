package frontend.ast;

import frontend.CompilerSettings;

public class StatementAstNode extends ASTNode{

    ASTNode child;

    public StatementAstNode(long sourceStart, long sourceEnd, ASTNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public String toString(){
        if (CompilerSettings.getInstance().verboseASTTree){
            return String.format("<Statement>%s</Statement>", child);
        } else
            return child.toString();
    }
}
