package backend.Sematic;

import ast.ASTNode;
import ast.Type;
import ast.nodes.*;
import backend.CodeGenerationVisitor;
import backend.Visitor;
import exceptions.LineNumberProvider;
import exceptions.SemanticErrorException;

import java.util.*;

public class SemanticVisitor implements Visitor<SemanticVisitor.VisitResult>{

    Stack<List<VarSymbol>> symbolTable = new Stack<>();
    //number of scopes reachable from the current node being traversed.
    public int scopeReach = 0;

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

        VisitResult exprVisitRes = n.expr.acceptVisitor(this);

        //loop through all the scopes reachable from current node
        for (int i = 0; i < scopeReach; i++){
            //get the (top-i) scope
            int indexInStack = symbolTable.size() - i - 1;

            List<VarSymbol> scope = symbolTable.get(indexInStack);

            //search this scope
            if (scope.stream().anyMatch(symbol->symbol.identifier.equals(n.identifier.getVal()))){
                //found in this scope
                //now get the type of the variable
                @SuppressWarnings("OptionalGetWithoutIsPresent") // we already know it's present - by the if statement above
                Type t = scope.stream().filter(symbol->symbol.identifier.equals(n.identifier.getVal())).map(symbol->symbol.t).findFirst().get();

                //check that type of variable and type of expression matches
                List<Type> allowedTypes = new ArrayList<>(Collections.singleton(t));

                //special rule to allow integers to be stored in floats
                if (t == Type.Float)
                    allowedTypes.add(Type.Int);

                assertType(allowedTypes.toArray(new Type[0]),exprVisitRes.type,n.expr);

                return new VisitResult(null); //return
            }
        }

        throw new SemanticErrorException(lineNumberProvider,"variable '"+n.identifier.getVal()+"' is not defined in this scope",n.getSourceStart(),n.getSourceEnd());

    }

    @Override
    public VisitResult visitBinaryOpAstNode(BinaryOpAstNode n){

        VisitResult lVisitResult = n.left.acceptVisitor(this);
        VisitResult rVisitResult = n.right.acceptVisitor(this);

        Type lType = lVisitResult.type;
        Type rType = rVisitResult.type;

        Type returnType;

        switch (n.opType){
            //boolean ops
            case and:
            case or:
                assertType(new Type[]{Type.Bool},lType,n.left);
                assertType(new Type[]{Type.Bool},rType,n.right);
                returnType = Type.Bool;
                break;
            //relops
            case GT:
            case LT:
            case GTE:
            case LTE:
            case EQ:
            case NE:
                assertType(new Type[]{Type.Int,Type.Float},lType,n.left);
                assertType(new Type[]{Type.Int,Type.Float},rType,n.right);
                returnType = Type.Bool;
                break;
            case add:
            case sub:
            case mul:
                assertType(new Type[]{Type.Int,Type.Float},lType,n.left);
                assertType(new Type[]{Type.Int,Type.Float},rType,n.right);
                //if both sides are int then the return is int
                //if at least 1 side is float, then the return is float
                returnType = (lType == Type.Int && rType == Type.Int)?Type.Int:Type.Float;
                break;
            case div:
                assertType(new Type[]{Type.Int,Type.Float},lType,n.left);
                assertType(new Type[]{Type.Int,Type.Float},rType,n.right);
                returnType = Type.Float; //division always return float
                break;
            default://this should never happen - but needed to get rid of 'might not have been initialized' error
                throw new RuntimeException("how did we get here? - forgot a case statement?");
        }

        return new VisitResult(returnType);
    }

    @Override
    public VisitResult visitBlockAstNode(BlockAstNode n){
        //create new frame in symbol table
        symbolTable.push(new ArrayList<>());
        scopeReach++;

        n.child.acceptVisitor(this);

        //pop frame in symbol table
        symbolTable.pop();
        scopeReach--;

        return new VisitResult(null);
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

        if (n.decl != null) n.decl.acceptVisitor(this);

        VisitResult conditionVisitRes = n.expr.acceptVisitor(this);

        assertType(new Type[]{Type.Bool},conditionVisitRes.type,n.expr);

        if (n.assignment != null) n.assignment.acceptVisitor(this);

        n.block.acceptVisitor(this);

        return new VisitResult(null);
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

        //loop through all the scopes reachable from current node
        for (int i = 0; i < scopeReach; i++){
            //get the (top-i) scope
            int indexInStack = symbolTable.size() - i - 1;

            List<VarSymbol> scope = symbolTable.get(indexInStack);

            //search this scope
            if (scope.stream().anyMatch(symbol->symbol.identifier.equals(n.getVal()))){
                //found in this scope
                //now get the type of the variable
                @SuppressWarnings("OptionalGetWithoutIsPresent") // we already know it's present - by the if statement above
                Type t = scope.stream().filter(symbol->symbol.identifier.equals(n.getVal())).map(symbol->symbol.t).findFirst().get();
                return new VisitResult(t); //return the type of the variable
            }
        }

        throw new SemanticErrorException(lineNumberProvider,"variable '"+n.getVal()+"' is not defined in this scope",n.getSourceStart(),n.getSourceEnd());

    }

    @Override
    public VisitResult visitIfAstNode(IfAstNode n){
        VisitResult conditionVisitRes = n.condition.acceptVisitor(this);

        assertType(new Type[]{Type.Bool},conditionVisitRes.type,n.condition);

        VisitResult thenVisitRes = n.thenBlock.acceptVisitor(this);
        if (n.elseBlock != null) n.elseBlock.acceptVisitor(this);

        return new VisitResult(null);


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
        return new VisitResult(Type.Int);
    }

    @Override
    public VisitResult visitPadRandiAstNode(PadRandiAstNode n){
        return new VisitResult(Type.Int);
    }

    @Override
    public VisitResult visitPadReadAstNode(PadReadAstNode n){
        VisitResult xVisitResult = n.x.acceptVisitor(this);
        assertType(new Type[]{Type.Int},xVisitResult.type,n.x);
        VisitResult yVisitResult = n.y.acceptVisitor(this);
        assertType(new Type[]{Type.Int},yVisitResult.type,n.y);

        return new VisitResult(Type.Colour);
    }

    @Override
    public VisitResult visitPadWidthAstNode(PadWidthAstNode n){

        return new VisitResult(Type.Int);
    }

    @Override
    public VisitResult visitPixelAstNode(PixelAstNode n){
        VisitResult xVisitResult = n.x.acceptVisitor(this);
        assertType(new Type[]{Type.Int},xVisitResult.type,n.x);
        VisitResult yVisitResult = n.y.acceptVisitor(this);
        assertType(new Type[]{Type.Int},yVisitResult.type,n.y);
        VisitResult colourVisitResult = n.colour.acceptVisitor(this);
        assertType(new Type[]{Type.Colour},colourVisitResult.type,n.colour);

        return new VisitResult(null);
    }

    @Override
    public VisitResult visitPixelRangeAstNode(PixelRangeAstNode n){
        VisitResult xVisitResult = n.x.acceptVisitor(this);
        assertType(new Type[]{Type.Int},xVisitResult.type,n.x);
        VisitResult yVisitResult = n.y.acceptVisitor(this);
        assertType(new Type[]{Type.Int},yVisitResult.type,n.y);
        VisitResult widthVisitResult = n.width.acceptVisitor(this);
        assertType(new Type[]{Type.Int},widthVisitResult.type,n.width);
        VisitResult heightVisitResult = n.height.acceptVisitor(this);
        assertType(new Type[]{Type.Int},heightVisitResult.type,n.height);
        VisitResult colourVisitResult = n.colour.acceptVisitor(this);
        assertType(new Type[]{Type.Colour},colourVisitResult.type,n.colour);
        
        return new VisitResult(null);
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
        scopeReach++;

        //check the semantics of the child
        n.child.acceptVisitor(this);

        //pop frame in symbol table
        symbolTable.pop();
        scopeReach--;

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
        return n.child.acceptVisitor(this);
    }

    @Override
    public VisitResult visitTypeLiteralAstNode(TypeLiteralAstNode n){
        if (n.getVal() == null){
            throw new SemanticErrorException(lineNumberProvider,"unknown type",n.getSourceStart(),n.getSourceEnd());
        }
        return new VisitResult(n.getVal());
    }

    @Override
    public VisitResult visitVarDeclAstNode(VarDeclAstNode n){


        VisitResult typeVisitResult = n.type.acceptVisitor(this);
        VisitResult exprVisitResult = n.expr.acceptVisitor(this);

        //check if variable is already defined in scope - error
        if(symbolTable.peek().stream().anyMatch(symbol -> symbol.identifier.equals(n.identifier.getVal()))){
            //variable already declared in scope
            throw new SemanticErrorException(lineNumberProvider,"Variable '" + n.identifier.getVal() + "' is already defined within this scope",n.getSourceStart(),n.getSourceEnd());
        }

        //check that type of variable and type of expression matches
        List<Type> allowedTypes = new ArrayList<>(Collections.singleton(typeVisitResult.type));

        //special rule to allow integers to be stored in floats
        if (typeVisitResult.type == Type.Float)
            allowedTypes.add(Type.Int);

        assertType(allowedTypes.toArray(new Type[0]),exprVisitResult.type,n.expr);


        //add it to the symbol table
        symbolTable.peek().add(new VarSymbol(typeVisitResult.type,n.identifier.getVal()));
        return new VisitResult(null);
    }

    @Override
    public VisitResult visitWhileAstNode(WhileAstNode n){
        VisitResult conditionVisitRes = n.expr.acceptVisitor(this);

        assertType(new Type[]{Type.Bool},conditionVisitRes.type,n.expr);

        VisitResult blockVisitRes = n.block.acceptVisitor(this);

        return new VisitResult(null);
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
