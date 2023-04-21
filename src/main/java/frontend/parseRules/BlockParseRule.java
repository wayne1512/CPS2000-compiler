package frontend.parseRules;

import ast.BlockAstNode;
import ast.StatementListAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class BlockParseRule implements ParseRule<BlockAstNode>{

    @Override
    public BlockAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token openCurly = pc.consumeTokenSkipComments();

        if (openCurly.getType() != Token.TokenType.CurlyBracOpen)
            //error
            pc.throwUnexpectedTokenException(openCurly);

        StatementListAstNode child = new StatementListParseRule().parse(pc);

        Token closeCurly = pc.consumeTokenSkipComments();

        if (closeCurly.getType() != Token.TokenType.CurlyBracClose)
            //error
            pc.throwUnexpectedTokenException(closeCurly);



        return new BlockAstNode(openCurly.getTokenStart(),closeCurly.getTokenEnd(),child);
    }
}
