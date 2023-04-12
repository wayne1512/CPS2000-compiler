package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.FunDeclAstNode;
import frontend.ast.StatementAstNode;
import frontend.tokens.Token;
import org.apache.commons.lang3.NotImplementedException;

public class StatementParseRule implements ParseRule<StatementAstNode>{
    @Override
    public StatementAstNode parse(ParserContext pc) throws SyntaxErrorException{

        Token t = pc.lookahead(0);

        ASTNode child = null;

        switch (t.getType()) {
            case Let:
                child = new VarDeclParseRule().parse(pc);
                consumeSemicolon(pc);
                break;
            case Identifier:
                child = new AssignmentParseRule().parse(pc);
                consumeSemicolon(pc);
                break;
            case Print:
                child = new PrintParseRule().parse(pc);
                consumeSemicolon(pc);
                break;
            case Delay:
                child = new DelayParseRule().parse(pc);
                consumeSemicolon(pc);
                break;
            case Pixel:
                child = new PixelParseRule().parse(pc);
                consumeSemicolon(pc);
                break;
            case PixelRange:
                child = new PixelRangeParseRule().parse(pc);
                consumeSemicolon(pc);
                break;
            case Retrn:
                child = new ReturnParseRule().parse(pc);
                consumeSemicolon(pc);
                break;
            case CurlyBracOpen:
                child = new BlockParseRule().parse(pc);
                break;
            case If:
                child = new IfParseRule().parse(pc);
                break;
            case For:
                child = new ForParseRule().parse(pc);
                break;
            case While:
                child = new WhileParseRule().parse(pc);
                break;
            case Fun:
                child = new FunDeclParseRule().parse(pc);
                break;
        }

        if (child == null)
            pc.throwUnexpectedTokenException(t);

        return new StatementAstNode(t.getTokenStart(), t.getTokenEnd(), child);

    }

    private static Token consumeSemicolon(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeToken();
        if (t.getType() != Token.TokenType.SemiColon)
            pc.throwUnexpectedTokenException(t);

        return t;
    }
}
