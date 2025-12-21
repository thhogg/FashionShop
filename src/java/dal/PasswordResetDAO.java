/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.time.LocalDateTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import model.entity.PasswordResetToken;

/**
 *
 * @author Leo
 */
public class PasswordResetDAO extends DBContext {

    private static PasswordResetDAO instance;

    public PasswordResetDAO() {
    }

    public static synchronized PasswordResetDAO getInstance() {
        if (instance == null) {
            instance = new PasswordResetDAO();
        }
        return instance;
    }

    public void saveToken(int userId, String token, LocalDateTime expiry) {
        String sql = """
            INSERT INTO PasswordResetToken(UserID, Token, Expiry)
            VALUES (?, ?, ?)
        """;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, token);
            ps.setTimestamp(3, Timestamp.valueOf(expiry));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteToken(String token) {
        String sql = "DELETE FROM PasswordResetToken WHERE Token = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, token);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PasswordResetToken findValidOTP(Integer userId, String otp) {
        String sql = "SELECT * FROM PasswordResetToken WHERE UserID = ? and Token = ? and Expiry > ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, otp);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PasswordResetToken t = new PasswordResetToken();
                t.setTokenID(rs.getInt("TokenID"));
                t.setUserID(rs.getInt("UserID"));
                t.setToken(rs.getString("Token"));
                t.setExpiry(rs.getTimestamp("Expiry").toLocalDateTime());
                return t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
