/*
 *	Michael Buffone 
 * 	3/01/2019
 * 	COSC1047W19
 * 	Assignment 5 Question A1 (15.5 pg 635)
 * 	This program will prompt the user to enter values and return a future value
 */
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class PartA1 extends Application{
	
	// Create the displayable objects for the program
	private TextField tfInvestAmount = new TextField();
	private TextField tfYearNum = new TextField();
	private TextField tfAnInRate = new TextField();
	private TextField tfFutureValue = new TextField();
	private Button btCalculate = new Button("Calculate");
	private Button btClear = new Button("Clear");
	
	@Override
	public void start(Stage primaryStart) {
		
		// Create the user interface
		GridPane pane = new GridPane();
		pane.setHgap(5);
		pane.setVgap(5);
		
		pane.add(new Label("Investment Amount:"), 0, 0);
		pane.add(tfInvestAmount, 1, 0);
		pane.add(new Label("Number of Years:"), 0, 1);
		pane.add(tfYearNum, 1, 1);
		pane.add(new Label("Annual Interest Rate:"), 0, 2);
		pane.add(tfAnInRate, 1, 2);
		pane.add(new Label("Future Value:"), 0, 3);
		pane.add(tfFutureValue, 1, 3);
		pane.add(btCalculate, 1, 4);
		pane.add(btClear, 1, 4);
		
		// Set properties / locations for objects in the user interface
		pane.setAlignment(Pos.CENTER);
		tfInvestAmount.setAlignment(Pos.BOTTOM_RIGHT);
		tfYearNum.setAlignment(Pos.BOTTOM_RIGHT);
		tfAnInRate.setAlignment(Pos.BOTTOM_RIGHT);
		tfFutureValue.setAlignment(Pos.BOTTOM_RIGHT);
		GridPane.setHalignment(btCalculate, HPos.RIGHT);
		GridPane.setHalignment(btClear, HPos.LEFT);

		
		tfFutureValue.setEditable(false);
		
		// Process events
		btCalculate.setOnAction(e -> calculateFutureValue());
		btClear.setOnAction(e -> clearTextFields());
		
		// Create the scene and add it to the stage
		Scene scene = new Scene(pane, 300, 200);
		primaryStart.setTitle("Future Value Calculator");
		primaryStart.setResizable(false);
		primaryStart.setScene(scene);
		primaryStart.show();
		
	}
	
	private void calculateFutureValue() {
		try {
			// Store the values and use them in the future value formula
			double invAmnt = Double.parseDouble(tfInvestAmount.getText());
			double year = Double.parseDouble(tfYearNum.getText());
			
			// Monthly rate = the percent / 12
			double rate  = Double.parseDouble(tfAnInRate.getText());
			rate /= 100.0;
			rate /= 12;
			
			double futureValue = invAmnt * Math.pow(1 + rate, year*12);
			futureValue = Math.round(futureValue * 100) / 100.00;
			
			// Set the textbox text
			tfFutureValue.setText("$" + futureValue);
			
		}
		catch(Exception e) {
			// Set the textbox text
			tfFutureValue.setText("Error!");
		}
	}
	
	private void clearTextFields() {
		tfInvestAmount.setText("");
		tfYearNum.setText("");
		tfAnInRate.setText("");
		tfFutureValue.setText("");
	}
	
	public static void main(String[] args) {
		Application.launch();
	}
}
