package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.*;
import frontend.tokens.Token;

public class VarDeclParseRule implements ParseRule<VarDeclAstNode>{
    @Override
    public VarDeclAstNode parse(ParserContext pc) throws SyntaxErrorException{

        Token let = pc.consumeToken();
        if (let.getType() != Token.TokenType.Let)
            pc.throwUnexpectedTokenException(let);

        IdentifierAstNode identifier = new IdentifierParseRule().parse(pc);

        Token colon = pc.consumeToken();
        if (colon.getType()!= Token.TokenType.Colon)
            pc.throwUnexpectedTokenException(colon);

        TypeLiteralAstNode type = new TypeLiteralParseRule().parse(pc);

        Token equal = pc.consumeToken();
        if (equal.getType()!= Token.TokenType.Equals)
            pc.throwUnexpectedTokenException(equal);

        ASTNode expr = new ExprParseRule().parse(pc);

        return new VarDeclAstNode(let.getTokenStart(),expr.getSourceEnd(),identifier,type,expr);
    }
}
