import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Launcher {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get(args[0]);
        String result = new PopulationCounter().count(path);
        System.out.println(result);
    }
}
