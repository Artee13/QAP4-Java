import java.sql.*;
import java.util.Properties;

// Centralized JDBC connection helper
public class Database {

    public static Connection getConnection() throws SQLException {
        String url  = System.getenv().getOrDefault("PG_URL", "jdbc:postgresql://localhost:5432/qap4");
        String user = System.getenv().getOrDefault("PG_USER", "postgres");
        String pass = System.getenv().getOrDefault("PG_PASSWORD", "postgres");

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pass);
        return DriverManager.getConnection(url, props);
    }
}
