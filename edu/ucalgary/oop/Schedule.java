/**
@author Tanis Smith <a href="mailto:tanis.smith@ucalgary.ca">
tanis.smith@ucalgary.ca</a>
@author Rohil Dhillon <a href="mailto:Rohil.Dhillon@ucalgary.ca">
Rohil.Dhillon@ucalgary.ca</a>
@author Mehrnaz Zafari <a href="mailto:mehrnaz.zafari@ucalgary.ca">
mehrnaz.zafari@ucalgary.ca</a>
@author Abhyudai Singh <a href="mailto:abhyudai.singh@ucalgary.ca">
abhyudai.singh@ucalgary.ca</a>
@version 3.5
@since 1.0
*/

package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;

import edu.ucalgary.oop.Animal;

import java.time.LocalDate;
import java.io.*;   
import java.lang.Math.*;
    
public class Schedule {
    private int[] availableTimes = new int[24];
    private boolean[] volunteerNeeded = new boolean[24];
    private ArrayList<Animal> animals = new ArrayList<Animal>();
    private ArrayList<ScheduleItem>[] schedule = new ArrayList[24]; 
    private ArrayList<ScheduleItem> treatmentItems = new ArrayList<ScheduleItem>();
    private ArrayList<Integer> orphanIDs= new ArrayList<Integer>();
    private int problemHour = -1;
    private int timeUsed = 0;
    private boolean buildCheck = false;
    private String url = "jdbc:mysql://localhost/EWR";
    
    /**
     * This constructor initializes the schedule by filling the availableTimes and
     * schedule data members with default values.
     */
    public Schedule() {
        Arrays.fill(this.availableTimes, 60);
            
        for (int i = 0; i < 24; i++){
            this.schedule[i] = new ArrayList<ScheduleItem>();
        }
    
    }
    
    /**
     * This method generates the schedule by calling helper methods to assign
     * treatments, generate feeding tasks, and generate cleaning tasks. It uses a
     * while loop to ensure that if a TimeLimitExceededException is thrown, it will
     * adjust the database and try again until a successful build occurs.
     */
    public void buildSchedule() {

        while(buildCheck == false){
            
            try{
                assignTreatments();
                generateFeedingTasks();
                generateCleaningTasks();
                buildCheck = true;
            }
            catch(TimeLimitExceededException e){
                adjustDatabase(this.problemHour);
            }
        }
    
    }
        
    /**
     * Estimates the total time needed for completing all the scheduled tasks.
     * Adds up the duration of all the medical tasks in the schedule and then adds
     * the duration of cleaning and feeding tasks for each animal species in the
     * schedule.
     * Finally, it updates the total time used by adding up all the durations.
     * 
     * @throws IllegalArgumentException if an invalid animal species type is found
     *                                  in the generate cleaning tasks function
     */
    public void estimateTimeUsed(){
        for (ScheduleItem item: this.treatmentItems){
            this.timeUsed += item.getDuration();
        }

        Species species[] = Species.values();
        ArrayList<Animal> currentAnimals = new ArrayList<Animal>();
        int numberAnimal = 0;
        for (Species currentSpecies : species){
            String type = currentSpecies.getType();
            Type typeEnum;
			try{
				typeEnum = Type.valueOf(type); 			
			}
			catch(IllegalArgumentException e){
				throw new IllegalArgumentException("Error: Invalid type found in generate cleaning tasks.");
			}			

            for (Animal animal: this.animals){
                if (animal.getSpecies().equals(currentSpecies.toString())){
                    currentAnimals.add(animal);
                    numberAnimal +=1;
                }
            }
            
            if(numberAnimal>0){
                int cleanTimeNeeded = currentSpecies.getCageCleanDuration()*numberAnimal;
                int feedTimeNeeded = currentSpecies.getFoodPrepDuration()  + currentSpecies.getFeedingDuration()*numberAnimal;
                this.timeUsed += cleanTimeNeeded + feedTimeNeeded;
            }
            numberAnimal = 0;
        }        

    }
    
    /**

    Retrieves animals from the database and adds them to the list of animals.

    @throws DatabaseConnectionException if there is an error in connecting to the database or executing the query
    */
    public void retrieveAnimals() throws DatabaseConnectionException{
        try{
            Connection connection = DriverManager.getConnection(this.url, "oop", "password");
            Statement statement = connection.createStatement();               
            ResultSet rs = statement.executeQuery("SELECT * FROM ANIMALS;");
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
            throw new DatabaseConnectionException("Database Error in retrieveAnimals.");
        }
    
    }

    /**
    Retrieves treatments from the database and populates the 'treatmentItems' ArrayList of Schedule objects.

    @throws DatabaseConnectionException If there is an error connecting to the database.
    */
    public void retrieveTreatments() throws DatabaseConnectionException{
        try{
            Connection connection = DriverManager.getConnection(this.url, "oop", "password");
            Statement statement = connection.createStatement();             
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
            connection.close();
        }
    
        catch(SQLException e){
            e.printStackTrace();
            throw new DatabaseConnectionException("Database Error in retrieveTreatments.");
        }
    
    }

    /**
     * 
     * Assigns treatments to available times in the schedule.
     * 
     * Throws TimeLimitExceededException if a treatment cannot be assigned within
     * the required window.
     * 
     * @throws TimeLimitExceededException if a treatment cannot be assigned within
     *                                    the required window.
     */
    private void assignTreatments() throws TimeLimitExceededException {
        for(ScheduleItem item : this.treatmentItems){
            int startHour = item.getStartHour();
            int duration = item.getDuration();
            int maxWindow = item.getMaxWindow();
            boolean assignCheck = false;

            int assignHour = startHour;
            for (int n = 0; n < maxWindow; n++){
                if(assignHour < 24){               
                    if (this.availableTimes[assignHour] >= duration){
                        this.schedule[assignHour].add(item);
                        this.availableTimes[assignHour] = this.availableTimes[assignHour] - duration;
                        assignCheck = true;
                        break;
                    }
                    assignHour++;
                }
            }

            assignHour = startHour;
            if (assignCheck == false){
                for(int n = 0; n < maxWindow; n++){
                    if (assignHour < 24){
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

            if(item.getDescription().contains("Kit feeding")){
                this.orphanIDs.add(item.getAnimalID());
            }

            if (assignCheck == false){
                this.problemHour = startHour;
                throw new TimeLimitExceededException("Unable to assign task within required window. Please consult vetrenarian and adjust times in database.");
    
            }
            
        }
    }

   
    /**
     * Generates cleaning tasks for all animal species.
     * Uses the available time slots in the schedule to add cleaning tasks to the
     * schedule.
     */
    private void generateCleaningTasks(){
        Species species[] = Species.values();
        ArrayList<Animal> currentAnimals = new ArrayList<Animal>();
        int numberAnimal = 0;
        for (Species currentSpecies : species){
            String type = currentSpecies.getType();
            Type typeEnum;
			try{
				typeEnum = Type.valueOf(type); 			
			}
			catch(IllegalArgumentException e){
				throw new IllegalArgumentException("Error: Invalid type found in generate cleaning tasks.");
			}			

            for (Animal animal: this.animals){
                if (animal.getSpecies().equals(currentSpecies.toString())){
                    currentAnimals.add(animal);
                    numberAnimal +=1;
                }
            }
            
            int cagesCleaned = 0;

            for (int i = 0; i < 24; i++){
                int time = this.availableTimes[i];
                int possibleNumber = time/currentSpecies.getCageCleanDuration();
                int cagesToClean = Math.min(possibleNumber, numberAnimal - cagesCleaned);

                if(cagesToClean > 0){
                    String description = "Clean "+ Integer.toString(cagesToClean) + " " + currentSpecies.toString().toLowerCase() + " ";
                    if(numberAnimal > 1){
                        description +="cages (";
                    }
                    else{description +="cage (";}

                    for (int j = 0; j < cagesToClean; j++) {
                        Animal animal = currentAnimals.get(cagesCleaned + j);
                        description += animal.getNickName() + ", ";
                    }

                    description = description.substring(0, description.length()-2);
                    description += ")\n";
                
                    ScheduleItem item = new ScheduleItem(0, 0, 0, 0, 0, 0, description);
                    schedule[i].add(item);
                    this.availableTimes[i] -= currentSpecies.getCageCleanDuration()*cagesToClean;
                        
                    cagesCleaned += cagesToClean;
                    if (cagesCleaned >= numberAnimal) {
                        break;
                    }
                    
                }
            }

            currentAnimals = new ArrayList<Animal>();
            numberAnimal = 0;

        }
    }

    /**
     * 
     * Generates feeding tasks for the animals based on their species and available
     * times. Throws an exception if there are
     * 
     * too many animals to feed during a given hour.
     * 
     * @throws TimeLimitExceededException if there are too many animals to feed
     *                                    during a given hour
     */
    private void generateFeedingTasks()  throws TimeLimitExceededException{
        Species species[] = Species.values();
        ArrayList<Animal> currentAnimals = new ArrayList<Animal>();
        int numberAnimal = 0;
        int window = 3;
        int startTime = -1;
        
        for (Species currentSpecies : species) {
            String type = currentSpecies.getType();
            Type typeEnum;
            
            try {
                typeEnum = Type.valueOf(type);
                startTime = typeEnum.getFeedingStartTime();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Error: Invalid type found in generate feeding tasks.");
            }
    
            for (Animal animal : this.animals) {
                if (animal.getSpecies().equals(currentSpecies.toString()) && this.orphanIDs.contains(animal.getAnimalID()) == false) {
                    currentAnimals.add(animal);
                    numberAnimal += 1;
                }
            }
    
            int animalsFed = 0;
            for (int i = startTime; i < startTime + window; i++) {
                if(i < 24){
                    int Time = this.availableTimes[i];
                    int workingTime = Time - currentSpecies.getFoodPrepDuration();
                    int possibleNumber = workingTime / currentSpecies.getFeedingDuration();
                    this.problemHour = i;
        
                    int animalsToFeed = Math.min(possibleNumber, numberAnimal - animalsFed);
                    if (animalsToFeed > 0) {
                        String description = "Feed " + Integer.toString(animalsToFeed) + " " + currentSpecies.toString().toLowerCase();
                        if (currentSpecies.toString().equals("FOX") && animalsToFeed > 1) {
                            description += "es (";
                        } else if (animalsToFeed > 1) {
                            description += "s (";
                        } else {
                            description += " (";
                        }
        
                        for (int j = 0; j < animalsToFeed; j++) {
                            Animal animal = currentAnimals.get(animalsFed + j);
                            description += animal.getNickName() + ", ";
                        }
                        description = description.substring(0, description.length() - 2);
                        description += ")\n";
                        ScheduleItem item = new ScheduleItem(0, 0, 0, 0, 0, 0, description);
                        schedule[i].add(item);
                        this.availableTimes[i] -= currentSpecies.getFoodPrepDuration() + animalsToFeed*currentSpecies.getFeedingDuration();

                        animalsFed += animalsToFeed;
                        if (animalsFed >= numberAnimal) {
                            break;
                        }
                    }
                }
            }
            if(animalsFed < numberAnimal){
                throw new TimeLimitExceededException("Too many animals to feed at this hour.");
            }

            this.problemHour = -1;
            currentAnimals = new ArrayList<Animal>();
            numberAnimal = 0;
            startTime = -1;
        }
    }
    

    /**
     * 
     * Adjusts the schedule if there is not enough time in one day to care for all
     * animals, even with a backup volunteer.
     * 
     * Allows the user to change the start time for a treatment that is scheduled
     * during a problematic hour.
     * 
     * @param problemHour the hour where too many tasks need to be scheduled
     */
    private void adjustDatabase(int problemHour){
        if (this.timeUsed > 48*60){
            System.out.println("There is not enough time in one day to care for this number of animals (medical and regular tasks), even with a backup volunteer.");
            System.out.println("Please contact other rescues and arrange to transfer some animals for care elsewhere.");
            System.out.println("Adjust the database before re-trying schedule generation.");
            System.exit(1);
        }

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
            Connection connection = DriverManager.getConnection(this.url, "oop", "password");
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
            this.timeUsed = 0;
            connection.close();
            
            retrieveTreatments();

            
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        problemHour = -1;
        
    }

    
    
    /**
     * Sets the list of animals to be managed
     *
     * @param givenAnimals The list of animals to set.
     */
    public void setAnimals(ArrayList<Animal> givenAnimals){
        this.animals = new ArrayList<Animal>();
        for(Animal animal : givenAnimals){
            this.animals.add(animal);
        }
    }

    /**
     * Sets the list of treatment items to be scheduled
     *
     * @param givenTreatments The list of treatment items to set.
     */
    public void setTreatmentItems(ArrayList<ScheduleItem> givenTreatments){
        this.treatmentItems = new ArrayList<ScheduleItem>();
        for(ScheduleItem item : givenTreatments){
            this.treatmentItems.add(item);
        }
    }
    


   /**
   * Method to return ArrayList of animals stored in the Schedule object.
   * @return this.animals
   */
    public ArrayList<Animal> getAnimals(){
        return this.animals;
    }
    
    /**
   * Method to return ArrayList of scheduleItems (medical tasks) stored in the Schedule object.
   * @return this.treatmentItems
   */
    public ArrayList<ScheduleItem> getTreatmentItems(){
        return this.treatmentItems;
    }
    
    /**
    * Method to return Array of available time in minutes for which hour in the day, stored in the Schedule object.
    * @return this.availableTimes
    */
    public int[] getAvailableTimes(){
        return this.availableTimes;
    }
     
    /**
    * Method to return Array of boolean values to indicate whether or not a volunteer is needed per hour, stored in the Schedule object.
    * @return this.volunteerNeeded
    */
    public boolean[] getVolunteerNeeded(){
        return this.volunteerNeeded;
    }

    /**
    * Method to return ArrayList of ScheduleItems (tasks) for the indicated hour, stored in the Schedule object.
    * @param hour the hour to retreive tasks from as an integer value
    * @return this.availableTimes
    */
    public ArrayList<ScheduleItem> getHourSchedule(int hour){
        return this.schedule[hour];
    }

    /**
    Returns an ArrayList of integers representing the IDs of orphans associated with this object.
    @return the ArrayList of orphan IDs
    */
    public ArrayList<Integer> getOrphanIDs(){
        return this.orphanIDs;
    }

    /**
    Returns the hour of the day (in 24-hour format) when the problem associated with this object was reported.
    @return the problem hour
    */
    public int getProblemHour(){
        return this.problemHour;
    }

    /**
    Returns the amount of time (in seconds) that was spent on resolving the problem associated with this object.
    @return the time used
    */
    public int getTimeUsed(){
        return this.timeUsed;
    }

    /**
    Returns a boolean value indicating whether the build associated with this object has passed all checks.
    @return the build check result
    */
    public boolean getBuildCheck(){
        return this.buildCheck;
    }

    /**
    Returns the URL associated with this object.
    @return the URL
    */
    public String getUrl(){
        return this.url;
    }

    
    /**
    * Method to save schedule as a .txt file, name will have format: 'Schedule_YYYY-MM-DD'.
    * @param formattedSchedule the formatted string version of the Schedule item to format
    * @param date the date for the Schedule (date following day of generation)
    * @return formattedSchedule
    */
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

        //Call to saveToFile to save .txt file of generated schedule.
        this.saveToFile(formattedSchedule, date);
        return formattedSchedule;
    }

    
    /**
     * Method to save schedule as a .txt file, name will have format:
     * 'Schedule_YYYY-MM-DD'.
     * 
     * @param formattedSchedule the formatted string version of the Schedule item to
     *                          format
     * @param date              the date for the Schedule (date following day of
     *                          generation)
     * @return formattedSchedule
     */
    private void saveToFile(String formattedSchedule, LocalDate date){
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

