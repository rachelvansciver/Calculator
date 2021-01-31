import generatedGrammar.calcGrammarLexer;
import generatedGrammar.calcGrammarParser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;

public class Calculator extends Application implements EventHandler<ActionEvent> {
    static TextField text = new TextField();
    //displays expression
    static StringBuilder infix = new StringBuilder();
    //string for manipulating expressions
    static Button[] buttons = {
            //array of buttons
            new Button("7"), new Button("8"), new Button("9"), new Button("/"),
            new Button("4"), new Button("5"), new Button("6"), new Button("*"),
            new Button("1"), new Button("2"), new Button("3"), new Button("+"),
            new Button("0"), new Button("."), new Button("(-)"), new Button("-"),
            new Button("^"), new Button("C"), new Button("CE"), new Button("=")
    };
    static HashMap<Button, String> buttonMap;
    public static void main(String[] args) throws IOException {
        buttonMap = new HashMap<>();
        //adding values to hashmap
        buttonMap.put(buttons[0], "7");
        buttonMap.put(buttons[1], "8");
        buttonMap.put(buttons[2], "9");
        buttonMap.put(buttons[3], "/");
        buttonMap.put(buttons[4], "4");
        buttonMap.put(buttons[5], "5");
        buttonMap.put(buttons[6], "6");
        buttonMap.put(buttons[7], "*");
        buttonMap.put(buttons[8], "1");
        buttonMap.put(buttons[9], "2");
        buttonMap.put(buttons[10], "3");
        buttonMap.put(buttons[11], "+");
        buttonMap.put(buttons[12], "0");
        buttonMap.put(buttons[13], ".");
        buttonMap.put(buttons[14],"-");
        buttonMap.put(buttons[15],"-");
        buttonMap.put(buttons[16], "^");
        buttonMap.put(buttons[17],"C");
        buttonMap.put(buttons[18], "CE");
        buttonMap.put(buttons[19], "=");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        for(Button i: buttons){
            //adding handler and style options
            i.setOnAction(e -> {
                handle(e);
            });
            i.setMinSize(50, 50);
            i.setAlignment(Pos.CENTER);
            i.setStyle("-fx-font-size: 16");
            i.setStyle("-fx-font-family: 'Arial Black'");
        }
        text.setStyle("-fx-font-size: 16");
        GridPane grid = new GridPane();
        //gridpane for holding buttons
        grid.setStyle("-fx-background-image: url(https://www.grinnell.edu/sites/default/files/styles/carousel__image_feature/public/images/2020-01/mathboard.jpg?h=8fad2c98&itok=O82VnrU_)");
        /**background to make it pretty*/
        HBox txtBox = new HBox(text);
        txtBox.setStyle("-fx-min-width: 350");
        txtBox.setStyle("-fx-min-height: 55");
        txtBox.setAlignment(Pos.TOP_CENTER);
        HBox rowOne = new HBox(buttons[0], buttons[1], buttons[2], buttons[3]);
        rowOne.setAlignment(Pos.BASELINE_CENTER);
        rowOne.setStyle("-fx-min-width: 350");
        rowOne.setStyle("-fx-min-height: 55");
        rowOne.setSpacing(5);
        HBox rowTwo = new HBox(buttons[4], buttons[5], buttons[6], buttons[7]);
        rowTwo.setAlignment(Pos.BASELINE_CENTER);
        rowTwo.setStyle("-fx-min-width: 350");
        rowTwo.setStyle("-fx-min-height: 55");
        rowTwo.setSpacing(5);
        HBox rowThree = new HBox(buttons[8], buttons[9], buttons[10], buttons[11]);
        rowThree.setAlignment(Pos.BASELINE_CENTER);
        rowThree.setStyle("-fx-min-width: 350");
        rowThree.setStyle("-fx-min-height: 55");
        rowThree.setSpacing(5);
        HBox rowFour = new HBox(buttons[12], buttons[13], buttons[14],buttons[15]);
        rowFour.setAlignment(Pos.BASELINE_CENTER);
        rowFour.setStyle("-fx-min-width: 350");
        rowFour.setStyle("-fx-min-height: 55");
        rowFour.setSpacing(5);
        HBox rowFive = new HBox(buttons[16], buttons[17], buttons[18], buttons[19]);
        rowFive.setAlignment(Pos.BASELINE_CENTER);
        rowFive.setStyle("-fx-min-width: 350");
        rowFive.setStyle("-fx-min-height: 55");
        rowFive.setSpacing(5);
        grid.addRow(0, txtBox);
        grid.addRow(1, rowOne);
        grid.addRow(2, rowTwo);
        grid.addRow(3, rowThree);
        grid.addRow(4, rowFour);
        grid.addRow(5, rowFive);
        /**adding style options to buttons and adding to gridpane container*/
        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid,400,400);
        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.show();

    }
    @Override
    public void handle(ActionEvent e) {
        /**adds text from buttons pressed and evaluates expression*/
        Object tmp = e.getSource();
        String expression = text.getText();
        if (tmp.equals(buttons[17])) {
            //if tmp == CLEAR
            infix.deleteCharAt(infix.length()-1);
            display(infix);
        } else if (tmp.equals(buttons[18])) {
            //if tmp == CLEAR EVERYTHING
            infix.delete(0, infix.length());
            display(infix);
        } else if (tmp.equals(buttons[19]) || tmp.equals(KeyEvent.VK_ENTER)) {
            //generating ANTLR lexer, tokens, parser, and evaluating expression
            CharStream input = CharStreams.fromString(expression);
            calcGrammarLexer lexer = new calcGrammarLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            calcGrammarParser parser = new calcGrammarParser(tokens);
            ParseTree tree = parser.start();
            calcGrammarBaseVisitorImpl impl = new calcGrammarBaseVisitorImpl();
            Double result = impl.visit(tree);
            infix.delete(0,infix.length());
            infix.append(String.valueOf(result));
            display(infix);
        } else {
            infix.append(buttonMap.get(tmp));
            display(infix);
        }
    }
    public void display(StringBuilder s){
        text.setText(String.valueOf(s));
    }
}



