package records;

import java.io.*;
import java.nio.file.Files; //fileInfo
import java.nio.file.Paths; //fileInfo
import java.nio.file.attribute.BasicFileAttributes; //fileInfo
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class BankRecords extends Client implements Serializable {
    /**
     * Creates the bank record objects,
     * Calls the read, process, print data
     *
     */

    final static int NUMOFRECORDS = 600; //constant to hold the number of records in file
    //array of BankRecords objects
    protected static BankRecords robjs[] = new BankRecords[NUMOFRECORDS];
    //arraylist to hold spreadsheet rows & columns
    static ArrayList<List<String>> array = new ArrayList<>();
    //set the filePath
    String filePath = "Resources\\bank-Detail.csv";
    
    
    // Instance fields
    private String id;
    private int age;
    private String sex;
    private String region;
    private double income;
    private String married;
    private int children;
    private String car;
    private String save_act;
    private String current_act;
    private String mortgage;
    private String pep;

    //flag variable
    static boolean fileFlag = false;


    /**
     *  access the csv file and read the data
     */
    @Override
    public void readData() {

        //if the array is not empty clear it out
        if (!array.isEmpty()){
            //this is needed as the menu recalls the read...
            //TODO find a way where the menu does not constantly call re-read for memory usange and efficiency
            array.clear();
        }

        BufferedReader br = null;

        //initialize reader object and set file path to root of project
        try {
            br = new BufferedReader(new FileReader(new File(filePath)));            
        
        } catch (FileNotFoundException e) {
            System.out.println("File: " + filePath + " not found.");
            //e.printStackTrace();
        }

        String line;

        int counter = 0;
        //read each record in csv file
        try {
            int i = 0;
            while ((line = br.readLine()) != null) {
                //parse each record in csv file by a comma ( , )
                //into a list stored in the arraylist-> Arrays
                array.add(Arrays.asList(line.split(",")));
                //System.out.println(array.get(i++));
                counter++;
            }

        } catch (IOException | NullPointerException e) { //catch multiple excpetions
            System.out.println("File not found error, please locate file");
            System.out.println("");
            fileFlag = true; //set a flag to alert that the file is missing or currupt
            //e.printStackTrace();
        }
        //System.out.println("Counter=" + counter);

        processData();  //call function for processing record data

    }

    /**
     *  create the bank records object
     */
    @Override
    public void processData() {
        //with each entry in csv add those data to bank record object
        int bankArray_counter = 0; //initialize a counter to index all items of bank object array
        for (List<String> rows: array){
            //initalize a single bank record object
            BankRecords rObj = new BankRecords();

            // populate bank record with data
            try {
                rObj.setId(rows.get(0));
                rObj.setAge(Integer.parseInt(rows.get(1)));
                rObj.setSex(rows.get(2));
                rObj.setRegion(rows.get(3));
                rObj.setIncome(Double.parseDouble(rows.get(4)));
                rObj.setMarried(rows.get(5));
                rObj.setChildren(Integer.parseInt(rows.get(6)));
                rObj.setCar(rows.get(7));
                rObj.setSave_act(rows.get(8));
                rObj.setCurrent_act(rows.get(9));
                rObj.setMortgage(rows.get(10));
                rObj.setPep(rows.get(11));
            } catch (ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            }
            //System.out.println(rObj.toString());

            //add current Bank Record object to Bank Record array at current counter index
            robjs[bankArray_counter] = rObj;
            //increment bank array counter index
            bankArray_counter++;
        }
    }

    /**
     * Print out the selected number of rows
     *
     * @param rows
     */
    @Override
    public void printData(int rows) {
        // print header
        // formatting spaced out the items to be in columns
        System.out.println(String.format("%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s\n",
                " ",
                "Bank Id",
                "Age",
                "Sex",
                "Region",
                "Income",
                "Married",
                "Children",
                "Car",
                "Savings",
                "Current",
                "Mortgage",
                "PEP"));

        //print data output from toString
        for (int i = 0; i < rows; i++){
            System.out.println(robjs[i].toString());
        }

    }

    /**
     * Gets the file info from the filePath specified
     *
     * @return String value of the file info
     */
    public StringBuilder fileInfo(){

        //initialize the basic file attr object
        BasicFileAttributes attr = null;

        //read the file attributes
        try {
            attr = Files.readAttributes(Paths.get(filePath), BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //create the string builder for output
        StringBuilder sb = new StringBuilder();

        //check that attr is not null
        if (attr != null) {
            sb.append("*************Report File Information**************\n" +
                    "fileName: " + "\t\t\t\t" + filePath + "\n" +
                    "creationTime: " + "\t\t\t" + attr.creationTime() + "\n" +
                    "lastAccessTime: " + "\t\t"  + attr.lastAccessTime() + "\n" +
                    "lastModifiedTime: " + "\t\t"  + attr.lastModifiedTime() + "\n" +
                    "size: " + "\t\t\t\t\t"  + attr.size() + "\n" );
        }

        return sb;

    }

    @Override
    public String toString() {
        // formatting spaced out the items to be in columns
        return String.format("%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s%-12s\n",
                "BankRecord: ",
                id,
                age,
                sex,
                region,
                income,
                married,
                children ,
                car,
                save_act,
                current_act,
                mortgage,
                pep);
    }

    /**
     * Gets id
     *
     * @return String of id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *  Gets age
     *
     * @return value of age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     *  Gets sex
     *
     * @return string of sex
     */
    public String getSex() {
        return sex;
    }

    /**
     *  Sets sex
     *
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Gets region
     *
     * @return string of region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets region
     *
     * @param region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     *  Gets income
     *
     * @return value of income
     */
    public double getIncome() {
        return income;
    }

    /**
     * Sets income
     *
     * @param income
     */
    public void setIncome(double income) {
        this.income = income;
    }

    /**
     * Gets married
     *
     * @return string of married
     */
    public String getMarried() {
        return married;
    }

    /**
     * Sets married
     *
     * @param married
     */
    public void setMarried(String married) {
        this.married = married;
    }

    /**
     * Gets children
     *
     * @return value of children
     */
    public int getChildren() {
        return children;
    }

    /**
     * Sets children
     *
     * @param children
     */
    public void setChildren(int children) {
        this.children = children;
    }

    /**
     * Gets car
     *
     * @return String of car
     */
    public String getCar() {
        return car;
    }

    /**
     * Sets car
     *
     * @param car
     */
    public void setCar(String car) {
        this.car = car;
    }

    /**
     * Gets save_act
     *
     * @return value of save_act
     */
    public String getSave_act() {
        return save_act;
    }

    /**
     * Sets save_act
     *
     * @param save_act
     */
    public void setSave_act(String save_act) {
        this.save_act = save_act;
    }

    /**
     * Gets current_act
     *
     * @return string of current_act
     */
    public String getCurrent_act() {
        return current_act;
    }

    /**
     * Sets current_act
     *
     * @param current_act
     */
    public void setCurrent_act(String current_act) {
        this.current_act = current_act;
    }

    /**
     * Gets mortgage
     *
     * @return string of mortgage
     */
    public String getMortgage() {
        return mortgage;
    }

    /**
     * Sets mortgage
     *
     * @param mortgage
     */
    public void setMortgage(String mortgage) {
        this.mortgage = mortgage;
    }

    /**
     * Gets pop
     *
     * @return string of pep
     */
    public String getPep() {
        return pep;
    }

    /**
     * Sets pep
     *
     * @param pep
     */
    public void setPep(String pep) {
        this.pep = pep;
    }
}
