package frontend;

public class CompilerSettings{
    private static CompilerSettings _instance;

    private CompilerSettings(){}


    public boolean verboseASTTree = true;




    public static CompilerSettings getInstance(){
        if (_instance == null)
            _instance = new CompilerSettings();
        return _instance;
    }
}
