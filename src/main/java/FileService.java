import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileService {
    private final Path filePath;

    public FileService(String filename) {
        this.filePath = Paths.get(filename);
    }

    public void saveDrugs(List<Drug> drugs) throws IOException {
        Files.createDirectories(filePath.getParent() == null ? Paths.get(".") : filePath.getParent());
        try (BufferedWriter w = Files.newBufferedWriter(filePath)) {
            for (Drug d : drugs) {
                w.write(d.toCsv());
                w.newLine();
            }
        }
    }

    public List<Drug> readDrugs() throws IOException {
        if (!Files.exists(filePath)) return Collections.emptyList();
        List<Drug> list = new ArrayList<>();
        try (BufferedReader r = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                list.add(Drug.fromCsv(line));
            }
        }
        return list;
    }
}
