package frontend;

import exceptions.SyntaxErrorException;
import frontend.tokens.Token;
import frontend.tokens.Token.TokenType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LexerTest{


    Random rnd = new Random();

    static Stream<Arguments> getTestMap(){

        LinkedHashMap<String, TokenType> singleTokenTestMap = new LinkedHashMap<>();

        singleTokenTestMap.put("1.2", TokenType.Float);
        singleTokenTestMap.put("1", TokenType.Int);
        singleTokenTestMap.put("#ff0000", TokenType.Colour);

        singleTokenTestMap.put("true", TokenType.True);
        singleTokenTestMap.put("false", TokenType.False);
        singleTokenTestMap.put("float", TokenType.FloatType);
        singleTokenTestMap.put("int", TokenType.IntegerType);
        singleTokenTestMap.put("bool", TokenType.BoolType);
        singleTokenTestMap.put("colour", TokenType.ColourType);
        singleTokenTestMap.put("and", TokenType.And);
        singleTokenTestMap.put("or", TokenType.Or);
        singleTokenTestMap.put("not", TokenType.Not);
        singleTokenTestMap.put("return", TokenType.Retrn);
        singleTokenTestMap.put("let", TokenType.Let);


        singleTokenTestMap.put("a", TokenType.Identifier);
        singleTokenTestMap.put("var", TokenType.Identifier);
        singleTokenTestMap.put("number_", TokenType.Identifier);
        singleTokenTestMap.put("i", TokenType.Identifier);
        singleTokenTestMap.put("i1", TokenType.Identifier);
        singleTokenTestMap.put("i1_", TokenType.Identifier);
        singleTokenTestMap.put("i_1", TokenType.Identifier);


        singleTokenTestMap.put("/", TokenType.Divide);
        singleTokenTestMap.put("*", TokenType.Multiply);
        singleTokenTestMap.put("+", TokenType.Add);
        singleTokenTestMap.put("-", TokenType.Subtract);
        singleTokenTestMap.put(">", TokenType.GT);
        singleTokenTestMap.put("<", TokenType.LT);
        singleTokenTestMap.put("==", TokenType.EQ);
        singleTokenTestMap.put("!=", TokenType.NE);
        singleTokenTestMap.put("<=", TokenType.LTE);
        singleTokenTestMap.put(">=", TokenType.GTE);



        singleTokenTestMap.put("(", TokenType.BracOpen);
        singleTokenTestMap.put(")", TokenType.BracClose);
        singleTokenTestMap.put("{", TokenType.CurlyBracOpen);
        singleTokenTestMap.put("}", TokenType.CurlyBracClose);
        singleTokenTestMap.put(";", TokenType.SemiColon);
        singleTokenTestMap.put(":", TokenType.Colon);
        singleTokenTestMap.put(",", TokenType.Comma);
        singleTokenTestMap.put("=", TokenType.Equals);

        singleTokenTestMap.put("__print", TokenType.Print);
        singleTokenTestMap.put("__width", TokenType.PadWidth);
        singleTokenTestMap.put("__height", TokenType.PadHeight);
        singleTokenTestMap.put("__read", TokenType.PadRead);
        singleTokenTestMap.put("__randi", TokenType.PadRandI);
        singleTokenTestMap.put("__delay", TokenType.Delay);
        singleTokenTestMap.put("__pixel", TokenType.Pixel);
        singleTokenTestMap.put("__pixelr", TokenType.PixelRange);

        return singleTokenTestMap.entrySet().stream().map(set -> Arguments.of(set.getKey(), set.getValue()));

    }


    @ParameterizedTest
    @MethodSource("getTestMap")
    void singleToken(String input, TokenType expectedType) throws SyntaxErrorException{
        Lexer l = new Lexer(new MockCharProvider(input));

        Token t = l.nextToken();

        assertEquals(expectedType, t.getType(), "token type was wrong");
        assertEquals(1, t.getTokenStart(), "token start was wrong");
        assertEquals(input.length(), t.getTokenEnd(), "token end was wrong");
        assertEquals(input, t.getLexeme(), "lexeme was wrong");
    }

    @ParameterizedTest
    @MethodSource("getTestMap")
    void singleTokenWithPadding(String input, TokenType expectedType) throws SyntaxErrorException{

        String leftPadding = "";
        String rightPadding = "";
        int paddingLeft = rnd.nextInt(3);
        int paddingRight = rnd.nextInt(3);

        for (int i = 0; i < paddingLeft; i++){
            leftPadding += " ";
        }

        for (int i = 0; i < paddingRight; i++){
            rightPadding += " ";
        }

        String paddedInput = leftPadding + input + rightPadding;

        Lexer l = new Lexer(new MockCharProvider(paddedInput));

        Token t = l.nextToken();

        assertEquals(expectedType, t.getType(), "token type was wrong");
        assertEquals(paddingLeft + 1, t.getTokenStart(), "token start was wrong");
        assertEquals(paddingLeft + input.length(), t.getTokenEnd(), "token end was wrong");
        assertEquals(input, t.getLexeme(), "lexeme was wrong");
    }


    public class MockCharProvider implements CharacterProvider{


        String s;
        int ptr = 0;

        public MockCharProvider(String s){
            this.s = s;
        }

        @Override
        public Character next(){
            if (ptr <= s.length() - 1)
                return s.charAt(ptr++);
            else
                return null;
        }

        @Override
        public long getPointer(){
            return ptr;
        }

        @Override
        public void rewind(){
            ptr--;
        }

        @Override
        public void close() throws IOException{

        }
    }
}