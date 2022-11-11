import java.util.ArrayList;
import java.util.List;

public class Ghost {
    int leftRightPosition;
    int upDownPosition;
    List<String> moves = new ArrayList<>();

    public Ghost() {
    }

    public Ghost(int leftRightPosition, int upDownPosition) {
        this.leftRightPosition = leftRightPosition;
        this.upDownPosition = upDownPosition;
    }
}
