package exceptions;

public class SyntaxErrorException extends Exception{

    long from, to;
    String fromFormatted, toFormatted;

    public SyntaxErrorException(LineNumberProvider lineNumberProvider,String message, long from, long to){
        super(message);
        this.from = from;
        this.to = to;
        fromFormatted = lineNumberProvider.get(from);
        toFormatted = lineNumberProvider.get(to);
    }

    public long getFrom(){
        return from;
    }

    public long getTo(){
        return to;
    }

    @Override
    public String getMessage(){
        return super.getMessage() + " in position (" + fromFormatted + "-" + toFormatted + ")";
    }
}
