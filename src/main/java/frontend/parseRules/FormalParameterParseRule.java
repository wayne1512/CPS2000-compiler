package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.*;
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
