package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.*;
import frontend.tokens.Token;

public class BlockParseRule implements ParseRule<ASTNode>{

    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token openCurly = pc.consumeToken();

        if (openCurly.getType() != Token.TokenType.CurlyBracOpen)
            //error
            pc.throwUnexpectedTokenException(openCurly);

        StatementListAstNode child = new StatementListParseRule().parse(pc);

        Token closeCurly = pc.consumeToken();

        if (closeCurly.getType() != Token.TokenType.CurlyBracClose)
            //error
            pc.throwUnexpectedTokenException(closeCurly);



        return new BlockAstNode(openCurly.getTokenStart(),closeCurly.getTokenEnd(),child);
    }
}
