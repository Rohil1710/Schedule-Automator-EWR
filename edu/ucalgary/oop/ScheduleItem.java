/**
@author Tanis Smith <a href="mailto:tanis.smith@ucalgary.ca">
tanis.smith@ucalgary.ca</a>
@version 1.1
@since 1.0
*/

package edu.ucalgary.oop;

public class ScheduleItem{

	private final int treatmentID;
	private final int animalID;
	private final int taskID;
	private final int startHour;
	private final int maxWindow;
	private final int duration;
	private final String description;

	//Constructor
	public ScheduleItem(int treatmentID, int animalID, int taskID, int startHour, int maxWindow, int duration, String description){
		this.treatmentID = treatmentID;
		this.animalID = animalID;
		this.taskID = taskID;
		this.startHour = startHour;
		this.maxWindow = maxWindow;
		this.duration = duration;
		this.description = description;
	}
	
	//Getters:
	public int getTreatmentID(){
		return this.treatmentID;
	}
	
	public int getAnimalID(){
		return this.animalID;
	}
	
	public int getTaskID(){
		return this.taskID;
	}
	
	public int getStartHour(){
		return this.startHour;
	}
	
	public int getMaxWindow(){
		return this.maxWindow;
	}
	
	public int getDuration(){
		return this.duration;
	}
	
	public String getDescription(){
		return this.description;
	}
	
}