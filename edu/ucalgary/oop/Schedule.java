package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
}
