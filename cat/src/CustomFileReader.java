import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class CustomFileReader {
    public static void main(String[] args) throws IOException {
        final String filePath = Paths.get(".").toAbsolutePath().normalize() + "/cat/test.txt";
        File theFile = new File(filePath);
        LineIterator it = FileUtils.lineIterator(theFile, "UTF-8");
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                // do something with line
                System.out.println(line);
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
    }
}
