import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Level5 {

    public static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter("output.out"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        final String filePath = Paths.get(".").toAbsolutePath().normalize() + "/cat/level4_5.in";
        File theFile = new File(filePath);
        LineIterator it = FileUtils.lineIterator(theFile, "UTF-8");
        Board board;
        String line;
        try {
            Integer noLines = Integer.valueOf(it.nextLine());
            board = new Board(noLines);

            // read board
            for (int i = 0; i < noLines; i++) {
                line = it.nextLine();
                String[] split = line.split("");
                for (int j = 0; j < split.length; j++) {
                    board.board[i][j] = split[j];
                    if (split[j].equals("G")) {
                        board.ghosts.add(new Ghost(j, i));
                        //TODO board.board[i][j] = "C"
                    }
                }
            }

            // pacman details
            line = it.nextLine();
            String[] split = line.split(" ");
            board.upDownPosition = Integer.valueOf(split[0]) - 1;
            board.leftRightPosition = Integer.valueOf(split[1]) - 1;
//            line = it.nextLine();
//            int numberOfMoves = Integer.valueOf(line);
//            String[] moves = it.nextLine().split("");
//            board.moves = Arrays.asList(moves);

//            // ghost details
//            int numberOfGhosts = Integer.valueOf(it.nextLine());
//
//            for (int i = 0; i < numberOfGhosts; i++) {
//                Ghost ghost = new Ghost();
//                line = it.nextLine();
//                split = line.split(" ");
//                ghost.upDownPosition = Integer.valueOf(split[0]) - 1;
//                ghost.leftRightPosition = Integer.valueOf(split[1]) - 1;
//                line = it.nextLine();
//                numberOfMoves = Integer.valueOf(line);
//                moves = it.nextLine().split("");
//                ghost.moves = Arrays.asList(moves);
//                board.ghosts.add(ghost);
//            }

            // read max moves
            int maxMoves = Integer.valueOf(it.nextLine());
//            board.print();
            //execution
            for (int i = 0; i < 25000; i++) {
                if (board.turn(i) == null) {
                    break;
                }
            }
            System.out.println("");
            System.out.println("");
            board.print();
            System.out.println(board.isDepleted());
            writer.flush();
//            GameResult gameResult = execute(board, numberOfMoves);
//
//            System.out.println(gameResult);
//            BufferedWriter writer = new BufferedWriter(new FileWriter("output.out"));
//            writer.write(gameResult.coinsCollected + " " + (gameResult.hasDied ? "NO" : "YES"));
//            writer.flush();
        } finally {
            LineIterator.closeQuietly(it);
        }
    }

    private static GameResult execute(Board board, int numberOfMoves) {
        int counter = 0;
        for (int i = 0; i < numberOfMoves; i++) {
//            System.out.println(board.upDownPosition + " " + board.leftRightPosition);
            MoveResult moveResult = board.turn(i);
            switch (moveResult) {
                case WALL, GHOST -> {
                    return new GameResult(counter, true);
                }
                case COIN -> counter++;
            }
        }
        return new GameResult(counter, false);
    }
}
