/**
@author Tanis Smith <a href="mailto:tanis.smith@ucalgary.ca">
tanis.smith@ucalgary.ca</a>
@version 1.2
@since 1.0
*/

package edu.ucalgary.oop;

public enum Type{
	NOCTURNAL,
	DIURNAL,
	CREPUSCULAR;
	
	//Times given in 24-hour format.
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
				//Maybe throw an error instead?
		}
	}
	
}