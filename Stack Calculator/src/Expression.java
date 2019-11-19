/*
 *	Assignment 4
 * 	Michael Buffone
 * 	November 8th, 2019
 * 
 * 	Abstract expression class that models a mathematical expression
 */

public abstract class Expression {
	
	public abstract boolean verify();
	public abstract String evaluate() throws StackException;

}
