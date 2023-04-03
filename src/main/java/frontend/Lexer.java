package frontend;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import frontend.tokens.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

import static frontend.tokens.Token.*;

public class Lexer{

    CharacterProvider cp;

    public Lexer(CharacterProvider cp){
        this.cp = cp;
    }



    public Token nextToken(){

        //ket for debugging purposes
        long tokenStart = -1;


        //the current state in the DSA - set to start state
        String currentState = transitionStateHeaders[0];

        StringBuilder lexeme = new StringBuilder();


        Stack<String> stateStack = new Stack<>();
        stateStack.push(null);

        //current state is empty when it will return an error due to an undefined transition
        while (!currentState.isEmpty()){


            Character c = cp.next();

            if (lexeme.length() == 0 && c!=null){
                while (Character.isWhitespace(c))
                    c = cp.next();
                tokenStart = cp.getPointer();
            }

            if (c == null)
                break;

            String charClass = classifierMap.get(c);
            lexeme.append(c);

            if (acceptedStates.containsKey(currentState)){
                stateStack.clear();
            }

            stateStack.push(currentState);

            currentState = transitionState(currentState,charClass);
        }

        //end of file has been reached
        if (lexeme.length() == 0)
            return null;

        //rollback until we find the last accepted state
        while (!acceptedStates.containsKey(currentState) && currentState!=null){
            currentState = stateStack.pop();
            lexeme.deleteCharAt(lexeme.length() - 1);
            cp.rewind();
        }

        return new Token(acceptedStates.get(currentState).getType(lexeme.toString()),tokenStart,cp.getPointer(),lexeme.toString());
    }

    static Map<Character,String> classifierMap = new HashMap<>();

    static String[][] transition;
    static String[] transitionClassHeaders;
    static String[] transitionStateHeaders;

    static Map<String, TokenTypeFetcher> acceptedStates = new HashMap<>();

    static {
        //classifier
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(Lexer.class.getClassLoader().getResourceAsStream("CharClassification.csv")))) {
            //skip headers
            csvReader.skip(1);

            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                classifierMap.put(values[0].charAt(0),values[1]);
            }
        } catch (CsvValidationException | IOException | NullPointerException e) {
            throw new RuntimeException("internal error while reading internal CharClassification.csv",e);
        }



        //transition

        List<String> rowHeaders = new LinkedList<>();
        List<String> colHeaders;
        List<List<String>> transitions = new LinkedList<>();


        try (CSVReader csvReader = new CSVReader(new InputStreamReader(Lexer.class.getClassLoader().getResourceAsStream("LexerTransition.csv")))) {
            String[] values = null;

            //copy column headers, but remove the 1st column since it's empty
            colHeaders = new LinkedList<>(Arrays.asList(csvReader.readNext()));
            colHeaders.remove(0);

            while ((values = csvReader.readNext()) != null) {
                //add the row header
                rowHeaders.add(values[0]);
                //add the cells
                transitions.add(Arrays.asList(values).subList(1,values.length));
            }

            transitionClassHeaders = colHeaders.toArray(new String[0]);
            transitionStateHeaders = rowHeaders.toArray(new String[0]);

            transition = transitions.stream()
                    .map(l -> l.toArray(new String[l.size()]))
                    .toArray(String[][]::new);

        } catch (CsvValidationException | IOException | NullPointerException e) {
            throw new RuntimeException("internal error while reading internal CharClassification.csv",e);
        }




        acceptedStates.put("sysFunc",lexeme -> {
            switch (lexeme){
                case "__print": return TokenType.Print;
                case "__width": return TokenType.PadWidth;
                case "__height": return TokenType.PadHeight;
                case "__read": return TokenType.PadRead;
                case "__randi": return TokenType.PadRandI;
                case "__delay": return TokenType.Delay;
                default: return null;
            }
        });
        acceptedStates.put("int", lexeme -> TokenType.Int);
        acceptedStates.put("float", lexeme -> TokenType.Float);
        acceptedStates.put("colour",lexeme -> TokenType.Colour);
        acceptedStates.put("word",null);
        acceptedStates.put("equals",null);
        acceptedStates.put("exclamation",null);
        acceptedStates.put("relop.GT",null);
        acceptedStates.put("relop.LT",null);
        acceptedStates.put("relop.EQ",null);
        acceptedStates.put("relop.NE",null);
        acceptedStates.put("relop.GTE",null);
        acceptedStates.put("relop.LTE",null);
        acceptedStates.put("mulOp.Mul", lexeme -> TokenType.Multiply);
        acceptedStates.put("mulOp.Div", lexeme -> TokenType.Divide);
        acceptedStates.put("addOp.Add",null);
        acceptedStates.put("addOp.Sub",null);

    }


    protected String transitionState(String state, String charClass){

        int row = Arrays.asList(transitionStateHeaders).indexOf(state);
        int col = Arrays.asList(transitionClassHeaders).indexOf(charClass);

        if (row == -1)
            throw new RuntimeException("state is not defined in trasition function " + state);

        if (col == -1)
            return "";

        return transition[row][col];
    }

    public interface TokenTypeFetcher{
        public TokenType getType(String lexeme);
    }
}
