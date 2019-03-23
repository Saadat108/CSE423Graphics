/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSE_423_lab_2.Cohen_Sutherland;

/**
 *
 * @author User
 */
public class trial {
    public static void main(String args[]){
        
        
        int x= 0B0000;
        int R = 0B0010;
        int L = 0B0001;
        int B = 0B0100;
        int T = 0B1000;
//        if(Integer.toBinaryString(R) ||Integer.toBinaryString(L) > 0){
//            
//        }
        if((R|L)>0){
        
        }
        int and = R|L;
        System.out.println(and);
        System.out.println(0 | 9);
        System.out.println(x & T);
        System.out.println(T & B);
        System.out.println(R & L);
    }
}
