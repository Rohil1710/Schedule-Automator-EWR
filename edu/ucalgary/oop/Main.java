package edu.ucalgary.oop;

import java.util.*;   

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
