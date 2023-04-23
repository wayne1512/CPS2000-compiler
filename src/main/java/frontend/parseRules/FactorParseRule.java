package frontend.parseRules;

import ast.ASTNode;
import ast.nodes.FactorAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class FactorParseRule implements ParseRule<FactorAstNode>{
    @Override
    public FactorAstNode parse(ParserContext pc) throws SyntaxErrorException{

        Token t = pc.lookaheadSkipComments(0);

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
            case Identifier: {
                //check if it is a variable or a function call
                Token t1 = pc.lookaheadSkipComments(1);

                if (t1.getType() == Token.TokenType.BracOpen)
                    //function
                    child = new FunctionCallParseRule().parse(pc);
                else
                    //variable
                    child = new IdentifierParseRule().parse(pc);
                break;
            }
            case BracOpen:
                child = new SubExprParseRule().parse(pc);
                break;
            case Not:
            case Subtract:
                child = new UnaryParseRule().parse(pc);
                break;
            case PadRandI:
                child = new PadRandIParseRule().parse(pc);
                break;

        }

        if (child == null)
            pc.throwUnexpectedTokenException(t);
        return new FactorAstNode(t.getTokenStart(), t.getTokenEnd(), child);

    }
}
