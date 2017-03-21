
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 1320100021
 */
public class RegexEngine {

    Scanner sc = new Scanner(System.in);

    char digit[];

    public void init() {
        System.out.println("***this is running in a infinite while loop***");
        System.out.println("Real number: ");
        while (true) {
            String input = sc.nextLine();
            digit = input.toCharArray();

            boolean b = check();
            if (b) {
                System.out.println("Accept");
            } else {
                System.out.println("Reject");
            }
        }

    }

    //checks if input is a real number or not
    //if real number then return true otherwise return false
    public boolean check() {

        int dotCount = 0;
        for (int i = 0; i < digit.length; i++) {

            if (Character.isDigit(digit[i])) {//digit found 0-9
                 //continue;
            } else if (digit[i] == '.') {//dot found
                dotCount++;
                if (dotCount > 1) {//dot appears more than one time
                    return false;
                }
            } else if (digit[i] == '+' || digit[i] == '-') {//found +/-
                if (i != 0) {//+/- not in first position
                    return false;
                }
            } else {
                return false;
            }

        }
        return true;

    }
}
