/**
@author Tanis Smith <a href="mailto:tanis.smith@ucalgary.ca">
tanis.smith@ucalgary.ca</a>
@version 1.1
@since 1.0
*/

package edu.ucalgary.oop;

public enum Species{
	COYOTE,
	FOX,
	PORCUPINE,
	BEAVER,
	RACCOON;
	
	//Returns number of minutes for feeding duration for corresponding species.
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
	
	//Returns number of minutes for food preparation duration for corresponding species.
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
	
	
	//Returns number of minutes for cage cleaning duration for corresponding species.
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