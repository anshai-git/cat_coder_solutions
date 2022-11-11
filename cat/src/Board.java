import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {
    String[][] board;
    int leftRightPosition;
    int upDownPosition;
    List<String> moves = new ArrayList<>();
    List<Ghost> ghosts = new ArrayList<>();


    public Board(int size) {
        board = new String[size][size];
    }

    public MoveResult turn(int index) {
//        print();
//        for (int i = 0; i < ghosts.size(); i++) {
//            moveGhost(ghosts.get(i), index);
//        }
        ShortestPath.Cell nextMove = getNextMove();
        if (nextMove != null) {
            String moveString = getMove(nextMove);
            try {
                Level4.writer.write(moveString);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            System.out.println(moveString);
            return movePacMan(moveString);
        } else return null;
    }

    public String getMove(ShortestPath.Cell cell) {
        if (cell.y == leftRightPosition) {
            if (cell.x < upDownPosition) {
                return "U";
            } else {
                return "D";
            }
        } else {
            if (cell.y < leftRightPosition) {
                return "L";
            } else {
                return "R";
            }
        }
    }

    public void moveGhost(Ghost ghost, int index) {
//        System.out.print("Ghost " + ghost.upDownPosition + " " + ghost.leftRightPosition + " -> ");
        switch (ghost.moves.get(index)) {
            case "L" -> ghost.leftRightPosition--;
            case "R" -> ghost.leftRightPosition++;
            case "U" -> ghost.upDownPosition--;
            case "D" -> ghost.upDownPosition++;
        }
//        System.out.println(ghost.upDownPosition + " " + ghost.leftRightPosition);
    }

    public MoveResult movePacMan(String move) {
//        System.out.print("PacMan " + upDownPosition + " " + leftRightPosition + " -> ");
        switch (move) {
            case "L" -> leftRightPosition--;
            case "R" -> leftRightPosition++;
            case "U" -> upDownPosition--;
            case "D" -> upDownPosition++;
        }
//        System.out.println(upDownPosition + " " + leftRightPosition);
        return getMoveResult();
    }

    private MoveResult getMoveResult() {
        if (isGhost()) {
            return MoveResult.GHOST;
        }
        if (isWall()) {
            return MoveResult.WALL;
        }
        if (isCoin()) {
            board[upDownPosition][leftRightPosition] = "E";
            return MoveResult.COIN;
        }
        return MoveResult.EMPTY;
    }

    public boolean isGhost() {
        for (Ghost ghost : ghosts) {
            if (ghost.upDownPosition == upDownPosition && ghost.leftRightPosition == leftRightPosition) {
                return true;
            }
        }
        return false;
    }

    public boolean isWall() {
        return board[upDownPosition][leftRightPosition].equals("W");
    }

    public boolean isCoin() {
        return board[upDownPosition][leftRightPosition].equals("C");
//    TODO    return board[upDownPosition][leftRightPosition].equals("C") || board[upDownPosition][leftRightPosition].equals("G");
    }

    public ShortestPath.Cell getNextMove() {
        LinkedList<ShortestPath.Cell> shortestPath = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].equals("C")) {
                    // compute distance
                    LinkedList<ShortestPath.Cell> path = ShortestPath.shortestPath(getPathBoard(), new int[]{upDownPosition, leftRightPosition}, new int[]{i, j});
                    if (shortestPath == null || (path != null && path.size() < shortestPath.size())) {
                        shortestPath = path;
                    }
                }
            }
        }
        if (shortestPath != null) {
            return shortestPath.get(1);
        } else {
            return null;
        }
    }

    public int[][] getPathBoard() {
        int[][] path = new int[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                path[i][j] = (board[i][j].equals("C") || board[i][j].equals("P") || board[i][j].equals("E")) ? 1 : 0;
            }
        }
        return path;
    }

    public boolean isDepleted() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].equals("C")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void print() {
        for (int i = 0; i < board.length; i++) {
//            System.out.println(Arrays.toString(board[i]));
        }
    }
}
