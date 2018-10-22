package com.company;
import java.util.Comparator;

public class LocationComparator implements Comparator<BankRecords>{
	 
	@Override
	public int compare(BankRecords o1, BankRecords o2) {
	// use compareTo to compare strings
	int result = o1.getRegion().compareTo(o2.getRegion());
	return result;
	}
	
 }
