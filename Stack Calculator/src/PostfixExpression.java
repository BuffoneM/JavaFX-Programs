/*
 *	Assignment 4
 * 	Michael Buffone
 * 	November 8th, 2019
 * 
 * 	Models a postfix expression
 */

import java.util.StringTokenizer;

public class PostfixExpression extends Expression {
	
	private String exp;
	
	// Constructor
	public PostfixExpression(String exp) {
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
		
		StringTokenizer st = new StringTokenizer(getExp());
		while(st.hasMoreTokens()) {
			String item = st.nextToken();
			
			// If item = operator, decrement count
			if(isOperator(item)) count--;
			// If the item is a bracket, return false
			else if(item.equals("(") || item.equals(")")) return false;
			// Increment count for each digit in the item
			else for(int i = 0; i < item.length(); i++) count++;
						
			if(count < 1) return false;
				
		}
		if(count == 1) return true;
		return false;
	}

	// Evaluate uses Stack class
	@Override
	public String evaluate() throws StackException {
		
		// Stack declaration for numbers
		Stack<String> element = new Stack<String>();
		
		StringTokenizer st = new StringTokenizer(getExp());
		while(st.hasMoreTokens()) {
			String item = st.nextToken();
			
			// If the item is a number, push it on the stack
			if(!isOperator(item)) element.push(item);
			// The item is an operator
			else {
				Double operand2 = Double.parseDouble(element.pop());
				Double operand1 = Double.parseDouble(element.pop());
				element.push("" + calculate(operand1, operand2, item.charAt(0)));
			}
		}
		
		return element.pop();
	}
	
	// ****** Utility Methods ******
	private boolean isOperator(String item) {
		return item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/");
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
