package avi.dev.tennis.score.game;

import java.util.Optional;

public class GameScorePrinter {

    private static final String ADVANTAGE = "advantage";
    private static final String PLAYER_SCORE_TEMPLATE = "Player %s : %s";
    private static final String GAME_SCORE_TEMPLATE = "%s / %s";
    private static final String PLAYER_WINS_THE_GAME_TEMPLATE = "Player %s wins the game";

    public void print(final Player player1, final Player player2) {
        findWinner(player1, player2)
                .ifPresentOrElse(
                        winner -> print(PLAYER_WINS_THE_GAME_TEMPLATE.formatted(winner.getName())),
                        () -> print(GAME_SCORE_TEMPLATE.formatted(playerScore(player1), playerScore(player2)))
                );
    }

    private Optional<Player> findWinner(final Player player1, final Player player2) {
        if (player1.isWinner()) {
            return Optional.of(player1);
        }
        if (player2.isWinner()) {
            return Optional.of(player2);
        }
        return Optional.empty();
    }

    private String playerScore(final Player player) {
        return PLAYER_SCORE_TEMPLATE.formatted(player.getName(), player.hasAdvantage() ? ADVANTAGE : player.getPoint());
    }

    void print(final String message) {
        System.out.println(message);
    }
}
