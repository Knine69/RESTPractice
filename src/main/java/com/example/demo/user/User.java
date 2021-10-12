package com.example.demo.user;

import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;
    private String userAddress;
    private String userEmail;

    public User() {
    }

    public User(String userName, String userAddress, String userEmail) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUser(User user){

    }
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
