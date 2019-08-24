package com.office_hour;

public class JavaInterviewQuestions {

    public static void main(String[] args) {

      int result = remainder(11, 2);
        System.out.println(result);

    }

   // can you write a method which finds the remainder of 2 integers without % operator

    public static int remainder(int num1, int num2){

        int remainder = 0;

        remainder = (num1 - num2 * (num1 / num2)); // 11 / 2 = 5, remainder 1 (11-2 * (11 / 2); 


        return  remainder;
    }



}
