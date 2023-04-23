package frontend.parseRules;

import ast.Type;
import ast.nodes.TypeLiteralAstNode;
import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.tokens.Token;

public class TypeLiteralParseRule implements ParseRule<TypeLiteralAstNode>{
    @Override
    public TypeLiteralAstNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeTokenSkipComments();

        Type val = null;

        switch (t.getLexeme()) {
            case "float":
                val = Type.Float;
                break;
            case "int":
                val = Type.Int;
                break;
            case "bool":
                val = Type.Bool;
                break;
            case "colour":
                val = Type.Colour;
                break;
        }


        return new TypeLiteralAstNode(t.getTokenStart(), t.getTokenEnd(), val);
    }
}
