import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Basic DAO for Patient (insert, findAll, and table bootstrap). */
public class PatientDAO {
    public void createTableIfNotExists() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS patients (
              patient_id      INTEGER PRIMARY KEY,
              first_name      VARCHAR(100) NOT NULL,
              last_name       VARCHAR(100) NOT NULL,
              dob             DATE NOT NULL
            );
        """;
        try (Connection con = Database.getConnection();
             Statement st = con.createStatement()) {
            st.execute(sql);
        }
    }

    public void insert(Patient p) throws SQLException {
        String sql = "INSERT INTO patients (patient_id, first_name, last_name, dob) VALUES (?,?,?,?)";
        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getPatientId());
            ps.setString(2, p.getPatientFirstName());
            ps.setString(3, p.getPatientLastName());
            ps.setDate(4, java.sql.Date.valueOf(p.getPatientDOB()));
            ps.executeUpdate();
        }
    }

    public List<Patient> findAll() throws SQLException {
        String sql = "SELECT patient_id, first_name, last_name, dob FROM patients ORDER BY patient_id";
        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<Patient> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("patient_id");
                String fn = rs.getString("first_name");
                String ln = rs.getString("last_name");
                LocalDate dob = rs.getDate("dob").toLocalDate();
                list.add(new Patient(id, fn, ln, dob));
            }
            return list;
        }
    }
}
