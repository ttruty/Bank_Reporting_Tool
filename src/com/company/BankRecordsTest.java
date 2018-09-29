package com.company;

public class BankRecordsTest {
    public static void main(String[] args) {
        // write your code here



        //System.out.println("Programmed by Timothy Truty");

        Login.run();

        if (Login.loginState) {
            Menu bankMenu = new Menu();

            bankMenu.bankMenu();
        }
         else {
            return;
        }

    }
}
