package records;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class SerialRecord {
	
	final String serSaveFile = "Resources//bankrecords.ser";
	double serialRunTime = 0;
	double deserialRunTime = 0;
	
	public void makeMap() throws IOException {
		
	    Map<Integer,BankRecords> map=new HashMap<Integer, BankRecords>();
    
	    BankRecords br = new BankRecords();
	    br.readData();
	    int recordCount = 1;
	    
	    System.out.println("Starting serializtion");
	    long startSer = new java.util.Date().getTime();
	    //System.out.println(startSer);

	    for (BankRecords robj : br.robjs)
	    {
	    	//System.out.println(robj.getId());
	    	
	    	map.put(recordCount, robj);
	    	recordCount++;
	    }
	    
	    try {
			FileOutputStream fos = new FileOutputStream(serSaveFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(map);
			oos.close();
			fos.close();
			System.out.println("Serialized Map Data is saves in: " + serSaveFile);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    long endSer = new java.util.Date().getTime();
	    System.out.println("Serialization Complete");
	    //System.out.println(endSer);
	    
	    serialRunTime = (double)((endSer - startSer)) /1000;
	    System.out.println("Serialization time delta: \t" + serialRunTime);
	    
	    
	    
	    try {
	    	System.out.println("Sleep for 5 Seconds");
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public void deserialize() throws IOException, ClassNotFoundException
	{
		long startDeser = 0;
		long endDeser = 0;

	    List<Object> results = new ArrayList<Object>();	
	    FileInputStream fis = null;
	    try {
	    	
	    	startDeser =  new java.util.Date().getTime();
	    	
	        fis = new FileInputStream(serSaveFile);
	        while (true) {
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            //must cast to a BankRecords Objects
	            results.add((Object) ois.readObject());
	        }
	    } catch (EOFException ignored) {
	        // as expected
	    } finally {
	    	endDeser =  new java.util.Date().getTime();
	    	
	        if (fis != null)
	            fis.close();
	    }
	    //System.out.println("results = " + results);
	    //System.out.println(results.get(0));
	    
	    deserialRunTime = (double)((endDeser - startDeser)) /1000;
	    System.out.println("Deserialization time delta: \t" + deserialRunTime);
	    
	    
	    double runTimeDiff = deserialRunTime - serialRunTime;
	    System.out.println("Time Differece between Serialization and Deserializaton: " + runTimeDiff);
	    
	    
	}
}