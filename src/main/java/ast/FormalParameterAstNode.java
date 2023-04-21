package ast;

public class FormalParameterAstNode extends ASTNode{
    IdentifierAstNode identifier;
    ASTNode type;
    public FormalParameterAstNode(long sourceStart, long sourceEnd, IdentifierAstNode identifier, ASTNode type) {
        super(sourceStart, sourceEnd);
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public String toString(){
        return String.format("<FormalParam>%s%s</FormalParam>", identifier,type);
    }

}
