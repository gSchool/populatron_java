import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PopulationCounter {
    public String count(Path path) throws IOException {
        long sum = 0;
        List<String> lines = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] terms = line.split(",");
            String popText = terms[4];
            if (popText.length() == 0) {
                continue;
            }
            long population = Integer.parseInt(popText);
            sum += population;
        }
        String num = NumberFormat.getInstance(Locale.getDefault()).format(sum);
        String result = String.format("World population is: %s", num);
        return result;
    }
}
