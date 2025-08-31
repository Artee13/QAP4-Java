import java.util.Objects;
//Entity for file persistence
public class Drug {
    private int drugId;
    private String drugName;
    private double drugCost;
    private String dosage;

    public Drug(int drugId, String drugName, double drugCost, String dosage) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.drugCost = drugCost;
        this.dosage = dosage;
    }

    public int getDrugId() { return drugId; }
    public String getDrugName() { return drugName; }
    public double getDrugCost() { return drugCost; }
    public String getDosage() { return dosage; }

    @Override
    public String toString() {
        return "Drug{id=" + drugId + ", name='" + drugName + "', cost=$" +
               String.format("%.2f", drugCost) + ", dosage='" + dosage + "'}";
    }

    //CSV helpers for file 
    public String toCsv() {
        String safeName = drugName.replace(',', ' ');
        String safeDosage = dosage.replace(',', ' ');
        return drugId + "," + safeName + "," + drugCost + "," + safeDosage;
    }

    public static Drug fromCsv(String line) {
        String[] p = line.split(",", -1);
        if (p.length < 4) throw new IllegalArgumentException("Bad CSV line: " + line);
        return new Drug(Integer.parseInt(p[0]), p[1], Double.parseDouble(p[2]), p[3]);
    }

    @Override public boolean equals(Object o) {
        if (!(o instanceof Drug)) return false;
        Drug d = (Drug) o;
        return drugId == d.drugId &&
               Double.compare(drugCost, d.drugCost) == 0 &&
               Objects.equals(drugName, d.drugName) &&
               Objects.equals(dosage, d.dosage);
    }
    @Override public int hashCode() { return Objects.hash(drugId, drugName, drugCost, dosage); }
}
