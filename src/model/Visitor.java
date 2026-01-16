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
    
    // Constructors
    public Visitor() {}
    
    // Constructor for adding new visitor
    public Visitor(String name, String phone, String purpose, String personToMeet) {
        this.name = name;
        this.phone = phone;
        this.purpose = purpose;
        this.personToMeet = personToMeet;
    }
    
    // Constructor for fetching from database
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
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    
    public String getPersonToMeet() { return personToMeet; }
    public void setPersonToMeet(String personToMeet) { this.personToMeet = personToMeet; }
    
    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }
    
    public LocalTime getVisitTime() { return visitTime; }
    public void setVisitTime(LocalTime visitTime) { this.visitTime = visitTime; }
    
    // For backward compatibility
    public String getMeet() { return personToMeet; }
    public void setMeet(String meet) { this.personToMeet = meet; }
    
    public String getDate() { return visitDate != null ? visitDate.toString() : null; }
    public String getTime() { return visitTime != null ? visitTime.toString() : null; }
}