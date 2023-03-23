//Basic Tests for Task class to be added to main ScheduleTest file


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


