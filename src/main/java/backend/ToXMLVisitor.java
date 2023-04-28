package backend;

import ast.*;
import ast.nodes.*;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ToXMLVisitor implements Visitor<String>{

    int indent = 0;

    @Override
    public String visitActualParamsAstNode(ActualParamsAstNode n){

        StringBuilder sb = new StringBuilder();

        sb.append(performIndentation()).append("<ActualParams>");

        indent++;
        sb.append(Arrays.stream(n.children).map((ASTNode node )->node.acceptVisitor(this)).collect(Collectors.joining()));
        indent--;

        sb.append(performIndentation()).append("</ActualParams>");

        return sb.toString();
    }

    @Override
    public String visitAssignmentAstNode(AssignmentAstNode n){

        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Assign>");

        indent++;
        sb.append(n.identifier.acceptVisitor(this));
        sb.append(n.expr.acceptVisitor(this));
        indent--;

        sb.append(performIndentation()).append("</Assign>");

        return sb.toString();
    }

    @Override
    public String visitBinaryOpAstNode(BinaryOpAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append(String.format("<BinaryOp type=\"%s\">", n.opType.humanReadableName));
        indent++;
        sb.append(n.left.acceptVisitor(this));
        sb.append(n.right.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</BinaryOp>");
        return sb.toString();
    }

    @Override
    public String visitBlockAstNode(BlockAstNode n){
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Block>");
        indent++;
        sb.append(n.child.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</Block>");
        return sb.toString();
    }

    @Override
    public String visitBooleanLiteralAstNode(BooleanLiteralAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Bool>");
        indent++;
        sb.append(performIndentation()).append(n.getVal());
        indent--;
        sb.append(performIndentation()).append("</Bool>");
        return sb.toString();
    }

    @Override
    public String visitColourLiteralAstNode(ColourLiteralAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Colour>");
        indent++;
        sb.append(performIndentation()).append(n.getVal());
        indent--;
        sb.append(performIndentation()).append("</Colour>");
        return sb.toString();
    }


    @Override
    public String visitDelayAstNode(DelayAstNode n){
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<PadDelay>");
        indent++;
        sb.append(n.x.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</PadDelay>");
        return sb.toString();
    }

    @Override
    public String visitFactorAstNode(FactorAstNode n){
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Factor>");
        indent++;
        sb.append(n.child.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</Factor>");
        return sb.toString();
    }

    @Override
    public String visitFloatLiteralAstNode(FloatLiteralAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Float>");
        indent++;
        sb.append(performIndentation()).append(n.getVal());
        indent--;
        sb.append(performIndentation()).append("</Float>");
        return sb.toString();
    }


    @Override
    public String visitForAstNode(ForAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<For>");
        indent++;
        sb.append((n.decl != null)?n.decl.acceptVisitor(this):simulateVisitNullNode());
        sb.append(n.expr.acceptVisitor(this));
        sb.append((n.assignment != null)?n.assignment.acceptVisitor(this):simulateVisitNullNode());
        sb.append(n.block.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</For>");
        return sb.toString();
    }


    public String visitFormalParameterAstNode(FormalParameterAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<FormalParam>");
        indent++;
        sb.append(n.identifier.acceptVisitor(this));
        sb.append(n.type.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</FormalParam>");
        return sb.toString();
    }


    @Override
    public String visitFormalParamsAstNode(FormalParamsAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<FormalParams>");
        indent++;
        for (ASTNode node : n.children) {
            sb.append(node.acceptVisitor(this));
        }
        indent--;
        sb.append(performIndentation()).append("</FormalParams>");
        return sb.toString();
    }


    @Override
    public String visitFunctionCallAstNode(FunctionCallAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<FunctionCall>");
        indent++;
        sb.append(n.identifier.acceptVisitor(this));
        sb.append(n.params.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</FunctionCall>");
        return sb.toString();
    }


    @Override
    public String visitFunDeclAstNode(FunDeclAstNode n){
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<FunctionDecl>");
        indent++;
        sb.append(n.identifier.acceptVisitor(this));
        sb.append( n.params!=null?n.params.acceptVisitor(this):simulateVisitNullNode());
        sb.append( n.type.acceptVisitor(this));
        sb.append( n.codeBlock.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</FunctionDecl>");
        return sb.toString();
    }

    @Override
    public String visitIdentifierAstNode(IdentifierAstNode n){
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Id>");
        indent++;
        sb.append(performIndentation()).append(n.getVal());
        indent--;
        sb.append(performIndentation()).append("</Id>");
        return sb.toString();
    }

    @Override
    public String visitIfAstNode(IfAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<If>");
        indent++;
        sb.append(n.condition.acceptVisitor(this));
        sb.append(n.thenBlock.acceptVisitor(this));
        sb.append(n.elseBlock !=null?n.elseBlock.acceptVisitor(this):simulateVisitNullNode());
        indent--;
        sb.append(performIndentation()).append("</If>");
        return sb.toString();
    }

    @Override
    public String visitIntegerLiteralAstNode(IntegerLiteralAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Int>");
        indent++;
        sb.append(performIndentation()).append(n.getVal());
        indent--;
        sb.append(performIndentation()).append("</Int>");
        return sb.toString();
    }

    @Override
    public String visitLiteralAstNode(LiteralAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Literal>");
        indent++;
        sb.append(n.child.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</Literal>");
        return sb.toString();
    }

    @Override
    public String visitNegativeAstNode(NegativeAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Negative>");
        indent++;
        sb.append(n.child.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</Negative>");
        return sb.toString();
    }

    @Override
    public String visitNotAstNode(NotAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Not>");
        indent++;
        sb.append(n.child.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</Not>");
        return sb.toString();
    }

    @Override
    public String visitPadHeightAstNode(PadHeightAstNode n) {
        return performIndentation() + "<PadHeight/>";
    }

    @Override
    public String visitPadRandiAstNode(PadRandiAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<PadRandI>");
        indent++;
        sb.append(n.x.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</PadRandI>");
        return sb.toString();
    }

    @Override
    public String visitPadReadAstNode(PadReadAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<PadRead>");
        indent++;
        sb.append(n.x.acceptVisitor(this));
        sb.append(n.y.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</PadRead>");
        return sb.toString();
    }

    @Override
    public String visitPadWidthAstNode(PadWidthAstNode n) {
        return performIndentation() + "<PadWidth/>";
    }

    @Override
    public String visitPixelAstNode(PixelAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Pixel>");
        indent++;
        sb.append(n.x.acceptVisitor(this));
        sb.append(n.y.acceptVisitor(this));
        sb.append(n.colour.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</Pixel>");
        return sb.toString();
    }

    @Override
    public String visitPixelRangeAstNode(PixelRangeAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<PixelRange>");
        indent++;
        sb.append(n.x.acceptVisitor(this));
        sb.append(n.y.acceptVisitor(this));
        sb.append(n.width.acceptVisitor(this));
        sb.append(n.height.acceptVisitor(this));
        sb.append(n.colour.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</PixelRange>");
        return sb.toString();
    }


    @Override
    public String visitPrintAstNode(PrintAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<PadPrint>");
        indent++;
        sb.append(n.x.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</PadPrint>");
        return sb.toString();
    }


    @Override
    public String visitProgramAstNode(ProgramAstNode n){
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Program>");
        indent++;
        sb.append(n.child.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</Program>");
        return sb.toString();
    }

    @Override
    public String visitReturnAstNode(ReturnAstNode n){
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Return>");
        indent++;
        sb.append(n.x.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</Return>");
        return sb.toString();
    }

    @Override
    public String visitStatementAstNode(StatementAstNode n){
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Statement>");

        indent++;
        sb.append(n.child.acceptVisitor(this));
        indent--;

        sb.append(performIndentation()).append("</Statement>");
        return sb.toString();
    }

    @Override
    public String visitStatementListAstNode(StatementListAstNode n){

        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Statements>");

        indent++;
        sb.append(Arrays.stream(n.children).map((StatementAstNode node )->node.acceptVisitor(this)).collect(Collectors.joining()));
        indent--;

        sb.append(performIndentation()).append("</Statements>");
        return sb.toString();
    }

    @Override
    public String visitSubExprAstNode(SubExprAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<SubExpr>");
        indent++;
        sb.append(n.child.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</SubExpr>");
        return sb.toString();
    }

    @Override
    public String visitTypeLiteralAstNode(TypeLiteralAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<Type>");
        indent++;
        sb.append(performIndentation()).append(n.getVal());
        indent--;
        sb.append(performIndentation()).append("</Type>");
        return sb.toString();
    }


    @Override
    public String visitVarDeclAstNode(VarDeclAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<VarDecl>");
        indent++;
        sb.append(n.identifier.acceptVisitor(this));
        sb.append(n.type.acceptVisitor(this));
        sb.append(n.expr.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</VarDecl>");
        return sb.toString();
    }

    @Override
    public String visitWhileAstNode(WhileAstNode n) {
        StringBuilder sb = new StringBuilder();
        sb.append(performIndentation()).append("<While>");
        indent++;
        sb.append(n.expr.acceptVisitor(this));
        sb.append(n.block.acceptVisitor(this));
        indent--;
        sb.append(performIndentation()).append("</While>");
        return sb.toString();
    }

    public String simulateVisitNullNode(){

        return performIndentation() + "<NULL />";
    }

    private String performIndentation(){

        StringBuilder sb = new StringBuilder(indent);
        sb.append("\n");

        for (int i = 0; i < indent; i++){
            sb.append("\t");
        }

        return sb.toString();
    }


}
