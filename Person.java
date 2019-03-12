import java.util.Date;

public class Person {
    private String name;
    private String surename;
    private String rodc;
    private Date dob;

    public Person(String name, String surename, String rodc, Date dob) {
        this.name = name;
        this.surename = surename;
        this.rodc = rodc;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getRodc() {
        return rodc;
    }

    public void setRodc(String rodc) {
        this.rodc = rodc;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}

