import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Level1 {
    public static void main(String[] args) throws IOException {
        final String filePath = Paths.get(".").toAbsolutePath().normalize() + "/cat/level1_5.in";
        File theFile = new File(filePath);
        LineIterator it = FileUtils.lineIterator(theFile, "UTF-8");
        try {
            Integer noLines = Integer.valueOf(it.nextLine());
            int counter = 0;
            for (int i = 0; i < noLines; i++) {
                String line = it.nextLine();
                String[] split = line.split("");
                long lineCount = Stream.of(split).filter(c -> c.equals("C")).count();
                counter += lineCount;
            }
            System.out.println(counter);
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.out"));
            writer.write(String.valueOf(counter));
            writer.flush();
        } finally {
            LineIterator.closeQuietly(it);
        }
    }
}
