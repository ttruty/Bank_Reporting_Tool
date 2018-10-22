package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Menu {

    //create scanner class object to use in methods
    public Scanner scanner = new Scanner(System.in);
    //create the bank records  objects
    BankRecords br = new BankRecords();


    /**
     menu to allow for user control of bank records system
     using Scanner class and error trapping
     to only allow proper selections
     */
    public void bankMenu(){

        //called read data method at initialization of menu
        br.readData();

        int selection; //holds user selection choice
        do {
            //display bank menu options
            bankMenuDisplay();

            System.out.println("");
            System.out.println("Select an option number from menu above:");

            //run loop until the user makes a correct choice
            while (!scanner.hasNextInt()) { //validate is Int
                System.out.println("Please enter a valid selection 1-6");
                scanner.next(); //check scanner again
            }
            selection = scanner.nextInt();
        } while(selection <=0 || selection > 6);

        menuSelection(selection);
    }

    /**
     menu display options
     */
    public void bankMenuDisplay(){
        //TODO: create login screen w/ account number and pin

        //display menu options
        System.out.println("Menu Options: \n");
        System.out.println("\t\t 1. Display Report");
        System.out.println("\t\t 2. Report File Info");
        System.out.println("\t\t 3. User List");
        System.out.println("\t\t 4. About");
        System.out.println("\t\t 5. Analysis");        
        System.out.println("\t\t 6. Log Out");
    }

    public void menuSelection(int selection){

        // switch statement
        switch(selection) {
            // case statements
            // values must be of same type of expression
            case 1:
                //display report
                System.out.println("-----Display Report-----");
                System.out.println("");

                //if the file can not be read alert the user
                fileFlagAlert(BankRecords.fileFlag);

                System.out.println("Please enter the numbers of records you would like to view: ");
                int inputRecord = scanner.nextInt();

                // error trap for non-positive number
                // keep asking for a new balance if the account is negative
                while (inputRecord > BankRecords.NUMOFRECORDS || inputRecord < 0) {
                System.out.println("Number of requested records must be greater than 0" +
                        "\nand below " + BankRecords.NUMOFRECORDS);
                    System.out.println("Please enter VALID request: ");
                    inputRecord = scanner.nextInt();
                }
                //TODO Report for only number of records
                br.printData(inputRecord);
                terminationOption(); //user control for another transaction or finish
                break;

            case 2:
                // get and print file attributes
                fileFlagAlert(BankRecords.fileFlag);
                System.out.println(br.fileInfo());

                terminationOption(); //user control for another transaction or finish
                break;

            case 3:
                //user list
                //TODO create lits options ie. change username/password add user
                Users.printUsers();
                terminationOption(); //user control for another transaction or finish
                break;

            case 4:
                //about
                //print system info
                systemInfo();
                terminationOption(); //user control for another transaction or finish
                break;
              
            case 5:
                //about
                // Run analysis menu
            	AnalysisMenu analysisMenu = new AnalysisMenu();
                analysisMenu.bankMenu();
            	//TODO: make Analysis Menu
                
//        	    Records br = new Records();
//                br.readData();
//                    //call functions to perform analytics 
//            	    Records.LocationComp(); // analyze average income per loc
//            	    //MaxMinComp();  //compare max and min incomes per loc  
//            	    //femsComp();    //  analyze females w. mort/savings accounts per loc
//            	    //malesComp();  // analyze male count w. car and 1 child per loc 
//
//                // *** close out file object ***//
//            	try {
//        		Records.fw.close();
//        		} catch (IOException e) {
//        		// TODO Auto-generated catch block
//        		e.printStackTrace();
//        		}
                
                //terminationOption(); //user control for another transaction or finish
                break;
            case 6:
                System.out.println("Would you like to log out?\n" +
                        "(y)es or (n)o");

                // user control for termination or continue
                while (true) {
                    char charInput = scanner.next().charAt(0);

                    // log out
                    if (charInput == 'y' || charInput == 'Y') {
                        Login.loginState = false; //reset login state
                        Login.loginTryCount = 0; //reset logint try counter
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                        Login.run(); //re run log in
                        if (Login.loginState) {
                            bankMenu(); //re run bank menu
                        }
                        else{
                            return;
                        }
                        break;

                        // termination of transactions
                    } else if (charInput == 'n' || charInput == 'N') {
                        // display final menu
                        // thank you message
                        bankMenu();
                        break;
                    } else {
                        // catch error
                        System.out.println("Please enter a valid response");
                    }
                }
        }
    }

    /**
     * print out system info
     */
    public void systemInfo(){
        System.out.println("");
        System.out.println(
                "*************Reporting System Info**************\n" +
                "************************************************\n" +
                "************************************************\n" +
                "******    Bank Record reporting system   *******\n" +
                "******    Programmed by: Timothy Truty   *******\n" +
                "******  Last Updated: September 29, 2018 *******\n" +
                "******         ITM 510 Fall 2018         *******\n" +
                "******  Illinois Institute of Technology *******\n" +
                "************************************************\n" +
                "************************************************\n");
    }

    /**
     * Alert user that there is an error with the file trying to read
     *
     * @param flag
     */
    public void fileFlagAlert(boolean flag){
        // if the file flag is true display the message and relaunch menu
        if (flag){
            System.out.println("");
            System.out.println("***************WARNING*****^^^^^^^^^^^^");
            System.out.println("************************^^^^^^^^^^^^^^^");
            System.out.println("*File error, please contact IT Support*");
            System.out.println("**************************************^");
            System.out.println("**************************************^");
            System.out.println("");
            bankMenu(); //re-launch bank menu
        }
    }

    /**
     *  control for leaving the system or continuing usa
     */
    public void terminationOption()
    {
        System.out.println("Would you like to complete another report?\n" +
                "(y)es or (n)o");

        // user control for termination or continue
        while (true) {
            char charInput = scanner.next().charAt(0);

            // continue using bank menu
            if (charInput == 'y' || charInput == 'Y') {
                bankMenu();
                break;

                // termination of transactions
            } else if (charInput == 'n' || charInput == 'N') {
                // display thank you message
                System.out.println("\tThank you for using IIT BANK\n" +
                        "\tHAVE A GREAT DAY!\n\n");

                // time stamp and time stamp
                String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
                System.out.println("Cur dt=" + timeStamp + "\nProgrammed by Tim Truty\n");

                break;
            } else {
                // catch error
                System.out.println("Please enter a valid response");
            }

        }
        scanner.close(); //close out scanner object
    }
        
    public void logout()
    {
    
	    System.out.println("Would you like to log out?\n" +
	            "(y)es or (n)o");
	
	    // user control for termination or continue
	    while (true) {
	        char charInput = scanner.next().charAt(0);
	
	        // log out
	        if (charInput == 'y' || charInput == 'Y') {
	            Login.loginState = false; //reset login state
	            Login.loginTryCount = 0; //reset logint try counter
	            System.out.println("");
	            System.out.println("");
	            System.out.println("");
	            System.out.println("");
	            Login.run(); //re run log in
	            if (Login.loginState) {
	                bankMenu(); //re run bank menu
	            }
	            else{
	                return;
	            }
	            break;
	
	            // termination of transactions
	        } else if (charInput == 'n' || charInput == 'N') {
	            // display final menu
	            // thank you message
	            bankMenu();
	            break;
	        } else {
	            // catch error
	            System.out.println("Please enter a valid response");
	        }
	    }
	    scanner.close();
    }
}
