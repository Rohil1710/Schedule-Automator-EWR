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

public enum Type{
	NOCTURNAL,
	DIURNAL,
	CREPUSCULAR;
	
	/**
	 * Returns the feeding start time for an animal based on its activity pattern.
	 *
	 * @return an integer representing the feeding start time for the animal
	 *
	 * @throws IllegalArgumentException if the animal's activity pattern is not recognized
	 *
	 * @since 1.0
	 */
	public int getFeedingStartTime(){
		switch(this){
			case NOCTURNAL:
				int timesNocturnal = 0;
				return timesNocturnal;	
			case DIURNAL:
				int timesDiurnal = 8;
				return timesDiurnal;
			case CREPUSCULAR:
				int timesCrepuscular = 19;
				return timesCrepuscular;
			default:
				int times = -1;
				return times;
				
		}
	}
	
}