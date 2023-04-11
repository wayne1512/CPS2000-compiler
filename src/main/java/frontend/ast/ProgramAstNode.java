package frontend.ast;

public class ProgramAstNode extends ASTNode{
    StatementListAstNode child;

    public ProgramAstNode(long sourceStart, long sourceEnd, StatementListAstNode child){
        super(sourceStart, sourceEnd);
        this.child = child;
    }

    @Override
    public String toString(){
        return "<Program>"+child+"</Program>";
    }
}
