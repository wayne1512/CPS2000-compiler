package frontend.parseRules;

import ast.ASTNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;

public interface ParseRule<T extends ASTNode>{
    T parse(ParserContext pc) throws SyntaxErrorException;
}
