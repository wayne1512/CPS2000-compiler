package exceptions;

public class SyntaxErrorException extends Exception{

    long from,to;

    public SyntaxErrorException(String message, long from, long to){
        super(message);
        this.from = from;
        this.to = to;
    }

    public long getFrom(){
        return from;
    }

    public long getTo(){
        return to;
    }

    @Override
    public String getMessage(){
        return super.getMessage() + " in position ("+from +"-" + to +")";
    }
}
