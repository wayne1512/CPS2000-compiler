package backend.Sematic;

import ast.Type;

public class FunctionDeclarationProperties{

    public FunctionDeclarationProperties(Type returnType, Type[] paramType){
        this.returnType = returnType;
        this.paramType = paramType;
    }

    public Type returnType;
    public Type[] paramType;
}
