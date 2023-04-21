package ast;

public abstract class ASTNode{
    //start and end in source code - used to debug compiler and error if needed
    long sourceStart, sourceEnd;

    public ASTNode(long sourceStart, long sourceEnd){
        this.sourceStart = sourceStart;
        this.sourceEnd = sourceEnd;
    }

    public long getSourceStart(){
        return sourceStart;
    }

    public long getSourceEnd(){
        return sourceEnd;
    }

    @Override
    public abstract String toString();
}
