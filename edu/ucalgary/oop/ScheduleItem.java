/**
@author Tanis Smith <a href="mailto:tanis.smith@ucalgary.ca">
tanis.smith@ucalgary.ca</a>
@author Rohil Dhillon <a href="mailto:Rohil.Dhillon@ucalgary.ca">
Rohil.Dhillon@ucalgary.ca</a>
@author Mehrnaz Zafari <a href="mailto:mehrnaz.zafari@ucalgary.ca">
mehrnaz.zafari@ucalgary.ca</a>
@author Abhyudai Singh <a href="mailto:abhyudai.singh@ucalgary.ca">
abhyudai.singh@ucalgary.ca</a>
@version 1.1
@since 1.0
*/

package edu.ucalgary.oop;

public class ScheduleItem{

	/**
	 * 
	 * This class represents a single item on a schedule. It contains information
	 * about a specific task to be completed
	 * at a particular time, such as a medical treatment for a specific animal.
	 */
	private final int treatmentID;
	private final int animalID;
	private final int taskID;
	private final int startHour;
	private final int maxWindow;
	private final int duration;
	private final String description;

	/**
	 * Constructs a new ScheduleItem object with the specified parameters.
	 *
	 * @param treatmentID the ID of the treatment associated with this item
	 * @param animalID    the ID of the animal associated with this item
	 * @param taskID      the ID of the task associated with this item
	 * @param startHour   the hour at which the task is scheduled to start
	 * @param maxWindow   the maximum number of hours after the start time during
	 *                    which the task can be completed
	 * @param duration    the duration of the task in hours
	 * @param description a brief description of the task
	 */
	public ScheduleItem(int treatmentID, int animalID, int taskID, int startHour, int maxWindow, int duration, String description){
		this.treatmentID = treatmentID;
		this.animalID = animalID;
		this.taskID = taskID;
		this.startHour = startHour;
		this.maxWindow = maxWindow;
		this.duration = duration;
		this.description = description;
	}
	
	/**
	 * Returns the ID of the treatment associated with this schedule item.
	 *
	 * @return the ID of the treatment
	 */
	public int getTreatmentID(){
		return this.treatmentID;
	}
	
	/**
	 * Returns the ID of the animal associated with this schedule item.
	 *
	 * @return the ID of the animal
	 */
	public int getAnimalID(){
		return this.animalID;
	}
	
	/**
	 * Returns the ID of the task associated with this schedule item.
	 *
	 * @return the ID of the task
	 */
	public int getTaskID(){
		return this.taskID;
	}
	
	/**
	 * Returns the hour at which the task is scheduled to start.
	 *
	 * @return the start hour
	 */
	public int getStartHour(){
		return this.startHour;
	}
	
	/**
	 * Returns the maximum number of hours after the start time during which the
	 * task can be completed.
	 *
	 * @return the maximum window
	 */
	public int getMaxWindow(){
		return this.maxWindow;
	}
	
	/**
	 * Returns the duration of the task in hours.
	 *
	 * @return the duration
	 */
	public int getDuration(){
		return this.duration;
	}
	
	/**
	 * Returns a brief description of the task.
	 *
	 * @return the description
	 */
	public String getDescription(){
		return this.description;
	}
	
}