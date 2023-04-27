package backend;

import ast.ASTNode;
import ast.Type;
import ast.nodes.*;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;

public class CodeGenerationVisitor implements Visitor<CodeGenerationVisitor.VisitResult>{


    //to be used as a symbol table
    Stack<List<String>> memory = new Stack<>();

    @Override
    public VisitResult visitActualParamsAstNode(ActualParamsAstNode n){
        return null;
    }

    @Override
    public VisitResult visitAssignmentAstNode(AssignmentAstNode n){
        return null;
    }

    @Override
    public VisitResult visitBinaryOpAstNode(BinaryOpAstNode n){
        VisitResult visitResultR = n.right.acceptVisitor(this);
        VisitResult visitResultL = n.left.acceptVisitor(this);

        List<String> instructions = new ArrayList<>();

        instructions.addAll(Arrays.asList(visitResultR.instructions));
        instructions.addAll(Arrays.asList(visitResultL.instructions));


        String op = " ";

        switch (n.opType){
            case mul:
                op = "mul";
                break;
            case div:
                op = "div";
                break;
            case add:
                op = "add";
                break;
            case sub:
                op="sub";
                break;
            case LT:
                op="lt";
                break;
            case LTE:
                op ="le";
                break;
            case GT:
                op = "gt";
                break;
            case GTE:
                op = "ge";
                break;
            case EQ:
                op = "eq";
                break;
            case NE:
                op="neq";
                break;
            case and:
                op = "and";
                break;
            case or:
                op="or";
                break;
            default:
                throw new NotImplementedException();
        }

        instructions.add(op+debugComment(n));

        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitBlockAstNode(BlockAstNode n){
        //open new frame in symbol table
        memory.push(new ArrayList<>());


        VisitResult visitResult = n.child.acceptVisitor(this);




        List<String> instructions = new ArrayList<>();


        //open a stack frame and remove the frame from the symbol table
        instructions.add("push " + memory.pop().size() + " // stack frame for program");
        instructions.add("oframe"+debugComment(n));
        instructions.addAll(Arrays.asList(visitResult.instructions));
        //close the stack frame
        instructions.add("cframe");

        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitBooleanLiteralAstNode(BooleanLiteralAstNode n){
        return new VisitResult(new String[]{n.getVal()?"1":"0"});
    }

    @Override
    public VisitResult visitColourLiteralAstNode(ColourLiteralAstNode n){
        return new VisitResult(new String[]{String.valueOf(n.getVal())});
    }

    @Override
    public VisitResult visitDelayAstNode(DelayAstNode n){
        return null;
    }

    @Override
    public VisitResult visitFactorAstNode(FactorAstNode n){
        return n.child.acceptVisitor(this);
    }

    @Override
    public VisitResult visitFloatLiteralAstNode(FloatLiteralAstNode n){
        return new VisitResult(new String[]{String.valueOf(n.getVal())});
    }

    @Override
    public VisitResult visitForAstNode(ForAstNode n){
        return null;
    }

    @Override
    public VisitResult visitFormalParameterAstNode(FormalParameterAstNode n){
        return null;
    }

    @Override
    public VisitResult visitFormalParamsAstNode(FormalParamsAstNode n){
        return null;
    }

    @Override
    public VisitResult visitFunctionCallAstNode(FunctionCallAstNode n){
        return null;
    }

    @Override
    public VisitResult visitFunDeclAstNode(FunDeclAstNode n){
        return null;
    }

    @Override
    public VisitResult visitIdentifierAstNode(IdentifierAstNode n){


        int frameIndex = 0;
        int indexInFrame = 0;


        for (int i = memory.size() - 1; i >= 0; i--){
            List<String> frame = memory.get(i);

            //if found in this frame
            if (frame.contains(n.getVal())){
                indexInFrame = frame.indexOf(n.getVal());
                frameIndex = memory.size() - (i + 1);
                return new VisitResult(new String[]{"push ["+indexInFrame+":"+frameIndex+"]" + debugComment(n)});
            }
        }

        throw new RuntimeException("memory address for identifier "+ n.getVal() +"was not found, this should have been caught by the semantic analyzer");

    }

    @Override
    public VisitResult visitIfAstNode(IfAstNode n){
        return null;
    }

    @Override
    public VisitResult visitIntegerLiteralAstNode(IntegerLiteralAstNode n){
        return new VisitResult(new String[]{String.valueOf(n.getVal())});
    }

    @Override
    //pushed the literal to the OP stack
    public VisitResult visitLiteralAstNode(LiteralAstNode n){
        VisitResult visitResult = n.child.acceptVisitor(this);
        return new VisitResult(new String[]{"push " + visitResult.instructions[0]+debugComment(n)});
    }

    @Override
    public VisitResult visitNegativeAstNode(NegativeAstNode n){
        VisitResult visitResult = n.child.acceptVisitor(this);
        List<String> instructions = new ArrayList<>(Arrays.asList(visitResult.instructions));
        instructions.add("push -1 // *-1 for negative");
        instructions.add("mul"+debugComment(n));
        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitNotAstNode(NotAstNode n){
        VisitResult visitResult = n.child.acceptVisitor(this);
        List<String> instructions = new ArrayList<>(Arrays.asList(visitResult.instructions));
        instructions.add("not");
        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitPadHeightAstNode(PadHeightAstNode n){
        return null;
    }

    @Override
    public VisitResult visitPadRandiAstNode(PadRandiAstNode n){
        return null;
    }

    @Override
    public VisitResult visitPadReadAstNode(PadReadAstNode n){
        return null;
    }

    @Override
    public VisitResult visitPadWidthAstNode(PadWidthAstNode n){
        return null;
    }

    @Override
    public VisitResult visitPixelAstNode(PixelAstNode n){
        return null;
    }

    @Override
    public VisitResult visitPixelRangeAstNode(PixelRangeAstNode n){
        return null;
    }

    @Override
    //prints the value at the top of the OP stack
    public VisitResult visitPrintAstNode(PrintAstNode n){
        VisitResult visitResult = n.x.acceptVisitor(this);
        List<String> instructions = new ArrayList<>(Arrays.asList(visitResult.instructions));
        instructions.add("print"+debugComment(n));
        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitProgramAstNode(ProgramAstNode n){

        //open new frame in symbol table
        memory.push(new ArrayList<>());


        VisitResult visitResult = n.child.acceptVisitor(this);




        List<String> instructions = new ArrayList<>(Arrays.asList(".main"));


        //open a stack frame and remove the frame from the symbol table
        instructions.add("push " + memory.pop().size() + " // stack frame for program");
        instructions.add("oframe");
        instructions.addAll(Arrays.asList(visitResult.instructions));
        //close the stack frame
        instructions.add("cframe");
        //halt
        instructions.add("halt");

        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitReturnAstNode(ReturnAstNode n){
        return null;
    }

    @Override
    public VisitResult visitStatementAstNode(StatementAstNode n){
        return n.child.acceptVisitor(this);
    }

    @Override
    public VisitResult visitStatementListAstNode(StatementListAstNode n){
        List<String> instructions = new ArrayList<>();

        for (StatementAstNode statement : n.children) {

            VisitResult childVisitResult = statement.acceptVisitor(this);

            instructions.addAll(Arrays.asList(childVisitResult.instructions));
        }

        return new VisitResult(instructions.toArray(new String[0]));

    }

    @Override
    public VisitResult visitSubExprAstNode(SubExprAstNode n){
        return null;
    }

    @Override
    public VisitResult visitTypeLiteralAstNode(TypeLiteralAstNode n){
        return n.acceptVisitor(this);
    }

    @Override
    public VisitResult visitVarDeclAstNode(VarDeclAstNode n){
        List<String> instructions = new ArrayList<>();

        VisitResult exprVisitRes = n.expr.acceptVisitor(this);

        String identifier = n.identifier.getVal();

        //add it to the symbol table
        memory.peek().add(identifier);

        //push the result of the expr
        instructions.addAll(Arrays.asList(exprVisitRes.instructions));

        instructions.add("push " + memory.peek().indexOf(identifier) +" //index of " +identifier);
        instructions.add("push 0" + "//in stack frame 0");
        instructions.add("st" + debugComment(n));



        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitWhileAstNode(WhileAstNode n){
        return null;
    }
    
    public String debugComment(ASTNode n){
        return String.format(Locale.US,"    //(%d-%d) %s",n.getSourceStart(),n.getSourceEnd(),n.getClass().getSimpleName());
    }

    public static class VisitResult{

        public String[] instructions;

        public VisitResult(String[] instructions){
            this.instructions = instructions;
        }

        public VisitResult(String[] instructions, Stack<HashMap<String, Type>> memory){
            this.instructions = instructions;
        }

        @Override
        public String toString(){
            return new StringJoiner(", ", VisitResult.class.getSimpleName() + "[", "]")
                    .add("instructions=" + Arrays.toString(instructions))
                    .toString();
        }
    }
}
