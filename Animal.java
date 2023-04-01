
public class Animal {
    private String species;
    private String type;
    // private ArrayList<int> feedingTimes = new ArrayList<>();
    private int foodPrepDuration;
    private int feedingDuratoin;
    private int caseCleanDuration;
    private String nickName;
    private int animalID;
    private boolean orphan;
    private boolean needTreatment;

    public Animal(int id, String typ, String animalNickName, String animalSpecies, boolean orph, boolean treatment) {
        this.animalID = id;
        this.type = typ;
        this.nickName = animalNickName;
        this.species = animalSpecies;
        this.orphan = orph;
        this.needTreatment = treatment;
    }

    public String getSpecies() {
        return this.species;
    }

    public String getType() {
        return this.type;
    }

    // public List getFeedingTimes(){
    // return
    // }

    public int getFoodPrepDuration() {
        return this.foodPrepDuration;
    }

    public int getFeedingDuration() {
        return this.feedingDuratoin;
    }

    public int getCageCleanDuration() {
        return this.caseCleanDuration;
    }

    public String getNickName() {
        return this.nickName;
    }

    public int getAnimalID() {
        return this.animalID;
    }

    public boolean getOrphan() {
        return this.orphan;
    }

    public boolean getNeedTreatment() {
        return this.needTreatment;
    }
}