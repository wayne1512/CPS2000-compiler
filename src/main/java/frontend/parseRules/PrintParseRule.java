package frontend.parseRules;

import ast.ASTNode;
import ast.nodes.PrintAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class PrintParseRule implements ParseRule<PrintAstNode>{
    @Override
    public PrintAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeTokenSkipComments();
        if (t.getType()!= Token.TokenType.Print)
            pc.throwUnexpectedTokenException(t);

        ASTNode x = new ExprParseRule().parse(pc);

        return new PrintAstNode(t.getTokenStart(),x.getSourceEnd(),x);
    }
}
