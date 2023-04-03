package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Schedule {
    private int[] availableTimes = new int[23];
    private boolean[] volunteerNeeded = new boolean[23];
    private ArrayList<Animal> animals;
    private ArrayList<ScheduleItem>[] schedule = new ArrayList[23]; 
    private ArrayList<ScheduleItem> treatmentItems;
    private ArrayList<ScheduleItem> taskItems;


    public Schedule(){
        retrieveAnimals();
        retrieveTreatments();

    }

    public ResultSet queryDatabase(String query) throws DatabaseConnectionException{

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "user1", "ensf");
            Statement statement = connection.createStatement();

            // Query the database and create ScheduleItem objects
            // String query = "SELECT TREATMENTS.TreatmentID, TREATMENTS.AnimalID, TREATMENTS.TaskID, TREATMENTS.StartHour, TASKS.MaxWindow, TASKS.Duration, TASKS.Description FROM TREATMENTS INNER JOIN TASKS ON TREATMENTS.TaskID = TASKS.TaskID";
            ResultSet resultSet = statement.executeQuery(query);
            connection.close();
            return resultSet;
        }
    
        catch (Exception e) {
            throw new DatabaseConnectionException("Could not connect to database.");
            //e.printStackTrace();
        }

    }

    public void retrieveAnimals(){
        try{
            ResultSet rs = queryDatabase("SELECT * FROM EWR.ANIMALS;");
            while (rs.next()) {
                int animalID = rs.getInt("AnimalID");
                String animalNickname = rs.getString("AnimalNickname");
                String animalSpecies = rs.getString("AnimalSpecies");

                // Assuming all animals are not orphans and don't need treatment for simplicity
                Animal animal = new Animal(animalID, animalNickname, animalSpecies);
                this.animals.add(animal);
            }
        }
        
        catch(DatabaseConnectionException e){
            e.printStackTrace();
        }

        catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void retrieveTreatments(){
        try{
            ResultSet rs = queryDatabase("SELECT * FROM EWR.ANIMALS;");
            while (rs.next()) {
                int treatmentID = rs.getInt("TreatmentID");
                int animalID = rs.getInt("AnimalID");
                int taskID = rs.getInt("TaskID");
                int startHour = rs.getInt("StartHour");
                int maxWindow = rs.getInt("MaxWindow");
                int duration = rs.getInt("Duration");
                String description = rs.getString("Description");

                ScheduleItem item = new ScheduleItem(treatmentID, animalID, taskID, startHour, maxWindow, duration,
                        description);
                this.treatmentItems.add(item);
            }
        }
        
        catch(DatabaseConnectionException e){
            e.printStackTrace();
        }

        catch(SQLException e){
            e.printStackTrace();
        }

    }

    public ArrayList<Animal> getAnimals(){
        return this.animals;
    }

}
