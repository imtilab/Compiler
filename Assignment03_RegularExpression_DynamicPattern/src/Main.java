/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mti
 */
public class Main {
    public static void main(String[] args) {
        RegularExpression re = new RegularExpression();
        re.init();
    }
}
/*
//input_line to match pattern
            boolean matched = true;
            int start_index_pattern = 0, end_index_pattern = 0, index_input = 0;
            
            for (int j = 0; j < pattern.length; j++) {
                
                if(pattern[j] == '*'){//for *
                    System.out.println("* found");
                    for (int k = index_input; k < input_line.length; k++) {
                        if(input_line[k] == pattern[j-1]){
                            System.out.println("mathed: "+input_line[k]);
                            index_input++;

                        }else{
                            break;
                        }
                    }
                }//* check ends here
                
                else if(pattern[j] == '+'){//for +
                    System.out.println("+ found");
                    if(input_line[index_input] == pattern[j-1]){//at least one
                        System.out.println("not matched: "+input_string+" with pattern: "+pattern[i]);
                        break;
                    }else{//more than one
                        for (int k = index_input; k < input_line.length; k++) {
                            if(input_line[k] == pattern[j-1]){
                                System.out.println("mathed: "+input_line[k]);
                                index_input++;
                                
                            }else{
                                break;
                            }
                        }
                    }
                }//+ part ends here
                
                else{
                    if(input_line[j+1] != '+' || input_line[j+1] != '*'){
                        System.out.println("*+ not found");
                        if(input_line[index_input] == pattern[j]){
                            System.out.println("mathed: "+input_line[index_input]);
                            index_input++;

                        }else{
                            System.out.println("not matched: "+input_string+" with pattern: "+pattern[i]);
                            break;
                        }
                    }
                }//else ends here
            }//
*/