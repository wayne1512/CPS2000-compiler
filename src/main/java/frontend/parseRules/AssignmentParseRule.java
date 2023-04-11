package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.AssignmentAstNode;
import frontend.ast.IdentifierAstNode;
import frontend.tokens.Token;

public class AssignmentParseRule implements ParseRule<AssignmentAstNode>{
    @Override
    public AssignmentAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token ident = pc.consumeToken();
        if (ident.getType()!= Token.TokenType.Identifier)
            pc.throwUnexpectedTokenException(ident);

        IdentifierAstNode identifier = new IdentifierParseRule().parse(pc);

        Token equal = pc.consumeToken();
        if (equal.getType()!= Token.TokenType.Equals)
            pc.throwUnexpectedTokenException(equal);

        ASTNode expr = new ExprParseRule().parse(pc);

        return new AssignmentAstNode(ident.getTokenStart(),expr.getSourceEnd(),identifier,expr);
    }
}
