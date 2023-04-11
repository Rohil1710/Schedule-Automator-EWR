/**
@author Tanis Smith <a href="mailto:tanis.smith@ucalgary.ca">
tanis.smith@ucalgary.ca</a>
@author Rohil Dhillon <a href="mailto:Rohil.Dhillon@ucalgary.ca">
Rohil.Dhillon@ucalgary.ca</a>
@author Mehrnaz Zafari <a href="mailto:mehrnaz.zafari@ucalgary.ca">
mehrnaz.zafari@ucalgary.ca</a>
@author Abhyudai Singh <a href="mailto:abhyudai.singh@ucalgary.ca">
abhyudai.singh@ucalgary.ca</a>
@version 1.2
@since 1.0
*/

package edu.ucalgary.oop;

/**
 * 
 * The Species enumeration represents the different species of animals
 * 
 * It also provides methods to get the feeding duration, food preparation
 * duration, cage cleaning duration, and type for each species.
 */
public enum Species{
	COYOTE,
	FOX,
	PORCUPINE,
	BEAVER,
	RACCOON;

	/**
	 * 
	 * Returns the number of minutes for feeding duration for the corresponding
	 * species.
	 * 
	 * @return an integer representing the feeding duration in minutes
	 */
	public int getFeedingDuration(){
		switch(this){
			case COYOTE:
				return 5;	
			case FOX:
				return 5;
			case PORCUPINE:
				return 5;
			case BEAVER:
				return 5;
			case RACCOON:
				return 5;
			default:
				return 0;
		}
	}
	
	/**
	 * 
	 * Returns the number of minutes for food preparation duration for the
	 * corresponding species.
	 * 
	 * @return an integer representing the food preparation duration in minutes
	 */
	public int getFoodPrepDuration(){
		switch(this){
			case COYOTE:
				return 10;	
			case FOX:
				return 5;
			case PORCUPINE:
				return 0;
			case BEAVER:
				return 0;
			case RACCOON:
				return 0;
			default:
				return 0;
		}
	}
	
	
	/**
	 * 
	 * Returns the number of minutes for cage cleaning duration for the
	 * corresponding species.
	 * 
	 * @return an integer representing the cage cleaning duration in minutes
	 */
	public int getCageCleanDuration(){
		switch(this){
			case COYOTE:
				return 5;	
			case FOX:
				return 5;
			case PORCUPINE:
				return 10;
			case BEAVER:
				return 5;
			case RACCOON:
				return 5;
			default:
				return 0;
		}
	}

	/**
	 * 
	 * Returns the type for the corresponding species.
	 * 
	 * @return a string representing the type
	 */
	public String getType(){
		switch(this){
			case COYOTE:
				return "CREPUSCULAR";	
			case FOX:
				return "NOCTURNAL";
			case PORCUPINE:
				return "CREPUSCULAR";
			case BEAVER:
				return "DIURNAL";
			case RACCOON:
				return "NOCTURNAL";
			default:
				return null;
		}

	}
}