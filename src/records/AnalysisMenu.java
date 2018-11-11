package records;

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
        } while(selection <=0 || selection > 6);

        this.menuSelection(selection);
    } //end bankMenu

	@Override
	public void bankMenuDisplay(){
        
		
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
        System.out.println("\t\t 3. Sex based analysis");
        System.out.println("\t\t 4. Age based analysis");
        System.out.println("\t\t 5. Print Analysis to File");        
        System.out.println("\t\t 6. Return to Main Menu");
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
            	// check flags and alert if file not found
        		fileFlagAlert(BankRecords.fileFlag);            	
            	
                //display report
                System.out.println("----- Full Analyisis Display -----");
                System.out.println("");
                records.printLocation(); // analyze average income per loc
                System.out.println("");
                records.printLocationMinMax(); //analyze min and max income per location
                System.out.println("");
                records.printSexAnalysis(); // analyze income based on sex
                
                
                terminationOption(); //user control for another transaction or finish
                break;

            case 2:
            	// check flags and alert if file not found
        		fileFlagAlert(BankRecords.fileFlag);
            	
            	System.out.println("----- Location based analysis -----");     
            	System.out.println("");
            	System.out.println(" ");
        		System.out.println("Printing Location Results to Screen. ");
        		System.out.println(" ");
            	records.printLocation(); // analyze average income per loc
                terminationOption(); //user control for another transaction or finish
                break;

            case 3:
            	// check flags and alert if file not found
        		fileFlagAlert(BankRecords.fileFlag);
            	
            	System.out.println("----- Sex based Display -----");
                System.out.println("");
                
                records.printSexAnalysis(); // analyze income based on sex
                terminationOption(); //user control for another transaction or finish
                break;

            case 4:
            	// check flags and alert if file not found
        		fileFlagAlert(BankRecords.fileFlag);
                
            	System.out.println("--------- Age Analysis ---------");
                System.out.println("");
                records.printAgeAnalysis(); // analyze age aerages

                terminationOption(); //user control for another transaction or finish
                break;
              
            case 5:
            	// check flags and alert if file not found
        		fileFlagAlert(BankRecords.fileFlag);
            	
            	// save the data to the file
            	System.out.println("----- Save Analysis to File -----");
                System.out.println("");
        	    
        	    records.saveLocation(); // save location analysis to file
        	    records.saveLocationMinMax(); // save min and max based on location to file 
        	    records.saveSexAnalysis(); // save sex analysis to file

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
            	// return to main menu
            	Menu bankMenu = new Menu();
                bankMenu.bankMenu(); 
                break;
        } //end swith statement
	} // end menuSelection
} // end class
