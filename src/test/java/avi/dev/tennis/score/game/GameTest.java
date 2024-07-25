package avi.dev.tennis.score.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class GameTest {

    @Test
    void should_player_a_wins_when_player_a_scores_and_have_40_point_and_game_not_in_deuce() {
        //Given
        var playerA = new Player("A", 3);
        var playerB = new Player("B", 0);

        var gameScorePrinter = mock(GameScorePrinter.class);
        var game = spy(new Game(playerA, playerB, gameScorePrinter, false));

        //When
        game.updateScore("A");

        //Then
        assertEquals(0, playerB.getPoint());
        assertFalse(playerB.isWinner());

        assertEquals(40, playerA.getPoint());
        assertTrue(playerA.isWinner());

        then(gameScorePrinter)
                .should()
                .print(same(playerA), same(playerB));
    }

    @Test
    void should_player_b_loses_advantage_when_player_a_scores_and_game_in_deuce() {
        //Given
        var playerA = new Player("A", 3);
        var playerB = new Player("B", 3);
        playerB.setAdvantage(true);

        var gameScorePrinter = mock(GameScorePrinter.class);
        var game = spy(new Game(playerA, playerB, gameScorePrinter, true));

        //When
        game.updateScore("A");

        //Then

        assertEquals(40, playerA.getPoint());
        assertFalse(playerA.hasAdvantage());
        assertFalse(playerA.isWinner());

        assertEquals(40, playerB.getPoint());
        assertFalse(playerB.hasAdvantage());
        assertFalse(playerB.isWinner());

        assertTrue(game.inDeuce());

        then(gameScorePrinter)
                .should()
                .print(same(playerA), same(playerB));
    }

    @Test
    void should_player_a_get_advantage_when_player_a_scores_and_game_in_deuce() {
        //Given
        var playerA = new Player("A", 3);
        var playerB = new Player("B", 3);

        var gameScorePrinter = mock(GameScorePrinter.class);
        var game = spy(new Game(playerA, playerB, gameScorePrinter, true));

        //When
        game.updateScore("A");

        //Then

        assertEquals(40, playerA.getPoint());
        assertTrue(playerA.hasAdvantage());
        assertFalse(playerA.isWinner());

        assertEquals(40, playerB.getPoint());
        assertFalse(playerB.hasAdvantage());
        assertFalse(playerB.isWinner());

        assertFalse(game.inDeuce());

        then(gameScorePrinter)
                .should()
                .print(same(playerA), same(playerB));
    }

    @Test
    void should_increment_player_a_score_when_player_a_scores_and_does_not_have_40_point_and_game_not_in_deuce() {
        //Given
        var playerA = new Player("A");
        var playerB = new Player("B");

        var gameScorePrinter = mock(GameScorePrinter.class);
        var game = new Game(playerA, playerB, gameScorePrinter, false);

        //When
        game.updateScore("A");

        //Then
        assertEquals(15, playerA.getPoint());
        assertFalse(playerA.hasAdvantage());
        assertFalse(playerA.isWinner());

        assertEquals(0, playerB.getPoint());
        assertFalse(playerB.hasAdvantage());
        assertFalse(playerB.isWinner());

        assertFalse(game.inDeuce());

        then(gameScorePrinter)
                .should()
                .print(same(playerA), same(playerB));
    }
}