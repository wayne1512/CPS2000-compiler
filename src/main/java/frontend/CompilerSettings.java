package frontend;

public class CompilerSettings{
    private static CompilerSettings _instance;
    public boolean verboseASTTree = true;


    private CompilerSettings(){
    }

    public static CompilerSettings getInstance(){
        if (_instance == null)
            _instance = new CompilerSettings();
        return _instance;
    }
}
