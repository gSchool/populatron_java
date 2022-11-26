package com.emeritus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader {
    public List<String> readAllLines(Path path) throws IOException {
        return Files.readAllLines(path, StandardCharsets.ISO_8859_1);
    }
}
