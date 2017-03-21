
import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mti
 */
public class RegularExpression {

    Scanner sc = new Scanner(System.in);

    String[] regex_patterns;//keeps the patterns as string
    String[] string_lines;//keeps string lines
    //initializes and takes input
    public void init() {
        //regex pattern input

        System.out.println("--------------------------------------------");
        System.out.println("completed/working regex pattern rules: * ? +");
        System.out.println("--------------------------------------------");

        System.out.println("Number of regex pattern: ");
        int number_of_pattern = sc.nextInt();
        sc.nextLine();

        regex_patterns = new String[number_of_pattern];

        System.out.println("Regex patterns: ");
        for (int i = 0; i < regex_patterns.length; i++) {
            String line = sc.nextLine();
            regex_patterns[i] = line.replaceAll("\\s+", "");//removing spaces
        }
        System.out.println(Arrays.toString(regex_patterns));

        //string lines input
        System.out.println("Number of string line: ");
        int number_of_string = sc.nextInt();
        sc.nextLine();

        string_lines = new String[number_of_string];

        System.out.println("String lines: ");
        for (int i = 0; i < string_lines.length; i++) {
            String line = sc.nextLine();
            string_lines[i] = line.replaceAll("\\s+", "");//removing spaces
        }
        //System.out.println(Arrays.toString(string_lines));

        //evaluation 
        for (int i = 0; i < number_of_string; i++) {
            evaluate(string_lines[i]);
        }

    }
    //takes an input string and tries to match with the regex rules like: * + ?
    private void evaluate(String input_string) {
        char[] input_line = input_string.toCharArray();
        System.out.println("-----------output------------");

        for (int i = 0; i < regex_patterns.length; i++) {
            char[] pattern = regex_patterns[i].toCharArray();
            //System.out.println("pattern: " + Arrays.toString(pattern));

            //input_line to match a pattern
            boolean matched = true;//this is flag for input matching
            int index_inputLine = 0;//input line char index to track checked position

            for (int index_pattern = 0; index_pattern < pattern.length; index_pattern++) {
                if (pattern.length == 1 && pattern[0] == '*') {//only *
                    //System.out.println("only * pattern");
                    index_inputLine += input_line.length + 2;//index>input to make it matched
                    break;
                }

                char match = pattern[index_pattern];
                boolean outer_loop_breaker = false;
                if (index_pattern != pattern.length - 1) {//not last char of pattern

//                    //for parenthesis
//                    if(pattern[index_pattern] == '('){//found '('
//                        //System.out.println("found (");
//                        index_pattern++;//update index of outer pattern loop
//                        String pattern_part = "";//keeps pattern part inside parenthesis
//                        
//                        for(int ip = index_pattern; ip < pattern.length; ip++){
//                            if(pattern[ip] != ')'){//until found ')'
//                                pattern_part += pattern[ip];//pattern part adding
//                            }else{
//                                index_pattern = ip;//update index of outer pattern loop
//                            }
//                        }     
//                        
//                        if(index_pattern >= pattern.length-1){//pattern part ended
//                            String input_part = "";
//                            for(int in = 0; in < pattern_part.length(); in++){
//                                if (input_line.length <= index_inputLine) {//input finished while pattern still exists
//                                    matched = false;
//                                    outer_loop_breaker = true;//will also break outer loop
//                                    break;
//                                }else{//exist more in input line
//                                    input_part += input_line[index_inputLine];
//                                    index_inputLine++;
//                                }
//                            }
//                            if(!pattern_part.equals(input_part)){//parts not matched inside parenthesis
//                                matched = false;
//                                break;
//                            }
//                        }else{//more chars in pattern exists
//                            if(pattern[index_pattern] == '*'){//for ()*
//                                System.out.println("do for ()* pattern please");
//                            }continue;
//                        }
//                         
//                    }//()parenthesis part ends here
//                    else
                    if (pattern[index_pattern + 1] == '*') {//for *  
                        //System.out.println("* found on next");
                        index_pattern++;
                        //try to match the * char with the input chars
                        for (int k = index_inputLine; k < input_line.length; k++) {
                            if (input_line[k] == match) {
                                index_inputLine++;
                            } else {
                                break;
                            }
                        }
                    }//* check ends here
                    else if (pattern[index_pattern + 1] == '+') {//for +
                        //System.out.println("+ found on next");
                        index_pattern++;

                        if (input_line.length <= index_inputLine) {//input finished while pattern still exists
                            matched = false;
                            break;
                        }

                        if (input_line[index_inputLine] != match) {//at least one
                            matched = false;
                            break;

                        } else {//more than one
                            index_inputLine++;//already checked once
                            //check if more char matches
                            for (int k = index_inputLine; k < input_line.length; k++) {
                                if (input_line[k] == match) {
                                    index_inputLine++;
                                } else {
                                    break;
                                }
                            }
                        }
                    }//+ part ends here
                    else if (pattern[index_pattern + 1] == '?') {//for ?, 0 or 1 time
                        //System.out.println("? found on next");
                        index_pattern++;

                        int quesMatchCount = 0;//counter
                        for (int k = index_inputLine; k < input_line.length; k++) {
                            if (input_line[k] == match) {
                                index_inputLine++;
                                quesMatchCount++;
                            } else {//can reach here for 0 or 1 times
                                break;
                            }
                        }
                        if (quesMatchCount > 1) {//matches more than 1
                            matched = false;
                            break;
                        }
                    }//? part ends here
                    else {
                        //System.out.println("*/+ not found");
                        if (input_line.length <= index_inputLine) {//input finished while pattern still exists
                            matched = false;
                            break;
                        }

                        if (input_line[index_inputLine] == match) {//match
                            index_inputLine++;
                        } else {//don't match
                            matched = false;
                            break;
                        }
                    }//else ends here

                    if (outer_loop_breaker) {//something inside wants to break the outer loop
                        System.out.println("outer breaker");
                        outer_loop_breaker = false;//before break, make it false
                        break;
                    }
                } else {
                    //System.out.println("*/+ not found end");

                    if (input_line.length <= index_inputLine) {//input finished while pattern still exists
                        matched = false;
                        break;
                    }

                    if (input_line[index_inputLine] == match) {//matches
                        index_inputLine++;
                    } else {//don't match
                        matched = false;
                        break;
                    }
                }//else ends here
            }//loop ends

            if (index_inputLine < input_line.length) {//extra input exists
                matched = false;
            }
            if (matched) {//matches
                System.out.println(input_string + " matched pattern: " + regex_patterns[i]);
            } else {//don't match
                System.out.println(input_string + " NOT matched pattern: " + regex_patterns[i]);
            }
        }//loop ends
    }
}
