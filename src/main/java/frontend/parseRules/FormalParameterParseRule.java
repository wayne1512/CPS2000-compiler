package frontend.parseRules;

import ast.nodes.FormalParameterAstNode;
import ast.nodes.IdentifierAstNode;
import ast.nodes.TypeLiteralAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class FormalParameterParseRule implements ParseRule<FormalParameterAstNode>{
    @Override
    public FormalParameterAstNode parse(ParserContext pc) throws SyntaxErrorException{

        IdentifierAstNode identifier = new IdentifierParseRule().parse(pc);

        Token colon = pc.consumeTokenSkipComments();
        if (colon.getType()!= Token.TokenType.Colon)
            pc.throwUnexpectedTokenException(colon);

        TypeLiteralAstNode type = new TypeLiteralParseRule().parse(pc);

        return new FormalParameterAstNode(identifier.getSourceStart(),type.getSourceEnd(),identifier,type);
    }
}
