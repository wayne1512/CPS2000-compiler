package frontend.parseRules;

import frontend.ParserContext;
import frontend.ast.ASTNode;

public interface ParseRule<T extends ASTNode>{
    T parse(ParserContext pc);
}
