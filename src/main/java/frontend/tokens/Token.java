package frontend.tokens;

public class Token{


    TokenType type;
    long tokenStart, tokenEnd;
    String lexeme;

    public Token(TokenType type, long tokenStart, long tokenEnd, String lexeme){
        this.type = type;
        this.tokenStart = tokenStart;
        this.tokenEnd = tokenEnd;
        this.lexeme = lexeme;
    }

    @Override
    public String toString(){
        return lexeme + " - " + this.getClass().getSimpleName();
    }

    public long getTokenStart(){
        return tokenStart;
    }

    public long getTokenEnd(){
        return tokenEnd;
    }

    public String getLexeme(){
        return lexeme;
    }

    public TokenType getType(){
        return type;
    }

    public enum TokenType{

        //literals
        Float,
        Int,
        Colour,

        //reserved words
        True,
        False,
        Retrn,
        FloatType,
        IntegerType,
        BoolType,
        ColourType,

        //identifier
        Identifier,


        //operations
        Divide,
        Multiply,
        Add,
        Subtract,
        And,
        Or,
        Not,

        //relops
        LT,
        GT,
        EQ,
        NE,
        LTE,
        GTE,

        //punctuation
        BracOpen,
        BracClose,
        CurlyBracOpen,
        CurlyBracClose,
        SemiColon,
        Colon,
        Comma,


        //vm operations
        PadHeight,
        PadRandI,
        PadRead,
        PadWidth,
        Print,
        Delay,
        PixelRead,
        Pixel

    }
}
