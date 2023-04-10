package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.PrintAstNode;
import frontend.tokens.Token;

public class PrintParseRule implements ParseRule<PrintAstNode>{
    @Override
    public PrintAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeToken();
        if (t.getType()!= Token.TokenType.Print)
            pc.throwUnexpectedTokenException(t);

        ASTNode x = new ExprParseRule().parse(pc);

        return new PrintAstNode(t.getTokenStart(),x.getSourceEnd(),x);
    }
}
