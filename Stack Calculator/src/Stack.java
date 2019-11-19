/*
 * Assignment 3
 * Michael Buffone
 * November 8th, 2019
 * 
 * Generic stack that can be used with any type of object
 * 
 * This stack implements StackInterface and is used by InfixExpression
 * and PostfixExpression
 */

public class Stack<T> implements StackInterface<T> {
	
		// ****** Node definition ******
		private class Node {
			T item;
			Node next;
			
			public Node(T item, Node next) {
				this.item = item;
				this.next = next;
			}
		}
		
		// Stack variables
		private Node head;
		private int size;

		// Stack constructor
		public Stack() {
			head = null;
			size = 0;
		}
		
		// ****** Utility methods ******
		// *** T return type ***
		@Override
		public T peek() throws StackException {
			if(isEmpty()) throw new StackException("StackException: Peek(): Empty stack!");
			return head.item;
		}

		@Override
		public T pop() throws StackException {
			if(isEmpty()) throw new StackException("StackException: Pop(): Empty stack!");
			else {
				T temp = head.item;
				head = head.next;
				size -= 1;
				return temp;
			}
		}

		// *** Void return type ***
		@Override
		public void push(T item) {
			head = new Node(item, head);
			size += 1;
		}

		@Override
		public void popAll() {
			head = null;
		}

		@Override
		public boolean isEmpty() {
			return head == null;
		}

		@Override
		public int size() {
			return size;
		}
		
		// Print stack
		public void print(String stackName) {
			// Save head
			Node headCopy = head;
			
			// Print 
			System.out.println("--- " + stackName + " ---");
			while (head != null) {
				System.out.println(head.item);
				head = head.next;
			}
			
			// Restore head
			head = headCopy;
		}

}
