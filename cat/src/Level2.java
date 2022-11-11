import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Level2 {
    public static void main(String[] args) throws IOException {
        final String filePath = Paths.get(".").toAbsolutePath().normalize() + "/cat/level2_5.in";
        File theFile = new File(filePath);
        LineIterator it = FileUtils.lineIterator(theFile, "UTF-8");
        Board board;
        String line;
        try {
            Integer noLines = Integer.valueOf(it.nextLine());
            board = new Board(noLines);
            int counter = 0;
            for (int i = 0; i < noLines; i++) {
                line = it.nextLine();
                String[] split = line.split("");
                for (int j = 0; j < split.length; j++) {
                    board.board[i][j] = split[j];
                }
            }

            // position
            line = it.nextLine();
            String[] split = line.split(" ");
            board.upDownPosition = Integer.valueOf(split[0]) - 1;
            board.leftRightPosition = Integer.valueOf(split[1]) - 1;
            line = it.nextLine();
            int numberOfMoves = Integer.valueOf(line);
            String[] moves = it.nextLine().split("");
            for (int i = 0; i < numberOfMoves; i++) {

                System.out.println(board.upDownPosition + " " + board.leftRightPosition);
//                if (board.movePacMan(moves[i])) {
//                    System.out.println("coin");
//                    counter++;
//                }
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
