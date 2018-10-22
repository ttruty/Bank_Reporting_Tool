package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class AnalysisMenu extends Menu{
	
	// create scanner object
	// private Scanner scanner = new Scanner(System.in);
	
	@Override
	public void bankMenu(){

		
        int selection; //holds user selection choice
        do {
            //display bank menu options
            this.bankMenuDisplay();

            System.out.println("");
            System.out.println("Select an option number from menu above:");

            //run loop until the user makes a correct choice
            while (!scanner.hasNextInt()) { //validate is Int
                System.out.println("Please enter a valid selection 1-7");
                scanner.next(); //check scanner again
            }
            selection = scanner.nextInt();
        } while(selection <=0 || selection > 7);

        this.menuSelection(selection);
    }

	@Override
	public void bankMenuDisplay(){
        //TODO: create login screen w/ account number and pin
    	
    	/*
    	
    		average income per location
    		max and min income per location
    		number of females with both a mortgage and savings account per location
    		number of males with both a car and 1 child per location

    	*
    	*/
        //display menu options
        System.out.println("Menu Options: \n");
        System.out.println("\t\t 1. Full Analysis Display");
        System.out.println("\t\t 2. Location based analysis");
        System.out.println("\t\t 3. Gender based analysis");
        System.out.println("\t\t 4. Other Bank analysis");
        System.out.println("\t\t 5. Print Analysis to File");        
        System.out.println("\t\t 6. Return to Main Menu");
        System.out.println("\t\t 7. Log Out");
    }

	@Override
    public void menuSelection(int selection){

		//create records object
		Records records = new Records();
		
        // switch statement
        switch(selection) {
            // case statements
            // values must be of same type of expression
            case 1:
                //display report
                System.out.println("----- Full Analyisis Display -----");
                System.out.println("");
                records.printLocation();
                System.out.println("");
                records.printLocationMinMax();
                System.out.println("");
                terminationOption(); //user control for another transaction or finish
                break;

            case 2:
            	System.out.println("----- Location based analysis -----");     
            	System.out.println("");
            	System.out.println(" ");
        		System.out.println("Printing Location Results to Screen. ");
        		System.out.println(" ");
            	records.printLocation();
                terminationOption(); //user control for another transaction or finish
                break;

            case 3:
            	System.out.println("----- Sex based Display -----");
                System.out.println("");
                int[] femaleCt= records.sexComp();
                for (int i : femaleCt) {
                	System.out.println(i);
                }
                
                System.out.println(femaleCt);
                terminationOption(); //user control for another transaction or finish
                break;

            case 4:
                //about
                //print system info
            	System.out.println("----- Other Bank analysis -----");
                System.out.println("");

                terminationOption(); //user control for another transaction or finish
                break;
              
            case 5:
            	System.out.println("----- Save Analyisis to File -----");
                System.out.println("");
        	    
        	    records.saveLocation();
        	    records.saveLocationMinMax();
                    //call functions to perform analytics 
            	    //br.locationComp(); // analyze average income per loc
            	    //MaxMinComp();  //compare max and min incomes per loc  
            	    //femsComp();    //  analyze females w. mort/savings accounts per loc
            	    //malesComp();  // analyze male count w. car and 1 child per loc 

                // *** close out file object ***//
            	try { 
        		Records.fw.close();
        		} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        		}
                
                terminationOption(); //user control for another transaction or finish
                break;
            case 6:
                bankMenu();
                break;
            case 7:
            	bankMenu();
            	logout();
        }
	}

    /**
     * Alert user that there is an error with the file trying to read
     *
     * @param flag
     */
	@Override
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
}
