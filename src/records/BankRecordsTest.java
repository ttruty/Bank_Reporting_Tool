package records;

import java.io.IOException;

import controllers.LoanProcessing;

public class BankRecordsTest {
    public static void main(String[] args) {
        // write your code here


    	System.out.println("Programmed by Timothy Truty");

// SERIALIZATION
    	
//    	SerialRecord sobj = new SerialRecord();
//    	try {
//			sobj.makeMap();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	try {
//			sobj.deserialize();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
// DATABASE CONNECTION
    	LoanProcessing.main();
    	
    	
// BANK GUI 
//        Login.run();
//
//        if (Login.loginState) {
//            Menu bankMenu = new Menu();
//            bankMenu.bankMenu();
//        }
//         else {
//            return;
//        }

    }
}
