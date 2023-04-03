package edu.ucalgary.oop;

public class runScheduleToo {
    public static void main(String[] args) {
        try{
            ScheduleToo schedule = new ScheduleToo();
            
            for (Animal animal : schedule.getAnimals()) {
                System.out.println("Animal ID: " + animal.getAnimalID());
                System.out.println("Nickname: " + animal.getNickName());
                System.out.println("Species: " + animal.getSpecies());
                System.out.println("Type: " + animal.getType());
                // System.out.println("Orphan: " + animal.getOrphan());
                // System.out.println("Need Treatment: " + animal.getNeedTreatment());
                System.out.println();
            }
        }

        catch(DatabaseConnectionException e){
            e.printStackTrace();
        }

    }
}