package meowsic.dao;

import meowsic.model.User;
import meowsic.util.DBConnection;
import meowsic.util.PasswordUtil;

import java.sql.*;

public class UserDAO {

    // Register a new user; returns true on success, false if userId already exists
    public boolean registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (user_id, password, display_name, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUserId());
            ps.setString(2, PasswordUtil.hash(user.getPassword()));
            ps.setString(3, user.getDisplayName());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            // PostgreSQL unique violation SQLState = 23505
            if ("23505".equals(e.getSQLState())) {
                return false;
            }
            throw e;
        }
    }

    // Validate login credentials; returns User object on success, null on failure
    public User loginUser(String userId, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                if (PasswordUtil.verify(password, storedHash)) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserId(rs.getString("user_id"));
                    user.setDisplayName(rs.getString("display_name"));
                    user.setEmail(rs.getString("email"));
                    return user;
                }
            }
            return null;
        }
    }

    // Check if a userId already exists
    public boolean userExists(String userId) throws SQLException {
        String sql = "SELECT id FROM users WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }
}
