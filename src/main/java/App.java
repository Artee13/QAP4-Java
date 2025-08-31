import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class App {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        FileService fileService = new FileService("drugs.txt");
        PatientDAO patientDAO = new PatientDAO();

        try { patientDAO.createTableIfNotExists(); }
        catch (Exception e) {
            System.out.println("Warning: Could not ensure table exists: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n==== QAP4 MENU ====");
            System.out.println("1) Save Drug data to a TEXT file");
            System.out.println("2) Read Drug data from the TEXT file");
            System.out.println("3) Save Patient data to the DATABASE");
            System.out.println("4) Read Patient data from the DATABASE");
            System.out.println("0) Exit");
            System.out.print("Select: ");

            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> saveDrugsToFile(fileService);
                    case "2" -> readDrugsFromFile(fileService);
                    case "3" -> savePatientsToDb(patientDAO);
                    case "4" -> readPatientsFromDb(patientDAO);
                    case "0" -> { System.out.println("Bye!"); return; }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private static void saveDrugsToFile(FileService fs) throws IOException {
        System.out.print("How many drugs to input? ");
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Drug> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Drug #" + (i + 1));
            int id = askInt("  id: ");
            String name = askStr("  name: ");
            double cost = askDouble("  cost (e.g., 19.99): ");
            String dosage = askStr("  dosage (e.g., 500mg): ");
            list.add(new Drug(id, name, cost, dosage));
        }
        fs.saveDrugs(list);
        System.out.println("Saved " + n + " drug(s) to drugs.txt");
    }

    private static void readDrugsFromFile(FileService fs) throws IOException {
        var drugs = fs.readDrugs();
        if (drugs.isEmpty()) System.out.println("No data found (drugs.txt empty or missing).");
        else {
            System.out.println("Drugs in file:");
            drugs.forEach(d -> System.out.println("  " + d));
        }
    }

    private static void savePatientsToDb(PatientDAO dao) throws Exception {
        System.out.print("How many patients to input? ");
        int n = Integer.parseInt(sc.nextLine().trim());
        for (int i = 0; i < n; i++) {
            System.out.println("Patient #" + (i + 1));
            int id = askInt("  id: ");
            String first = askStr("  first name: ");
            String last = askStr("  last name: ");
            String dobStr = askStr("  DOB (yyyy-mm-dd): ");
            LocalDate dob = LocalDate.parse(dobStr);
            dao.insert(new Patient(id, first, last, dob));
        }
        System.out.println("Inserted " + n + " patient(s) into DB.");
    }

    private static void readPatientsFromDb(PatientDAO dao) throws Exception {
        var all = dao.findAll();
        if (all.isEmpty()) System.out.println("No patients in DB.");
        else {
            System.out.println("Patients in DB:");
            for (Patient p : all) System.out.println("  " + p);
        }
    }

    private static int askInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (Exception e) { System.out.println("  please enter a whole number."); }
        }
    }
    private static double askDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Double.parseDouble(sc.nextLine().trim()); }
            catch (Exception e) { System.out.println("  please enter a number like 12.34."); }
        }
    }
    private static String askStr(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
