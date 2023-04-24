package backend;

import ast.ASTNode;
import ast.nodes.*;

import java.util.*;

public class CodeGenerationVisitor implements Visitor<CodeGenerationVisitor.VisitResult>{
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
        return null;
    }

    @Override
    public VisitResult visitBlockAstNode(BlockAstNode n){
        return null;
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
        return null;
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
        return null;
    }

    @Override
    public VisitResult visitNotAstNode(NotAstNode n){
        return null;
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
        VisitResult visitResult = n.child.acceptVisitor(this);
        List<String> instructions = new ArrayList<>(Arrays.asList(".main"));
        instructions.addAll(Arrays.asList(visitResult.instructions));
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
        return null;
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

        @Override
        public String toString(){
            return new StringJoiner(", ", VisitResult.class.getSimpleName() + "[", "]")
                    .add("instructions=" + Arrays.toString(instructions))
                    .toString();
        }
    }
}
