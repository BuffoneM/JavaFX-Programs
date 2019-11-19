/*
 *	Assignment 4
 * 	Michael Buffone
 * 	November 8th, 2019
 * 
 * 	Models an infix expression in which the operator comes between it's operands
 */

import java.util.StringTokenizer;

public class InfixExpression extends Expression {
	
	private String exp;
	
	// Constructor
	public InfixExpression(String exp) {
		this.exp = exp;
	}
	
	// Getters and Setters
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	
	@Override
	public boolean verify() {
		int count = 0;
		int countBracket = 0;
		String item = "";
		
		// Use string tokenizer to verify if the string expression is valid
		StringTokenizer st = new StringTokenizer(getExp());
		while(st.hasMoreTokens()) {

			item = st.nextToken();
			// The current item is an operator
			if(isOperator(item)) {
				count--;
			}
			// The current item is a left or right bracket
			else if(item.equals("(")) {
				countBracket++;
			}
			else if(item.equals(")")) {
				countBracket--;
			}
			// The current item is a number
			else count++;
			
		}
		// If the last element of the expression is an open bracket, it incomplete
		if(item.equals("(")) return false;
		if(count == 1 && countBracket == 0) return true;
		return false;
	}

	// Evaluate uses Stack class
	@Override
	public String evaluate() throws StackException {
		
		// Stack declaration
		Stack<String> operand = new Stack<String>();		// For numbers
		Stack<String> operator = new Stack<String>();		// For operators
		
		StringTokenizer st = new StringTokenizer(getExp());
		while(st.hasMoreTokens()) {

			String item = st.nextToken();
			
			// If character is number, push operand on stack
			if(!isOperator(item) && !item.equals("(") && !item.equals(")")) operand.push(item);	
			
			// If character is operator
			if(isOperator(item)) {
				// If the stack is empty, push the operator on the stack
				if(operator.isEmpty()) operator.push(item);
				
				else {
					// If the item precedence is >= operator.peek() precedence, push it on the stack
					if(precedence(item) >= precedence(operator.peek())) operator.push(item);
					else {
						
						while(!operator.isEmpty() && precedence(item)<precedence(operator.peek())) {
							
							// Pop operator from stack
							char tempOperator = operator.pop().charAt(0);
							// Pop the operands
							Double operand2 = Double.parseDouble(operand.pop());
							Double operand1 = Double.parseDouble(operand.pop());
							
							operand.push("" + calculate(operand1, operand2, tempOperator));

						}
					}
				}
			}
			
			// If character is (, push it on the operator stack
			if(item.equals("(")) operator.push(item);
			
			// If character is ), calculate everything in the brackets
			if(item.equals(")")) {
				
				while(!operator.peek().equals("(")) {
					// Pop operator from stack
					char tempOperator = operator.pop().charAt(0);
					// Pop the operands
					Double operand2 = Double.parseDouble(operand.pop());
					Double operand1 = Double.parseDouble(operand.pop());
					
					operand.push("" + calculate(operand1, operand2, tempOperator));
				}
				operator.pop();
			}
		} // end while()
		
		// Do the final calculation
		while(!operator.isEmpty()) {
			// Pop operator from stack
			char tempOperator = operator.pop().charAt(0);
			// Pop the operands
			Double operand2 = Double.parseDouble(operand.pop());
			Double operand1 = Double.parseDouble(operand.pop());
			
			operand.push("" + calculate(operand1, operand2, tempOperator));
		}
		
		return operand.pop();
	}
	
	// ****** Utility Methods ******
	private boolean isOperator(String item) {
		return item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/");
	}
	
	private int precedence(String operator) {
		if(operator.equals("/") || operator.equals("*")) return 2;
		return 1;
	}
	
	private String calculate(Double o1, Double o2, char op) {
		String answer = "";
		
		switch(op) {
		case '+':
			answer += o1 + o2;
			break;
		case '-':
			answer += o1 - o2;
			break;
		case '*':
			answer += o1 * o2;
			break;
		case '/':
			if(o2 != 0) answer += o1 / o2;
			else answer = "Error: Division by 0!";
			break;
		}
		return answer;
		
	}

}

