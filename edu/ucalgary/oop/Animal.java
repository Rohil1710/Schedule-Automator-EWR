/**
@author Tanis Smith <a href="mailto:tanis.smith@ucalgary.ca">
tanis.smith@ucalgary.ca</a>
@author Rohil Dhillon <a href="mailto:Rohil.Dhillon@ucalgary.ca">
Rohil.Dhillon@ucalgary.ca</a>
@author Mehrnaz Zafari <a href="mailto:mehrnaz.zafari@ucalgary.ca">
mehrnaz.zafari@ucalgary.ca</a>
@author Abhyudai Singh <a href="mailto:abhyudai.singh@ucalgary.ca">
abhyudai.singh@ucalgary.ca</a>
@version 1.4
@since 1.0
*/
package edu.ucalgary.oop;

import java.util.*;

/**
 * Represents an animal with various attributes such as species, type, feeding
 * times, nickname, and animal ID.
 */
public class Animal {
    private final String species;
    private final String type;
    private final String nickName;
    private final int animalID;

    /**
     * Constructs an Animal object with the specified animal ID, nickname, and
     * species.
     * The species parameter is used to determine the animal type.
     *
     * @param id             the unique ID of the animal
     * @param animalNickName the nickname of the animal
     * @param animalSpecies  the species of the animal
     * @throws IllegalArgumentException if an invalid species or type is provided
     */
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

    /**
     * Returns the species of the animal.
     *
     * @return the species of the animal
     */
    public String getSpecies() {
        return this.species;
    }

    /**
     * Returns the type of the animal.
     *
     * @return the type of the animal
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the nickname of the animal.
     *
     * @return the nickname of the animal
     */
    public String getNickName() {
        return this.nickName;
    }

    /**
     * Returns the unique animal ID.
     *
     * @return the unique animal ID
     */
    public int getAnimalID() {
        return this.animalID;
    }

}
