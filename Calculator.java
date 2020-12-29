import generatedGrammar.calcGrammarLexer;
import generatedGrammar.calcGrammarParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Calculator {
    public static void main(String[] args) throws IOException {
        CharStream input = CharStreams.fromString("(2 * 2) / 3");
        calcGrammarLexer lexer = new calcGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        calcGrammarParser parser = new calcGrammarParser(tokens);
        ParseTree tree = parser.start();

        calcGrammarBaseVisitorImpl impl = new calcGrammarBaseVisitorImpl();
        Double result = impl.visit(tree);
        System.out.println("Result " + result);
    }
}