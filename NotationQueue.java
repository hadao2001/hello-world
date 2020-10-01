package Notation;

import java.util.ArrayList;
import java.util.Objects;


/**
 * CMSC 204 Assignment 2
 * Class: MyQueue
 * 
 * @author Ha T Dao
 * 
 * Description: MyQueue will implement the QueueInterface. 
 * 
 */

public class NotationQueue<T> implements QueueInterface<T>{
    
	private Object[] queue_Array; 
	private int size; //Size of Queue
	private int numElements; //Number of elements on Queue
	private int top;
	private int bottom;

	
	/**
	 * default constructor - uses a default as the size of the queue		
	 */
	public NotationQueue() {
		    size = 10;
			queue_Array = new Object[size];

		}
	/*
	 * MyQueue
	 * @param Integer for the size of the Queue
	 *
	 */
	public NotationQueue (int size){
		this.size = size;
		this.numElements = 0;
		this.top = this.bottom = -1;
		queue_Array = new Object[size];
	}
	
	/**
	 * isEmpty
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	@Override
	  public boolean isEmpty() {
	    if(numElements == 0) {
			return true;
		} 
		else {
			return false;
		}
	  }

	  /**
	   * Determines if Queue is full
	   * @return true if Queue is full, false if not
	   */
	  @Override
	  public boolean isFull() {
		  if(numElements == size) {
				return true;
			} 
			else {
				return false;
			}
	  }
		
	  /**
	   * Deletes and returns the element at the front of the Queue
	   * @return the element at the front of the Queue
	   */
	  @Override
	  public boolean enqueue(T e) throws QueueOverflowException {
		    if (isFull()) {
		      throw new QueueOverflowException();
		    }
		    
		    if(isEmpty()) {
		      top = bottom = 0;
		    } else {
		      bottom++;
		    }
		    numElements++;
		    queue_Array[bottom] = e;
		    return true;
		  }
	  
	  /**
	   * Deletes and returns the element at the front of the Queue
	   * @return the element at the front of the Queue
	   */
	  @Override
	  public T dequeue() throws QueueUnderflowException {
	    if (isEmpty()) {
	      throw new QueueUnderflowException();
	    }
	    @SuppressWarnings("unchecked")
	    T firstInLine = (T) queue_Array[top];
	    if (firstInLine == null)
	      return null;
	    queue_Array[top] = null;
	    top++;
	    numElements--;
	    return firstInLine;
	  }
	  
	  /**
	   * size
	   * Number of elements in the Queue
	   * @return the number of elements in the Queue
	   */
	  @Override
		public int size() {
			return numElements;
			
		}
		
		/**
		 * Returns the string representation of the elements in the Queue, 
		 * the beginning of the string is the front of the queue
		 * @return string representation of the Queue with elements
	     */
		  @Override
		  public String toString() {
		    StringBuilder s = new StringBuilder();
		   
		    for (int i = top; i <= bottom; i++) {
		      s.append(queue_Array[i]);
		    }
		    return s.toString();
		  }

		  /**
		   * Returns the string representation of the elements in the Queue, 
		   * the beginning of the string is the front of the queue
		   * Place the delimiter between all elements of the Queue
		   * @param delimiter - string used to separate queue elements
		   * @return string representation of the Queue with elements separated with the delimiter
		   */
		  @Override
		  public String toString(String delimiter) {
		    StringBuilder s = new StringBuilder();
		    
		    for (int i = top; i < bottom; i++) {
		      s.append(queue_Array[i] + delimiter);
		    }
		    s.append(queue_Array[bottom]);
		    return s.toString();
		  }

		  /**
		   * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
		   * is the first element in the Queue
		   * @param list elements to be added to the Queue
		   */
		  @Override
		  public void fill(ArrayList<T> list) throws QueueOverflowException{
				System.out.println("The sizeOfQueue = " + size);

				int tempFront = 0;
				if( list.size() > size) {
					System.out.println("Entry list is greater than the size of the queue");
					throw new QueueOverflowException();
				}
				else {
					for(T s : list) {
						System.out.println("The tempFront = " + tempFront + " , s = " + s);
						queue_Array[tempFront] = s;
						System.out.println("generticArray[" + tempFront + "] = " + queue_Array[tempFront]);
						tempFront++;
						numElements++;
					}
					

				}
		
			}
		 


	
}
