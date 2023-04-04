package edu.ucalgary.oop;

import java.util.*;   

public class runSchedule {
    public static void main(String[] args) {
        try{
            Schedule schedule = new Schedule();

            System.out.println("\nTEST ANIMALS:");
            for (Animal animal : schedule.getAnimals()) {
                System.out.println("Animal ID: " + animal.getAnimalID());
                System.out.println("Nickname: " + animal.getNickName());
                System.out.println("Species: " + animal.getSpecies());
                System.out.println("Type: " + animal.getType());
                //System.out.println("Orphan: " + animal.getOrphan());
                // System.out.println("Need Treatment: " + animal.getNeedTreatment());
                System.out.println();
            }

            System.out.println("\nTEST TREATMENTS:");
            for (ScheduleItem item : schedule.getTreatmentItems()) {
                System.out.println("Treatment ID: " + item.getTreatmentID() + ", Animal ID: " + item.getAnimalID()
                        + ", Task ID: " + item.getTaskID() + ", Start Hour: " + item.getStartHour() + ", Max Window: "
                        + item.getMaxWindow() + ", Duration: " + item.getDuration() + ", Description: "
                        + item.getDescription());
            }

            System.out.println("\nTEST AVAILABLE TIMES:");
            System.out.println(Arrays.toString(schedule.getAvailableTimes()));

            System.out.println("\nTEST VOLUNTEER NEEDED:");
            System.out.println(Arrays.toString(schedule.getVolunteerNeeded()));

            System.out.println("\nTEST TREATMENTS PER HOUR:");

            ArrayList<ScheduleItem>[] currentSchedule = new ArrayList[24]; 
            for (int i = 0; i < 24; i++){
                currentSchedule[i] = new ArrayList<ScheduleItem>();
                currentSchedule[i] = schedule.getHourSchedule(i);
                System.out.println("Time: "+ i);
                for (ScheduleItem item : currentSchedule[i]) {
                    System.out.println("Treatment ID: " + item.getTreatmentID() + "\nAnimal ID: " + item.getAnimalID()
                            + "\nTask ID: " + item.getTaskID() + "\nStart Hour: " + item.getStartHour() + "\nMax Window: "
                            + item.getMaxWindow() + "\nDuration: " + item.getDuration() + "\nDescription: "
                            + item.getDescription());
                }
                
            }
            
        }

        catch(DatabaseConnectionException e){
            e.printStackTrace();
        }

    }
}
