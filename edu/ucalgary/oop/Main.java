/**
@author Tanis Smith <a href="mailto:tanis.smith@ucalgary.ca">
tanis.smith@ucalgary.ca</a>
@version 1.3
@since 1.0
*/

package edu.ucalgary.oop;

import java.util.*;   

/**
    * Method to instantiate a Schedule object for the following day, then saves it as a .txt file via class method.
    * @param args[] (Not used.)
    * @return Nothing
    */
public class Main {
    public static void main(String[] args) {
        try{
            Schedule schedule = new Schedule();
            String formatted = new String();
            formatted = schedule.formatSchedule();
            System.out.println("Schedule completed and saved to text file.");
            
        }

        catch(DatabaseConnectionException e){
            e.printStackTrace();
        }

    }
}
