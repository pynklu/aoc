package aoc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class Solver {
    public List<String> readFileByLine(String filename) throws IOException, URISyntaxException {
        Path path = Path.of(getClass().getClassLoader().getResource(filename).toURI());
        return Files.readAllLines(path);
    }
}
