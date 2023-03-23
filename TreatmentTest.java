
//Basic Tests for Treatment class to be added to main ScheduleTest file


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

}

