import ast.ASTNode;
import ast.nodes.ColourLiteralAstNode;
import ast.nodes.LiteralAstNode;
import backend.Sematic.SemanticVisitor;
import backend.ToXMLVisitor;
import exceptions.SyntaxErrorException;
import frontend.CharacterProvider;
import frontend.FileCharacterProvider;
import frontend.Lexer;
import frontend.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws FileNotFoundException{

        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);

        SemanticVisitor semanticVisitor = new SemanticVisitor();
        ASTNode r = new LiteralAstNode(0,0,new ColourLiteralAstNode(0,0,"#123123"));

        SemanticVisitor.VisitResult res = r.acceptVisitor(semanticVisitor);
        System.out.println(res);


        try (CharacterProvider cp = new FileCharacterProvider("in.txt")) {

            Lexer lexer = new Lexer(cp);
            Parser parser = new Parser(lexer);
            ASTNode root = parser.parse();
            System.out.println(root.acceptVisitor(new ToXMLVisitor()));


        } catch (IOException e) {
            System.err.println("Error while opening file");
            e.printStackTrace();
        } catch (SyntaxErrorException e) {
            throw new RuntimeException(e);
        }


    }
}
