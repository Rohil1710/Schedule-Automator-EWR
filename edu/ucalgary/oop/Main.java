package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "user1", "ensf");
            Statement statement = connection.createStatement();

            // Query the database and create ScheduleItem objects
            String query = "SELECT TREATMENTS.TreatmentID, TREATMENTS.AnimalID, TREATMENTS.TaskID, TREATMENTS.StartHour, TASKS.MaxWindow, TASKS.Duration, TASKS.Description FROM TREATMENTS INNER JOIN TASKS ON TREATMENTS.TaskID = TASKS.TaskID";
            ResultSet resultSet = statement.executeQuery(query);

            List<ScheduleItem> scheduleItems = new ArrayList<>();

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
                scheduleItems.add(item);
            }

            // Print the schedule items
            for (ScheduleItem item : scheduleItems) {
                System.out.println("Treatment ID: " + item.getTreatmentID() + ", Animal ID: " + item.getAnimalID()
                        + ", Task ID: " + item.getTaskID() + ", Start Hour: " + item.getStartHour() + ", Max Window: "
                        + item.getMaxWindow() + ", Duration: " + item.getDuration() + ", Description: "
                        + item.getDescription());
            }

            // Query ANIMALS table and create Animal objects
            ArrayList<Animal> animals = new ArrayList<>();
            ResultSet rs = statement.executeQuery("SELECT * FROM EWR.ANIMALS;");
            while (rs.next()) {
                int animalID = rs.getInt("AnimalID");
                String animalNickname = rs.getString("AnimalNickname");
                String animalSpecies = rs.getString("AnimalSpecies");

                // Assuming all animals are not orphans and don't need treatment for simplicity
                Animal animal = new Animal(animalID, animalSpecies, animalNickname);
                animals.add(animal);
            }

            // Print Animal objects
            for (Animal animal : animals) {
                System.out.println("Animal ID: " + animal.getAnimalID());
                System.out.println("Nickname: " + animal.getNickName());
                System.out.println("Species: " + animal.getSpecies());
                System.out.println("Type: " + animal.getType());
                // System.out.println("Orphan: " + animal.getOrphan());
                // System.out.println("Need Treatment: " + animal.getNeedTreatment());
                System.out.println();
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
