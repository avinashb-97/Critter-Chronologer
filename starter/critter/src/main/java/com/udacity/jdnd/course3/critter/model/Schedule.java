package com.udacity.jdnd.course3.critter.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "PetScheduleMapping",
            joinColumns = @JoinColumn(name = "scheduleId"),
            inverseJoinColumns = @JoinColumn(name = "petId")
    )
    private List<Pet> pets;

    @ManyToMany
    @JoinTable(
            name = "EmployeeScheduleMapping",
            joinColumns = @JoinColumn(name = "scheduleId"),
            inverseJoinColumns = @JoinColumn(name = "employeeId")
    )
    private List<Employee> employees;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
