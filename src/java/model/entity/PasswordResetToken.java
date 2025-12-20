/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entity;
import java.time.LocalDateTime;

/**
 *
 * @author Leo
 */
public class PasswordResetToken {
    private int tokenID;
    private int userID;
    private String token;
    private LocalDateTime expiry;

    public PasswordResetToken() {
    }

    public PasswordResetToken(int tokenID, int userID, String token, LocalDateTime expiry) {
        this.tokenID = tokenID;
        this.userID = userID;
        this.token = token;
        this.expiry = expiry;
    }

    public int getTokenID() {
        return tokenID;
    }

    public void setTokenID(int tokenID) {
        this.tokenID = tokenID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }
    
    
}
