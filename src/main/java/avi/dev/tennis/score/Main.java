package avi.dev.tennis.score;

import avi.dev.tennis.score.game.Game;
import avi.dev.tennis.score.game.Player;

public class Main {

    public static void main(final String[] args) {
        Player playerA = new Player("A");
        Player playerB = new Player("B");

        Game game = new Game(playerA, playerB);
        for (String scoredPlayer : "ABABAA".split("")) {
            game.updateScore(scoredPlayer);
        }
    }
}