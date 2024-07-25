package avi.dev.tennis.score.game;

import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.spy;

class GameScorePrinterTest {

    @Test
    void should_print_winner_message_when_player_a_is_the_winner() {
        //Given
        var playerA = new Player("A");
        playerA.setWinner(true);
        var playerB = new Player("B");

        var gameScorePrinter = spy(new GameScorePrinter());

        //When
        gameScorePrinter.print(playerA, playerB);

        //Then
        then(gameScorePrinter)
                .should()
                .print("Player A wins the game");
    }

    @Test
    void should_print_winner_message_when_player_b_is_the_winner() {
        //Given
        var playerA = new Player("A");
        var playerB = new Player("B");
        playerB.setWinner(true);

        var gameScorePrinter = spy(new GameScorePrinter());

        //When
        gameScorePrinter.print(playerA, playerB);

        //Then
        then(gameScorePrinter)
                .should()
                .print("Player B wins the game");
    }

    @Test
    void should_print_advantage_message_when_player_a_has_advantage() {
        //Given
        var playerA = new Player("A", 3);
        playerA.setAdvantage(true);
        var playerB = new Player("B", 3);

        var gameScorePrinter = spy(new GameScorePrinter());

        //When
        gameScorePrinter.print(playerA, playerB);

        //Then
        then(gameScorePrinter)
                .should()
                .print("Player A : advantage / Player B : 40");
    }

    @Test
    void should_print_score_message() {
        //Given
        var playerA = new Player("A", 1);
        var playerB = new Player("B", 0);

        var gameScorePrinter = spy(new GameScorePrinter());

        //When
        gameScorePrinter.print(playerA, playerB);

        //Then
        then(gameScorePrinter)
                .should()
                .print("Player A : 15 / Player B : 0");
    }
}