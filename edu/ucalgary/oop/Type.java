/**
@author Tanis Smith <a href="mailto:tanis.smith@ucalgary.ca">
tanis.smith@ucalgary.ca</a>
@version 1.1
@since 1.0
*/

package edu.ucalgary.oop;

public enum Type{
	NOCTURNAL,
	DIURNAL,
	CREPUSCULAR;
	
	//Times given in 24-hour format.
	public int[] getFeedingTimes(){
		switch(this){
			case NOCTURNAL:
				int[] timesNocturnal = {0, 1, 2};
				return timesNocturnal;	
			case DIURNAL:
				int[] timesDiurnal = {8, 9, 10};
				return timesDiurnal;
			case CREPUSCULAR:
				int[] timesCrepuscular = {19, 20, 21};
				return timesCrepuscular;
			default:
				int[] times = {0};
				return times;
				//Maybe throw an error instead?
		}
	}
	
}