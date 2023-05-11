package backend.Sematic;

import ast.Type;
import ast.nodes.*;
import backend.Visitor;
import exceptions.LineNumberProvider;
import exceptions.SemanticErrorException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


//goes through any place where a function declaration is possible, to find all the functions declared
public class FunctionDeclarationVisitor implements Visitor<Void>{


    private final LineNumberProvider lineNumberProvider;

    public FunctionDeclarationVisitor(LineNumberProvider lineNumberProvider){
        this.lineNumberProvider = lineNumberProvider;
    }

    Map<String,FunctionDeclarationProperties> functions = new HashMap<>();

    public Map<String, FunctionDeclarationProperties> getFunctions(){
        return functions;
    }

    @Override
    public Void visitActualParamsAstNode(ActualParamsAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitAssignmentAstNode(AssignmentAstNode n){
        return null; //function decl is not allowed here
    }

    @Override
    public Void visitBinaryOpAstNode(BinaryOpAstNode n){
        return null; //function decl is not allowed here
    }

    @Override
    public Void visitBlockAstNode(BlockAstNode n){
        return n.child.acceptVisitor(this);
    }

    @Override
    public Void visitBooleanLiteralAstNode(BooleanLiteralAstNode n){
        return null; //function decl is not allowed here
    }

    @Override
    public Void visitColourLiteralAstNode(ColourLiteralAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitDelayAstNode(DelayAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitFactorAstNode(FactorAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitFloatLiteralAstNode(FloatLiteralAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitForAstNode(ForAstNode n){
        return n.block.child.acceptVisitor(this);
    }

    @Override
    public Void visitFormalParameterAstNode(FormalParameterAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitFormalParamsAstNode(FormalParamsAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitFunctionCallAstNode(FunctionCallAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitFunDeclAstNode(FunDeclAstNode n){

        //found a function, add it to the list

        String ident = n.identifier.getVal();
        Type retType = n.type.getVal();

        Type[] paramTypes = new Type[0];
        if (n.params!=null)
                paramTypes = Arrays.stream(n.params.children)//stream params
                        .map(param->param.type.getVal())//get their types
                        .toArray(Type[]::new);//convert to array


        if (functions.containsKey(ident))
            throw new SemanticErrorException(lineNumberProvider,"Function already exists with identifier '"+ident+"'",n.identifier.getSourceStart(),n.identifier.getSourceEnd());

        //put in functions list
        functions.put(ident, new FunctionDeclarationProperties(retType, paramTypes));

        return null;
    }

    @Override
    public Void visitIdentifierAstNode(IdentifierAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitIfAstNode(IfAstNode n){
        n.thenBlock.child.acceptVisitor(this);

        if (n.elseBlock!=null)
            n.elseBlock.child.acceptVisitor(this);

        return null;
    }

    @Override
    public Void visitIntegerLiteralAstNode(IntegerLiteralAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitLiteralAstNode(LiteralAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitNegativeAstNode(NegativeAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitNotAstNode(NotAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitPadHeightAstNode(PadHeightAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitPadRandiAstNode(PadRandiAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitPadReadAstNode(PadReadAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitPadWidthAstNode(PadWidthAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitPixelAstNode(PixelAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitPixelRangeAstNode(PixelRangeAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitPrintAstNode(PrintAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitProgramAstNode(ProgramAstNode n){
        return n.child.acceptVisitor(this);
    }

    @Override
    public Void visitReturnAstNode(ReturnAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitStatementAstNode(StatementAstNode n){
        return n.child.acceptVisitor(this);
    }

    @Override
    public Void visitStatementListAstNode(StatementListAstNode n){
        for (StatementAstNode child : n.children){
            child.acceptVisitor(this);
        }
        return null;
    }

    @Override
    public Void visitSubExprAstNode(SubExprAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitTypeLiteralAstNode(TypeLiteralAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitVarDeclAstNode(VarDeclAstNode n){
        return null;//function decl is not allowed here
    }

    @Override
    public Void visitWhileAstNode(WhileAstNode n){
        return n.block.acceptVisitor(this);
    }


}
