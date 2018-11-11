package records;

import java.util.Scanner;

public class Login {
    public static boolean loginState = false;
    static int loginTryCount = 0;
    public static String userLogName;
    /**
     * Login method to run the login options
     * and authentication
     */
    public static void run(){
        //Welcome screen
        System.out.println("");
        System.out.println("");
        System.out.println("*********************************");
        System.out.println("----Bank of IIT Records System---");
        System.out.println("*********************************");
        System.out.println("");
        System.out.println("");
        System.out.println("--------Please Log in -----------");
        System.out.println("");

        while(!(loginState)) {

            //only allow 5 trys to login
            if (loginTryCount < 5) {
                // set initial username to empty string
                String username = "";
                String password = "";
                // set a regular expression to validate the username
                // username must only contain alpha-numeric, underscore, dot, hyphen
                // and be at least 5 characters
                String valid_regex = "[a-zA-Z0-9_.\\-]{5,}";
                //initialize scanner object
                Scanner scanner = new Scanner(System.in);

                //check that username is not null, empty or containing spaces
                while (!(username.matches(valid_regex))) {
                    System.out.print("Username: ");
                    username = scanner.nextLine();
                }

                System.out.print("Password: ");

                //TODO: possible to hide type in console when typing
                //check to see if this should be a char[] array.
                password = scanner.nextLine();

                //System.out.println("Usnername ===== " + username.toLowerCase());
                //System.out.println("Password ===== " + password);

                // variable holds boolean auth
                boolean authentic = authenticate(username, password);

                if (authentic) {
                    System.out.println("");
                    System.out.println("");
                    System.out.println("*******YOU ARE LOGGED IN********");
                    System.out.println("");
                    System.out.println("");
                    loginState = true;
                    userLogName = username;
                }
                else if (!authentic)
                {
                    System.out.println("");
                    System.out.println("****Invalid Username or Password****");
                    System.out.println("");
                }
                loginTryCount++; //increment the counter
            }
            else {
                System.out.println("");
                System.out.println("");
                System.out.println("*********************************");
                System.out.println("---To many attempts to log in,---");
                System.out.println("please contact bank support to" +
                                   "\nreset your username and password");
                System.out.println("*********************************");
                return;
            }
        } // end while login false
    }


    private static boolean authenticate(String username, String password) {
        //initialize user class
        Users.readUsers();
        return Users.find(username, password);
    }
}
