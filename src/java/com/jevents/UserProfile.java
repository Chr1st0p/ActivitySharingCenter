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
public class UserProfile {
    
    private int UserProfileId;
    private String Nickname;
    private int Gender;
    private Date DOB;
    private String Nationality;
    private String Address;

    public UserProfile(int UserProfileId, String Nickname, int Gender, Date DOB, String Nationality, String Address) {
        this.UserProfileId = UserProfileId;
        this.Nickname = Nickname;
        this.Gender = Gender;
        this.DOB = DOB;
        this.Nationality = Nationality;
        this.Address = Address;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }


    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }


    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }


    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String Nickname) {
        this.Nickname = Nickname;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }


    public int getUserProfileId() {
        return UserProfileId;
    }

    public void setUserProfileId(int UserProfileId) {
        this.UserProfileId = UserProfileId;
    }

}
