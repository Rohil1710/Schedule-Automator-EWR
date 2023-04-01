import org.junit.*;

import edu.ucalgary.oop.Animal;

import static org.junit.Assert.*;

public class ScheduleTest {
    // Tests for class Animal
    @Test
    public void testGetNickName() {
        Animal animalFox = new Animal(16, "crepuscular", "Spots", "fox", false, false);
        String expectedNickName = "Spots";
        String actualnickName = animalFox.getName();
        assertEquals(expectedNickName, actualnickName);
    }

    @Test
    public void testGetAnimalID() {
        Animal animalBeaver = new Animal(17, "crepuscular", "lulu", "beaver", true, false);
        int expectedAnimalID = 19;
        int actualAnimalID = animalBeaver.getName();
        assertEquals(expectedAnimalID, actualAnimalID);
    }

    @Test
    public void testGetSpecies() {
        Animal animalRaccoon = new Animal(18, "nocturnal", "Bilo", "Raccoon", false, false);
        String expectedSpecies = "Raccoon";
        String actualSpecies = animalRaccoon.getSpecies();
        assertEquals(expectedSpecies, actualSpecies);
    }

    @Test
    public void TestGetOrphan() {
        Animal animalRaccoonBaby = new Animal(19, "nocturnal", "Tutu", "Raccoon", true, false);
        boolean expectedOrphan = true;
        String actualOrphan = animalRaccoonBaby.getOrphan();
        assertEquals(expectedOrphan, actualOrphan);

    }

    @Test
    public void TestGetNeedTreatment() {
        Animal animalCoyote = new Animal(20, "crepuscular", "Lola", "Coyote", false, false);
        boolean expectedNeedTreatment = false;
        boolean actualNeedTreatment = animalCoyote.getNeedTreatment();
        assertEquals(expectedNeedTreatment, actualNeedTreatment);
    }

    @Test
    public void TestGetType() {
        Animal animalRaccoonNew = new Animal(21, "Nocturnal", "Jess", "Raccoon", false, false);
        String expectedAnimalType = "Nocturnal";
        String actualAnimalType = animalRaccoonNew.getType();
        assertEquals(expectedAnimalType, actualAnimalType);
    }

    @Test
    public void TestGetFidingTimes() {
        Animal animalRaccoonEnum = new Animal(22, "nocturnal", "Jelly", "Raccoon", false, false);
        String animalType = animalRaccoonEnum.getType();
        int[] expectedFidingTimes = { 12, 1, 2 };
        Type enumValue = Type.valueOf(animalType);
        int[] actualFidingTimes = enumValue.getFidingTimes();
        assertEquals(expectedFidingTimes, actualFidingTimes);
    }

    @Test
    public void TestGetFeedingDuration() {
        Animal animalCoyoteEnum = new Animal(23, "crepuscular", "Cujo", "Coyote", false, false);
        String animalSpecies = animalCoyoteEnum.getSpecies();
        int expectedFeedingDuration = 5;
        Species enumValue = Species.valueOf(animalSpecies);
        int actualFeedingDuration = enumValue.getFeedingDuration();
        assertEquals(expectedFeedingDuration, actualFeedingDuration);
    }

    @Test
    public void TestGetetFoodPrepDuration() {
        Animal animalCoyoteEnum2 = new Animal(24, "crepuscular", "Gio", "Coyote", false, false);
        String animalSpecies = animalCoyoteEnum2.getSpecies();
        int expectedFoodPrepDuration = 10;
        Species enumValue = Species.valueOf(animalSpecies);
        int actualFoodPrepDuration = enumValue.getetFoodPrepDuration();
        assertEquals(expectedFoodPrepDuration, actualFoodPrepDuration);
    }

    @Test
    public void TestGetCageCleanDuration() {
        Animal animalCoyoteEnum3 = new Animal(25, "crepuscular", "His", "Coyote", false, false);
        String animalSpecies = animalCoyoteEnum3.getSpecies();
        int expectedCageCleanDuration = 5;
        Species enumValue = Species.valueOf(animalSpecies);
        int actualCageCleanDuration = enumValue.getCageCleanDuration();
        assertEquals(expectedCageCleanDuration, actualCageCleanDuration);
    }

    // Basic Tests for Treatment class to be added to main ScheduleTest file

    @Test
    public void testGetTreatmentID() {
        
        Treatment treatment1 = new Treatment(1, 6, 1, 0)
              
        System.out.println("\nTesting getTreatmentID...");
        int expResult = 1;
        int result = treatment1.getTreatmentID();
        assertEquals("treatmentID was incorrect: ", expResult, result);

    }

    @Test
    public void testGetAnimalID() {

        Treatment treatment1 = new Treatment(2, 6, 1, 2);

        System.out.println("\nTesting getAnimalID...");
        int expResult = 6;
        int result = treatment1.getAnimaltID();
        assertEquals("animalID was incorrect: ", expResult, result);

    }

    @Test
    public void testGetTaskID() {

        Treatment treatment1 = new Treatment(3, 6, 1, 4);

        System.out.println("\nTesting getTaskID...");
        int expResult = 1;
        int result = treatment1.getTaskID();
        assertEquals("taskID was incorrect: ", expResult, result);

    }

    @Test
    public void testGetStartHour() {

        Treatment treatment1 = new Treatment(4, 6, 1, 6);

        System.out.println("\nTesting getStartHour...");
        int expResult = 1;
        int result = treatment1.getStartHour();
        assertEquals("startHour was incorrect: ", expResult, result);

    }

    // Basic Tests for Task class to be added to main ScheduleTest file

    @Test
    public void testGetTaskID() {
    
        Task task1 = new Task(1, 30, 2, 'Kit feeding');
          
        System.out.println("\nTesting getTaskID...");
        int expResult = 1;
        int result = task1.getTaskID();
        assertEquals("taskID was incorrect: ", expResult, result);

    }

    @Test
    public void testGetTaskDescription() {

        Task task1 = new Task(3, 10, 3, 'Apply burn ointment back');

        System.out.println("\nTesting getTaskDescription...");
        int expResult = 'Apply burn ointment back';
        int result = task1.getTaskDescription();
        assertEquals("taskDescription was incorrect: ", expResult, result);

    }

    @Test
    public void testGetTaskDuration() {

        Task task1 = new Task(10, 5, 2, 'Inspect broken leg');

        System.out.println("\nTesting getTaskDuration...");
        int expResult = 5;
        int result = task1.getTaskDuration();
        assertEquals("taskDescription was incorrect: ", expResult, result);

    }

    @Test
    public void testGetTaskMaxTime() {

        Task task1 = new Task(9, 25, 1, 'Eyedrops');
    
        System.out.println("\nTesting getTaskMaxTime...");
        int expResult = 25;
        int result = task1.getStartHour();
        assertEquals("taskMaxTime was incorrect: ", expResult, result);

    }

    // Basic test for Schedule Class:
    @Test
    public void testGetScheduleItem() {
        
        Treatment treatment1 = new Treatment(1, 6, 1, 0);
        Treatment treatment2 = new Treatment(2, 6, 1, 2);
        
        Task task1 = new Task(1, 'Kit feeding', 30, 2);
        Task task2 = new Task(2, 'Rebandage leg wound', 20, 1);
    
        ScheduleItem item1 = new ScheduleItem(treatment1);
        ScheduleItem item2 = new ScheduleItem(treatment2);
        ScheduleItem item3 = new ScheduleItem(task1);
        ScheduleItem item4 = new ScheduleItem(task2);

        ScheduleItem[] itemList1 = {item1, item2, item3, item4};
        ScheduleItem[] itemList2 = {item1, item2, item3, item4};
        ScheduleItem[] itemList2 = {item1, item2, item3, item4};

        ScheduleItem[][] tasksPerHour = {itemList1, itemList2, itemList3};

        int[] availableTime = {59, 59, 59};

        Bool[] volunteerNeeded = {false, false, false};

        Schedule schedule = new Schedule();
        schedule.tasksPerHour = tasksPerHour;
        schedule.availableTime = availableTime;
        schedule.volunteerNeeded = volunteerNeeded;

        System.out.println("\nTesting getTask...");
        ScheduleItem expResult = new SchdeuleItem(item3);
        ScheduleItem result = new SchdeuleItem(schedule.getScheduleItem(1, 2));
        assertEquals("getScheduleItem was incorrect: ", expResult, result);

       }

    @Test
    public void testGetTreatmentID() {

        Treatment treatment1 = new Treatment(2, 6, 1, 2);
        Task task1 = new Task(1, 2, 3, "feeding");

        ScheduleItem item1 = new ScheduleItem(treatment1);
        ScheduleItem item2 = new ScheduleItem(task1);
        int expResult = 2;
        int result = item1.getTreatmenttID();
        assertEquals("TreatmentID was incorrect: ", expResult, result);
    }

    @Test
    public void testGetAnimalID() {

        Animak animal1 = new Animal(7, "Spots", "Coyote");
        Task task1 = new Task(1, 2, 3, "feeding");

        ScheduleItem item1 = new ScheduleItem(treatment1);
        ScheduleItem item2 = new ScheduleItem(task1);
        int expResult = 7;
        int result = item1.getAnimaltID();
        assertEquals("animalID was incorrect: ", expResult, result);
    }

    @Test
    public void testGetTaskID() {

        Treatment treatment1 = new Treatment(2, 6, 1, 2);
        Task task1 = new Task(1, 2, 3, "feeding");

        ScheduleItem item1 = new ScheduleItem(treatment1);
        ScheduleItem item2 = new ScheduleItem(task1);
        int expResult = 1;
        item1.getTaskID();
        assertEquals("taskID was incorrect: ", expResult, result);
    }

    @Test
    public void testGetStartHour() {

        Treatment treatment1 = new Treatment(2, 6, 1, 2);
        Task task1 = new Task(1, 2, 3, "feeding");

        ScheduleItem item1 = new ScheduleItem(treatment1);
        ScheduleItem item2 = new ScheduleItem(task1);
        int expResult = 2;
        item1.getStartHour();
        assertEquals("StartHour was incorrect: ", expResult, result);
    }

    @Test
    public void testGetTaskDescription() {

        Treatment treatment1 = new Treatment(2, 6, 1, 2);
        Task task1 = new Task(1, 2, 3, "feeding");

        ScheduleItem item1 = new ScheduleItem(treatment1);
        ScheduleItem item2 = new ScheduleItem(task1);
        int expResult = "feeding";
        item1.getTaskDescription();
        assertEquals("TaskDescription was incorrect: ", expResult, result);
    }

    @Test
    public void testGetTaskDuration() {

        Treatment treatment1 = new Treatment(2, 6, 1, 2);
        Task task1 = new Task(1, 2, 3, "feeding");

        ScheduleItem item1 = new ScheduleItem(treatment1);
        ScheduleItem item2 = new ScheduleItem(task1);
        int expResult = 6;
        item1.getTaskDuration();
        assertEquals("TaskDuration was incorrect: ", expResult, result);
    }

    @Test
    public void testGetTaskMaxTimeVariation() {

        Treatment treatment1 = new Treatment(2, 6, 1, 2);
        Task task1 = new Task(1, 2, 3, "feeding");

        ScheduleItem item1 = new ScheduleItem(treatment1);
        ScheduleItem item2 = new ScheduleItem(task1);
        int expResult = 3;
        item1.getTaskMaxTimeVariation();
        assertEquals("TaskMaxTimeVariation was incorrect: ", expResult, result);
    }

}
