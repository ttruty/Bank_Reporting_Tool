package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

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
			
			//timeStamp variable to hold current date/time
			String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
			try {
		 	      fw.write("Analyisis completed on:" + "\n" +
		 	    		 timeStamp + "\nProgrammed by Tim Truty\n");
		          fw.write("...\n");
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	} 

	/**
	 * @return array of location analysis averages
	 */
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
		} // end for loop
		//setup resulting averages to print to console and to file
		double ruralAvg = ruralIncSum/(ruralCt);
		double innerAvg = innerIncSum/(innerCt);
		double townAvg = townIncSum/(townCt);
		double suburbanAvg = suburbanIncSum/(suburbanCt);
	
		//create array to return
		double[] avgIncArr = {ruralAvg, innerAvg, townAvg, suburbanAvg};

	    return avgIncArr;
	    
		} // end LocationComp
	
	/**
	 * Save location analysis to file
	 */
	public void saveLocation() {
		//save location comparison to file
		double[] incArr = locationComp(); //double array for location compare output
		
		System.out.println(" ... ");
		System.out.println(" Printing Location Results to File. ");
		System.out.println(" File location at " + saveFile);
		System.out.println(" ...\n ");
		
		
		;
		
		// Math.round(incArr[0]*100.0)/100.0 = make 2 decimal
		try {
	 	      fw.write("Avg inc. for rural region \t\t" + Math.round(incArr[0]*100.0)/100.0 + "\n" +
		    		   "Avg inc. for inner city region \t" + Math.round(incArr[1]*100.0)/100.0 + "\n" +
		    		   "Avg inc. for town region \t\t" + Math.round(incArr[2]*100.0)/100.0 + "\n" +
		    		   "Avg inc. for suburban region \t" + Math.round(incArr[3]*100.0)/100.0 + "\n");
	                  fw.write("...\n");
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	} //end saveLocation
	
	/**
	 *  Print the location analysis to screen
	 */
	public void printLocation() {
		//print location compairing details to screen
		System.out.println("-------Average income by location--------");
		double[] incArr = locationComp(); //double array for location compare output		
		System.out.printf("Avg inc. for rural region \t" + Math.round(incArr[0]*100.0)/100.0 + "\n" +
	    		   "Avg inc. for inner city region \t" + Math.round(incArr[1]*100.0)/100.0 + "\n" +
	    		   "Avg inc. for town region \t" + Math.round(incArr[2]*100.0)/100.0 + "\n" +
	    		   "Avg inc. for suburban region \t" + Math.round(incArr[3]*100.0)/100.0 + "\n");	 
	} // end PrintLocation
	
	/**
	 * @return array of min max for each location
	 */
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
			if (robjs[i].getRegion().equals("RURAL")) {
				double temp = robjs[i].getIncome();
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
	
	/**
	 * Print the location min max analysis to screen
	 */
	public void printLocationMinMax() {
		
		//print location compairing details to screen
		double[] minmaxArr = locationMinMax(); //double array for location compare output		
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
	
	/**
	 * Save the location min max analysis for the file
	 */
	public void saveLocationMinMax() {

		//print location compairing details to file
		double[] minmaxArr = this.locationMinMax(); //double array for location compare output
		
		try {
	 	      fw.write("Rural region max income \t\t" + Math.round(minmaxArr[1]*100.0)/100.0 + "\n" +
		    		   "Inner city region max income \t" + Math.round(minmaxArr[3]*100.0)/100.0 + "\n" +
		    		   "Town region max income \t\t\t" + Math.round(minmaxArr[5]*100.0)/100.0 + "\n" +
		    		   "Suburban region max income \t\t" + Math.round(minmaxArr[7]*100.0)/100.0 + "\n");
	          fw.write("...\n");
              fw.write("Rural region max income \t\t" + Math.round(minmaxArr[1]*100.0)/100.0 + "\n" +
	    		   "Inner city region max income \t" + Math.round(minmaxArr[3]*100.0)/100.0 + "\n" +
	    		   "Town region max income \t\t\t" + Math.round(minmaxArr[5]*100.0)/100.0 + "\n" +
	    		   "Suburban region max income \t\t" + Math.round(minmaxArr[7]*100.0)/100.0 + "\n");
              fw.write("...\n");
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	} // saveLocationMinMax

	/**
	 * Count the number of male vs females by location
	 * Females have mortgare and savings acct
	 * Males with car and 1 child
	 * @return array of counts on sex criteria
	 */
	public int[] sexComp() {
		Arrays.sort(robjs,new SexComparator());
		
		//set up needed vars for sex counts
		int townCtFemale = 0, innerCtFemale=0, suburbanCtFemale=0,ruralCtFemale=0;
		int townCtMale = 0, innerCtMale = 0, suburbanCtMale = 0, ruralCtMale = 0;
		
		for (int i=0;i<robjs.length;i++)
		{
			// boolean for the male and female criteria
			boolean femaleCriteria = robjs[i].getSex().equals("FEMALE") &&
					robjs[i].getSave_act().equals("YES") &&
					robjs[i].getMortgage().equals("YES");
			boolean maleCriteria = robjs[i].getSex().equals("MALE") &&
					robjs[i].getCar().equals("YES") &&
					robjs[i].getChildren() == 1;
			
			//Count females by location with mortgage and savings account
			//Rural counting female
			if (robjs[i].getRegion().equals("RURAL") &&
					femaleCriteria) { 
				++ruralCtFemale;
			}	
			// Inner city count female
			else if (robjs[i].getRegion().equals("INNER_CITY")&&
					femaleCriteria){
					++innerCtFemale;
			}
		    // Town count female
			else if (robjs[i].getRegion().equals("TOWN") &&
					femaleCriteria) {
					++townCtFemale;
			}
			// suburban female
			else if (robjs[i].getRegion().equals("SUBURBAN")&&
					femaleCriteria) {
					++suburbanCtFemale;
			}
			//Rural male
			else if (robjs[i].getRegion().equals("RURAL")&&
					maleCriteria) {
					++ruralCtMale;
			}
			//Inner City male
			else if (robjs[i].getRegion().equals("INNER_CITY")&&
					maleCriteria) {
					++innerCtMale;
			}
			//Town male
			else if (robjs[i].getRegion().equals("TOWN")&&
					maleCriteria) {
					++townCtMale;
			}
			//suburban male
			else if (robjs[i].getRegion().equals("SUBURBAN")&&
					maleCriteria) {
					++suburbanCtMale;
			}
			
	    
		} // end for loop
	int[] sexCount = {ruralCtFemale, innerCtFemale, townCtFemale, suburbanCtFemale,
			ruralCtMale, innerCtMale, townCtMale, suburbanCtMale};
	return sexCount;
	
	} // end SexComp
	
	/**
	 * Print the sex analysis to screen
	 */
	public void printSexAnalysis() {
		
		//print location compairing details to screen
		int[] sexArr = sexComp(); //double array for location compare output		
		System.out.println("---------- Sex Region Counts ------------");
		System.out.println("");
		System.out.println("-Females with mortages and savings Acct--");
		System.out.printf("Rural region females with mort and savings acct: \t" + sexArr[0] + "\n" +
	    		   "Inner city region females with mort and savings acct: \t" + sexArr[1] + "\n" +
	    		   "Town region females with mort and savings acct: \t" + sexArr[2] + "\n" +
	    		   "Suburban region females with mort and savings acct: \t" + sexArr[3] + "\n");
		System.out.println("");
		
		
		System.out.println("-----Males with car and 1 child ------");
		System.out.printf("Rural region males with car and 1 child: \t" + sexArr[4] + "\n" +
	    		   "Inner city region males with car and 1 child: \t" + sexArr[5] + "\n" +
	    		   "Town region males with car and 1 child: \t" + sexArr[6] + "\n" +
	    		   "Suburban region males with car and 1 child: \t" + sexArr[7] + "\n");
		System.out.println("");
	 
	} // end PrintSexAnalysis
	
	/**
	 *  Save the sex analysis output to file
	 */
	public void saveSexAnalysis() { 
		//print sex compairing details to file
		int[] sexArr = sexComp(); //double array for location compare output		
				
		try {
	 	      fw.write("Rural region females with mort and savings acct: \t\t" + sexArr[0] + "\n" +
		    		   "Inner city region females with mort and savings acct: \t" + sexArr[1] + "\n" +
		    		   "Town region females with mort and savings acct: \t\t" + sexArr[2] + "\n" +
		    		   "Suburban region females with mort and savings acct: \t" + sexArr[3] + "\n");
	          fw.write("...\n");
              fw.write("Rural region females with mort and savings acct: \t\t" + sexArr[0] + "\n" +
   	    		   "Inner city region females with mort and savings acct: \t" + sexArr[1] + "\n" +
   	    		   "Town region females with mort and savings acct: \t\t" + sexArr[2] + "\n" +
   	    		   "Suburban region females with mort and savings acct: \t" + sexArr[3] + "\n");
              fw.write("...\n");
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		
	} //end saveSexAnalysis


	/**
	 * 
	 * @return arrays with values of average ages of region
	 */
	public double[] ageCompRegion() {
		Arrays.sort(robjs,new LocationComparator());
		
		//set up needed vars for region counts & incomes per loc
		double townCt = 0, innerCt=0, suburbanCt=0,ruralCt=0,
			townAgeSum = 0, innerAgeSum=0, suburbanAgeSum=0,ruralAgeSum=0;
			
		for (int i=0;i<robjs.length;i++)
		{
			//Rural counting
			if (robjs[i].getRegion().equals("RURAL")) { 
				ruralAgeSum += robjs[i].getAge();
				++ruralCt;
			}	
				// Inner city count
			else if (robjs[i].getRegion().equals("INNER_CITY")) { 
				innerAgeSum += robjs[i].getAge();
				++innerCt;
			}
		    // Town count
			else if (robjs[i].getRegion().equals("TOWN")) { 
				townAgeSum += robjs[i].getAge();
				++townCt;
			}
			// suburban
			else if (robjs[i].getRegion().equals("SUBURBAN")) { 
				suburbanAgeSum += robjs[i].getAge();
				++suburbanCt;
			}
		} // end for loop
		//setup resulting averages to print to console and to file
		double ruralAvgAge = ruralAgeSum/(ruralCt);
		double innerAvgAge = innerAgeSum/(innerCt);
		double townAvgAge = townAgeSum/(townCt);
		double suburbanAvgAge = suburbanAgeSum/(suburbanCt);
		
		//create array to return
		double[] avgAgeArr = {ruralAvgAge, innerAvgAge, townAvgAge, suburbanAvgAge};

	    return avgAgeArr;
	} // end ageCompRegion
	
	
	/**
	 * 
	 * @return arrays with values of average ages of region
	 */
	public double[] ageCompSex() {
		Arrays.sort(robjs,new SexComparator());
		
		//set up needed vars for region counts & incomes per loc
		double maleCt = 0, femaleCt=0, femaleAgeSum = 0, maleAgeSum=0;
			
		for (int i=0;i<robjs.length;i++)
		{
			//female counting
			if (robjs[i].getSex().equals("FEMALE")) { 
				femaleAgeSum += robjs[i].getAge();
				++femaleCt;
			}	
				// male count
			else if (robjs[i].getSex().equals("MALE")) { 
				maleAgeSum += robjs[i].getAge();
				++maleCt;
			}
		} // end for loop
		
		//setup resulting averages to print to console and to file
		double femaleAvgAge = femaleAgeSum/(femaleCt);
		double maleAvgAge = maleAgeSum/(maleCt);
		
		//create array to return
		double[] avgAgeArr = {femaleAvgAge, maleAvgAge};

	    return avgAgeArr;
	} // end ageCompSex

	
	/**
	 * Print the age analysis to screen
	 */
	public void printAgeAnalysis() {
		
		//print agerage age compairing details to screen
		double[] ageArr = ageCompRegion(); //double array for location compare output
		double[] ageSex = ageCompSex();
		System.out.println("------- Average Age Per Region Counts ---------");
		System.out.println("");
		System.out.printf("Rural region average age: \t" + ageArr[0] + "\n" +
	    		   "Inner city region average age: \t" + ageArr[1] + "\n" +
	    		   "Town region average age: \t" + ageArr[2] + "\n" +
	    		   "Suburban region average age: \t" + ageArr[3] + "\n");
		System.out.println("");
		System.out.println("------------- Average Age by Sex --------------");
		System.out.println("");
		System.out.printf("Female average age: \t" + ageSex[0] + "\n" +
	    		   "Male average age: \t" + ageSex[1] + "\n");
		System.out.println("");
	} // end PrintAgeAnalysis
	
} // end class