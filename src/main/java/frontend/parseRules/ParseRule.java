package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.ASTNode;

public interface ParseRule<T extends ASTNode>{
    T parse(ParserContext pc) throws SyntaxErrorException;
}
