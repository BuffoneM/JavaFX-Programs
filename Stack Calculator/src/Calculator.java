/*
 *	Assignment 4
 * 	Michael Buffone
 * 	November 8th, 2019
 * 
 * 	JavaFX calculator UI that utilizes InfixExpression and PostfixExpression
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Calculator extends Application {

	
	/*--------------------Pane object declaration--------------------*/
	// Infix / Postfix radio buttons
	ToggleGroup tgFixGroup = new ToggleGroup();
	RadioButton rbInfix = new RadioButton("Infix");
	RadioButton rbPostfix = new RadioButton("Postfix");
	
	// Operator buttons
	private TextField calcArea = new TextField();
	private Button btClear = new Button("C");
	private Button btAdd = new Button("+");
	private Button btMinus = new Button("-");
	private Button btMultiply = new Button("x");
	private Button btDivide = new Button("/");
	private Button btEquals = new Button("=");
	private Button btLeftBracket = new Button("(");
	private Button btRightBracket = new Button(")");
	private Button btSpace = new Button("Space");
	private Button btExample = new Button("Ex.");

	// Number buttons
	private Button btOne = new Button("1");
	private Button btTwo = new Button("2");
	private Button btThree = new Button("3");
	private Button btFour = new Button("4");
	private Button btFive = new Button("5");
	private Button btSix = new Button("6");
	private Button btSeven = new Button("7");
	private Button btEight = new Button("8");
	private Button btNine = new Button("9");
	private Button btZero = new Button("0");
	
	// Have a calculation type to allow modularity if another calculation type gets added in the future
	private int calculationType = 0;
	
	@Override
	public void start(Stage primaryStage) {
		
		// Main pane
		BorderPane mainPane = new BorderPane();
		mainPane.setPadding(new Insets(20, 20, 20, 20));
		
		// Create tempPane to prevent the text area from being selected
		StackPane tempPane = new StackPane();
		tempPane.setPrefSize(400, 40);
		Rectangle rect = new Rectangle(470, 45);
		rect.setStyle("-fx-background-color:#ffffff; -fx-opacity:0;");
		tempPane.getChildren().addAll(calcArea, rect);
		mainPane.setTop(tempPane);
		
		// Text box default at initial launch
		calcArea.setPromptText("Ex. ( 4 + 8 ) * ( 6 - 5 ) / ( ( 3 - 2 ) * ( 2 + 2 ) )");
		calcArea.setPrefSize(400, 40);
		calcArea.setStyle("-fx-font-size: 12pt;");
		calcArea.setEditable(false);
		calcArea.setFocusTraversable(false);
		
		
		/*--------------------Create buttons etc. with properties in a pane--------------------*/
		GridPane buttonPane = new GridPane();
		mainPane.setBottom(buttonPane);
		buttonPane.setAlignment(Pos.TOP_CENTER);
		buttonPane.setStyle("-fx-background-color:#eeeeee; -fx-opacity:1; -fx-border-style: solid inside;");
		buttonPane.setPadding(new Insets(10, 10, 10, 10));
		buttonPane.setVgap(10);
		buttonPane.setHgap(10);		
		
		// Set button size, space and example separate because of their sizing
		btSpace.setPrefSize(70, 40);
		btSpace.setStyle("-fx-font-size: 12pt;");
		btSpace.setFocusTraversable(false);
		btExample.setPrefSize(70, 40);
		btExample.setStyle("-fx-font-size: 12pt;");
		btExample.setFocusTraversable(false);
		setButtonSize(btClear);
		setButtonSize(btAdd);
		setButtonSize(btMinus);
		setButtonSize(btMultiply);
		setButtonSize(btDivide);
		setButtonSize(btEquals);
		setButtonSize(btLeftBracket);
		setButtonSize(btRightBracket);
		setButtonSize(btOne);
		setButtonSize(btTwo);
		setButtonSize(btThree);
		setButtonSize(btFour);
		setButtonSize(btFive);
		setButtonSize(btSix);
		setButtonSize(btSeven);
		setButtonSize(btEight);
		setButtonSize(btNine);
		setButtonSize(btZero);

		// Top row of the numbers
		buttonPane.add(btSeven, 1, 1);
		buttonPane.add(btEight, 2, 1);
		buttonPane.add(btNine, 3, 1);
		
		// Middle row of the numbers
		buttonPane.add(btFour, 1, 2);
		buttonPane.add(btFive, 2, 2);
		buttonPane.add(btSix, 3, 2);
		
		// Bottom row of the numbers
		buttonPane.add(btOne, 1, 3);
		buttonPane.add(btTwo, 2, 3);
		buttonPane.add(btThree, 3, 3);
		
		// Side row of operators
		buttonPane.add(btAdd, 4, 1);
		buttonPane.add(btMinus, 4, 2);
		buttonPane.add(btMultiply, 4, 3);
		buttonPane.add(btDivide, 4, 4);
		buttonPane.add(btLeftBracket, 5, 1);
		buttonPane.add(btRightBracket, 5, 2);
		buttonPane.add(btSpace, 6, 1);
		buttonPane.add(btExample, 6, 2);
		
		// Bottom row of operators
		buttonPane.add(btClear, 1, 4);
		buttonPane.add(btZero, 2, 4);
		buttonPane.add(btEquals, 3, 4);
		
		// Radio button grouping and other properties
		rbInfix.setSelected(true);
		rbInfix.setFocusTraversable(false);
		rbPostfix.setFocusTraversable(false);
		rbInfix.setToggleGroup(tgFixGroup);
		rbPostfix.setToggleGroup(tgFixGroup);
		rbInfix.setStyle("-fx-font-size: 12pt;");
		rbPostfix.setStyle("-fx-font-size: 12pt;");
		buttonPane.add(rbInfix, 5, 3);
		buttonPane.add(rbPostfix, 5, 4);
		
		
		/*--------------------Button actions--------------------*/

		// Number button actions
		btOne.setOnAction(e -> btOnePress());
		btTwo.setOnAction(e -> btTwoPress());
		btThree.setOnAction(e -> btThreePress());
		btFour.setOnAction(e -> btFourPress());
		btFive.setOnAction(e -> btFivePress());
		btSix.setOnAction(e -> btSixPress());
		btSeven.setOnAction(e -> btSevenPress());
		btEight.setOnAction(e -> btEightPress());
		btNine.setOnAction(e -> btNinePress());
		btZero.setOnAction(e -> btZeroPress());

		// Operator Button actions
		btSpace.setOnAction(e -> btSpacePress());
		btExample.setOnAction(e -> btExamplePress());
		btClear.setOnAction(e -> btClearPress());
		btAdd.setOnAction(e -> btAddPress());
		btMinus.setOnAction(e -> btMinusPress());
		btMultiply.setOnAction(e -> btMultiplyPress());
		btDivide.setOnAction(e -> btDividePress());
		btEquals.setOnAction(e -> btEqualsPress());
		btLeftBracket.setOnAction(e -> btLeftBracketPress());
		btRightBracket.setOnAction(e -> btRightBracketPress());	
		

		/*--------------------Create radio button functionality--------------------*/
		rbInfix.setOnAction(e -> {
			if(rbInfix.isSelected()) {
				calculationType = 0;
				calcArea.setPromptText("Ex. ( 4 + 8 ) * ( 6 - 5 ) / ( ( 3 - 2 ) * ( 2 + 2 ) )");
			}
		});
		rbPostfix.setOnAction(e -> {
			if(rbPostfix.isSelected()) {
				calculationType = 1;
				calcArea.setPromptText("Ex. 4 8 + 6 5 - * 3 2 - 2 2 + * /");
			}
		});
		
		
		/*--------------------Scene and stage--------------------*/
		Scene scene = new Scene(mainPane, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("JavaFX Calculator");
		primaryStage.show();
		
		
		/*--------------------Key presses--------------------*/
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.NUMPAD1 || e.getCode() == KeyCode.DIGIT1) btOnePress();
			if(e.getCode() == KeyCode.NUMPAD2 || e.getCode() == KeyCode.DIGIT2) btTwoPress();
			if(e.getCode() == KeyCode.NUMPAD3 || e.getCode() == KeyCode.DIGIT3) btThreePress();
			if(e.getCode() == KeyCode.NUMPAD4 || e.getCode() == KeyCode.DIGIT4) btFourPress();
			if(e.getCode() == KeyCode.NUMPAD5 || e.getCode() == KeyCode.DIGIT5) btFivePress();
			if(e.getCode() == KeyCode.NUMPAD6 || e.getCode() == KeyCode.DIGIT6) btSixPress();
			if(e.getCode() == KeyCode.NUMPAD7 || e.getCode() == KeyCode.DIGIT7) btSevenPress();
			if(e.getCode() == KeyCode.NUMPAD8 || e.getCode() == KeyCode.DIGIT8) btEightPress();
			if(e.getCode() == KeyCode.NUMPAD9 || e.getCode() == KeyCode.DIGIT9) btNinePress();
			if(e.getCode() == KeyCode.NUMPAD0 || e.getCode() == KeyCode.DIGIT0) btZeroPress();
			
			if(e.getCode() == KeyCode.SPACE) btSpacePress();
			if(e.getCode() == KeyCode.C) btClearPress();
			if(e.getCode() == KeyCode.ADD) btAddPress();
			if(e.getCode() == KeyCode.SUBTRACT) btMinusPress();
			if(e.getCode() == KeyCode.MULTIPLY) btMultiplyPress();
			if(e.getCode() == KeyCode.DIVIDE) btDividePress();
			if(e.getCode() == KeyCode.ENTER) btEqualsPress();
			if(e.getCode() == KeyCode.COMMA) btLeftBracketPress();
			if(e.getCode() == KeyCode.PERIOD) btRightBracketPress();

		});
		
	} // end start()
	
	
	/*--------------------Create number button functionality--------------------*/
	private void btOnePress() {
		calcArea.appendText("1");
	}
	private void btTwoPress() {
		calcArea.appendText("2");
	}
	private void btThreePress() {
		calcArea.appendText("3");
	}
	private void btFourPress() {
		calcArea.appendText("4");
	}
	private void btFivePress() {
		calcArea.appendText("5");
	}
	private void btSixPress() {
		calcArea.appendText("6");
	}
	private void btSevenPress() {
		calcArea.appendText("7");
	}
	private void btEightPress() {
		calcArea.appendText("8");
	}
	private void btNinePress() {
		calcArea.appendText("9");
	}
	private void btZeroPress() {
		calcArea.appendText("0");
	}
	
	
	/*--------------------Create operator button functionality--------------------*/
	private void btSpacePress() {
		calcArea.appendText(" ");
	}
	private void btExamplePress() {
		switch(calculationType) {
		case 0:
			calcArea.clear();
			calcArea.setText("( 4 + 8 ) * ( 6 - 5 ) / ( ( 3 - 2 ) * ( 2 + 2 ) )");
			break;
		case 1:
			calcArea.clear();
			calcArea.setText("4 8 + 6 5 - * 3 2 - 2 2 + * /");
			break;
		}
	}
	
	/* 
	 * - If there is nothing in the textArea, append the symbol
	 * - If the previous char in the textArea has a space, don't print a space with the symbol
	 * - If the previous char doesn't have a space, print a space with the symbol
	 */
	private void btAddPress() {
		if(calcArea.getLength() == 0) calcArea.appendText(" + ");
		else if(calcArea.getText().charAt(calcArea.getLength()-1) == ' ') calcArea.appendText("+ ");
		else calcArea.appendText(" + ");
	}
	private void btMinusPress() {
		if(calcArea.getLength() == 0) calcArea.appendText(" - ");
		else if(calcArea.getText().charAt(calcArea.getLength()-1) == ' ') calcArea.appendText("- ");
		else calcArea.appendText(" - ");
	}
	private void btMultiplyPress() {
		if(calcArea.getLength() == 0) calcArea.appendText(" * ");
		else if(calcArea.getText().charAt(calcArea.getLength()-1) == ' ') calcArea.appendText("* ");
		else calcArea.appendText(" * ");
	}
	private void btDividePress() {
		if(calcArea.getLength() == 0) calcArea.appendText(" / ");
		else if(calcArea.getText().charAt(calcArea.getLength()-1) == ' ') calcArea.appendText("/ ");
		else calcArea.appendText(" / ");
	}
	private void btLeftBracketPress() {
		if(calcArea.getLength() == 0) calcArea.appendText(" ( ");
		else if(calcArea.getText().charAt(calcArea.getLength()-1) == ' ') calcArea.appendText("( ");
		else calcArea.appendText(" ( ");
	}
	private void btRightBracketPress() {
		if(calcArea.getLength() == 0) calcArea.appendText(" ) ");
		else if(calcArea.getText().charAt(calcArea.getLength()-1) == ' ') calcArea.appendText(") ");
		else calcArea.appendText(" ) ");
	}
	private void btEqualsPress() {
		
		// Set calculation type depending on what radio button is used
		switch(calculationType) {
		
		// Infix
		case 0:
			InfixExpression ie = new InfixExpression(calcArea.getText());
			
			// If the expression is valid, try to evaluate it
			if(ie.verify()) {
				try {
					calcArea.clear();
					calcArea.appendText(ie.evaluate());
				} catch (StackException se) {
					System.out.println(se.getMessage());
					calcArea.clear();
					calcArea.appendText("Invalid expression");
				}
			}
			else {
				calcArea.clear();
				calcArea.appendText("Invalid expression");
			}
			break;
			
		// Prefix
		case 1:
			PostfixExpression pe = new PostfixExpression(calcArea.getText());
			
			// If the expression is valid, try to evaluate it
			if(pe.verify()) {
				try {
					calcArea.clear();
					calcArea.appendText(pe.evaluate());
				} catch (StackException se) {
					System.out.println(se.getMessage());
					calcArea.clear();
					calcArea.appendText("Invalid expression");
				}
			}
			else {
				calcArea.clear();
				calcArea.appendText("Invalid expression");
			}
			break;
		} // end switch(calculationType)
		
	} // end equals()
	private void btClearPress() {
		calcArea.setText("");
	}

	
	/*--------------------Utility functions--------------------*/
	private void setButtonSize(Button bt) {
		bt.setPrefSize(40, 40);
		bt.setStyle("-fx-font-size: 12pt;");
		bt.setFocusTraversable(false);
	}

	public static void main(String[] args) {
		Application.launch();
		
	}
}