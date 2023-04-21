package ast;

import backend.Visitor;

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
//todo fix indentation
    public String visitBinaryOpAstNode(BinaryOpAstNode n){
        indent++;
        String s = String.format("<BinaryOp type = \"%s\">%s%s</BinaryOp>", n.opType.humanReadableName, n.left.acceptVisitor(this), n.right.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitBlockAstNode(BlockAstNode n){
        indent++;
        String s = "<Block>" + n.child.acceptVisitor(this) + "</Block>";
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitBooleanLiteralAstNode(BooleanLiteralAstNode n){
        indent++;
        String s = String.format("<Bool>%b</Bool>", n.val);
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitColourLiteralAstNode(ColourLiteralAstNode n){
        indent++;
        String s = String.format("<Colour>%s</Colour>", n.getVal());
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitDelayAstNode(DelayAstNode n){
        indent++;
        String s = String.format("<PadDelay>%s</PadDelay>", n.x.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitFactorAstNode(FactorAstNode n){

            indent++;
            String s = String.format("<Factor>%s</Factor>", n.child.acceptVisitor(this));
            indent--;
            return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitFloatLiteralAstNode(FloatLiteralAstNode n){
        indent++;
        String s = String.format("<Float>%f</Float>", n.getVal());
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitForAstNode(ForAstNode n){
        indent++;
        String s = String.format("<For>%s%s%s%s</For>", n.decl.acceptVisitor(this), n.expr.acceptVisitor(this), n.assignment.acceptVisitor(this), n.block.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitFormalParameterAstNode(FormalParameterAstNode n){
        indent++;
        String s = String.format("<FormalParam>%s%s</FormalParam>", n.identifier.acceptVisitor(this), n.type.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitFormalParamsAstNode(FormalParamsAstNode n){
        indent++;
        String s = "<FormalParams>" + Arrays.stream(n.children).map((ASTNode node )->node.acceptVisitor(this)).collect(Collectors.joining()) + "</FormalParams>";
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitFunctionCallAstNode(FunctionCallAstNode n){
        indent++;
        String s = String.format("<FunctionCall>%s%s</FunctionCall>", n.identifier.acceptVisitor(this), n.params.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
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
//todo fix indentation
    public String visitIdentifierAstNode(IdentifierAstNode n){
        indent++;
        String s = String.format("<Id>%s</Id>", n.getVal());
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitIfAstNode(IfAstNode n){
        indent++;
        String s = String.format("<If>%s%s%s</If>", n.condition.acceptVisitor(this), n.thenBlock.acceptVisitor(this), n.elseBlock.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitIntegerLiteralAstNode(IntegerLiteralAstNode n){
        indent++;
        String s = String.format("<Int>%d</Int>", n.getVal());
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitLiteralAstNode(LiteralAstNode n){

            indent++;
            String s = String.format("<Literal>%s</Literal>", n.child.acceptVisitor(this));
            indent--;
            return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitNegativeAstNode(NegativeAstNode n){
        indent++;
        String s = String.format("<Negative>%s</Negative>", n.child.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitNotAstNode(NotAstNode n){
        indent++;
        String s = String.format("<Not>%s</Not>", n.child.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitPadHeightAstNode(PadHeightAstNode n){
        indent++;
        String s = "<PadHeight/>";
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitPadRandiAstNode(PadRandiAstNode n){
        indent++;
        String s = String.format("<PadRandI>%s</PadRandI>", n.x.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitPadReadAstNode(PadReadAstNode n){
        indent++;
        String s = String.format("<PadRead>%s%s</PadRead>", n.x.acceptVisitor(this), n.y.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitPadWidthAstNode(PadWidthAstNode n){
        indent++;
        String s = "<PadWidth/>";
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitPixelAstNode(PixelAstNode n){
        indent++;
        String s = String.format("<Pixel>%s%s%s<Pixel>", n.x.acceptVisitor(this), n.y.acceptVisitor(this), n.colour.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitPixelRangeAstNode(PixelRangeAstNode n){
        indent++;
        String s = String.format("<PixelRange>%s%s%s%s%s<PixelRange>", n.x.acceptVisitor(this), n.y.acceptVisitor(this), n.width.acceptVisitor(this), n.height.acceptVisitor(this), n.colour.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitPrintAstNode(PrintAstNode n){
        indent++;
        String s = String.format("<PadPrint>%s</PadPrint>", n.x.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitProgramAstNode(ProgramAstNode n){
        indent++;
        String s = "<Program>" + n.child.acceptVisitor(this) + "</Program>";
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitReturnAstNode(ReturnAstNode n){
        indent++;
        String s = String.format("<Return>%s</Return>", n.x.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
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
//todo fix indentation
    public String visitSubExprAstNode(SubExprAstNode n){
            indent++;
            String s = String.format("<SubExpr>%s</SubExpr>", n.child.acceptVisitor(this));
            indent--;
            return performIndentation() + s;

    }

    @Override
//todo fix indentation
    public String visitTypeLiteralAstNode(TypeLiteralAstNode n){
        indent++;
        String s = String.format("<Type>%s</Type>", n.getVal());
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitVarDeclAstNode(VarDeclAstNode n){
        indent++;
        String s = String.format("<VarDecl>%s%s%s</VarDecl>", n.identifier.acceptVisitor(this), n.type.acceptVisitor(this), n.expr.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
    }

    @Override
//todo fix indentation
    public String visitWhileAstNode(WhileAstNode n){
        indent++;
        String s = String.format("<While>%s%s</While>", n.expr.acceptVisitor(this), n.block.acceptVisitor(this));
        indent--;
        return performIndentation() + s;
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
