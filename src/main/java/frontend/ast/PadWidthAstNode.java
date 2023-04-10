package frontend.ast;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.parseRules.PadWidthParseRule;

public class PadWidthAstNode extends ASTNode {
    public PadWidthAstNode(long sourceStart, long sourceEnd) {
        super(sourceStart, sourceEnd);
    }

    @Override
    public String toString() {
        return "<PadWidth/>";
    }
}
