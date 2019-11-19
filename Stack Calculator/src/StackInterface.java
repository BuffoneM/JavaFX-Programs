/*
 * Assignment 4
 * Michael Buffone
 * November 8th, 2019
 * 
 * Interface implemented by calculator stack
 */

public interface StackInterface<T> {
	
	public T peek() throws StackException;
	public T pop() throws StackException;
	public void push(T item);
	public void popAll();
	public boolean isEmpty();
	public int size();
	
}