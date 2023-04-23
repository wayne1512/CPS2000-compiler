package frontend.parseRules;

import ast.ASTNode;
import ast.nodes.IdentifierAstNode;
import ast.nodes.TypeLiteralAstNode;
import ast.nodes.VarDeclAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class VarDeclParseRule implements ParseRule<VarDeclAstNode>{
    @Override
    public VarDeclAstNode parse(ParserContext pc) throws SyntaxErrorException{

        Token let = pc.consumeTokenSkipComments();
        if (let.getType() != Token.TokenType.Let)
            pc.throwUnexpectedTokenException(let);

        IdentifierAstNode identifier = new IdentifierParseRule().parse(pc);

        Token colon = pc.consumeTokenSkipComments();
        if (colon.getType()!= Token.TokenType.Colon)
            pc.throwUnexpectedTokenException(colon);

        TypeLiteralAstNode type = new TypeLiteralParseRule().parse(pc);

        Token equal = pc.consumeTokenSkipComments();
        if (equal.getType()!= Token.TokenType.Equals)
            pc.throwUnexpectedTokenException(equal);

        ASTNode expr = new ExprParseRule().parse(pc);

        return new VarDeclAstNode(let.getTokenStart(),expr.getSourceEnd(),identifier,type,expr);
    }
}
