package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.*;
import frontend.tokens.Token;
import org.apache.commons.lang3.NotImplementedException;

public class LiteralParseRule implements ParseRule<LiteralASTNode>{
    @Override
    public LiteralASTNode parse(ParserContext pc) throws SyntaxErrorException{

        Token t = pc.lookahead(0);

        ASTNode child = null;

        switch (t.getType()){
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
            case PadHeight:
            case PadRandI:
                //todo
                throw new NotImplementedException("todo");

        }

        if (child == null)
            pc.throwUnexpectedTokenException(t);

        return new LiteralASTNode(t.getTokenStart(),t.getTokenEnd(),child);

    }
}
