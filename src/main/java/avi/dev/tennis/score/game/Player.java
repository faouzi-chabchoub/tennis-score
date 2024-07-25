package avi.dev.tennis.score.game;

public class Player {

    private static final int[] SCORE_TABLE = {0, 15, 30, 40};

    private final String name;
    private int pointIndex;
    private boolean advantage;
    private boolean winner;

    public Player(final String name) {
        this(name, 0);
    }

    Player(final String name, final int pointIndex) {
        this.name = name;
        this.pointIndex = Math.clamp(pointIndex, 0, 3);
    }

    public String getName() {
        return this.name;
    }

    public int getPoint() {
        return SCORE_TABLE[pointIndex];
    }

    public boolean hasAdvantage() {
        return this.advantage;
    }

    public void setAdvantage(final boolean advantage) {
        this.advantage = advantage;
    }

    public void setWinner(final boolean winner) {
        this.winner = winner;
    }

    public boolean isWinner() {
        return this.winner;
    }

    public void incrementPoint() {
        if (advantage || pointIndex == 3) {
            return;
        }
        pointIndex++;
    }
}
