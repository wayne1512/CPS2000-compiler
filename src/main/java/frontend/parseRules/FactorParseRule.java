package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.FactorAstNode;
import frontend.tokens.Token;
import org.apache.commons.lang3.NotImplementedException;

public class FactorParseRule implements ParseRule<FactorAstNode>{
    @Override
    public FactorAstNode parse(ParserContext pc) throws SyntaxErrorException{

        Token t = pc.lookahead(0);

        ASTNode child = null;

        switch (t.getType()) {
            case True:
            case False:
            case Int:
            case Float:
            case Colour:
            case PadWidth:
            case PadHeight:
            case PadRead:
                child = new LiteralParseRule().parse(pc);
                break;
            case Identifier:
                child = new IdentifierParseRule().parse(pc);
                break;
            case BracOpen:
                child = new SubExprParseRule().parse(pc);
                break;
            case PadRandI:
                throw new NotImplementedException("todo");

        }

        if (child == null)
            pc.throwUnexpectedTokenException(t);
        return new FactorAstNode(t.getTokenStart(), t.getTokenEnd(), child);

    }
}
