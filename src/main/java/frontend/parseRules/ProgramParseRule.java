package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.BlockAstNode;
import frontend.ast.StatementListAstNode;
import frontend.tokens.Token;

public class ProgramParseRule implements ParseRule<ASTNode>{

    @Override
    public ASTNode parse(ParserContext pc) throws SyntaxErrorException{

        StatementListAstNode child = new StatementListParseRule().parse(pc);

        return new BlockAstNode(child.getSourceStart(),child.getSourceEnd(),child);
    }
}
