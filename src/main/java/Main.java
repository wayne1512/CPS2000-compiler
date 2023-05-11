import ast.ASTNode;
import backend.CodeGenerationVisitor;
import backend.Sematic.FunctionDeclarationProperties;
import backend.Sematic.FunctionDeclarationVisitor;
import backend.Sematic.SemanticVisitor;
import backend.ToXMLVisitor;
import exceptions.SyntaxErrorException;
import frontend.CharacterProvider;
import frontend.FileCharacterProvider;
import frontend.Lexer;
import frontend.Parser;

import java.io.IOException;
import java.util.Map;

public class Main{
    public static void main(String[] args){

        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);

        try (CharacterProvider cp = new FileCharacterProvider("in.txt")) {

            Lexer lexer = new Lexer(cp);
            Parser parser = new Parser(lexer);
            ASTNode root = parser.parse();



            System.out.println(root.acceptVisitor(new ToXMLVisitor()));

            //perform a semantic check pass

            //get a list of declared functions
            FunctionDeclarationVisitor functionDeclarationVisitor = new FunctionDeclarationVisitor(cp.createLineNumberProvider());
            root.acceptVisitor(functionDeclarationVisitor);
            Map<String, FunctionDeclarationProperties> functions = functionDeclarationVisitor.getFunctions();

            root.acceptVisitor(new SemanticVisitor(functions,cp.createLineNumberProvider()));

            //generate the code
            String[] generated = root.acceptVisitor(new CodeGenerationVisitor(cp.createLineNumberProvider())).instructions;
            for (String s : generated) {
                System.out.println(s);
            }


        } catch (IOException e) {
            System.err.println("Error while opening file");
            e.printStackTrace();
        } catch (SyntaxErrorException e) {
            throw new RuntimeException(e);
        }


    }
}
