package backend.Sematic;

import ast.Type;
import ast.nodes.*;
import backend.Visitor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.StringJoiner;

public class SemanticVisitor implements Visitor<SemanticVisitor.VisitResult>{


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
        return new VisitResult(Type.Bool);
    }

    @Override
    public VisitResult visitColourLiteralAstNode(ColourLiteralAstNode n){
        return new VisitResult(Type.Colour);
    }

    @Override
    public VisitResult visitDelayAstNode(DelayAstNode n){
        return null;
    }

    @Override
    public VisitResult visitFactorAstNode(FactorAstNode n){
        return null;
    }

    @Override
    public VisitResult visitFloatLiteralAstNode(FloatLiteralAstNode n){
        return new VisitResult(Type.Float);
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
        return new VisitResult(Type.Int);
    }

    @Override
    public VisitResult visitLiteralAstNode(LiteralAstNode n){

        VisitResult childRes = n.child.acceptVisitor(this);


        return new VisitResult(childRes.type);
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
    public VisitResult visitPrintAstNode(PrintAstNode n){
        return null;
    }

    @Override
    public VisitResult visitProgramAstNode(ProgramAstNode n){
        return null;
    }

    @Override
    public VisitResult visitReturnAstNode(ReturnAstNode n){
        return null;
    }

    @Override
    public VisitResult visitStatementAstNode(StatementAstNode n){
        return null;
    }

    @Override
    public VisitResult visitStatementListAstNode(StatementListAstNode n){
        return null;
    }

    @Override
    public VisitResult visitSubExprAstNode(SubExprAstNode n){
        return null;
    }

    @Override
    public VisitResult visitTypeLiteralAstNode(TypeLiteralAstNode n){
        return null;
    }

    @Override
    public VisitResult visitVarDeclAstNode(VarDeclAstNode n){
        return null;
    }

    @Override
    public VisitResult visitWhileAstNode(WhileAstNode n){
        return null;
    }

    public static class VisitResult{

        Type type;

        public VisitResult(Type type){
            this.type = type;
        }

        @Override
        public String toString(){
            return new StringJoiner(", ", VisitResult.class.getSimpleName() + "[", "]")
                    .add("type=" + type)
                    .toString();
        }
    }
}
