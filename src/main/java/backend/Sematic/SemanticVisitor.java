package backend.Sematic;

import ast.ASTNode;
import ast.Type;
import ast.nodes.*;
import backend.Visitor;
import exceptions.LineNumberProvider;
import exceptions.SemanticErrorException;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;

public class SemanticVisitor implements Visitor<SemanticVisitor.VisitResult>{

    Stack<List<VarSymbol>> symbolTable = new Stack<>();
    //number of frames reachable from the current node being traversed.
    public int frameReach = 0;

    public LineNumberProvider lineNumberProvider;

    public SemanticVisitor(LineNumberProvider lineNumberProvider){
        this.lineNumberProvider = lineNumberProvider;
    }

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
        VisitResult childVisitRes = n.x.acceptVisitor(this);
        assertType(new Type[]{Type.Int},childVisitRes.type,n.x);
        return new VisitResult(null);
    }

    @Override
    public VisitResult visitFactorAstNode(FactorAstNode n){
        return n.child.acceptVisitor(this);
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
        VisitResult childVisitRes = n.child.acceptVisitor(this);
        assertType(new Type[]{Type.Float,Type.Int},childVisitRes.type,n.child);
        //type is same as child's type
        return new VisitResult(childVisitRes.type);
    }

    @Override
    public VisitResult visitNotAstNode(NotAstNode n){
        VisitResult childVisitRes = n.child.acceptVisitor(this);
        assertType(new Type[]{Type.Bool},childVisitRes.type,n.child);
        return new VisitResult(Type.Bool);
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
        n.x.acceptVisitor(this);
        return new VisitResult(null);
    }

    @Override
    public VisitResult visitProgramAstNode(ProgramAstNode n){
        //create new frame in symbol table
        symbolTable.push(new ArrayList<>());
        frameReach ++;

        //check the semantics of the child
        n.child.acceptVisitor(this);

        //pop frame in symbol table
        symbolTable.pop();
        frameReach --;

        return new VisitResult(null);
    }

    @Override
    public VisitResult visitReturnAstNode(ReturnAstNode n){
        return null;
    }

    @Override
    public VisitResult visitStatementAstNode(StatementAstNode n){
        //check the child
        return n.child.acceptVisitor(this);
    }

    @Override
    public VisitResult visitStatementListAstNode(StatementListAstNode n){
        for (StatementAstNode child : n.children){
            //check the semantics of each child
            child.acceptVisitor(this);
        }
        return new VisitResult(null);
    }

    @Override
    public VisitResult visitSubExprAstNode(SubExprAstNode n){
        return null;
    }

    @Override
    public VisitResult visitTypeLiteralAstNode(TypeLiteralAstNode n){
        return new VisitResult(null);
    }

    @Override
    public VisitResult visitVarDeclAstNode(VarDeclAstNode n){
        return null;
    }

    @Override
    public VisitResult visitWhileAstNode(WhileAstNode n){
        return null;
    }



    public void assertType(Type[] allowedTypes, Type actualType, ASTNode n){
        if (!Arrays.asList(allowedTypes).contains(actualType))
            throw new SemanticErrorException(lineNumberProvider,"Expected type '" + Arrays.toString(allowedTypes) + "' but got type '" + actualType+"'",n.getSourceStart(),n.getSourceEnd());
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

    public static class VarSymbol{
        public Type t;
        public String identifier;

        public VarSymbol(Type t, String identifier){
            this.t = t;
            this.identifier = identifier;
        }
    }
}
