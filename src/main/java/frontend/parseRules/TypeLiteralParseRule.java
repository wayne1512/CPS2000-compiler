package frontend.parseRules;

import exceptions.SyntaxErrorException;
import frontend.ParserContext;
import frontend.ast.BooleanLiteralASTNode;
import frontend.ast.TypeLiteralASTNode;
import frontend.tokens.Token;

public class TypeLiteralParseRule implements ParseRule<TypeLiteralASTNode>{
    @Override
    public TypeLiteralASTNode parse(ParserContext pc) throws SyntaxErrorException{
        Token t = pc.consumeToken();

        TypeLiteralASTNode.Type val = null;

        switch (t.getLexeme()){
            case "float": val= TypeLiteralASTNode.Type.Float; break;
            case "int": val= TypeLiteralASTNode.Type.Int; break;
            case "bool": val= TypeLiteralASTNode.Type.Bool; break;
            case "colour": val= TypeLiteralASTNode.Type.Colour; break;
        }


        return new TypeLiteralASTNode(t.getTokenStart(),t.getTokenEnd(),val);
    }
}
