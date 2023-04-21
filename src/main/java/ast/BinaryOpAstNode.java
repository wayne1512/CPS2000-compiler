package ast;

import backend.Visitor;

public class BinaryOpAstNode extends ASTNode{


    OpType opType;

    //temporarily allows any node
    ASTNode left;
    ASTNode right;

    public BinaryOpAstNode(long sourceStart, long sourceEnd, OpType opType, ASTNode left, ASTNode right){
        super(sourceStart, sourceEnd);
        this.opType = opType;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString(){
        return String.format("<BinaryOp type = \"%s\">%s%s</BinaryOp>", opType.humanReadableName, left, right);
    }


    @Override
    public <R> R acceptVisitor(Visitor<R> visitor){
        return visitor.visitBinaryOpAstNode(this);
    }

    public enum OpType{
        mul("*"),
        div("/"),

        add("+"),

        sub("-"),
        and("&"),
        or("|"),

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
