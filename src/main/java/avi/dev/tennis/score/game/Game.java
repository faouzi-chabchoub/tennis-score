package avi.dev.tennis.score.game;

public class Game {

    private final Player player1;
    private final Player player2;
    private final GameScorePrinter gameScorePrinter;
    private boolean deuce;

    public Game(final Player player1, final Player player2) {
        this(player1, player2, new GameScorePrinter(), false);
    }

    Game(final Player player1, final Player player2, final GameScorePrinter gameScorePrinter, final boolean deuce) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameScorePrinter = gameScorePrinter;
        this.deuce = deuce;
    }

    public void updateScore(final String scoredPlayerName) {
        var scoredPlayer = scoredPlayerName.equals(player1.getName()) ? player1 : player2;
        var opponentPlayer = scoredPlayerName.equals(player1.getName()) ? player2 : player1;

        updateScore(scoredPlayer, opponentPlayer);

        deuce = inDeuce();

        gameScorePrinter.print(player1, player2);
    }

    private void updateScore(final Player scoredPlayer, final Player opponentPlayer) {
        if (opponentPlayer.hasAdvantage()) {
            opponentPlayer.setAdvantage(false);
            return;
        }

        if (deuce) {
            scoredPlayer.setAdvantage(true);
            return;
        }

        if (scoredPlayer.getPoint() == 40) {
            scoredPlayer.setWinner(true);
            return;
        }

        scoredPlayer.incrementPoint();
    }

    boolean inDeuce() {
        return !player1.hasAdvantage() && player1.getPoint() == 40
                && !player2.hasAdvantage() && player2.getPoint() == 40;
    }
}
