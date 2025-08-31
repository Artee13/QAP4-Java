import java.time.LocalDate;

// Entity for database persistence (PostgreSQL)
public class Patient {
    private int patientId;
    private String patientFirstName;
    private String patientLastName;
    private LocalDate patientDOB;

    public Patient(int patientId, String first, String last, LocalDate dob) {
        this.patientId = patientId;
        this.patientFirstName = first;
        this.patientLastName = last;
        this.patientDOB = dob;
    }

    public int getPatientId() { return patientId; }
    public String getPatientFirstName() { return patientFirstName; }
    public String getPatientLastName() { return patientLastName; }
    public LocalDate getPatientDOB() { return patientDOB; }

    @Override
    public String toString() {
        return "Patient{id=" + patientId + ", name=" + patientFirstName + " " +
               patientLastName + ", dob=" + patientDOB + "}";
    }
}
