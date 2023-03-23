import org.junit.*;
import static org.junit.Assert.*;

public class ScheduleTest {

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

}