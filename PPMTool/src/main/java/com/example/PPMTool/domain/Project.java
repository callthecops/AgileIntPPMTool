package com.example.PPMTool.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

//We are creating Entity for database and also add all the constraints needed so we get the corect data in the Db.

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Project name is required")
    private String projectName;
    @NotBlank(message = "Project Identifier is required")
    @Size(min = 4, max = 5, message = "Please use 4 to 5 characters")
    //This constreint happens after the project went through the validation service.In order to validate for this correctly
    //we have to create a custom exception for this.
    @Column(updatable = false, unique = true)//we can't update this field,it is unique.It says a constraint at DB level.
    private String projectIdentifier;
    @NotBlank(message = "Project description is required")
    private String description;
    @JsonFormat(pattern = "yyyy-mm-dd")//This sets a pattern for the JSON date it will accept
    private Date start_date;
    @JsonFormat(pattern = "yyyy-mm-dd")//This sets a pattern for the JSON date it will accept
    private Date end_date;
    @JsonFormat(pattern = "yyyy-mm-dd")//This sets a pattern for the JSON date it will accept
    private Date created_At;
    @JsonFormat(pattern = "yyyy-mm-dd")//This sets a pattern for the JSON date it will accept
    private Date updated_At;


    public Project() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    // We use @PrePersist to indicate that this method is called before a Project entity is inserted into the Database
    //As such the project is inserted with the respective date into the Database after this date has been created.
    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    //This here is used so the method is triggered before the entity has been updated to the database
    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }

}
