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
				int[] times = {0, 1, 2};
				return times;	
			case DIURNAL:
				int[] times = {8, 9, 10};
				return times;
			case CREPUSCULAR:
				int[] times = {19, 20, 21};
				return times;
			default:
				int[] times = {0}];
				return times;
				//Maybe throw an error instead?
		}
	}
	
}