package backend;

import ast.ASTNode;
import ast.nodes.*;
import exceptions.LineNumberProvider;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;

public class CodeGenerationVisitor implements Visitor<CodeGenerationVisitor.VisitResult>{


    //to be used as a symbol table
    Stack<List<String>> memory = new Stack<>();

    List<List<String>> compiledFunctions = new ArrayList<>();

    LineNumberProvider lineNumberProvider;

    public CodeGenerationVisitor(LineNumberProvider lineNumberProvider){
        this.lineNumberProvider = lineNumberProvider;
    }

    @Override
    public VisitResult visitActualParamsAstNode(ActualParamsAstNode n){
        List<String> instructions = new ArrayList<>();

        List<ASTNode> children = new ArrayList<>(Arrays.asList(n.children));
        //reverse the children[] so that to fit the call instruction
        Collections.reverse(children);

        for (ASTNode param : children) {

            VisitResult childVisitResult = param.acceptVisitor(this);

            instructions.addAll(Arrays.asList(childVisitResult.instructions));
        }

        instructions.add("push "+children.size()+" //param count");

        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitAssignmentAstNode(AssignmentAstNode n){

        VisitResult exprVisitRes = n.expr.acceptVisitor(this);

        String identifier = n.identifier.getVal();

        int frameIndex = 0;
        int indexInFrame = 0;


        for (int i = memory.size() - 1; i >= 0; i--){
            List<String> frame = memory.get(i);

            //if found in this frame
            if (frame.contains(identifier)){
                indexInFrame = frame.indexOf(identifier);
                frameIndex = memory.size() - (i + 1);
                break;
            }
        }
        //push the result of the expr
        List<String> instructions = new ArrayList<>(Arrays.asList(exprVisitRes.instructions));

        instructions.add("push " + indexInFrame +" //index of " +identifier);
        instructions.add("push " + frameIndex + "//in stack frame");
        instructions.add("st" + debugComment(n));



        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitBinaryOpAstNode(BinaryOpAstNode n){
        VisitResult visitResultR = n.right.acceptVisitor(this);
        VisitResult visitResultL = n.left.acceptVisitor(this);

        List<String> instructions = new ArrayList<>();

        instructions.addAll(Arrays.asList(visitResultR.instructions));
        instructions.addAll(Arrays.asList(visitResultL.instructions));


        String op;

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
        instructions.add("push " + memory.pop().size() + " // stack frame for block");
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
        VisitResult visitResult = n.x.acceptVisitor(this);
        List<String> instructions = new ArrayList<>(Arrays.asList(visitResult.instructions));
        instructions.add("delay"+debugComment(n));
        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitFactorAstNode(FactorAstNode n){
        return n.child.acceptVisitor(this);
    }

    @Override
    public VisitResult visitFloatLiteralAstNode(FloatLiteralAstNode n){
        return new VisitResult(new String[]{String.valueOf(n.getVal())});
    }

    /*
    * decl
    * [A] expr
    not
    push location of to the 1st instruction after block [B]
    cjmp2 to [B] - if expr was false
    * block
    * assignment
    push location of expr [A]
    jmp to [A]
    [B] ...
     */
    @Override
    public VisitResult visitForAstNode(ForAstNode n){
        List<String> instructions = new ArrayList<>();



        if (n.decl != null){
            VisitResult decVisit = n.decl.acceptVisitor(this);
            instructions.addAll(Arrays.asList(decVisit.instructions));//add declaration to start
        }


        VisitResult condition = n.expr.acceptVisitor(this);
        VisitResult block = n.block.acceptVisitor(this);

        List<String> conditionInstructions = new ArrayList<>(Arrays.asList(condition.instructions));
        List<String> blockInstructions = new ArrayList<>(Arrays.asList(block.instructions));

        if (n.assignment != null){
            VisitResult assVisit = n.assignment.acceptVisitor(this);
            blockInstructions.addAll(Arrays.asList(assVisit.instructions)); //add assignment to end of block
        }

        blockInstructions.add("push #PC-"+(blockInstructions.size()+3+conditionInstructions.size()));
        blockInstructions.add("jmp");

        conditionInstructions.add("not");
        conditionInstructions.add("push #PC+"+(blockInstructions.size()+2));
        conditionInstructions.add("cjmp2"+debugComment(n));

        instructions.addAll(conditionInstructions);
        instructions.addAll(blockInstructions);

        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitFormalParameterAstNode(FormalParameterAstNode n){
        return new VisitResult(new String[]{n.identifier.getVal()});
    }

    @Override
    public VisitResult visitFormalParamsAstNode(FormalParamsAstNode n){

        //holds the parameter
        ArrayList<String> params = new ArrayList<>();

        for (FormalParameterAstNode param : n.children){
            VisitResult visitResult = param.acceptVisitor(this);

            //add the identifier of the param
            params.add(visitResult.instructions[0]);
        }

        return new VisitResult(params.toArray(new String[0]));
    }

    @Override
    public VisitResult visitFunctionCallAstNode(FunctionCallAstNode n){

        VisitResult actualParamsResult = n.params.acceptVisitor(this);

        List<String> instructions = new ArrayList<>(Arrays.asList(actualParamsResult.instructions));

        instructions.add("push ."+n.identifier.getVal() + debugComment(n));

        instructions.add("call");

        return new VisitResult(instructions.toArray(new String[0]));

    }

    @Override
    public VisitResult visitFunDeclAstNode(FunDeclAstNode n){

        VisitResult paramsRes = null;
        if (n.params!=null)
            paramsRes = n.params.acceptVisitor(this);
        //open a stack frame for the parameters
        memory.push(paramsRes!=null?Arrays.asList(paramsRes.instructions):new ArrayList<>());

        List<String> instructions = new ArrayList<>();

        instructions.add("."+n.identifier.getVal() + debugComment(n));

        VisitResult blockRes = n.codeBlock.acceptVisitor(this);

        instructions.addAll(Arrays.asList(blockRes.instructions));


        //close the param stack frame from symbol table
        memory.pop();

        //add to compiled functions
        compiledFunctions.add(instructions);

        //return no instructions as the instructions will be added to compiledFunctions instead
        return new VisitResult(new String[0]);
    }

    @Override
    public VisitResult visitIdentifierAstNode(IdentifierAstNode n){


        int frameIndex;
        int indexInFrame;


        for (int i = memory.size() - 1; i >= 0; i--){
            List<String> frame = memory.get(i);

            //if found in this frame
            if (frame.contains(n.getVal())){
                indexInFrame = frame.indexOf(n.getVal());
                frameIndex = memory.size() - (i + 1);
                return new VisitResult(new String[]{"push ["+indexInFrame+":"+frameIndex+"]" + debugComment(n)});
            }
        }

        throw new RuntimeException("memory address for identifier '"+ n.getVal() +"' was not found, this should have been caught by the semantic analyzer");

    }


    /*
    * condition
    push location of start of then block [A]
    cjmp2 to 'then' block - if condition was true
    * else block (or empty if the else was not specified)
    push location of instruction after the 'then' block ends [B]
    jmp to skip the 'then' block
    * [A] then block
    [B]...

     */
    @Override
    public VisitResult visitIfAstNode(IfAstNode n){
        VisitResult conditionVisitRes = n.condition.acceptVisitor(this);
        VisitResult thenVisitRes = n.thenBlock.acceptVisitor(this);
        VisitResult elseVisitRes;

        List<String> thenInstructions = Arrays.asList(thenVisitRes.instructions);
        List<String> elseInstructions = new ArrayList<>();

        if (n.elseBlock != null){
            elseVisitRes = n.elseBlock.acceptVisitor(this);
            elseInstructions.addAll(Arrays.asList(elseVisitRes.instructions));
        }
        elseInstructions.add("push #PC+" + (thenInstructions.size()+2)); //skip then block and go to the instruction AFTER the then block
        elseInstructions.add("jmp //end else block");

        List<String> instructions = new ArrayList<>(Arrays.asList(conditionVisitRes.instructions));

        int elseLength = elseInstructions.size();
        instructions.add("push #PC+"+ (elseLength+2)); //skip else block and go directly to then block
        instructions.add("cjmp2" + debugComment(n));
        instructions.addAll(elseInstructions);
        instructions.addAll(thenInstructions);


        return new VisitResult(instructions.toArray(new String[0]));

    }

    @Override
    public VisitResult visitIntegerLiteralAstNode(IntegerLiteralAstNode n){
        return new VisitResult(new String[]{String.valueOf(n.getVal())});
    }

    @Override
    //pushed the literal to the OP stack
    public VisitResult visitLiteralAstNode(LiteralAstNode n){
        VisitResult visitResult = n.child.acceptVisitor(this);

        if (n.child instanceof PadWidthAstNode||n.child instanceof PadHeightAstNode||n.child instanceof PadRandiAstNode)
            //exception for width, height and randi as they do not need the push command
            return visitResult;


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
        return new VisitResult(new String[]{"height"});
    }

    @Override
    public VisitResult visitPadRandiAstNode(PadRandiAstNode n){
        VisitResult visitResult = n.x.acceptVisitor(this);
        List<String> instructions = new ArrayList<>(Arrays.asList(visitResult.instructions));
        instructions.add("irnd"+debugComment(n));
        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitPadReadAstNode(PadReadAstNode n){
        List<String> instructions = new ArrayList<>();

        instructions.addAll(Arrays.asList(n.y.acceptVisitor(this).instructions));
        instructions.addAll(Arrays.asList(n.x.acceptVisitor(this).instructions));

        instructions.add("read" + debugComment(n));

        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitPadWidthAstNode(PadWidthAstNode n){
        return new VisitResult(new String[]{"width"});
    }

    @Override
    public VisitResult visitPixelAstNode(PixelAstNode n){

        List<String> instructions = new ArrayList<>();

        instructions.addAll(Arrays.asList(n.colour.acceptVisitor(this).instructions));
        instructions.addAll(Arrays.asList(n.y.acceptVisitor(this).instructions));
        instructions.addAll(Arrays.asList(n.x.acceptVisitor(this).instructions));

        instructions.add("pixel" + debugComment(n));

        return new VisitResult(instructions.toArray(new String[0]));

    }

    @Override
    public VisitResult visitPixelRangeAstNode(PixelRangeAstNode n){
        List<String> instructions = new ArrayList<>();

        instructions.addAll(Arrays.asList(n.colour.acceptVisitor(this).instructions));
        instructions.addAll(Arrays.asList(n.height.acceptVisitor(this).instructions));
        instructions.addAll(Arrays.asList(n.width.acceptVisitor(this).instructions));
        instructions.addAll(Arrays.asList(n.y.acceptVisitor(this).instructions));
        instructions.addAll(Arrays.asList(n.x.acceptVisitor(this).instructions));

        instructions.add("pixelr" + debugComment(n));

        return new VisitResult(instructions.toArray(new String[0]));
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




        List<String> instructions = new ArrayList<>(Collections.singletonList(".main"));


        //open a stack frame and remove the frame from the symbol table
        instructions.add("push " + memory.pop().size() + " // stack frame for program");
        instructions.add("oframe");
        instructions.addAll(Arrays.asList(visitResult.instructions));
        //close the stack frame
        instructions.add("cframe");
        //halt
        instructions.add("halt");

        //add all the compiled functions
        for (List<String> compiledFunction : compiledFunctions){
            instructions.add("\n");
            instructions.addAll(compiledFunction);
        }

        return new VisitResult(instructions.toArray(new String[0]));
    }

    @Override
    public VisitResult visitReturnAstNode(ReturnAstNode n){
        VisitResult childVisit = n.x.acceptVisitor(this);

        List<String> instructions = new ArrayList<>(Arrays.asList(childVisit.instructions));

        //close the frame
        instructions.add("cframe");
        instructions.add("ret");

        return new VisitResult(instructions.toArray(new String[0]));


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
        return n.child.acceptVisitor(this);
    }

    @Override
    public VisitResult visitTypeLiteralAstNode(TypeLiteralAstNode n){
        return n.acceptVisitor(this);
    }

    @Override
    public VisitResult visitVarDeclAstNode(VarDeclAstNode n){

        VisitResult exprVisitRes = n.expr.acceptVisitor(this);

        String identifier = n.identifier.getVal();

        //add it to the symbol table
        memory.peek().add(identifier);

        //push the result of the expr
        List<String> instructions = new ArrayList<>(Arrays.asList(exprVisitRes.instructions));

        instructions.add("push " + memory.peek().indexOf(identifier) +" //index of " +identifier);
        instructions.add("push 0" + "//in stack frame 0");
        instructions.add("st" + debugComment(n));



        return new VisitResult(instructions.toArray(new String[0]));
    }

    /*
    * [A] expr
    not
    push location of to the 1st instruction after block [B]
    cjmp2 to [B] - if expr was false
    * block
    push location of expr [A]
    jmp to [A]
    [B] ...
     */
    @Override
    public VisitResult visitWhileAstNode(WhileAstNode n){

        List<String> instructions = new ArrayList<>();

        VisitResult condition = n.expr.acceptVisitor(this);
        VisitResult block = n.block.acceptVisitor(this);

        List<String> conditionInstructions = new ArrayList<>(Arrays.asList(condition.instructions));
        List<String> blockInstructions = new ArrayList<>(Arrays.asList(block.instructions));

        blockInstructions.add("push #PC-"+(blockInstructions.size()+3+conditionInstructions.size()));
        blockInstructions.add("jmp");

        conditionInstructions.add("not");
        conditionInstructions.add("push #PC+"+(blockInstructions.size()+2));
        conditionInstructions.add("cjmp2"+debugComment(n));

        instructions.addAll(conditionInstructions);
        instructions.addAll(blockInstructions);

        return new VisitResult(instructions.toArray(new String[0]));
    }
    
    public String debugComment(ASTNode n){
        return String.format(Locale.US,"    //(%s-%s) %s",lineNumberProvider.get(n.getSourceStart()),lineNumberProvider.get(n.getSourceEnd()),n.getClass().getSimpleName());
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
