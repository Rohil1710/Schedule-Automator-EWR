package edu.ucalgary.oop;

import org.junit.Test;

import edu.ucalgary.oop.Schedule;

import org.junit.Before;
import static org.junit.Assert.*;

import java.beans.Transient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;


public class ScheduleTestSuite {
    
    //Tests for Animal Class:
    @Test
    public void testGetNickName() {
        Animal animalFox = new Animal(16, "Spots", "fox");
        String expectedNickName = "Spots";
        String actualNickName = animalFox.getNickName();
        assertEquals(expectedNickName, actualNickName);
    }

    @Test
    public void testGetAnimalID() {
        Animal animalBeaver = new Animal(17, "lulu", "beaver");
        int expectedAnimalID = 17;
        int actualAnimalID = animalBeaver.getAnimalID();
        assertEquals(expectedAnimalID, actualAnimalID);
    }

    @Test
    public void testGetSpecies() {
        Animal animalRaccoon = new Animal(18, "Bilo", "raccoon");
        String expectedSpecies = "RACCOON";
        String actualSpecies = animalRaccoon.getSpecies();
        assertEquals(expectedSpecies, actualSpecies);
    }

    @Test
    public void testGetType() {
        Animal animalRaccoonNew = new Animal(21, "Jess", "raccoon");
        String expectedAnimalType = "NOCTURNAL";
        String actualAnimalType = animalRaccoonNew.getType();
        assertEquals(expectedAnimalType, actualAnimalType);
    }

    //Test invalid species:
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSpecies() {
        Animal animalInvalidSpecies = new Animal(3, "Seabiscuit", "horse");
    }

    //Tests for Species Enumeration:
    @Test
    public void testGetFeedingDuration() {
        Animal animalCoyoteEnum = new Animal(23, "Cujo", "coyote");
        String animalSpecies = animalCoyoteEnum.getSpecies();
        int expectedFeedingDuration = 5;
        Species enumValue = Species.valueOf(animalSpecies);
        int actualFeedingDuration = enumValue.getFeedingDuration();
        assertEquals(expectedFeedingDuration, actualFeedingDuration);
    }

    @Test
    public void testGetFoodPrepDuration() {
        Animal animalCoyoteEnum2 = new Animal(24, "Gio", "coyote");
        String animalSpecies = animalCoyoteEnum2.getSpecies();
        int expectedFoodPrepDuration = 10;
        Species enumValue = Species.valueOf(animalSpecies);
        int actualFoodPrepDuration = enumValue.getFoodPrepDuration();
        assertEquals(expectedFoodPrepDuration, actualFoodPrepDuration);
    }

    @Test
    public void testGetCageCleanDuration() {
        Animal animalCoyoteEnum3 = new Animal(25, "Hitch", "coyote");
        String animalSpecies = animalCoyoteEnum3.getSpecies();
        int expectedCageCleanDuration = 5;
        Species enumValue = Species.valueOf(animalSpecies);
        int actualCageCleanDuration = enumValue.getCageCleanDuration();
        assertEquals(expectedCageCleanDuration, actualCageCleanDuration);
    }

    //Tests for Type Enumeration:
    @Test
    public void testGetFeedingStartTime() {
        Animal animalCoyoteEnum3 = new Animal(31, "Monty", "coyote");
        String animalType = animalCoyoteEnum3.getType();
        int expectedStartTime = 19;
        Type enumValue = Type.valueOf(animalType);
        int actualStartTime = enumValue.getFeedingStartTime();
        assertEquals(expectedStartTime, actualStartTime);
    }


    //Tests for ScheduleItem Class:

    @Test
    public void testGetTreatmentID(){
        ScheduleItem item = new ScheduleItem(1, 5, 4, 6, 3, 15, "Hugs and kisses <3");
        int expectedID = 1;
        int actualID = item.getTreatmentID();
        assertEquals(expectedID, actualID);
    }

    @Test
    public void testGetItemAnimalID(){
        ScheduleItem item = new ScheduleItem(1, 5, 4, 6, 3, 15, "Give medicine");
        int expectedID = 5;
        int actualID = item.getAnimalID();
        assertEquals(expectedID, actualID);
    }

    @Test
    public void testGetTaskID(){
        ScheduleItem item = new ScheduleItem(1, 5, 4, 6, 3, 15, "Put on new bandaid");
        int expectedID = 4;
        int actualID = item.getTaskID();
        assertEquals(expectedID, actualID);
    }

    @Test
    public void testGetStartHour(){
        ScheduleItem item = new ScheduleItem(1, 5, 4, 6, 3, 15, "Give magic beans");
        int expectedStartHour = 6;
        int actualStartHour= item.getStartHour();
        assertEquals(expectedStartHour, actualStartHour);
    }

    @Test
    public void testGetMaxWindow(){
        ScheduleItem item = new ScheduleItem(1, 5, 4, 6, 3, 15, "Daily dose of coffee");
        int expectedWindow = 3;
        int actualWindow = item.getMaxWindow();
        assertEquals(expectedWindow, actualWindow);
    }

    @Test
    public void testGetDuration(){
        ScheduleItem item = new ScheduleItem(1, 5, 4, 6, 3, 15, "Hugs and kisses again <3");
        int expectedDuration = 15;
        int actualDuration = item.getDuration();
        assertEquals(expectedDuration, actualDuration);
    }

    @Test
    public void testGetDescription(){
        ScheduleItem item = new ScheduleItem(1, 5, 4, 6, 3, 15, "Generic medical task of great importance");
        String expectedDescription = "Generic medical task of great importance";
        String actualDescription = item.getDescription();
        assertEquals(expectedDescription, actualDescription);
    }


    //Tests for Schedule Class:

    //SETTERS:

    @Test 
    public void testSetAnimals(){
        Schedule schedule = new Schedule();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        Animal animalFox = new Animal(16, "Spots", "fox");
        Animal animalBeaver = new Animal(17, "lulu", "beaver");
        Animal animalRaccoon = new Animal(18, "Bilo", "raccoon");
        Animal animalCoyote = new Animal(20, "Monty", "coyote");
        animals.add(animalFox);
        animals.add(animalBeaver);
        animals.add(animalRaccoon);
        animals.add(animalCoyote);
        schedule.setAnimals(animals);
        String expectedName = animals.get(1).getNickName();
        String actualName = schedule.getAnimals().get(1).getNickName();
        assertEquals(expectedName, actualName);
    }

    @Test 
    public void testSetTreatmentItems(){
        Schedule schedule = new Schedule();
        ArrayList<ScheduleItem> items = new ArrayList<ScheduleItem>();
        ScheduleItem item1 = new ScheduleItem(1, 5, 4, 6, 3, 15, "Hugs and kisses <3");
        ScheduleItem item2 = new ScheduleItem(2, 4, 6, 3, 2, 10, "Give medicine");
        ScheduleItem item3 = new ScheduleItem(4, 5, 7, 1, 2, 30, "Daily dose of coffee");
        ScheduleItem item4 = new ScheduleItem(6, 2, 4, 8, 3, 15, "Generic medical task of great importance");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        schedule.setTreatmentItems(items);
        String expectedDescription = items.get(2).getDescription();
        String actualDescription = schedule.getTreatmentItems().get(2).getDescription();
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testBuildSchedule(){
        Schedule schedule = new Schedule();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        Animal animalFox = new Animal(16, "Spots", "fox");
        Animal animalBeaver = new Animal(17, "lulu", "beaver");
        Animal animalRaccoon = new Animal(18, "Bilo", "raccoon");
        Animal animalCoyote = new Animal(20, "Monty", "coyote");
        animals.add(animalFox);
        animals.add(animalBeaver);
        animals.add(animalRaccoon);
        animals.add(animalCoyote);
        schedule.setAnimals(animals);
        ArrayList<ScheduleItem> items = new ArrayList<ScheduleItem>();
        ScheduleItem item1 = new ScheduleItem(1, 16, 4, 6, 3, 15, "Hugs and kisses <3");
        ScheduleItem item2 = new ScheduleItem(2, 17, 6, 3, 2, 10, "Give medicine");
        ScheduleItem item3 = new ScheduleItem(4, 18, 7, 1, 2, 30, "Daily dose of coffee");
        ScheduleItem item4 = new ScheduleItem(6, 20, 4, 8, 3, 15, "Generic medical task of great importance");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        schedule.setTreatmentItems(items);
        schedule.buildSchedule();
        boolean expectedValue = true;
        boolean actualValue = schedule.getBuildCheck();
        assertEquals(expectedValue, actualValue);        
    }

    @Test
    public void testGetAvailableTimes(){
        Schedule schedule = new Schedule();
        int[] expectedTimes = new int[24];
        Arrays.fill(expectedTimes, 60);
        int expectedTime1 = expectedTimes[8];
        int[] actualTimes = schedule.getAvailableTimes();
        int actualTime1 = actualTimes[8];
        assertEquals(expectedTime1, actualTime1); 
    }

    @Test 
    public void testGetProblemHour(){
        Schedule schedule = new Schedule();
        int expectedHour = -1;
        int actualHour = schedule.getProblemHour();
        assertEquals(expectedHour, actualHour); 
    }

    @Test
    public void testGetVolunteerNeeded(){
        Schedule schedule = new Schedule();
        boolean[] expectedVolunteers = new boolean[24];
        boolean expectedVolunteer = expectedVolunteers[4];
        boolean[] actualVolunteers = schedule.getVolunteerNeeded();
        boolean actualVolunteer = actualVolunteers[4];
        assertEquals(expectedVolunteer, actualVolunteer); 
    }

    @Test
    public void testGetHourSchedule(){
        Schedule schedule = new Schedule();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        Animal animalFox = new Animal(16, "Spots", "fox");
        Animal animalBeaver = new Animal(17, "lulu", "beaver");
        Animal animalRaccoon = new Animal(18, "Bilo", "raccoon");
        Animal animalCoyote = new Animal(20, "Monty", "coyote");
        animals.add(animalFox);
        animals.add(animalBeaver);
        animals.add(animalRaccoon);
        animals.add(animalCoyote);
        schedule.setAnimals(animals);
        ArrayList<ScheduleItem> items = new ArrayList<ScheduleItem>();
        ScheduleItem item1 = new ScheduleItem(1, 16, 4, 6, 3, 5, "Hugs and kisses <3");
        ScheduleItem item2 = new ScheduleItem(2, 17, 6, 6, 2, 5, "Give medicine");
        ScheduleItem item3 = new ScheduleItem(4, 18, 7, 6, 2, 5, "Daily dose of coffee");
        ScheduleItem item4 = new ScheduleItem(6, 20, 4, 6, 3, 5, "Generic medical task of great importance");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        schedule.setTreatmentItems(items);
        schedule.buildSchedule();

        ArrayList<ScheduleItem> expectedItems = new ArrayList<ScheduleItem>();
        expectedItems.add(item1);
        expectedItems.add(item2);
        expectedItems.add(item3);
        expectedItems.add(item4);

        ArrayList<ScheduleItem> actualItems = schedule.getHourSchedule(6);
        assertEquals(expectedItems, actualItems); 
        
    }

    @Test
    public void testGetOrphanIDs(){
        Schedule schedule = new Schedule();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        Animal animalFox = new Animal(16, "Boots, Edwardo, Maybelline", "fox");
        Animal animalBeaver = new Animal(17, "lulu", "beaver");
        Animal animalRaccoon = new Animal(18, "Bilo", "raccoon");
        Animal animalCoyote = new Animal(20, "Monty", "coyote");
        animals.add(animalFox);
        animals.add(animalBeaver);
        animals.add(animalRaccoon);
        animals.add(animalCoyote);
        schedule.setAnimals(animals);
        ArrayList<ScheduleItem> items = new ArrayList<ScheduleItem>();
        ScheduleItem item1 = new ScheduleItem(1, 16, 4, 6, 3, 5, "Kit feeding");
        ScheduleItem item2 = new ScheduleItem(2, 17, 6, 6, 2, 5, "Give medicine");
        ScheduleItem item3 = new ScheduleItem(4, 18, 7, 6, 2, 5, "Daily dose of coffee");
        ScheduleItem item4 = new ScheduleItem(6, 20, 4, 6, 3, 5, "Generic medical task of great importance");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        schedule.setTreatmentItems(items);
        schedule.buildSchedule();
        ArrayList<Integer> expectedOrphanIDs = new ArrayList<Integer>();
        expectedOrphanIDs.add(16);
        ArrayList<Integer> actualOrphanIDs = schedule.getOrphanIDs();
        assertEquals(expectedOrphanIDs, actualOrphanIDs); 

    }

    @Test
    public void testGetTimeUsed(){
        Schedule schedule = new Schedule();
        ArrayList<Animal> animals = new ArrayList<Animal>();
        Animal animalFox = new Animal(16, "Boots, Edwardo, Maybelline", "fox");
        animals.add(animalFox);
        schedule.setAnimals(animals);
        ArrayList<ScheduleItem> items = new ArrayList<ScheduleItem>();
        ScheduleItem item1 = new ScheduleItem(1, 16, 4, 6, 3, 5, "Kit feeding");
        items.add(item1);
        schedule.setTreatmentItems(items);
        schedule.buildSchedule();
        schedule.estimateTimeUsed();
        int expectedTimeUsed = 20;
        int actualTimeUsed = schedule.getTimeUsed();
        assertEquals(expectedTimeUsed, actualTimeUsed); 

    }

    @Test
    public void testGetUrl(){
        Schedule schedule = new Schedule();
        String expectedUrl = "jdbc:mysql://localhost/EWR";
        String actualUrl = schedule.getUrl();
        assertEquals(expectedUrl, actualUrl); 
    }
}

