/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jevents;

import java.util.Date;

/**
 *
 * @author FENG0
 */
public class AdminActivity {
    private String ActivityID;
    private Date ActivityTime;
    private String CreatedBy;
    private String Category;
    private String Location;
    private String Status;

    public AdminActivity(String ActivityID, Date ActivityTime, String CreatedBy, String Category, String Location, int status) {
        this.ActivityID = ActivityID;
        this.ActivityTime = ActivityTime;
        this.CreatedBy = CreatedBy;
        this.Category = Category;
        this.Location = Location;
        switch (status)
        {
            case -1: this.Status = "Rejected"; break;
            case 0: this.Status = "Pending Approval"; break;
            case 1: this.Status = "Approved"; break;
            case 2: this.Status = "Completed"; break;
        }
    }

    public String getActivityID() {
        return ActivityID;
    }

    public void setActivityID(String ActivityID) {
        this.ActivityID = ActivityID;
    }

    public Date getActivityTime() {
        return ActivityTime;
    }

    public void setActivityTime(Date ActivityTime) {
        this.ActivityTime = ActivityTime;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
