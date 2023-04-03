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


    public Schedule() throws DatabaseConnectionException {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "user1", "ensf");
            Statement statement = connection.createStatement();

            // Query the database and create ScheduleItem objects
            // String query = "SELECT TREATMENTS.TreatmentID, TREATMENTS.AnimalID, TREATMENTS.TaskID, TREATMENTS.StartHour, TASKS.MaxWindow, TASKS.Duration, TASKS.Description FROM TREATMENTS INNER JOIN TASKS ON TREATMENTS.TaskID = TASKS.TaskID";
            retrieveAnimals(statement);
            retrieveTreatments(statement);
            connection.close();

        }

        catch(SQLException e){
            System.out.println("Oops");
            e.printStackTrace();
        }
    
        catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }

    }

    public void retrieveAnimals(Statement statement){
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM EWR.ANIMALS;");
            while (rs.next()) {
                int animalID = rs.getInt("AnimalID");
                String animalNickname = rs.getString("AnimalNickname");
                String animalSpecies = rs.getString("AnimalSpecies");

                // Assuming all animals are not orphans and don't need treatment for simplicity
                Animal animal = new Animal(animalID, animalNickname, animalSpecies);
                this.animals.add(animal);
            }
        }

        catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void retrieveTreatments(Statement statement){
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM EWR.ANIMALS;");
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

        catch(SQLException e){
            e.printStackTrace();
        }

    }

    public ArrayList<Animal> getAnimals(){
        return this.animals;
    }

}