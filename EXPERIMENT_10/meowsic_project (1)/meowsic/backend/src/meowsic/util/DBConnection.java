package meowsic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL      = "jdbc:postgresql://localhost:5432/meowsic_db";
    private static final String USER     = "postgres";    // Change to your PostgreSQL username
    private static final String PASSWORD = "postgres";    // Change to your PostgreSQL password

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL Driver not found. Add postgresql-42.x.x.jar to your build path.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
