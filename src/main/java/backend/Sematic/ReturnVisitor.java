package backend.Sematic;

import ast.nodes.*;
import backend.Visitor;

import java.util.Arrays;

//called by a function declaration to ensure that all paths in a block return a value
//not responsible for checking the type of the return

//each function returns true if the node is guaranteed to return,
// returns false if there exists a path which doesn't return, or if the node just doesn't contain a return
public class ReturnVisitor implements Visitor<Boolean>{
    @Override
    public Boolean visitActualParamsAstNode(ActualParamsAstNode n){
        return false;
    }

    @Override
    public Boolean visitAssignmentAstNode(AssignmentAstNode n){
        return false;
    }

    @Override
    public Boolean visitBinaryOpAstNode(BinaryOpAstNode n){
        return false;
    }

    @Override
    public Boolean visitBlockAstNode(BlockAstNode n){
        return n.child.acceptVisitor(this);
    }

    @Override
    public Boolean visitBooleanLiteralAstNode(BooleanLiteralAstNode n){
        return false;
    }

    @Override
    public Boolean visitColourLiteralAstNode(ColourLiteralAstNode n){
        return false;
    }

    @Override
    public Boolean visitDelayAstNode(DelayAstNode n){
        return false;
    }

    @Override
    public Boolean visitFactorAstNode(FactorAstNode n){
        return false;
    }

    @Override
    public Boolean visitFloatLiteralAstNode(FloatLiteralAstNode n){
        return false;
    }

    @Override
    public Boolean visitForAstNode(ForAstNode n){
        return false; //loop can run 0 times - therefore we don't guarantee a return, even if there is a return INSIDE the loop
    }

    @Override
    public Boolean visitFormalParameterAstNode(FormalParameterAstNode n){
        return false;
    }

    @Override
    public Boolean visitFormalParamsAstNode(FormalParamsAstNode n){
        return false;
    }

    @Override
    public Boolean visitFunctionCallAstNode(FunctionCallAstNode n){
        return false;
    }

    @Override
    public Boolean visitFunDeclAstNode(FunDeclAstNode n){
        return false;
    }

    @Override
    public Boolean visitIdentifierAstNode(IdentifierAstNode n){
        return false;
    }

    @Override
    public Boolean visitIfAstNode(IfAstNode n){

        //only return true if both the 'then' and 'else' block have a return

        return n.elseBlock!=null&&n.thenBlock.acceptVisitor(this)&&n.elseBlock.acceptVisitor(this);
    }

    @Override
    public Boolean visitIntegerLiteralAstNode(IntegerLiteralAstNode n){
        return false;
    }

    @Override
    public Boolean visitLiteralAstNode(LiteralAstNode n){
        return false;
    }

    @Override
    public Boolean visitNegativeAstNode(NegativeAstNode n){
        return false;
    }

    @Override
    public Boolean visitNotAstNode(NotAstNode n){
        return false;
    }

    @Override
    public Boolean visitPadHeightAstNode(PadHeightAstNode n){
        return false;
    }

    @Override
    public Boolean visitPadRandiAstNode(PadRandiAstNode n){
        return false;
    }

    @Override
    public Boolean visitPadReadAstNode(PadReadAstNode n){
        return false;
    }

    @Override
    public Boolean visitPadWidthAstNode(PadWidthAstNode n){
        return false;
    }

    @Override
    public Boolean visitPixelAstNode(PixelAstNode n){
        return false;
    }

    @Override
    public Boolean visitPixelRangeAstNode(PixelRangeAstNode n){
        return false;
    }

    @Override
    public Boolean visitPrintAstNode(PrintAstNode n){
        return false;
    }

    @Override
    public Boolean visitProgramAstNode(ProgramAstNode n){
        return n.child.acceptVisitor(this);
    }

    @Override
    public Boolean visitReturnAstNode(ReturnAstNode n){
        return true; //it's a return statement - of course it returns ¯\_(ツ)_/¯
    }

    @Override
    public Boolean visitStatementAstNode(StatementAstNode n){
        return n.child.acceptVisitor(this);
    }

    @Override
    public Boolean visitStatementListAstNode(StatementListAstNode n){
        //if one of the statements guarantee a return, then the whole statement list guarantees a return
        return Arrays.stream(n.children).anyMatch(child->child.acceptVisitor(this));
    }

    @Override
    public Boolean visitSubExprAstNode(SubExprAstNode n){
        return false;
    }

    @Override
    public Boolean visitTypeLiteralAstNode(TypeLiteralAstNode n){
        return false;
    }

    @Override
    public Boolean visitVarDeclAstNode(VarDeclAstNode n){
        return false;
    }

    @Override
    public Boolean visitWhileAstNode(WhileAstNode n){
        return false; //loop can run 0 times, so a return statement inside the loop might never run
    }
}
