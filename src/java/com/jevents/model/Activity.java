package com.jevents.model;

import java.sql.Timestamp;

public class Activity {

    int id;
    String name;
    String description;
    int creatorID;
    String creatorName;
    String creatorNationlity;

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorNationlity() {
        return creatorNationlity;
    }

    public void setCreatorNationlity(String creatorNationlity) {
        this.creatorNationlity = creatorNationlity;
    }
    
    
    int status;
    int joined;
    int rating;
    Timestamp time;
    int category;
    float latitude;
    float longitude;
    String location;
    boolean participant;
    String categoryStr;

    public String getCategoryStr() {
        return categoryStr;
    }

    public void setCategoryStr(String categoryStr) {
        this.categoryStr = categoryStr;
    }

    public boolean isParticipant() {
        return participant;
    }

    public void setParticipant(boolean participant) {
        this.participant = participant;
    }

    public Activity(String name, String description, int creatorID, Timestamp time,
            int category, float latitude, float longitude, String location) {
        super();
        this.name = name;
        this.description = description;
        this.creatorID = creatorID;
        this.time = time;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

    public Activity(int id, String name, String description, int creatorID, int status, int joined, int rating,
            Timestamp time, int category, float latitude, float longitude, String location) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorID = creatorID;
        this.status = status;
        this.joined = joined;
        this.rating = rating;
        this.time = time;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

    public Activity(int id, String name, String description, int creatorID, int status, int joined, int rating,
            Timestamp time, String category, float latitude, float longitude, String location) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorID = creatorID;
        this.status = status;
        this.joined = joined;
        this.rating = rating;
        this.time = time;
        this.categoryStr = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getJoined() {
        return joined;
    }

    public void setJoined(int joined) {
        this.joined = joined;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
