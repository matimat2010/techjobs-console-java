package org.launchcode.techjobs.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                //    System.out.println("Search all fields not yet implemented.");
                   printJobs(JobData.findByValue(searchTerm));
                    // passing in findByValue method
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        //someJobs is the variable name of an ArrayList of HashMap objects with String keys and String values.
        //This is being passed in by calling the printJobs class on lines 41 and 66.

        //Line 41 calls findAll from the jobData class line 31.  Which calls a loadData() method,
        //A new arrayList of Strings named "values" is initialized. In the for loop Hash Map "row iterates over allJobs.
        // String "aValue" is declared which assigns the row.get(field) method
        //if the ArrayList of Strings "values" does not contain the field from the row it os added to the ArrayList Values.
        //*ask how this works, for (HashMap<String, String> row : allJobs) is it declaring the HashMap "row"?
        // returns values
        //overloaded on line 49 and calls loadData then returns "allJobs"

        //Line 66 calls findByColumnAndValue from the jobData class line 75 where an array list of HashMap objects
        // called jobs is made of String keys and Values looks to be created by "getting" the information from the csv rows
        // using a for loop to compare the info from the rows and the array list of HashMap objects that are made of strings;
        // if they match they are stored in an arrayList of HashMap objects called "jobs"



        if(someJobs.size() == 0){
            System.out.println("There are no current positions that match your search criteria");
        }

        for(HashMap eachSomeJobs : someJobs){
         //   for each "some jobs" in "someJobs"
            System.out.println("\n");
            //create space at the top
            System.out.println("*****");
            //print asterix from the text

//            Key = eachSomeJobs.keySet();
//            eachSomeJobs.values();
//            System.out.println(eachSomeJobs.values() + ":");
   //         failed attempt needed to loop and print each value set
     //         then restart the loop.

            eachSomeJobs.forEach((k,v) -> System.out.println(k + " : " +v));
            //iterates over each element in the ArrayList and prints the key and value, "k & v"
            //from the HashMap object. At the end of the HashMap object code contines to the rest of the for loop

            System.out.println("*****");
            //print asterix from the text

        }

       // System.out.println("printJobs is not implemented yet");
    }
}
