# hello-world
First repository 
import java.util.HashMap;


/**
 * CMSC 204 Assignment 2
 * Class Notation
 * 
 * @author Ha T DAo
 * 
 * Description: The Notation class will have a method infixToPostfix to convert infix notation to postfix notation that will 
 * take in a string and return a string, a method postfixToInfix to convert postfix notation to infix notation that will take 
 * in a string and return a string, and a method to evaluatePostfix to evaluate the postfix expression. It will take in a string 
 * and return a double. A method to evaluateInfix to evaluate the infix expression. It will take in a string and return a double. 
 * 
 */
public class Notation {

	  private static NotationQueue<String> nQueue;
	  private static NotationStack<String> nStack;
	  private final static String OPS = "+-*/";

	  
	  /**
	   * @return the element at the top of nStack
	   */
	  private static String stackTop() {
	    try {
	      return nStack.top();
	    } catch (StackUnderflowException e) {
	      e.getMessage();
	    }
	    return null;
	  }

	  /**
	   * @return the element at the top of nStack
	   */
	  private static String stackPop() {
	    try {
	      return nStack.pop();
	    } catch (StackUnderflowException e) {
	      e.getMessage();
	    }
	    return null;
	  }

	  /**
	   * Add an element to the top of NotationStack<String> nStack.
	   * 
	   * @param c - - the element to add at the top of the stack
	   * @return true if the add was succesful, false if not
	   */
	  private static boolean stackPush(String s) {
	    try {
	      return nStack.push(s);
	    } catch (StackOverflowException e) {
	      e.getMessage();
	    }
	    return false;
	  }

	  /**
	   * Add an element to the end of the NotationQueue<String> nQueue.
	   * 
	   * @param c - - the element to add at the end of the queue
	   * @return true if the add was succesful, false if not
	   */
	  private static boolean enqueue(String s) {
	    try {
	      return nQueue.enqueue(s);
	    } catch (QueueOverflowException e) {
	      e.getMessage();
	    }
	    return false;
	  }

	  /**
	   * Deletes and returns the element at the front of the NotationQueue<String> nQueue.
	   * 
	   * @return the element at the front of the queue
	   */
	  private static String dequeue() {
	    try {
	      return nQueue.dequeue();
	    } catch (QueueUnderflowException e) {
	      e.getMessage();
	    }
	    return null;
	  }
	 

	  /**
	   * Calculates the precedence of the operator
	   * 
	   * @param c - - the operator to evaluate for precedence
	   * @return 1 if the operator has high precedence and 0 if low precedence. -1 if not operator
	   */
	  private static int calculatePrec(char s) {
	    if (s == '*' || s == '/') {
	      return 1;
	    } else if (s == '+' || s == '-') {
	      return 0;
	    }
	    return -1;
	  }

	  /**
	   * Evaluate the correspondant operation between two operands
	   * 
	   * @param first - - the first operand
	   * @param second - - the second operand
	   * @param operator - - the operator
	   * @return the string representation of the result of the operation
	   * @throws InvalidNotationFormatException
	   */
	  private static String applyOperator(String first, String second, char operator)
	      throws InvalidNotationFormatException {
	    double a = Double.parseDouble(first);
	    double b = Double.parseDouble(second);
	    switch (operator) {
	      case '+':
	        return Double.toString(a + b);
	      case '-':
	        return Double.toString(a - b);
	      case '*':
	        return Double.toString(a * b);
	      case '/':
	        if (b == 0)
	          throw new InvalidNotationFormatException();
	        return Double.toString(a / b);
	    }
	    return null;
	  }

	

	  /**
	   * Convert an infix expression into a postfix expression
	   * 
	   * @param infix - - the postfix expression in string format
	   * @return the infix expression in string format
	   * @throws InvalidNotationFormatException - if the postfix expression format is invalid
	   */
	  public static String convertInfixToPostfix(String infix)
	      throws InvalidNotationFormatException {
	    nQueue = new NotationQueue<String>();
	    nStack = new NotationStack<String>();

	    for (int i = 0; i < infix.length(); i++) {
	      char cur = infix.charAt(i);
	      if (cur == ' ') {
	        continue;
	      } else if (Character.isDigit(cur)) {
	        enqueue(Character.toString(cur));
	      } else if (cur == '(') {
	        stackPush(Character.toString(cur));
	      } else if (OPS.indexOf(cur) >= 0) {
	        while (!nStack.isEmpty() && calculatePrec(stackTop().charAt(0)) >= calculatePrec(cur)) {
	          enqueue(stackPop());
	        }
	        stackPush(Character.toString(cur));
	      } else if (cur == ')') {
	        char top = stackPop().charAt(0);
	        while (top != '(') {
	          enqueue(Character.toString(top));
	          if (nStack.isEmpty()) {
	            throw new InvalidNotationFormatException();
	          } else {
	            top = stackPop().charAt(0);
	          }
	        }
	      }
	    }
	    while (!nStack.isEmpty()) {
	      enqueue(stackPop());
	    }
	    return nQueue.toString();
	  }

	  /**
	   * Convert an infix expression into a postfix expression
	   * 
	   * @param infix - - the infix expression in string format
	   * @return the postfix expression in string format
	   * @throws InvalidNotationFormatException - if the infix expression format is invalid
	   */
	  public static String convertPostfixToInfix(String postFix)
	      throws InvalidNotationFormatException {
	    nStack = new NotationStack<String>();
	    for (int i = 0; i < postFix.length(); i++) {
	      char cur = postFix.charAt(i);
	      if (cur == ' ') {
	        continue;
	      } else if (Character.isDigit(cur)) {
	        stackPush(Character.toString(cur));
	      } else if (OPS.indexOf(cur) >= 0) {
	        String a = stackPop().toString(), b, tmp;
	        if (nStack.isEmpty()) {
	          throw new InvalidNotationFormatException();
	        } else {
	          b = stackPop().toString();
	          tmp = '(' + b + cur + a + ')';
	          stackPush(tmp);
	        }
	      }
	    }
	    if (nStack.size() != 1) {
	      throw new InvalidNotationFormatException();
	    }
	    return stackPop();
	  }

	  /**
	     * This method takes a postfix expression to find its result.
	     * @param postfixExpr postfix expression to be evaluated
	     * @return result value of infix expression
	     */
	    public static double evaluatePostfixExpression(String postfixExpr) {
	    	NotationStack<Double> valueStack = new NotationStack<>(postfixExpr.length());
	        valueStack.push(Double.NaN);
	        
	        HashMap<String, Integer> precedence = new HashMap<>();
	        precedence.put("-", 1);
	        precedence.put("+", 2);
	        precedence.put("/", 3);
	        precedence.put("*", 4);
	        
	        for (char c : postfixExpr.toCharArray()) {
	                        
	            if (Character.isDigit(c)) {
	                valueStack.push(Double.parseDouble(String.valueOf(c)));
	            } else if (c == '-' || c == '+' || c == '/' || c == '*') {
	                if (valueStack.size() < 3) {
	                    throw new InvalidNotationFormatException();
	                }
	                double first = valueStack.pop(), second = valueStack.pop();
	                double result = (c == '-') ? second - first : 
	                        (c == '+') ? second + first : 
	                        (c == '/') ? second / first :
	                        (c == '*') ? second * first : 0;
	                valueStack.push(result);
	            } 
	            
	        }
	        
	        if (valueStack.size() > 2) {
	            throw new InvalidNotationFormatException();
	        }
	        
	        return valueStack.peek();
	    }
    /**
     * This method takes a infix expression to find its result.
     * @param infixExpr infix expression to be evaluated
     * @return result value of infix expression
     */
    public static double evaluateInfixExpression(String infixExpr) {
        
        return evaluatePostfixExpression(convertInfixToPostfix(infixExpr));
    }
    
}
