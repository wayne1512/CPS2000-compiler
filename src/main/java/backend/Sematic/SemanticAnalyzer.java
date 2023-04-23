package backend.Sematic;

import ast.ASTNode;

public class SemanticAnalyzer{
    ASTNode root;

    public SemanticAnalyzer(ASTNode root){
        this.root = root;
    }

    public void analyze(){
        SemanticVisitor semanticVisitor = new SemanticVisitor();

        root.acceptVisitor(semanticVisitor);
    }
}
