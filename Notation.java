package Notation;
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
/**
 * Converts the postfix expression to the infix expression
 * @param postfix The postfix expression in string format
 * @return The infix expression in string format
 * @throws InvalidNotationFormatException If the postfix expression format is invalid
 */
public class Notation {

	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix The infix expression in string format
	 * @return The postfix expression in a string format
	 * @throws InvalidNotationFormatException If the infix expression format is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {

		NotationQueue<Character> queue = new NotationQueue<>(infix.length());
		NotationStack<Character> stack = new NotationStack<>(infix.length());
		char[] str = infix.toCharArray();

		try {
			for (char current : str) {
				if (current == ' ') {
					continue;
				}
				if (Character.isDigit(current)) {
					queue.enqueue(current);
					continue;
				}
				if (current == '(') {
					stack.push(current);
				}
				if (current == '*' || current == '/' || current == '+' || current == '-') {
					if (!queue.isEmpty()) {
						char top = stack.top();
						if (top == '*' || top == '/' || current == '-' && top == '-' || current == '-' && top == '+'
								|| current == '+' && top == '-' || current == '+' && top == '+') {
							queue.enqueue(stack.pop());

						}
					}
					stack.push(current);
					continue;
				}
				if (current == ')') {
					while (stack.top() != '(') {
						queue.enqueue(stack.pop());
						if (stack.top() == null) {
							throw new InvalidNotationFormatException();
						}
					}
					stack.pop();
				}

			}
		} catch (QueueOverflowException | StackOverflowException | StackUnderflowException ignore) {
			throw new InvalidNotationFormatException();
		}
		return queue.toString();

	}
	/**
	 * Converts the postfix expression to the infix expression
	 * @param postfix The postfix expression in string format
	 * @return The infix expression in string format
	 * @throws InvalidNotationFormatException If the postfix expression format is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {

		NotationStack<String> stack = new NotationStack<>(postfix.length());

		try {
			for (int i = 0; i < postfix.length(); i++) {
				char current = postfix.charAt(i);

				if (current == ' ') {
					continue;
				}
				if (Character.isDigit(current)) {
					stack.push(Character.toString(current));
					continue;
				}
				if (current == '*' || current == '/' || current == '+' || current == '-') {
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					String first = stack.pop();
					String second = stack.pop();
					String s = "(" + second + current + first + ")";
					stack.push(s);

				}
			}

		} catch (StackUnderflowException | StackOverflowException ignore) {
			throw new InvalidNotationFormatException();
		}
		if (stack.size() > 1) {
			throw new InvalidNotationFormatException();
		}
		return stack.toString();
	
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