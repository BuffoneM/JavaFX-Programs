/*
 *	Michael Buffone 
 * 	3/06/2019
 * 	COSC1047W19
 * 	Assignment 6 Question A2 (16.13 pg 685)
 * 	This program allows the user to enter a loan amount and loan period have have it display total payments
 */

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class PartA2 extends Application {

	private TextField tfLoanAmount = new TextField();
	private TextField tfLoanPeriod = new TextField();
	private Button btCalculate = new Button("Show Table");
	private Button btClear = new Button("Clear");
	private TextArea taLoanPane = new TextArea(textAreaDefaultLabel());

	@Override
	public void start(Stage primaryStage) {
		
		tfLoanAmount.setPromptText("Ex. 10000");
		tfLoanPeriod.setPromptText("Ex. 5");
		
		/*--------------------Create buttons etc. with properties in a pane--------------------*/
		BorderPane mainPane = new BorderPane();
		taLoanPane.setStyle("-fx-font-family: monospace");

		GridPane buttonPane = new GridPane();
		buttonPane.setPadding(new Insets(10, 10, 10, 10));
		buttonPane.setVgap(10);
		buttonPane.setHgap(10);
		
		buttonPane.add(new Label("Loan Amount:"), 0, 0);
		buttonPane.add(tfLoanAmount, 1, 0);
		buttonPane.add(new Label("Number of Years:"), 2, 0);
		buttonPane.add(tfLoanPeriod, 3, 0);
		buttonPane.add(btCalculate, 4, 0);
		buttonPane.add(btClear, 5, 0);

		GridPane.setHalignment(btCalculate, HPos.RIGHT);

		mainPane.setTop(buttonPane);

		/*--------------------Create text area--------------------*/
		mainPane.setCenter(taLoanPane);
		taLoanPane.setEditable(false);

		btCalculate.setOnAction(e -> printLoan());
		btClear.setOnAction(e -> clearTextFields());
		
		/*--------------------Scene and stage--------------------*/
		Scene scene = new Scene(mainPane, 650, 300);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Interest Rate Calculator");
		primaryStage.show();

	}

	private void printLoan() {
		try {
			// Get the values needed for the calculations
			double loanAmount = Double.parseDouble(tfLoanAmount.getText());
			double loanPeriod = Double.parseDouble(tfLoanPeriod.getText());
			
			// Calculate the payments in the loop and add them to the text area
			for(double i = 5; i <= 8; i += 0.125) {
				
				double monthlyPayment = loanAmount * (i / 1200) / (1 - 1 / Math.pow(1 + (i / 1200),  loanPeriod * 12));
				double totalPayment = monthlyPayment * loanPeriod * 12;
				
				monthlyPayment = Math.round(monthlyPayment * 100) / 100.00;
				totalPayment = Math.round(totalPayment * 100) / 100.00;
				//System.out.println(i + "\t\t\t" + monthlyPayment + "\t\t\t" + totalPayment);
				
				taLoanPane.appendText("\n" + i + "\t\t\t" + monthlyPayment + "\t\t\t" + totalPayment);
			}
		}
		catch (Exception e) {
			taLoanPane.setText("Error calculating!");
		}
	}

	private void clearTextFields() {
		tfLoanAmount.setText("");
		tfLoanPeriod.setText("");
		taLoanPane.setText(textAreaDefaultLabel());
	}
	
	private String textAreaDefaultLabel() {
		return "Interest Rate\t\tMonthly Payment\t\tTotal Payment\n-------------------------------------------------------------";
	}

	public static void main(String[] args) {
		Application.launch();
	}
}

