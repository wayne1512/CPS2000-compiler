import ast.ASTNode;
import backend.CodeGenerationVisitor;
import backend.Sematic.FunctionDeclarationProperties;
import backend.Sematic.FunctionDeclarationVisitor;
import backend.Sematic.SemanticVisitor;
import backend.ToXMLVisitor;
import exceptions.SemanticErrorException;
import exceptions.SyntaxErrorException;
import frontend.CharacterProvider;
import frontend.FileCharacterProvider;
import frontend.Lexer;
import frontend.Parser;

import java.io.*;
import java.util.Map;

public class Main{
    public static void main(String[] args){

        try (
                CharacterProvider cp = new FileCharacterProvider("in.txt");
                BufferedWriter intermediateOutputFile = new BufferedWriter(new FileWriter("intermediateXML.xml"));
                BufferedWriter compiledCodeOutputFile = new BufferedWriter(new FileWriter("output.txt"))
            ) {




            Lexer lexer = new Lexer(cp);
            Parser parser = new Parser(lexer);
            ASTNode root = parser.parse();


            String intermediateXML = root.acceptVisitor(new ToXMLVisitor());
            intermediateOutputFile.write(intermediateXML);
            System.out.println(intermediateXML);

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
                compiledCodeOutputFile.write(s + System.lineSeparator());
            }

            System.out.println("File compiled successfully - the output was saved to output.txt");


        } catch (IOException e) {
            System.err.println("Error while opening file");
            e.printStackTrace();
        } catch (SyntaxErrorException e) {
            System.err.println("Syntax Error:");
            System.err.println(e.getMessage());
        } catch (SemanticErrorException e){
            System.err.println("Semantic Error:");
            System.err.println(e.getMessage());
        }


    }
}
