package frontend.parseRules;

import ast.ASTNode;
import ast.LiteralAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class LiteralParseRule implements ParseRule<LiteralAstNode>{
    @Override
    public LiteralAstNode parse(ParserContext pc) throws SyntaxErrorException{

        Token t = pc.lookaheadSkipComments(0);

        ASTNode child = null;

        switch (t.getType()) {
            case True:
            case False:
                child = new BooleanLiteralParseRule().parse(pc);
                break;
            case Int:
                child = new IntegerLiteralParseRule().parse(pc);
                break;
            case Float:
                child = new FloatLiteralParseRule().parse(pc);
                break;
            case Colour:
                child = new TypeLiteralParseRule().parse(pc);
                break;
            case PadWidth:
                child = new PadWidthParseRule().parse(pc);
                break;
            case PadHeight:
                child = new PadHeightParseRule().parse(pc);
                break;
            case PadRead:
                child = new PadReadParseRule().parse(pc);

        }

        if (child == null)
            pc.throwUnexpectedTokenException(t);

        return new LiteralAstNode(t.getTokenStart(), t.getTokenEnd(), child);

    }
}
