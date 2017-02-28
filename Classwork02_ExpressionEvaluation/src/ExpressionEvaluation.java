
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
 * @author 1320100021
 */
public class ExpressionEvaluation {

    Scanner sc = new Scanner(System.in);

    //output queue from expression
    Queue<String> output = new LinkedList();

    //initializations
    //takes an mathematical expression as input
    public void init() {
        System.out.println("Enter the Mathematical Expression:");
        String mathExp = sc.nextLine();

        extractExp(mathExp);
        calculateFinalValue(output);

    }

    //takes a queue and calculates the result from the values
    private void calculateFinalValue(Queue<String> outputQueue) {
        Stack<String> calcStack = new Stack();
        String operators = "", operands = "";

        try {
            while (!outputQueue.isEmpty()) {//output has values

                String token = outputQueue.poll();

                if (token.equals("+")) {//operator
                    operators += token + " ";

                    int rightNumber = Integer.parseInt(calcStack.pop());
                    int leftNumber = Integer.parseInt(calcStack.pop());
                    int result = leftNumber + rightNumber;
                    calcStack.push(result + "");
                    //System.out.println(calcStack.peek());
                    //System.out.println(leftNumber+" "+rightNumber+" "+calcStack.peek());

                } else if (token.equals("-")) {//operator
                    operators += token + " ";

                    int rightNumber = Integer.parseInt(calcStack.pop());
                    int leftNumber = Integer.parseInt(calcStack.pop());
                    int result = leftNumber - rightNumber;
                    calcStack.push(result + "");
                    //System.out.println(calcStack.peek());
                    //System.out.println(leftNumber+" "+rightNumber+" "+calcStack.peek());

                } else if (token.equals("*")) {//operator
                    operators += token + " ";

                    int rightNumber = Integer.parseInt(calcStack.pop());
                    int leftNumber = Integer.parseInt(calcStack.pop());
                    int result = leftNumber * rightNumber;
                    calcStack.push(result + "");
                    //System.out.println(calcStack.peek());
                    //System.out.println(leftNumber+" "+rightNumber+" "+calcStack.peek());

                } else if (token.equals("/")) {//operator
                    operators += token + " ";

                    int rightNumber = Integer.parseInt(calcStack.pop());
                    int leftNumber = Integer.parseInt(calcStack.pop());
                    int result = leftNumber / rightNumber;
                    calcStack.push(result + "");
                    //System.out.println(calcStack.peek());
                    //System.out.println(leftNumber+" "+rightNumber+" "+calcStack.peek());

                } else {//operand, push to stack
                    //System.out.println("oprnd "+calcStack.peek());
                    operands += token + " ";

                    calcStack.push(token);
                }
            }
            System.out.println("Operators: " + operators + "; Operands: " + operands);
            System.out.println("Result: " + calcStack.peek());
        } catch (Exception e) {
            System.err.println("Invalid Expression!");
        }

        output.clear();
        outputQueue.clear();
        calcStack.clear();
    }

    //takes a string, split it and add the tokens to output queue
    public void extractExp(String mathExp) {
        String[] tokenArray = mathExp.split("\\s+");
        System.out.println(Arrays.toString(tokenArray));

        for (int i = 0; i < tokenArray.length; i++) {
            output.add(tokenArray[i]);//add to queue
        }
        //showQueueValue(output);//prints output (queue) values
    }

    //takes a queue and print the values
    public void showQueueValue(Queue<String> out) {
        System.out.print("Output queue: ");
        for (String s : out) {
            System.out.print(s.toString());
        }
        System.out.println();
    }
}
