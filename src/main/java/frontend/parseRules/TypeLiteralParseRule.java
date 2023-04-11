package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.TypeLiteralAstNode;
import frontend.tokens.Token;

public class TypeLiteralParseRule implements ParseRule<TypeLiteralAstNode>{
    @Override
    public TypeLiteralAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeToken();

        TypeLiteralAstNode.Type val = null;

        switch (t.getLexeme()) {
            case "float":
                val = TypeLiteralAstNode.Type.Float;
                break;
            case "int":
                val = TypeLiteralAstNode.Type.Int;
                break;
            case "bool":
                val = TypeLiteralAstNode.Type.Bool;
                break;
            case "colour":
                val = TypeLiteralAstNode.Type.Colour;
                break;
        }


        return new TypeLiteralAstNode(t.getTokenStart(), t.getTokenEnd(), val);
    }
}
