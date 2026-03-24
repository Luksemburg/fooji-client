package com.example.foojiclient;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class UserDTO {

    private String username;

    private String email;

    private String password;

    private Long phone;

    private String gender;

    private String location;

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    private String googleId;

    private String jwt;

    private Long inviteCode;

    public Long getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(Long inviteCode) {
        this.inviteCode = inviteCode;
    }

    // Constructors
    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", gender='" + gender + '\'' +
                ", location='" + location + '\'' +
                ", googleId='" + googleId + '\'' +
                ", jwt='" + jwt + '\'' +
                ", inviteCode=" + inviteCode +
                '}';
    }
}
