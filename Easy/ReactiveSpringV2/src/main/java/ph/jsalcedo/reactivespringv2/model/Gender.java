package ph.jsalcedo.reactivespringv2.model;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    public final String gender;
    private Gender(String gender){
        this.gender = gender;
    }
}
