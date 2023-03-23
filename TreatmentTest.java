
//Basic Tests for Treatment class to be added to main ScheduleTest file


    @Test
    public void testTreatmentClass() {
        
        Treatment treatment1 = new Treatment(int 1, int 6, int 1, int 0)
              
        System.out.println("Test Treatment Construcor and Getters:\n");
        System.out.println("\nTesting getTreatmentID...");
        int expResult = 1;
        int result = treatment1.getTreatmentID();
        assertEquals("TreatmentID was incorrect: ", expResult, result);

        System.out.println("\nTesting getAnimalID...");
        int expResult = 6;
        int result = treatment1.getTreatmentID();
        assertEquals("AnimalID was incorrect: ", expResult, result);

        System.out.println("\nTesting getTaskID...");
        int expResult = 1;
        int result = treatment1.getTaskID();
        assertEquals("TaskID was incorrect: ", expResult, result);
        
        System.out.println("\nTesting getStartHour...");
        int expResult = 1;
        int result = treatment1.getStartHour();
        assertEquals("TaskID was incorrect: ", expResult, result);

    }

}

