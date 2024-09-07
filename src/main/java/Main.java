/*
Filename: W10U2A5_MuhammadAhmad_HotelRewards
Author:  Muhammad Ahmad
Date: Monday, April 30, 2024
Purpose: To create a Hotel Awards tracking program that allows for any number of members, and keeps data on the member's names and number of nights a member stays per month.  
*/

import java.util.*;

public class Main {

  // Print method
  public static void print(String message) {
    System.out.println(message);
  }

  public static void main(String[] args) {
    // Create Array List of Arrays
    ArrayList<String[]> dataBase = new ArrayList<String[]>();
    
    // Initialize variables
    int optionChoice;

    // Set scanner to keyedInput
    Scanner keyedInput = new Scanner(System.in);

    // While loop to keep giving user a menu of options
    while (true) {

      // Print out intro
      intro();
      
      // Take option choice
      optionChoice = keyedInput.nextInt();

      switch (optionChoice) {
        // Add members
        case 1:
          dataBase.add(addMember());
          break;
        // Delete members
        case 2:
          dataBase = deleteMember(dataBase);
          break;
        // Display all database information
        case 3:
          displayMember(dataBase);
          break;
        // Display platnium (VIP) members
        case 4:
          displayPlatnium(dataBase);
          break;
        // Search database
        case 5:
          searchMember(dataBase);
          break;
        // Edit the numbers of nights stayed
        case 6:
          editMember(dataBase);
          break;
        // Exit database
        case 7:
          print("You have exited the program, have a good day.");
          System.exit(0);
          // Default case in case of incorrect input
        default:
          print("Invalid input. Enter a number between 1 and 7 to execute the corrosponding action.\n");
          break;

      }
    }
  }

  // Intro
  public static void intro() {
    // Give user options
    System.out.print(
        "\n\nInput the corrosponding number for the following executables (1-7)\n" +
            "1. Add a member to database\n" +
            "2. Delete a member's records from the database\n" +
            "3. Display all database information\n" +
            "4. Display the last names of platnium (VIP) members\n" +
            "5. Search database for members and list that members information\n" +
            "6. Edit the number of nights stayed per month\n" +
            "7. Exit program\n\n" +
            "Enter the number: ");
  }
  // Adding members
  public static String[] addMember() {
    // Initialize variables
    int nights;

    // Set array
    String[] memberData = new String[6];

    // Set scanner to keyedInput
    Scanner keyedInput = new Scanner(System.in);

    // Take inputs and make everything lowercase to ignore capitalization
    print(
        "To add a member to the database, you must enter their, first name, last name and the number of nights stayed.");

    print("\nEnter the last name: ");
    memberData[0] = keyedInput.next();
    memberData[0].toLowerCase();

    print("\nEnter the first name: ");
    memberData[1] = keyedInput.next();
    memberData[1].toLowerCase();

    print("\nEnter the number of nights stayed: ");
    memberData[2] = keyedInput.next();

    // Determine the reward level and points based off of number of nights stayed
    nights = Integer.parseInt(memberData[2]);
    if (nights == 0) {
      memberData[3] = "None";
      memberData[4] = "0";
    } else if (nights == 1 || nights == 2) {
      memberData[3] = "Silver";
      memberData[4] = String.valueOf(nights * 500);
    } else if (nights >= 3 && nights <= 9) {
      memberData[3] = "Gold";
      memberData[4] = String.valueOf(1000 + (nights - 2) * 1000);
    } else if (nights >= 10) {
      memberData[3] = "Platinum";
      memberData[4] = String.valueOf(8000 + (nights - 9) * 1500);
    }

    // Calculate free stays earned
    memberData[5] = String.valueOf(Integer.parseInt(memberData[4]) / 3000);

    return memberData;
  }

  // Deleting members
  public static ArrayList<String[]> deleteMember(ArrayList<String[]> dataBase) {
    // Initialize variables and scanner
    String lastName;
    Boolean cFound;
    Scanner keyedInput = new Scanner(System.in);

    // Ask user to enter last name and make it all lower case so as to ignore
    // capitlization
    print("To delete a member's record from the database, you must input their last name: ");
    lastName = keyedInput.next();
    lastName.toLowerCase();

    // Set to false as default
    cFound = false;

    // Iterate through dataBase
    for (int element = 0; element < dataBase.size(); element++) {
      String[] memberData = new String[6];
      memberData = dataBase.get(element);
      // If it finds the name is in the database, it outputs the following
      if (lastName.equals(memberData[0])) {
        print("\n'" + lastName + "' is the user's last name that you have removed from the database");
        dataBase.remove(memberData);
        cFound = true;
      }
    }

    // If eFound is never changed to true, then that means that no employee was
    // found
    if (cFound == false) {
      print("\nThere is no user with the last name you inputted in the database");
    }
    return dataBase;
  }

  // Display members
  public static void displayMember(ArrayList<String[]> dataBase) {
    print("This is the function to display members");
    // Print headers
    System.out.format("%n%-15s %-15s %-15s %-15s %-15s %-20s", "First Name", "Last Name", "Nights Stayed",
        "Reward Level", "Points Earned", "Free Stays Earned");

    for (int i = 0; i < dataBase.size(); i++) {
      String[] memberDisplay = dataBase.get(i);
      // Print headers
      System.out.format("%n%-15s %-15s %-15s %-15s %-15s %-20s", memberDisplay[1], memberDisplay[0], memberDisplay[2],
          memberDisplay[3], memberDisplay[4], memberDisplay[5]);
    }
  }

  // Display platnium members
  public static void displayPlatnium(ArrayList<String[]> dataBase) {

    Boolean platBoolean = false;

    for (int i = 0; i < dataBase.size(); i++) {
      String[] memberDisplay = dataBase.get(i);
      if (memberDisplay[3].equals("Platinum")) {
        // Print header
        print("VIP List");
        platBoolean = true;
        break;
      }
    }

    if (platBoolean == false) {
      print("No users in database are VIP members with platinum reward level");
    }

    if (platBoolean == true) {
      for (int i = 0; i < dataBase.size(); i++) {
        String[] memberDisplay = dataBase.get(i);
        if (memberDisplay[3].equals("Platinum")) {
          System.out.println(memberDisplay[0]);
        }
      }
    }
  }

  // Search members
  public static void searchMember(ArrayList<String[]> dataBase) {
    // Initialize local variables and scanner
    Scanner keyedInput = new Scanner(System.in);
    String lastName;
    Boolean cFound = false;

    // Ask for last name
    print("Input the last name of the user you wish to find: ");
    lastName = keyedInput.next();

    // Iterate through database
    for (int i = 0; i < dataBase.size(); i++) {
      // Assign array to element
      String[] memberSearch = dataBase.get(i);
      // Check if lastName equals
      if (lastName.equals(memberSearch[0])) {
        // Print headers
        System.out.format("%n%-15s %-15s %-15s %-15s %-15s %-20s", "First Name", "Last Name", "Nights Stayed",
            "Points Earned", "Reward Level", "Free Stays Earned");
        System.out.format("%n%-15s %-15s %-15s %-15s %-15s %-20s", memberSearch[1], memberSearch[0], memberSearch[2],
            memberSearch[3], memberSearch[4], memberSearch[5]);

        // Set to true to signal that the customer with the last name was found in the database
        cFound = true;
      }
    }
    if (cFound == false) {
      print("No user with '" + lastName + "' as a last name was found");
    }
  }

  // Edit member's number of nights stayed
  public static void editMember(ArrayList<String[]> dataBase) {
    // Initialize local variables and scanner
    String nStayed;
    String lastName;
    Boolean cFound;
    int nights;
    Scanner keyedInput = new Scanner(System.in);

    // Take input and adjust capitlization
    print("Please input the last name of the user who's record you want to edit: ");
    lastName = keyedInput.next();
    lastName.toLowerCase();

    cFound = false;
    // Find the array element the user is in
    for (int element = 0; element < dataBase.size(); element++) {
      // Assigns array to element
      String[] memberData = new String[6];
      memberData = dataBase.get(element);
      // If it finds the name is in the current array, it does the following
      if (lastName.equals(memberData[0])) {
        print("Please input the updated number of nights stayed for this calander month: ");
        nStayed = keyedInput.next();
        // Change nights stayed
        memberData[2] = nStayed;

        // Adjust points and reward level
        nights = Integer.parseInt(nStayed);
        if (nights == 0) {
          memberData[3] = "None";
          memberData[4] = "0";
        } else if (nights == 1 || nights == 2) {
          memberData[3] = "Silver";
          memberData[4] = String.valueOf(nights * 500);
        } else if (nights >= 3 && nights <= 9) {
          memberData[3] = "Gold";
          memberData[4] = String.valueOf(1000 + (nights - 2) * 1000);
        } else if (nights >= 10) {
          memberData[3] = "Platnium";
          memberData[4] = String.valueOf(8000 + (nights - 9) * 1500);
        }

        // Calculate free stays earned
        memberData[5] = String.valueOf(Integer.parseInt(memberData[4]) / 3000);

        // Set to true to signal that the customer with the last name was found in the database
        cFound = true;
      }
    }
    // If cFound is never changed to true, then that means that no user was found
    if (cFound == false) {
      print("\nThere is no user with the last name you inputted in the database");
    }
  }
}