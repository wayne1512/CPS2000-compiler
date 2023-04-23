package frontend.parseRules;

import ast.nodes.ProgramAstNode;
import ast.nodes.StatementListAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;

public class ProgramParseRule implements ParseRule<ProgramAstNode>{

    @Override
    public ProgramAstNode parse(ParserContext pc) throws SyntaxErrorException{

        StatementListAstNode child = new StatementListParseRule().parse(pc);

        return new ProgramAstNode(child.getSourceStart(),child.getSourceEnd(),child);
    }
}
