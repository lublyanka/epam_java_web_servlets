package util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExpressionParser {

    private final Map<Character, Integer> variables = new HashMap<>();

    public int parse(String expression) {
        expression = expression.replaceAll("\\s+", ""); // remove spaces
        return evaluateExpression(expression);
    }

    private int evaluateExpression(String expression) {
        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        int currentIndex = 0;

        while (currentIndex < expression.length()) {
            char currentChar = expression.charAt(currentIndex);

            if (Character.isDigit(currentChar)) { // number
                int operand = 0;
                while (currentIndex < expression.length() && Character.isDigit(expression.charAt(currentIndex))) {
                    operand = operand * 10 + Character.getNumericValue(expression.charAt(currentIndex));
                    currentIndex++;
                }
                operandStack.push(operand);
            } else if (Character.isLetter(currentChar)) { // variable
                int value;
                try {
                    value = variables.get(currentChar);
                } catch (Exception e) {
                    throw new IllegalArgumentException("No such argument " + currentChar);
                }
                operandStack.push(value);
                currentIndex++;
            } else if (currentChar == '(') { // left parenthesis
                operatorStack.push(currentChar);
                currentIndex++;
            } else if (currentChar == ')') { // right parenthesis
                while (operatorStack.peek() != '(') {
                    int result = applyOperation(operatorStack.pop(), operandStack.pop(), operandStack.pop());
                    operandStack.push(result);
                }
                operatorStack.pop(); // remove '(' from operator stack
                currentIndex++;
            } else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') { // operator
                while (!operatorStack.empty() && hasHigherPrecedence(currentChar, operatorStack.peek())) {
                    int result = applyOperation(operatorStack.pop(), operandStack.pop(), operandStack.pop());
                    operandStack.push(result);
                }
                operatorStack.push(currentChar);
                currentIndex++;
            } else {
                throw new IllegalArgumentException("Invalid character in expression: " + currentChar);
            }
        }

        while (!operatorStack.empty()) {
            int result = applyOperation(operatorStack.pop(), operandStack.pop(), operandStack.pop());
            operandStack.push(result);
        }

        return operandStack.pop();
    }

    private boolean hasHigherPrecedence(char operator1, char operator2) {
        if (operator2 == '(' || operator2 == ')') {
            return false;
        } else return (operator1 != '*' && operator1 != '/') || (operator2 != '+' && operator2 != '-');
    }

    private int applyOperation(char operator, int operand2, int operand1) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public void setVariable(char variable, int value) {
        variables.put(variable, value);
    }
}