//TESTING SHIFTING TASKS!!!!!!!!!!

package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*; 
import java.time.LocalDate;
import java.io.*;   
    
public class ScheduleTooAgain {
    private int[] availableTimes = new int[24];
    private boolean[] volunteerNeeded = new boolean[24];
    private ArrayList<Animal> animals = new ArrayList<Animal>();
    private ArrayList<ScheduleItem>[] schedule = new ArrayList[24]; 
    private ArrayList<ScheduleItem> treatmentItems = new ArrayList<ScheduleItem>();
    private ArrayList<ScheduleItem> taskItems = new ArrayList<ScheduleItem>();
    private int problemHour = -1;
    
    // CONSTRUCTOR - retrieves all required data from database and initializes data members via helper functions to create the schedule.
    public ScheduleTooAgain() throws DatabaseConnectionException {
    
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

            boolean buildCheck = false;

            while(buildCheck == false){
            
                try{
                    assignTreatments();
                    generateTasks();
                    buildCheck = true;
                }
                catch(TimeLimitExceededException e){
                    //System.out.println(e.getMessage());
                    adjustDatabase(this.problemHour);
                    //buildCheck = true;  //Just to see what its doing so far
                    //System.exit(1);
                }
            }

            boolean needed = isVolunteerNeeded();
            if (needed == true){
                confirmVolunteer();
            }
            
    
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

    public void assignTreatments() throws TimeLimitExceededException {
        for(ScheduleItem item : this.treatmentItems){
            int startHour = item.getStartHour();
            int duration = item.getDuration();
            int maxWindow = item.getMaxWindow();
            boolean assignCheck = false;

            int assignHour = startHour;
            for (int n = 0; n < maxWindow; n++){
                if (this.availableTimes[assignHour] >= duration){
                    this.schedule[assignHour].add(item);
                    this.availableTimes[assignHour] = this.availableTimes[assignHour] - duration;
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

            if (assignCheck == false){
                this.problemHour = startHour;
                throw new TimeLimitExceededException("Unable to assign task within required window. Please consult vetrenarian and adjust times in database.");
    
            }
            
        }
    }

    //Tasks done everyday:
    public void generateTasks(){

        Species species[] = Species.values();
        ArrayList<Animal> currentAnimals = new ArrayList<Animal>();
        int numberAnimal = 0;
        for (Species currentSpecies : species){
            //TestPrint:
            //System.out.println("Current Species: " + currentSpecies.toString());
            for (Animal animal: this.animals){
                if (animal.getSpecies() == currentSpecies.toString()){
                    currentAnimals.add(animal);
                    numberAnimal +=1;
                }
            }
            //Test prints:
            System.out.println("Number of "+ currentSpecies.toString() +"s: " + Integer.toString(numberAnimal));
            for (Animal animal: currentAnimals){
                System.out.println("\tAnimal ID: " + animal.getAnimalID());
                System.out.println("\tNickname: " + animal.getNickName());
                System.out.println("\n");
                //System.out.println("Species: " + animal.getSpecies());
            }
            currentAnimals = new ArrayList<Animal>();
            numberAnimal = 0;
        }
        // Coyote tasks
        //taskItems.add(new ScheduleItem(0, 0, 0, 19, 3, 15, "Feed and clean coyote cage")); // Feed at 7 PM
        //taskItems.add(new ScheduleItem(0, 0, 0, 0, 24, 5, "Clean coyote cage"));

        // Porcupine tasks
        //taskItems.add(new ScheduleItem(0, 0, 0, 19, 3, 10, "Feed and clean porcupine cage")); // Feed at 7 PM
        //taskItems.add(new ScheduleItem(0, 0, 0, 0, 24, 10, "Clean porcupine cage"));

        // Fox tasks
        //taskItems.add(new ScheduleItem(0, 0, 0, 0, 3, 10, "Feed and clean fox cage")); // Feed at 12 AM
        //taskItems.add(new ScheduleItem(0, 0, 0, 0, 24, 5, "Clean fox cage"));

        // Raccoon tasks
        //taskItems.add(new ScheduleItem(0, 0, 0, 0, 3, 5, "Feed and clean raccoon cage")); // Feed at 12 AM
        //taskItems.add(new ScheduleItem(0, 0, 0, 0, 24, 5, "Clean raccoon cage"));

        // Beaver tasks
        //taskItems.add(new ScheduleItem(0, 0, 0, 8, 3, 5, "Feed and clean beaver cage")); // Feed at 8 AM
        //taskItems.add(new ScheduleItem(0, 0, 0, 0, 24, 5, "Clean beaver cage"));

        // Print all tasks
        //for (ScheduleItem item : taskItems) {
            //System.out.println("Task ID: " + item.getTaskID() + ", Description: " + item.getDescription());
        //}
    }

    public boolean isVolunteerNeeded(){
        boolean needed = false;
        for (int i = 0; i < 24; i++){
            if (this.volunteerNeeded[i] == true){
                needed = true;
                break;
            }
        }
        
        return needed;
    }

    public void confirmVolunteer(){
        
        System.out.println("\nA back-up volunteer is required for the following times:");
        for (int i = 0; i < 24; i++){
            if (this.volunteerNeeded[i] == true){
                String hour = Integer.toString(i)+":00";
                System.out.println("\n\t"+hour);
            }
        }

        boolean confirm = false;

        while(confirm == false){
            System.out.println("\nPlease contact the back-up volunteer(s), then enter 'Y' to confirm:");
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if(line.equals("Y") || line.equals("y")){
                confirm = true;
            }

            else {
                System.out.println("\nOops! Invalid input.");
            }

        }

        System.out.println("\nBack-up volunteers confirmed.");
    
    }


    //Adjust start time for one treatment at a time to try to make workable schedule
    public void adjustDatabase(int problemHour){
        ArrayList<ScheduleItem> problemTreatments = new ArrayList<ScheduleItem>();
        for (ScheduleItem item : this.getTreatmentItems()){
            if (item.getStartHour() == problemHour){
                problemTreatments.add(item);
            }
        }

        System.out.println("\nToo many tasks need to be scheduled for "+ Integer.toString(problemHour)+":00.");
        System.out.println("\nPlease consult the vetrenarian and change the start time for one of the following treatments:");
        for (ScheduleItem item : problemTreatments){
            String animalName = new String();
            for(Animal animal : this.animals){
                if (item.getAnimalID() == animal.getAnimalID()){
                    animalName = " (" + animal.getNickName() + ")";
                }
            }
            System.out.println("\tTreatment ID: " + Integer.toString(item.getTreatmentID())+"\tDescription: " + item.getDescription()+ animalName);
        }
        System.out.println("\nEnter the Treatment ID of the treatment to adjust:");

        int fixID = -1;
        boolean validInput = false;
        while(validInput == false){
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            for (ScheduleItem item : problemTreatments){
                String ID = new String(Integer.toString(item.getTreatmentID()));
                if (ID.equals(line)){
                    validInput = true;
                    fixID = item.getTreatmentID();
                }
            }
            if (validInput == false){
                System.out.println("Invalid ID entered. Please re-enter.");
            }
        }

        System.out.println("Treatment ID entered: " + Integer.toString(fixID));

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "user1", "ensf");
            String update = new String("UPDATE TREATMENTS SET StartHour = ? WHERE TreatmentID = ?;");
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            int newHour = -1;
            boolean validHour = false;
            while(validHour == false){
                System.out.println("\nPlease enter the new start hour as an integer between 0 and 23:");
                Scanner input = new Scanner(System.in);
                String line = input.nextLine();
                try{
                    newHour = Integer.parseInt(line);
                }
                catch(Exception e){
                    System.out.println("Invalid input. Please enter the time as an integer number.");
                }

                if(newHour >= 0 && newHour < 24){
                    validHour = true;
                } 
            }
            
            preparedStatement.setInt(1, newHour);
            preparedStatement.setInt(2, fixID);

            preparedStatement.executeUpdate();
            preparedStatement.close(); 

            Arrays.fill(this.availableTimes, 60);
            this.schedule = new ArrayList[24];
            for (int i = 0; i < 24; i++){
                this.schedule[i] = new ArrayList<ScheduleItem>();
            } 
            this.treatmentItems = new ArrayList<ScheduleItem>();
            this.availableTimes = new int[24];
            this.volunteerNeeded = new boolean[24]; 
            Statement statement = connection.createStatement();
            retrieveTreatments(statement);

            connection.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            //throw new DatabaseConnectionException("Database Error");
            //e.printStackTrace();
        }

        problemHour = -1;
        System.out.println("\n***STILL UNDER CONSTRUCTION***");
    }

    
    
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
    public String formatSchedule(){
        String formattedSchedule = new String();
        LocalDate date = LocalDate.now();

        date = date.plusDays(1);
        formattedSchedule += "Schedule for " + date.toString() + "\n\n";
        for (int i = 0; i < 24; i++){
            if (schedule[i].isEmpty() != true){
                formattedSchedule += Integer.toString(i)+":00";
                if (this.volunteerNeeded[i] == true ){
                    formattedSchedule += " [+ Back-Up Volunteer]";
                }
                formattedSchedule += "\n";
                for (ScheduleItem item : this.schedule[i]){
                    formattedSchedule += "\t* " + item.getDescription();
                    for(Animal animal : this.animals){
                        if (item.getAnimalID() == animal.getAnimalID()){
                            formattedSchedule += " (" + animal.getNickName() + ")\n";
                        }
                    }
                }
                formattedSchedule += "\n";
            }
        }

        this.saveToFile(formattedSchedule, date);
        return formattedSchedule;
    }
    

public void saveToFile(String formattedSchedule, LocalDate date){
    String fileName = "Schedule_" + date.toString() + ".txt";
        try {
            FileWriter output = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(output);
            out.write(formattedSchedule);
            out.close();
            output.close();

        }
        catch(IOException e){
            System.out.println("Could not save to text file.");
        }
    }
}
