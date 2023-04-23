package backend;

import ast.nodes.*;

/**
 *
 * @param <R> result type
 */
public interface Visitor<R>{
    R visitActualParamsAstNode(ActualParamsAstNode n);

    R visitAssignmentAstNode(AssignmentAstNode n);

    R visitBinaryOpAstNode(BinaryOpAstNode n);

    R visitBlockAstNode(BlockAstNode n);

    R visitBooleanLiteralAstNode(BooleanLiteralAstNode n);

    R visitColourLiteralAstNode(ColourLiteralAstNode n);

    R visitDelayAstNode(DelayAstNode n);

    R visitFactorAstNode(FactorAstNode n);

    R visitFloatLiteralAstNode(FloatLiteralAstNode n);

    R visitForAstNode(ForAstNode n);

    R visitFormalParameterAstNode(FormalParameterAstNode n);

    R visitFormalParamsAstNode(FormalParamsAstNode n);

    R visitFunctionCallAstNode(FunctionCallAstNode n);

    R visitFunDeclAstNode(FunDeclAstNode n);

    R visitIdentifierAstNode(IdentifierAstNode n);

    R visitIfAstNode(IfAstNode n);

    R visitIntegerLiteralAstNode(IntegerLiteralAstNode n);

    R visitLiteralAstNode(LiteralAstNode n);

    R visitNegativeAstNode(NegativeAstNode n);

    R visitNotAstNode(NotAstNode n);

    R visitPadHeightAstNode(PadHeightAstNode n);

    R visitPadRandiAstNode(PadRandiAstNode n);

    R visitPadReadAstNode(PadReadAstNode n);

    R visitPadWidthAstNode(PadWidthAstNode n);

    R visitPixelAstNode(PixelAstNode n);

    R visitPixelRangeAstNode(PixelRangeAstNode n);

    R visitPrintAstNode(PrintAstNode n);

    R visitProgramAstNode(ProgramAstNode n);

    R visitReturnAstNode(ReturnAstNode n);

    R visitStatementAstNode(StatementAstNode n);

    R visitStatementListAstNode(StatementListAstNode n);

    R visitSubExprAstNode(SubExprAstNode n);

    R visitTypeLiteralAstNode(TypeLiteralAstNode n);

    R visitVarDeclAstNode(VarDeclAstNode n);

    R visitWhileAstNode(WhileAstNode n);


}
