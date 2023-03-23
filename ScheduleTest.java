//Basic Tests for Treatment class to be added to main ScheduleTest file


@Test
public void testScheduleItem() {
        Treatment treatment1 = new Treatment(2, 6, 1, 2);
        Task task1 = new Task(1,2,3,"feeding");

        ScheduleItem item1 = new ScheduleItem(treatment1);
        ScheduleItem item2 = new ScheduleItem(task1);

        System.out.println("\nTesting getAnimalID...");
        int expResult = 6;
        int result = item1.getAnimaltID();
        assertEquals("animalID was incorrect: ", expResult, result);

        System.out.println("\nTesting getTreatmentID...");
        int expResult = 2;
        int result = item1.getTreatmentID();
        assertEquals("treatmentID was incorrect: ", expResult, result);

        System.out.println("\nTesting getTaskID...");
        int expResult = 1;
        int result = item1.getTaskID();
        assertEquals("taskID was incorrect: ", expResult, result);

        System.out.println("\nTesting getStartHour...");
        int expResult = 1;
        int result = item2.getStartHour();
        assertEquals("startHour was incorrect: ", expResult, result);

        System.out.println("\nTesting getTaskDescription...");
        int expResult = "feeding";
        int result = item2.getTaskDescription();
        assertEquals("TaskDescription was incorrect: ", expResult, result);

        System.out.println("\nTesting getTaskDuration...");
        int expResult = 2;
        int result = item2.getTaskDuration();
        assertEquals("TaskDuration was incorrect: ", expResult, result);

        System.out.println("\nTesting getTaskMaxTimeVariation...");
        int expResult = 3;
        int result = item2.getTaskMaxTimeVariation();
        assertEquals("TaskMaxTimeVariation was incorrect: ", expResult, result);

}