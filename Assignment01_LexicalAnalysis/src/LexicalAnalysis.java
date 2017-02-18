
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mti
 */
public class LexicalAnalysis {

    Stack<String> keywords = new Stack();
    Stack<String> mathOperators = new Stack();
    Stack<String> identifiers = new Stack();
    Stack<String> logicalOperators = new Stack();
    Stack<String> numericalValues = new Stack();
    Stack<String> others = new Stack();

    //read input.txt file, send a String line to checkTokens() to check
    public void analyze() throws Exception {
        String line;
        FileReader fileReader = new FileReader("input.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while (true) {
            line = bufferedReader.readLine();
            if (line != null) {
                checkTokens(line);
            } else {
                displayResult();
                break;
            }
        }
        bufferedReader.close();
    }

    //takes a String and call different checking methods
    private void checkTokens(String line) {
        checkKeywords(line);
        checkMathOperators(line);
        checkLogicalOperators(line);
        checkNumericalValues(line);
        checkOthers(line);
        //checkIdentifiers(line);

    }

    //takes a string and check if it contains a seyword, 
    //if seyword found, call checkIdentifiers() method
    private void checkKeywords(String str) {
        boolean keyFound = false;
        if (str.contains("int")) {
            if (!keywords.contains("int")) {
                keywords.push("int");
            }
            keyFound = true;
        }
        if (str.contains("float")) {
            if (!keywords.contains("float")) {
                keywords.push("float");
            }
            keyFound = true;
        }
        if (str.contains("break")) {
            if (!keywords.contains("break")) {
                keywords.push("break");
            }
        }
        if (str.contains("case")) {
            if (!keywords.contains("case")) {
                keywords.push("case");
            }
        }
        if (str.contains("char")) {
            if (!keywords.contains("char")) {
                keywords.push("char");
            }
            keyFound = true;
        }
        if (str.contains("continue")) {
            if (!keywords.contains("continue")) {
                keywords.push("continue");
            }
        }
        if (str.contains("do")) {
            if (!keywords.contains("do")) {
                keywords.push("do");
            }
        }
        if (str.contains("else")) {
            if (!keywords.contains("else")) {
                keywords.push("else");
            }
        }
        if (str.contains("if")) {
            if (!keywords.contains("if")) {
                keywords.push("if");
            }
        }
        if (str.contains("for")) {
            if (!keywords.contains("for")) {
                keywords.push("for");
            }
        }
        if (str.contains("double")) {
            if (!keywords.contains("double")) {
                keywords.push("double");
            }
            keyFound = true;
        }
        if (str.contains("return")) {
            if (!keywords.contains("return")) {
                keywords.push("return");
            }
        }
        if (str.contains("static")) {
            if (!keywords.contains("static")) {
                keywords.push("static");
            }
        }
        if (str.contains("switch")) {
            if (!keywords.contains("switch")) {
                keywords.push("switch");
            }
        }
        if (str.contains("void")) {
            if (!keywords.contains("void")) {
                keywords.push("void");
            }
        }
        if (str.contains("while")) {
            if (!keywords.contains("while")) {
                keywords.push("while");
            }
        }

        //checheck Identifiers
        if (keyFound == true) {
            checkIdentifiers(str);
        }
    }

    //after found a keyword this method is called,
    //takes a string and check if it contains identifier
    private void checkIdentifiers(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, " ");
        String token = "";
        while (stringTokenizer.hasMoreTokens()) {

            token = stringTokenizer.nextToken();
            if (token.contains(";")) {
                token = token.replace(";", "");
                token = token.replaceAll("\\+", " ");

                //avoid init digit in identifiers like 45num
                char[] ch = token.toCharArray();
                if (!Character.isDigit(ch[0])) {//not started with digit
                    identifiers.push(token);
                }

            } else if (token.contains(",")) {
                token = token.replace(",", "");
                token = token.replaceAll("\\+", " ");

                //avoid init digit in identifiers like 45num
                char[] ch = token.toCharArray();
                if (!Character.isDigit(ch[0])) {//not started with digit
                    identifiers.push(token);
                }
            }
        }

    }

    //take a string and check if it contains mathoperator
    private void checkMathOperators(String str) {
        if (str.contains("+")) {
            if (!mathOperators.contains("+")) {
                mathOperators.push("+");
            }
        }
        if (str.contains("-")) {
            if (!mathOperators.contains("-")) {
                mathOperators.push("-");
            }
        }
        if (str.contains("*")) {
            if (!mathOperators.contains("*")) {
                mathOperators.push("*");
            }
        }
        if (str.contains("/")) {
            if (!mathOperators.contains("/")) {
                mathOperators.push("/");
            }
        }
        if (str.contains("%")) {
            if (!mathOperators.contains("%")) {
                mathOperators.push("%");
            }
        }
        if (str.contains("++")) {
            if (!mathOperators.contains("++")) {
                mathOperators.push("++");
            }
        }
        if (str.contains("--")) {
            if (!mathOperators.contains("--")) {
                mathOperators.push("--");
            }
        }
        if (str.contains("=")) {
            if (!mathOperators.contains("=")) {
                mathOperators.push("=");
            }
        }
    }

    //takes a string and check if it contains logical operators
    private void checkLogicalOperators(String str) {
        if (str.contains("&&")) {
            if (!logicalOperators.contains("&&")) {
                logicalOperators.push("&&");
            }
        }
        if (str.contains("||")) {
            if (!logicalOperators.contains("||")) {
                logicalOperators.push("||");
            }
        }
        if (str.contains("!")) {
            if (!logicalOperators.contains("!")) {
                logicalOperators.push("!");
            }
        }
        if (str.contains(">")) {
            if (!logicalOperators.contains(">")) {
                logicalOperators.push(">");
            }
        }
        if (str.contains("<")) {
            if (!logicalOperators.contains("<")) {
                logicalOperators.push("<");
            }
        }
        if (str.contains("<=")) {
            if (!logicalOperators.contains("<=")) {
                logicalOperators.push("<=");
            }
        }
        if (str.contains(">=")) {
            if (!logicalOperators.contains(">=")) {
                logicalOperators.push(">=");
            }
        }
        if (str.contains("==")) {
            if (!logicalOperators.contains("==")) {
                logicalOperators.push("==");
            }
        }
    }

    //takes a string and check if it contains numerical operators
    private void checkNumericalValues(String line) {
        String st1 = "", token = "";
        if (line.contains("=")) {
            String[] splitedArr = line.split("=");

            if (splitedArr.length == 2) {//1 equal sn
                st1 = splitedArr[1].replace(";", "");
                st1 = st1.replaceAll("\\s+", ""); //rmv all spaces

                if (st1.contains("+") || st1.contains("-") || st1.contains("*") || st1.contains("/")
                        || st1.contains("%")) {
                    String[] result = st1.split("[-+*/%]");

                    for (int i = 0; i < result.length; i++) {
                        token = result[i];

                        if (!identifiers.contains(token)) {
                            if (isNumeric(token)) {
                                numericalValues.push(token);
                            }
                        }
                    }
                } else {//no numeric sn
                    if (isNumeric(st1)) {
                        numericalValues.push(st1);
                    }
                }
            } else {//2 equal sn
                token = splitedArr[splitedArr.length - 1].replace(";", "");
                if (isNumeric(token)) {
                    numericalValues.push(token);
                }
            }
        }

    }

    //takses a string and check if it contains other tokens
    private void checkOthers(String str) {
        if (str.contains("}")) {
            if (!others.contains("}")) {
                others.push("}");
            }
        }
        if (str.contains("{")) {
            if (!others.contains("{")) {
                others.push("{");
            }
        }
        if (str.contains(")")) {
            if (!others.contains(")")) {
                others.push(")");
            }
        }
        if (str.contains("(")) {
            if (!others.contains("(")) {
                others.push("(");
            }
        }
        if (str.contains(",")) {
            if (!others.contains(",")) {
                others.push(",");
            }
        }
        if (str.contains(";")) {
            if (!others.contains(";")) {
                others.push(";");
            }
        }
    }

    //takes a string, check if it's a numeric or not
    //if numeric return true otherwise return false
    public boolean isNumeric(String str) {
        try {
            double num = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //display the result
    private void displayResult() {
        System.out.print("Keywords: ");
        while (!keywords.isEmpty()) {
            if (keywords.size() == 1) {
                System.out.print(keywords.pop());
            } else {
                System.out.print(keywords.pop() + ", ");
            }
        }
        System.out.println();

        System.out.print("Identifiers: ");
        while (!identifiers.isEmpty()) {
            if (identifiers.size() == 1) {
                System.out.print(identifiers.pop());
            } else {
                System.out.print(identifiers.pop() + ", ");
            }
        }
        System.out.println();

        System.out.print("Math Operators: ");
        while (!mathOperators.isEmpty()) {
            if (mathOperators.size() == 1) {
                System.out.print(mathOperators.pop());
            } else {
                System.out.print(mathOperators.pop() + ", ");
            }
        }
        System.out.println();

        System.out.print("Logical Operators: ");
        while (!logicalOperators.isEmpty()) {
            if (logicalOperators.size() == 1) {
                System.out.print(logicalOperators.pop());
            } else {
                System.out.print(logicalOperators.pop() + ", ");
            }
        }
        System.out.println();

        System.out.print("Numerical Values: ");
        while (!numericalValues.isEmpty()) {
            if (numericalValues.size() == 1) {
                System.out.print(numericalValues.pop());
            } else {
                System.out.print(numericalValues.pop() + ", ");
            }
        }
        System.out.println();

        System.out.print("Others: ");
        while (!others.isEmpty()) {
            if (others.size() == 1) {
                System.out.print(others.pop());
            } else {
                System.out.print(others.pop() + ", ");
            }
        }
        System.out.println();
    }
}
