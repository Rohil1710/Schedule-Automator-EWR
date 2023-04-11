package edu.ucalgary.oop;

import java.util.*;

public class Animal {
    private final String species;
    private final String type;
    private final String nickName;
    private final int animalID;

    public Animal(int id, String animalNickName, String animalSpecies) {
        this.animalID = id;
        this.nickName = animalNickName;
        this.species = animalSpecies.toUpperCase();
    
        Species speciess;

        try {
            speciess = Species.valueOf(this.species);

            try {
                this.type = speciess.getType();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Error: Invalid Species found.");
            }
        } catch (

        IllegalArgumentException e) {
            throw new IllegalArgumentException("Error: Invalid Type found.");
        }

    }

    public String getSpecies() {
        return this.species;
    }

    public String getType() {
        return this.type;
    }

    public String getNickName() {
        return this.nickName;
    }

    public int getAnimalID() {
        return this.animalID;
    }

}
