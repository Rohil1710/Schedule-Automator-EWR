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
    private int[] availableTimes = new int[24];
    private boolean[] volunteerNeeded = new boolean[24];
    private ArrayList<Animal> animals = new ArrayList<Animal>();
    private ArrayList<ScheduleItem>[] schedule = new ArrayList[24]; 
    private ArrayList<ScheduleItem> treatmentItems = new ArrayList<ScheduleItem>();
    private ArrayList<ScheduleItem> taskItems = new ArrayList<ScheduleItem>();
    
    // CONSTRUCTOR - retrieves all required data from database and initializes data members via helper functions to create the schedule.
    public Schedule() throws DatabaseConnectionException {
    
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "user1", "ensf");
            Statement statement = connection.createStatement();
            retrieveAnimals(statement);
            retrieveTreatments(statement);
            connection.close();

            Arrays.fill(this.availableTimes, 60);
            for (int i = 0; i < 24; i++){
                this.schedule[i] = new ArrayList<ScheduleItem>();
            }
            assignTreatments();
            //generateTasks();
            
    
        }
        
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new DatabaseConnectionException("Database Error");
            //e.printStackTrace();
        }
    
    }
    
    
    public void retrieveAnimals(Statement statement) {
        try{
                
            ResultSet rs = statement.executeQuery("SELECT * FROM EWR.ANIMALS;");
            while (rs.next()) {
                int animalID = rs.getInt("AnimalID");
                String animalNickname = rs.getString("AnimalNickname");
                String animalSpecies = rs.getString("AnimalSpecies");
    
                Animal animal = new Animal(animalID, animalNickname, animalSpecies);
                this.animals.add(animal);
            }
        }
    
        catch(SQLException e){
            e.printStackTrace();
        }
    
    }

    public void retrieveTreatments(Statement statement) {
        try{
            
            ResultSet resultSet = statement.executeQuery("SELECT TREATMENTS.TreatmentID, TREATMENTS.AnimalID, TREATMENTS.TaskID, TREATMENTS.StartHour, TASKS.MaxWindow, TASKS.Duration, TASKS.Description FROM TREATMENTS INNER JOIN TASKS ON TREATMENTS.TaskID = TASKS.TaskID;");
            while (resultSet.next()) {
                int treatmentID = resultSet.getInt("TreatmentID");
                int animalID = resultSet.getInt("AnimalID");
                int taskID = resultSet.getInt("TaskID");
                int startHour = resultSet.getInt("StartHour");
                int maxWindow = resultSet.getInt("MaxWindow");
                int duration = resultSet.getInt("Duration");
                String description = resultSet.getString("Description");

                ScheduleItem item = new ScheduleItem(treatmentID, animalID, taskID, startHour, maxWindow, duration,
                        description);
                this.treatmentItems.add(item);
            }
        }
    
        catch(SQLException e){
            e.printStackTrace();
        }
    
    }

    public void assignTreatments(){
        //System.out.println("Still working on it...");
        for(ScheduleItem item : this.treatmentItems){
            int startHour = item.getStartHour();
            int duration = item.getDuration();
            int maxWindow = item.getMaxWindow();
            boolean assignCheck = false;

            int assignHour = startHour;
            for (int n = 0; n < maxWindow; n++){
                if (this.availableTimes[assignHour] > duration){
                    this.schedule[assignHour].add(item);
                    this.availableTimes[assignHour] = this.availableTimes[startHour] - duration;
                    assignCheck = true;
                    break;
                }
                assignHour++;
            }

            assignHour = startHour;
            if (assignCheck == false){
                for(int n = 0; n < maxWindow; n++){
                    if (this.volunteerNeeded[assignHour] == false){
                        this.availableTimes[assignHour] += 60;
                        this.schedule[assignHour].add(item);
                        this.availableTimes[assignHour] = this.availableTimes[assignHour] - duration;
                        this.volunteerNeeded[assignHour] = true;
                        assignCheck = true;
                        break;
                    }
                    assignHour++;

                }
            }
            
        }
    }

    //public generateTasks(){
        
    //}

    
    
    
    // GETTERS:
    
    public ArrayList<Animal> getAnimals(){
        return this.animals;
    }
    
    public ArrayList<ScheduleItem> getTreatmentItems(){
        return this.treatmentItems;
    }
    
    public ArrayList<ScheduleItem> getTaskItems(){
        return this.taskItems;
    }
    
    public int[] getAvailableTimes(){
        return this.availableTimes;
    }
        
    public boolean[] getVolunteerNeeded(){
        return this.volunteerNeeded;
    }

    public ArrayList<ScheduleItem> getHourSchedule(int hour){
        return this.schedule[hour];
    }
    
// Formats the schedule into a single string, creates a .txt file and outputs string version of schedule to terminal.
    //public void formatSchedule(){}
    
}
