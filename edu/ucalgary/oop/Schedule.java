package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

// CONSTRUCTOR - retrieves all required data from database and initializes data members to create the schedule.

public class Schedule {
    private int[] availableTimes = new int[23];
    private boolean[] volunteerNeeded = new boolean[23];
    private ArrayList<Animal> animals = new ArrayList<Animal>();
    private ArrayList<ScheduleItem>[] schedule = new ArrayList[23]; 
    private ArrayList<ScheduleItem> treatmentItems = new ArrayList<ScheduleItem>();
    private ArrayList<ScheduleItem> taskItems = new ArrayList<ScheduleItem>();


    public Schedule() throws DatabaseConnectionException {

        try {
            retrieveAnimals();
            //retrieveTreatments();

        }
    
        catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }

    }

    public void retrieveAnimals() throws DatabaseConnectionException{
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "user1", "ensf");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM EWR.ANIMALS;");
            while (rs.next()) {
                int animalID = rs.getInt("AnimalID");
                String animalNickname = rs.getString("AnimalNickname");
                String animalSpecies = rs.getString("AnimalSpecies");

                Animal animal = new Animal(animalID, animalNickname, animalSpecies);
                this.animals.add(animal);
            }
            connection.close();
        }

        catch(SQLException e){
            e.printStackTrace();
        }

        catch(Exception e){
            throw new DatabaseConnectionException("Connection Error");
        }


    }


// GETTERS:

    public ArrayList<Animal> getAnimals(){
        return this.animals;
    }

    public ArrayList<Animal> getTreatmentItems(){
        return this.animals;
    }

    public ArrayList<Animal> getTaskItems(){
        return this.animals;
    }

    public int[] getAvailableTimes(){
        return this.availableTimes;
    }
    
    public boolean[] getVolunteerNeeded(){
        return this.volunteerNeeded;
    }

// Formats the schedule into a single string, creates a .txt file and outputs string version of schedule to terminal.
//public void formatSchedule(){}

}
