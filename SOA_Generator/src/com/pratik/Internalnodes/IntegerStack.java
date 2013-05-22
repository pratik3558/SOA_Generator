/**
 * 
 */
package com.pratik.Internalnodes;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Pratik
 *
 */
public class IntegerStack {
	
	 private  Deque<Integer> data ;

	  public IntegerStack() {
		super();
		data = new ArrayDeque<Integer>();
	}

	public  void push(Integer element) {
	    data.addFirst(element);
	  }
	  
	  public  int returnSize()
	  {
		  return data.size();
	  }
	  
	  public  void empty()
	  {
		  data.clear();
	  }

	  public  Integer pop() {
	    return data.removeFirst();
	  }

	  public  Integer peek() {
	    return data.peekFirst();
	  }

	  public String toString() {
	    return data.toString();
	  }

}
