package Notation;

import java.util.ArrayList;
import java.util.Stack;

/**
 * CMSC 204 Assignment 2
 * Class: MyStack
 * 
 * @author Ha T DAo
 * 
 * Description: MyStack will implement the StackInterface. 
 * Implementation of a generic stack data structure.
 
 * @param <T> data type
 */

public class NotationStack<T> implements StackInterface<T> {
    
	private T[] stack_Array; 
	private int size; //Size of Stack
	private int numElements; //Number of elements on Stack
	private int top;
	private int bottom;

	
	/*
	 * MyStack
	 * @param Integer that is the size of the Stack
	 */
	@SuppressWarnings("unchecked")
	public NotationStack(int s) {
		this.size = s;
	    this.top = this.bottom = -1;
	    this.numElements = 0;
		stack_Array = (T[]) new Object[size];
		
	}
	
	
	public NotationStack() {
		size = 5;
		top = -1;
		stack_Array = (T[]) new Object[size];

	}
	
	
	
	
	/**
	 * isEmpty
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty() {
		if(top == -1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * isFull
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		if(size== numElements) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 */
	  @Override
	  public T pop() throws StackUnderflowException {
			int topIndex; //Top is the end of the array list
			
			if(top == -1) {
				throw new StackUnderflowException();
			}
			else {
				topIndex = top;
				top--; //Decrement the top of the stack
				numElements--; //Decrement the number of stack elements
				return stack_Array[topIndex];
			}
	  }
	
	  /**
		* peek
		* Returns the element at the top of the Stack, does not pop it off the Stack
	    * @return the element at the top of the Stack
		*/
	
		public T peek() throws StackUnderflowException{		
			if(top == -1) {
				throw new StackUnderflowException();
			}
			else {
				return stack_Array[top];
			}

			
		}
	  
	  /**
	   * Returns the element at the top of the Stack, does not pop it off the Stack
	   * @return the element at the top of the Stack
	   */
	  @Override
	  public T top() throws StackUnderflowException {
	    if (isEmpty()) {
	      throw new StackUnderflowException();
	    }
	    @SuppressWarnings("unchecked")
	    T firstInTop = (T) stack_Array[bottom];
	    return firstInTop;
	  }

	  /**
	   * Number of elements in the Stack
	   * @return the number of elements in the Stack
	   */
	  @Override
	  public int size() {
	    return numElements;
	  }
	  
	  /**
	   * push
	   * Adds an element to the top of the Stack
	   * @param e the element to add to the top of the Stack
	   * @return true if the add was successful, false if not
	   */
		public boolean push(T e) throws StackOverflowException{
			if (isFull()) {
			      throw new StackOverflowException();
			    }

			else {
				top++;
				stack_Array[top] = e;
				numElements++; //Increment the number of stack elements
				return true;
			}
			  }
		/**
		 * Returns the elements of the Stack in a string from bottom to top, the beginning 
		 * of the String is the bottom of the stack
		 * @return an string which represent the Objects in the Stack from bottom to top
		 */
		  @Override
		  public String toString() {
		    StringBuilder s = new StringBuilder();

		    for (int i = top; i <= bottom; i++) {
		      s.append(stack_Array[i]);
		    }
		    return s.toString();
		  }
	
		  /**
		   * Returns the string representation of the elements in the Stack, the beginning of the 
		   * string is the bottom of the stack
		   * Place the delimiter between all elements of the Stack
		   * @param delimiter - String to separate the elements of the stack
		   * @return string representation of the Stack from bottom to top with elements 
		   * separated with the delimiter
		   */
		  @Override
		  public String toString(String delimiter) {
			  StringBuilder s = new StringBuilder();

			    for (int i = top; i < bottom; i++) {
			      s.append(stack_Array[i] + delimiter);
			    }
			    s.append(stack_Array[bottom]);
			    return s.toString();
		}
        

		  /**
		   * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
		   * is the first bottom element of the Stack
		   * @param list elements to be added to the Stack from bottom to top
		   */
		  @Override
		  public void fill(ArrayList<T> list) {
			  int tempTop = 0;
				
				if( list.size() > size) {
					throw new StackOverflowException();
				}
				else {
					for(T s : list) {
						stack_Array[tempTop] = s;
						top = tempTop;
						tempTop++;
						numElements++;
					}
					

				}
			}

		  

	
}
	
	