package records;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Users {
    /**
     * service class to read in the complete user data file
     * reference used answer here
     * https://stackoverflow.com/questions/42190602/reading-a-csv-file-using-another-class-then-login-java
     */

    //member list object
    static User memberList[] = new User[4]; //members length is hard coded in, possible to make dynamic?
    // initilaize users list
    static ArrayList<List<String>> users = new ArrayList<>();


    /**
     * read the csv file inter user object and to the member list
     */
    public static synchronized void readUsers(){

        //if the users array is not empty clear it out
        if (!users.isEmpty()){
            //this is needed as the menu recalls the read...
            //TODO find a way where the menu does not constantly call re-read for memory usange and efficiency
            users.clear();
        }

        String filePath = ("Resources\\members.csv");

        BufferedReader members = null;

        try {
            members = new BufferedReader(new FileReader(new File(filePath)));
        } catch (FileNotFoundException e) {
            System.out.println("Member File: " + filePath + " not found.");
            //e.printStackTrace();
        }

        String line;
        //read each record in csv file
        try {
            int i = 0;
            while ((line = members.readLine()) != null) {
                //parse each member record in csv file by a comma ( , )
                //into a list stored in the arraylist-> Arrays
                users.add(Arrays.asList(line.split(",")));

            }

        } catch (IOException | NullPointerException e) { //catch multiple excpetions
            System.out.println("Member file not found error, please locate file");
            System.out.println("");
            //e.printStackTrace();
        }

        //call the process user method to populate members list
        processUser();
    }

    /**
     * populat the membersList array with the members from the csv file
     */
    public static void processUser() {
        //with each entry in csv add those data to bank record object
        int memberCounter = 0; //initialize a counter to index all items of memberlist array
        for (List<String> rows: users){

            //initalize a single user record object
            User user = new User();

            // populate member record with data
            try {
                user.setId(Integer.parseInt(rows.get(0)));
                user.setUsername(rows.get(1));
                user.setPassword(rows.get(2));
                user.setPermisions(Integer.parseInt(rows.get(3)));
            } catch (ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            }

            //add current user object to member list array at current counter index
            memberList[memberCounter] = user;
            //increment member array counter index
            memberCounter++;
        }
    }

    public static void printUsers(){

        System.out.print(
                String.format("%-12s%-12s%-12s%-12s%-12s\n",
                " ",
                "Id",
                "Username",
                "Password",
                "Permissions"));
        for (User member : memberList){
            System.out.println(member.toString());
        }
    }

    /**
     * Find username password with params
     *
     * @param username
     * @param password
     * @return the boolean value if username password fouhnd
     */
    public static synchronized boolean find(String username, String password){
        if(null == memberList){
            throw new IllegalStateException("user list is not initialised");
        }

        //check the user list csv and validate, username casing doent matter for username
        for(User currentMember : memberList){

//            System.out.println(currentMember.getUsername() + " " + currentMember.getPassword());
//            System.out.println(username + " " + password);
//            System.out.println(username.toLowerCase().equals(currentMember.getUsername()) && password.equals(currentMember.getPassword()));

            // validate unsername and password
            if (username.toLowerCase().equals(currentMember.getUsername()) && password.equals(currentMember.getPassword()))
                return true;
            }
        return false;
        }
}
