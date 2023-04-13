package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;
import frontend.ast.BlockAstNode;
import frontend.ast.ProgramAstNode;
import frontend.ast.StatementListAstNode;
import frontend.tokens.Token;

public class ProgramParseRule implements ParseRule<ProgramAstNode>{

    @Override
    public ProgramAstNode parse(ParserContext pc) throws SyntaxErrorException{

        StatementListAstNode child = new StatementListParseRule().parse(pc);

        return new ProgramAstNode(child.getSourceStart(),child.getSourceEnd(),child);
    }
}
