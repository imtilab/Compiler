
import java.io.BufferedReader;
import java.io.FileReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mti
 */
public class FindMethods {
    //reads a source file input.txt and find out the methods name
    public void findMethod() throws Exception {
        FileReader fileReader = new FileReader("input.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while (true) {
            String line = bufferedReader.readLine();

            if (line != null) {
                String arr[] = line.split(" ");

                if (arr[0].equals("public") || arr[0].equals("private") || arr[0].equals("protected")) {
                    if (arr[1].equals("static")) {//static method

                        if (line.contains("(") && line.contains(")")) {//now its a method
                            String st = "";

                            for (int i = 3; i < arr.length; i++) {
                                st = st + " " + arr[i];//method name string from chars

                                if (arr[i].contains(")")) {//method name char ends
                                    break;
                                }
                            }
                            System.out.println(st);
                        }

                        //System.out.println("return type: "+arr[2]);
                    } else if (line.contains("(") && line.contains(")")) {//non-static method
                        String st = "";

                        for (int i = 2; i < arr.length; i++) {
                            st = st + " " + arr[i];//method name string from chars

                            if (arr[i].contains(")")) {//method name chars ends
                                break;
                            }
                        }
                        System.out.println(st);
                    } //System.out.println("return type: "+arr[1]);
                }
            } else {
                break;
            }
        }
    }
}
