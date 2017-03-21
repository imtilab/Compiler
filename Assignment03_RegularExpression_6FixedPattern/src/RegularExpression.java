
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
    String patternA = "a(bc)*de";
    String patternB = "a(bc)+de";
    String patternC = "a(bc)?de";
    String patternD = "[a-m]*";
    String patternE = "[^aeiou]";
    String patternF = "[^aeiou]{6}";

    //initializes and takes input
    public void init() {
        System.out.println("---------------\nValid expressions:\n"
                + "1. a(bc)*de\n2. a(bc)+de\n3. a(bc)?de\n4. [a-m]*\n5. [^aeiou]\n6. [^aeiou]{6}\n"
                + "---------------");
        //regex pattern input
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
        System.out.println(Arrays.toString(string_lines));

        //evaluation 
        for (int i = 0; i < string_lines.length; i++) {
            evaluate(string_lines[i]);
        }
    }
    //takes an input string and try to match with 6 fixed patterns
    private void evaluate(String input_string) {
        for (int i = 0; i < regex_patterns.length; i++) {
            if (regex_patterns[i].equals(patternA)) {//a(bc)*de
                pattern1(input_string);
            } else if (regex_patterns[i].equals(patternB)) {//a(bc)+de
                pattern2(input_string);
            } else if (regex_patterns[i].equals(patternC)) {//a(bc)?de
                pattern3(input_string);
            } else if (regex_patterns[i].equals(patternD)) {//[a-m]*
                pattern4(input_string);
            } else if (regex_patterns[i].equals(patternE)) {//[^aeiou]
                pattern5(input_string);
            } else if (regex_patterns[i].equals(patternF)) {//[^aeiou]{6}
                pattern6(input_string);
            }
        }
    }
    //takes input string try to match with the pattern a(bc)*de
    private void pattern1(String input_string) {
        char[] input_line = input_string.toCharArray();

        //a(bc)*de
        boolean matched = true;
        if (input_line.length < 3) {//less than min length 3
            matched = false;
        } else if (input_line[0] != 'a') {
            matched = false;
        } else if (input_line[input_line.length - 1] != 'e') {
            matched = false;
        } else if (input_line[input_line.length - 2] != 'd') {
            matched = false;
        } else if (input_line.length > 3) {//more than 3 chars
            int indexLimit = input_line.length - 2;
            for (int i = 1; i < indexLimit; i += 2) {
                if (input_line[i] == 'b') {//match a?
                    if (indexLimit - 1 == i) {//no more chars
                        matched = false;
                        break;
                    } else if (input_line[i + 1] != 'c') {//more chars, match b?
                        matched = false;
                        break;
                    }
                } else {
                    matched = false;
                    break;
                }
            }
        }
        if (matched) {
            System.out.println("YES " + 1 + " " + input_string + " matched a(bc)*de");
        } else {
            System.out.println("NO " + 1 + " " + input_string + " NOT matched a(bc)*de");
        }
    }
    //takes input string try to match with the pattern a(bc)+de
    private void pattern2(String input_string) {
        char[] input_line = input_string.toCharArray();

        //a(bc)+de
        boolean matched = true;
        if (input_line.length < 5) {//less than min length 5
            matched = false;
        } else if (input_line[0] != 'a') {
            matched = false;
        } else if (input_line[input_line.length - 1] != 'e') {
            matched = false;
        } else if (input_line[input_line.length - 2] != 'd') {
            matched = false;
        } else if (input_line[1] != 'b' && input_line[2] != 'c') {
            matched = false;
        } else if (input_line.length > 5) {//more than 5 chars
            int indexLimit = input_line.length - 2;
            for (int i = 3; i < indexLimit; i += 2) {
                if (input_line[i] == 'b') {//match a?
                    if (indexLimit - 1 == i) {//no more chars
                        matched = false;
                        break;
                    } else if (input_line[i + 1] != 'c') {//more chars, match b?
                        matched = false;
                        break;
                    }
                } else {
                    matched = false;
                    break;
                }
            }
        }
        if (matched) {
            System.out.println("YES " + 2 + " " + input_string + " matched a(bc)+de");
        } else {
            System.out.println("NO " + 2 + " " + input_string + " NOT matched a(bc)+de");
        }
    }
    //takes input string try to match with the pattern a(bc)?de
    private void pattern3(String input_string) {
        char[] input_line = input_string.toCharArray();
        
        //a(bc)?de
        boolean matched = true;
        if (input_line.length < 3) {//less than min length 3
            matched = false;
        } else if (input_line[0] != 'a') {
            matched = false;
        } else if (input_line[input_line.length - 1] != 'e') {
            matched = false;
        } else if (input_line[input_line.length - 2] != 'd') {
            matched = false;
        } else if (input_line.length == 3) {//length 3
            matched = true;
        } else if (input_line.length == 5) {//length equal 5, ? means 0 or once
            if (input_line[1] != 'b' || input_line[2] != 'c') {//
                matched = false;//executes if not bc
            }
        } else {//not 3 or 5 length
            matched = false;
        }

        if (matched) {
            System.out.println("YES " + 3 + " " + input_string + " matched a(bc)?de");
        } else {
            System.out.println("NO " + 3 + " " + input_string + " NOT matched a(bc)?de");
        }
    }
    //takes input string try to match with the pattern [a-m]*
    private void pattern4(String input_string) {
        char[] input_line = input_string.toCharArray();

        //[a-m]*
        boolean matched = true;
        for (int i = 0; i < input_line.length; i++) {
            if (input_line[i] != 'a' && input_line[i] != 'b' && input_line[i] != 'c'
                    && input_line[i] != 'd' && input_line[i] != 'e' && input_line[i] != 'f'
                    && input_line[i] != 'g' && input_line[i] != 'h' && input_line[i] != 'i'
                    && input_line[i] != 'j' && input_line[i] != 'k' && input_line[i] != 'l'
                    && input_line[i] != 'm') {//not matched any one of those chars
                matched = false;
                break;
            }
        }

        if (matched) {
            System.out.println("YES " + 4 + " " + input_string + " matched [a-m]*");
        } else {
            System.out.println("NO " + 4 + " " + input_string + " NOT matched [a-m]*");
        }
    }
    //takes input string try to match with the pattern [^aeiou]
    private void pattern5(String input_string) {
        char[] input_line = input_string.toCharArray();

        //[^aeiou]
        boolean matched = true;
        for (int i = 0; i < input_line.length; i++) {
            if (input_line[i] == 'a' || input_line[i] == 'e' || input_line[i] == 'i'
                    || input_line[i] == 'o' || input_line[i] == 'u') {//matches any of those
                matched = false;
                break;
            }
        }

        if (matched) {
            System.out.println("YES " + 5 + " " + input_string + " matched [^aeiou]");
        } else {
            System.out.println("NO " + 5 + " " + input_string + " NOT matched [^aeiou]");
        }
    }
    //takes input string try to match with the pattern [^aeiou]{6}
    private void pattern6(String input_string) {
        char[] input_line = input_string.toCharArray();

        //[^aeiou]{6}
        boolean matched = true;
        if (input_line.length != 6) {//length not 6
            matched = false;
        } else {
            for (int i = 0; i < input_line.length; i++) {
                if (input_line[i] == 'a' || input_line[i] == 'e' || input_line[i] == 'i'
                        || input_line[i] == 'o' || input_line[i] == 'u') {//matches any of those
                    matched = false;
                    break;
                }
            }
        }

        if (matched) {
            System.out.println("YES " + 6 + " " + input_string + " matched [^aeiou]{6}");
        } else {
            System.out.println("NO " + 6 + " " + input_string + " NOT matched [^aeiou]{6}");
        }
    }
}
