package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Records extends BankRecords{
	
	//create formatted object to write output directly to the
	//console and to a file
    static FileWriter fw = null;
    // creates empty builder, capacity 16
 	public static StringBuilder sb = new StringBuilder();
 	
 	final String saveFile = "Resources\\bankrecords.txt";
 	
	public Records(){
		
		try {
			fw = new FileWriter(saveFile, true);
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public double[] locationComp() {
				
		Arrays.sort(robjs,new LocationComparator());
		
		// set to a currently format with Decimal Formatter
		DecimalFormat df2 = new DecimalFormat(".##");
		
	    //set up needed vars for region counts & incomes per loc
		double townCt = 0, innerCt=0, suburbanCt=0,ruralCt=0,
			townIncSum = 0, innerIncSum=0, suburbanIncSum=0,ruralIncSum=0;
			
		for (int i=0;i<robjs.length;i++)
		{
			//Rural counting
			if (robjs[i].getRegion().equals("RURAL")) { 
				ruralIncSum += robjs[i].getIncome();
				++ruralCt;
			}	
				// Inner city count
			else if (robjs[i].getRegion().equals("INNER_CITY")) { 
					innerIncSum += robjs[i].getIncome();
					++innerCt;
			}
		    // Town count
			else if (robjs[i].getRegion().equals("TOWN")) { 
				townIncSum += robjs[i].getIncome();
				++townCt;
			}
			// suburban
			else if (robjs[i].getRegion().equals("SUBURBAN")) { 
				suburbanIncSum += robjs[i].getIncome();
				++suburbanCt;
			}
			else {
			     // TODO DO OTHER LOCATIONS...
			}
		}
		//setup resulting averages to print to console and to file
		double ruralAvg = ruralIncSum/(ruralCt);
		double innerAvg = innerIncSum/(innerCt);
		double townAvg = townIncSum/(townCt);
		double suburbanAvg = suburbanIncSum/(suburbanCt);
	
		//create array to return
		double[] avgIncArr = {ruralAvg, innerAvg, townAvg, suburbanAvg};

	    return avgIncArr;
	    
		} // end LocationComp
	
	public void saveLocation() {
		//save location comparison to file
		double[] incArr = this.locationComp(); //double array for location compare output
		
		System.out.println(" ... ");
		System.out.println(" Printing Location Results to File. ");
		System.out.println(" File location at " + saveFile);
		System.out.println(" ...\n ");
		
		
		;
		
		// Math.round(incArr[0]*100.0)/100.0 = make 2 decimal
		try {
	 	      fw.write("Avg inc. for rural region " + Math.round(incArr[0]*100.0)/100.0 + "\n" +
		    		   "Avg inc. for inner city region " + Math.round(incArr[1]*100.0)/100.0 + "\n" +
		    		   "Avg inc. for town region " + Math.round(incArr[2]*100.0)/100.0 + "\n" +
		    		   "Avg inc. for suburban region " + Math.round(incArr[3]*100.0)/100.0 + "\n");
	                  fw.write("...\n");
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	} //end saveLocation
	
	public void printLocation() {
		//print location compairing details to screen
		System.out.println("-------Average income by location--------");
		double[] incArr = this.locationComp(); //double array for location compare output		
		System.out.printf("Avg inc. for rural region \t" + Math.round(incArr[0]*100.0)/100.0 + "\n" +
	    		   "Avg inc. for inner city region \t" + Math.round(incArr[1]*100.0)/100.0 + "\n" +
	    		   "Avg inc. for town region \t" + Math.round(incArr[2]*100.0)/100.0 + "\n" +
	    		   "Avg inc. for suburban region \t" + Math.round(incArr[3]*100.0)/100.0 + "\n");
	 
	} // end PrintLocation
	
	public double[] locationMinMax() {
		
		Arrays.sort(robjs,new LocationComparator());
		
		// set to a currently format with Decimal Formatter
		DecimalFormat df2 = new DecimalFormat(".##");
		
	    //set up needed vars for region counts & incomes per loc
		double townMax= 0, innerMax= 0, suburbanMax= 0,ruralMax = 0;
		//set up mins but do not initialize
		double townMin= 0, innerMin= 0, suburbanMin= 0,ruralMin = 0;
		
		//set location mins maxs as item
		for (int i=0;i<robjs.length;i++) {
			if (robjs[i].getRegion().equals("RURAL")) {
				ruralMin = robjs[i].getIncome();
				ruralMax = robjs[i].getIncome();
			}
			else if (robjs[i].getRegion().equals("INNER_CITY")) {
				innerMin = robjs[i].getIncome();
				innerMax = robjs[i].getIncome();
			}
			else if (robjs[i].getRegion().equals("TOWN")) {
				townMin = robjs[i].getIncome();
				townMax = robjs[i].getIncome();
			}
			else if (robjs[i].getRegion().equals("SUBURBAN")) {
				suburbanMin = robjs[i].getIncome();
				suburbanMax = robjs[i].getIncome();
			}
		} //end for loop
		
		//iterate over record obj array and set min and max
		for (int i=0;i<robjs.length;i++) {
			//Rural counting
			System.out.println("Rural Min =" + ruralMin);
			if (robjs[i].getRegion().equals("RURAL")) {
				double temp = robjs[i].getIncome();
				System.out.println("Temp =" + temp);
				if (temp > ruralMax)
					ruralMax = temp;
				if (temp < ruralMin)
					ruralMin = temp;
			}	
			// Inner city count
			else if (robjs[i].getRegion().equals("INNER_CITY")) { 
				double temp = robjs[i].getIncome();
				if (temp > innerMax)
					innerMax = temp;
				if (temp < innerMin)
					innerMin = temp;
			}
		    // Town count
			else if (robjs[i].getRegion().equals("TOWN")) { 
				double temp = robjs[i].getIncome();
				if (temp > townMax)
					townMax = temp;
				if (temp < townMin)
					townMin = temp;
			}
			// suburban
			else if (robjs[i].getRegion().equals("SUBURBAN")) { 
				double temp = robjs[i].getIncome();
				if (temp > suburbanMax)
					suburbanMax = temp;
				if (temp < suburbanMin)
					suburbanMin = temp;
			}
		} //end for loop

		//create array to return
		double[] minMaxArr = {ruralMin, ruralMax, innerMin, innerMax, townMin, townMax, suburbanMin, suburbanMax};

	    return minMaxArr;
	    
	} // end locationMinMax
	
	public void printLocationMinMax() {
		
		//print location compairing details to screen
		double[] minmaxArr = this.locationMinMax(); //double array for location compare output		
		System.out.println("---------- Max Region Income ------------");
		System.out.printf("Rural region max income \t" + Math.round(minmaxArr[1]*100.0)/100.0 + "\n" +
	    		   "Inner city region max income \t" + Math.round(minmaxArr[3]*100.0)/100.0 + "\n" +
	    		   "Town region max income \t\t" + Math.round(minmaxArr[5]*100.0)/100.0 + "\n" +
	    		   "Suburban region max income \t" + Math.round(minmaxArr[7]*100.0)/100.0 + "\n");
		System.out.println("");
		
		
		System.out.println("---------- Min Region Income ------------");
		System.out.printf("Rural region min income \t" + Math.round(minmaxArr[0]*100.0)/100.0 + "\n" +
	    		   "Inner city region min income \t" + Math.round(minmaxArr[2]*100.0)/100.0 + "\n" +
	    		   "Town region min income \t\t" + Math.round(minmaxArr[4]*100.0)/100.0 + "\n" +
	    		   "Suburban region min income \t" + Math.round(minmaxArr[6]*100.0)/100.0 + "\n");
		System.out.println("");
	 
	} // end PrintLocation
	
	public void saveLocationMinMax() {

		//print location compairing details to file
		double[] minmaxArr = this.locationMinMax(); //double array for location compare output
		
		try {
	 	      fw.write("Rural region max income \t" + Math.round(minmaxArr[1]*100.0)/100.0 + "\n" +
		    		   "Inner city region max income \t" + Math.round(minmaxArr[3]*100.0)/100.0 + "\n" +
		    		   "Town region max income \t\t" + Math.round(minmaxArr[5]*100.0)/100.0 + "\n" +
		    		   "Suburban region max income \t" + Math.round(minmaxArr[7]*100.0)/100.0 + "\n");
	          fw.write("...\n");
              fw.write("Rural region max income \t" + Math.round(minmaxArr[1]*100.0)/100.0 + "\n" +
	    		   "Inner city region max income \t" + Math.round(minmaxArr[3]*100.0)/100.0 + "\n" +
	    		   "Town region max income \t\t" + Math.round(minmaxArr[5]*100.0)/100.0 + "\n" +
	    		   "Suburban region max income \t" + Math.round(minmaxArr[7]*100.0)/100.0 + "\n");
              fw.write("...\n");
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	} // saveLocationMinMax

	public int[] sexComp() {
		Arrays.sort(robjs,new SexComparator());
		
		//set up needed vars for region counts & incomes per loc
		int townCt = 0, innerCt=0, suburbanCt=0,ruralCt=0;
			
		for (int i=0;i<robjs.length;i++)
		{
			//Rural counting
			if (robjs[i].getRegion().equals("RURAL") &&
					robjs[i].getSex().equals("FEMALE") &&
					robjs[i].getSave_act().equals("YES")) { 
				++ruralCt;
			}	
			// Inner city count
			else if (robjs[i].getRegion().equals("INNER_CITY")&&
					robjs[i].getSex().equals("FEMALE") &&
					robjs[i].getSave_act().equals("YES")) {
					++innerCt;
			}
		    // Town count
			else if (robjs[i].getRegion().equals("TOWN") &&
					robjs[i].getSex().equals("FEMALE") &&
					robjs[i].getSave_act().equals("YES")) {
				++townCt;
			}
			// suburban
			else if (robjs[i].getRegion().equals("SUBURBAN")&&
					robjs[i].getSex().equals("FEMALE") &&
					robjs[i].getSave_act().equals("YES")) {
					++suburbanCt;
			}
	    
		} // end for loop
	int[] femaleCount = {ruralCt, innerCt, townCt, suburbanCt};
	return femaleCount;
	} // end SexComp

} // end class