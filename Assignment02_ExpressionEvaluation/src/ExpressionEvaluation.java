
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mti
 */
public class ExpressionEvaluation {

    Scanner sc = new Scanner(System.in);

    String[] identiValues;
    String[] identifiers;
    int[] values;//value and identifiers refer to same array index
    String[] expressions;//math expressions stored
    //String[]expToken;//keeps exp string tokens
    Stack<String> opeParStack = new Stack();//operators and parenthesis
    //Stack <String> output = new Stack();//outputs
    Queue<String> output = new LinkedList();

    //initializations: takes inputs and call necessary methods
    public void init() {

        //input identifiers and corresponding values
        System.out.println("Number of identifiers & values: ");
        int n = sc.nextInt();

        identiValues = new String[n];
        identifiers = new String[n];
        values = new int[n];
        sc.nextLine();

        System.out.println("First Identifiers and then values: ");
        for (int i = 0; i < n; i++) {
            identiValues[i] = sc.nextLine();
        }
        System.out.println(Arrays.toString(identiValues));

        //input expressions
        System.out.println("Number of Expressions: ");
        int m = sc.nextInt();
        expressions = new String[m];
        sc.nextLine();

        System.out.println("Expressions: (use at least one space to seperate)");
        for (int i = 0; i < m; i++) {
            expressions[i] = sc.nextLine();
        }
        System.out.println(Arrays.toString(expressions));

        extractIdentiValues();//seperate identifiers and values and stored in diff arrys

        for (int i = 0; i < expressions.length; i++) {
            extractExpression(expressions[i]);

            //stack and output status    
            //showQueueValue(output);
            //showStackValue(opeParStack);
        }
    }

    //takes a output queue and calculates the final result
    private void calculateFinalValue(Queue<String> outputQueue) {
        Stack<String> calcStack = new Stack();

        try {
            System.out.println("calc stages: ");

            while (!outputQueue.isEmpty()) {//output has values

                String token = outputQueue.poll();

                if (token.equals("+")) {//operator
                    int rightNumber = Integer.parseInt(calcStack.pop());
                    int leftNumber = Integer.parseInt(calcStack.pop());
                    int result = leftNumber + rightNumber;
                    calcStack.push(result + "");
                    //System.out.println(calcStack.peek());
                    System.out.println(leftNumber + " " + rightNumber + " " + calcStack.peek());

                } else if (token.equals("-")) {//operator
                    int rightNumber = Integer.parseInt(calcStack.pop());
                    int leftNumber = Integer.parseInt(calcStack.pop());
                    int result = leftNumber - rightNumber;
                    calcStack.push(result + "");
                    //System.out.println(calcStack.peek());
                    System.out.println(leftNumber + " " + rightNumber + " " + calcStack.peek());

                } else if (token.equals("*")) {//operator
                    int rightNumber = Integer.parseInt(calcStack.pop());
                    int leftNumber = Integer.parseInt(calcStack.pop());
                    int result = leftNumber * rightNumber;
                    calcStack.push(result + "");
                    //System.out.println(calcStack.peek());
                    System.out.println(leftNumber + " " + rightNumber + " " + calcStack.peek());

                } else if (token.equals("/")) {//operator
                    int rightNumber = Integer.parseInt(calcStack.pop());
                    int leftNumber = Integer.parseInt(calcStack.pop());
                    int result = leftNumber / rightNumber;
                    calcStack.push(result + "");
                    //System.out.println(calcStack.peek());
                    System.out.println(leftNumber + " " + rightNumber + " " + calcStack.peek());

                } else {//operand, push to stack
                    //System.out.println("oprnd "+calcStack.peek());
                    calcStack.push(getIdentifiersValue(token) + "");
                }
            }
            System.out.println("-------------------");
            System.out.println("Final result: " + calcStack.peek());
            System.out.println("-------------------");
        } catch (Exception e) {
            System.err.println("Exception found in calculateFinalValue");
        }

        calcStack.clear();
    }

    //takes a string of expression and evaluate it and keeps in output queue
    private void extractExpression(String exp) {
        String expToken[] = exp.split("\\s+");
        //System.out.println(Arrays.toString(expToken));
        boolean isCompiled = true;

        try {
            for (int i = 0; i < expToken.length; i++) {

                if (isOperand(expToken[i])) {//operand, add to output
                    output.add(expToken[i]);
                    //System.out.println("1 operand output push " + expToken[i]);

                } else if (expToken[i].equals("(")) {//opening parenthesis, push to stack
                    opeParStack.push(expToken[i]);
                    //System.out.println("2 (, stack push " + "(");

                } else if (expToken[i].equals("+") || expToken[i].equals("-")
                        || expToken[i].equals("*") || expToken[i].equals("/")) {//operator

                    if (opeParStack.isEmpty()) {//stack empty, push to stack
                        opeParStack.push(expToken[i]);
                        //System.out.println("3 stack empty, stack push " + expToken[i]);

                    } else if (opeParStack.peek().equals("(")) {//stack top (, push to stack
                        opeParStack.push(expToken[i]);
                        //System.out.println("4 stack top (, stack push " + expToken[i]);

                    } else if (isHigherPriority(expToken[i])) {//is higher priority than stack top
                        opeParStack.push(expToken[i]);//push to stack
                        //System.out.println("5 higher priority, stack push " + expToken[i]);

                    } else {//lower or equal priority
                        output.add(opeParStack.pop());//pop from stack and add to output
                        opeParStack.push(expToken[i]);//push current operator to stack
                        //System.out.println("6 lower priority, stack pop, output push, stack push " + expToken[i]);
                    }

                } else if (expToken[i].equals(")")) {//closing parenthesis

                    while (!opeParStack.peek().equals("(")) {//while "(" not found
                        //showStackValue(opeParStack);
                        output.add(opeParStack.pop());//pop from stack and add to output
                    }
                    opeParStack.pop();//discard ")" from stack
                    //System.out.println("7 stack pop output push " + expToken[i]);

                } else {//can't evaluat expression from given identifiers (unknown identifiers/token)
                    System.out.println("Compilation Error");
                    isCompiled = false;
                    break;
                }
            }

            while (!opeParStack.isEmpty()) {//all remaining operators
                output.add(opeParStack.pop());//pop from stack and add to output
                //System.out.println("final stack pop output push");
            }
        } catch (Exception e) {
            System.err.println("exception found in extractExpression");
        }

        if (isCompiled) {
            showQueueValue(output);
            calculateFinalValue(output);//calculate final result
            opeParStack.clear();
            output.clear();
        } else {
            opeParStack.clear();
            output.clear();
        }
    }

    //splits the string of identifiers and values and put them in individual arrays
    private void extractIdentiValues() {
        for (int i = 0; i < identiValues.length; i++) {
            String sArr[] = identiValues[i].split("\\s*\\=\\s*");
            identifiers[i] = sArr[0];
            values[i] = Integer.parseInt(sArr[1]);
        }
        //System.out.println(Arrays.toString(identifiers));
        //System.out.println(Arrays.toString(values));
    }

    //take and identifier and return its value
    private int getIdentifiersValue(String identi) {
        for (int i = 0; i < identifiers.length; i++) {
            if (identifiers[i].equals(identi)) {
                return values[i];
            }
        }
        return 0;
    }

    //take a operator and check if it has higher priority than stack top operator
    //if yes, return true, otherwise return false
    private boolean isHigherPriority(String opStr) {
        String opera[] = {"+", "-", "*", "/"};
        int prece[] = {1, 1, 2, 2};

        String stackTop = opeParStack.peek();

        int stTop = 0, opST = 0;
        for (int i = 0; i < opera.length; i++) {
            if (stackTop.equals(opera[i])) {
                stTop = prece[i];//priority value for stack top
            }
            if (opStr.equals(opera[i])) {
                opST = prece[i];//priority value for operator string
            }
        }
        if (opST > stTop) {//current operator has the higher priority than stack top operator
            return true;
        } else {
            return false;
        }
    }

    //check if the string token is an operand or not
    //if operand return true otherwise return false
    private boolean isOperand(String st) {
        for (int i = 0; i < identifiers.length; i++) {
            if (identifiers[i].equals(st)) {
                return true;
            }
        }
        return false;
    }

    //takes a stack and prints its values
    public void showStackValue(Stack<String> out) {
        System.out.print("Stack status: ");
        while (!out.isEmpty()) {
            System.out.print(out.pop() + " ");
        }
        System.out.println();
    }

    //takes a queue and prints its values
    public void showQueueValue(Queue<String> out) {
        System.out.print("Output queue: ");
        for (String s : out) {
            System.out.print(s.toString());
        }
        System.out.println();
    }
}
