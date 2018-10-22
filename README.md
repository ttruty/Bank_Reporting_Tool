# Bank_Reporting_Tool
Bank reproting tool and menu system. Lab 02 for ITM 510 IIT Fall 2018

### Objective	
To write a program that parses and processes bank data from a file.


## PROJECT DESCRIPTION
Bank of IIT has gotten their hands on some interesting data which will allow for possible loans to various clients from various regions.

Accompanying the labs specs is a csv (comma separated value) file named 
bank-Detail.csv which contains valuable raw data to allow the bank to process loans based on client details from the file.

You need to parse the data and print record data for future loan considerations.

### PROJECT Overview

Initial execution of program loads a welcome message then a login screen.
The login validates the login data according to a csv file with user data.
The user is allowed 5 trys to login and then the program closes if login is not successful.
The username must be only alpha numeric and “_” “.” “-“, validation with regex.
Case to username is ignored.
Case for password must be adhered to.
Upon successful login the user can control system use with a menu.
The menu has 5 options.
1. Display Report
* User can display a selected number of records from the bank details csv files 
* Data is printed to display in neat columns 
2. Report File Info
* System display information about the bank detail csv file.
* File name
* Time of creation/modification
* Size
3. User List
* Lists out the user details from the acceptable users to login
* Id, username, password, permissions

4. About 
* Displays an about message
5. Log out
* User is logged out, taken back to login screen
