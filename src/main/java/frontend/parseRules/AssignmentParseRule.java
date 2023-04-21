package frontend.parseRules;

import ast.ASTNode;
import ast.AssignmentAstNode;
import ast.IdentifierAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class AssignmentParseRule implements ParseRule<AssignmentAstNode>{
    @Override
    public AssignmentAstNode parse(ParserContext pc) throws SyntaxErrorException{

        IdentifierAstNode identifier = new IdentifierParseRule().parse(pc);

        Token equal = pc.consumeTokenSkipComments();
        if (equal.getType()!= Token.TokenType.Equals)
            pc.throwUnexpectedTokenException(equal);

        ASTNode expr = new ExprParseRule().parse(pc);

        return new AssignmentAstNode(identifier.getSourceStart(),expr.getSourceEnd(),identifier,expr);
    }
}
