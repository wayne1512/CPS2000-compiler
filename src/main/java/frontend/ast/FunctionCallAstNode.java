package frontend.ast;

public class FunctionCallAstNode extends ASTNode{
    IdentifierAstNode identifier;
    ActualParamsAstNode params;

    public FunctionCallAstNode(long sourceStart, long sourceEnd, IdentifierAstNode identifier, ActualParamsAstNode params){
        super(sourceStart, sourceEnd);
        this.identifier = identifier;
        this.params = params;
    }

    @Override
    public String toString(){
        return String.format("<FunctionCall>%s%s</FunctionCall>", identifier,params);
    }

}
