package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Visitor {

    private int id;
    private String name;
    private String phone;
    private String purpose;
    private String personToMeet;
    private LocalDate visitDate;
    private LocalTime visitTime;

    // ✅ ADD THIS CONSTRUCTOR (IMPORTANT)
    public Visitor(String name, String phone, String purpose, String personToMeet) {
        this.name = name;
        this.phone = phone;
        this.purpose = purpose;
        this.personToMeet = personToMeet;
        this.visitDate = LocalDate.now();
        this.visitTime = LocalTime.now().withNano(0);
    }

    // Existing constructor (keep)
    public Visitor(String name, String phone, String purpose,
                   String personToMeet, LocalDate visitDate, LocalTime visitTime) {
        this.name = name;
        this.phone = phone;
        this.purpose = purpose;
        this.personToMeet = personToMeet;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
    }

    // Existing constructor (keep)
    public Visitor(int id, String name, String phone, String purpose,
                   String personToMeet, LocalDate visitDate, LocalTime visitTime) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.purpose = purpose;
        this.personToMeet = personToMeet;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getPurpose() { return purpose; }
    public String getPersonToMeet() { return personToMeet; }
    public LocalDate getVisitDate() { return visitDate; }
    public LocalTime getVisitTime() { return visitTime; }
}
