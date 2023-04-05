package frontend.ast;

public class BinaryOpASTNode extends ASTNode{


    OpType opType;

    //temporarily allows any node
    ASTNode left;
    ASTNode right;

    public BinaryOpASTNode(long sourceStart, long sourceEnd, OpType opType, ASTNode left, ASTNode right){
        super(sourceStart, sourceEnd);
        this.opType = opType;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString(){
        return String.format("<BinaryOp type = \"%s\">%s%s</BinaryOp>", opType.humanReadableName, left, right);
    }


    public enum OpType{
        mul("*"),
        div("/"),

        add("+"),

        sub("-"),

        //relops
        LT("<"),
        GT(">"),
        EQ("=="),
        NE("!="),
        LTE("<="),
        GTE(">=");


        final String humanReadableName;

        OpType(String humanReadableName){
            this.humanReadableName = humanReadableName;
        }
    }


}
