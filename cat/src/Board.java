import java.util.ArrayList;
import java.util.Arrays;
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
        print();
        for (int i = 0; i < ghosts.size(); i++) {
            moveGhost(ghosts.get(i), index);
        }
        return movePacMan(moves.get(index));
    }

    public void moveGhost(Ghost ghost, int index) {
        System.out.print("Ghost " + ghost.upDownPosition + " " + ghost.leftRightPosition + " -> ");
        switch (ghost.moves.get(index)) {
            case "L" -> ghost.leftRightPosition--;
            case "R" -> ghost.leftRightPosition++;
            case "U" -> ghost.upDownPosition--;
            case "D" -> ghost.upDownPosition++;
        }
        System.out.println(ghost.upDownPosition + " " + ghost.leftRightPosition);
    }

    public MoveResult movePacMan(String move) {
        System.out.print("PacMan " + upDownPosition + " " + leftRightPosition + " -> ");
        switch (move) {
            case "L" -> leftRightPosition--;
            case "R" -> leftRightPosition++;
            case "U" -> upDownPosition--;
            case "D" -> upDownPosition++;
        }
        System.out.println(upDownPosition + " " + leftRightPosition);
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

    public void print() {
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}
