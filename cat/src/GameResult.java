public class GameResult {
    int coinsCollected;
    boolean hasDied;

    public GameResult(int coinsCollected, boolean hasDied) {
        this.coinsCollected = coinsCollected;
        this.hasDied = hasDied;
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "coinsCollected=" + coinsCollected +
                ", hasDied=" + hasDied +
                '}';
    }
}
