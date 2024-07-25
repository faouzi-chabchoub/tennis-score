package avi.dev.tennis.score.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    @ParameterizedTest
    @CsvSource({"0,15", "1,30", "2,40"})
    void should_increment_point(int currentPointIndex, int expectedPoint) {
        //Given
        var player = new Player("A", currentPointIndex);

        //When
        player.incrementPoint();

        //Then
        assertEquals(expectedPoint, player.getPoint());
    }

    @Test
    void should_not_increment_point_when_player_have_advantage() {
        //Given
        var player = new Player("A", 3);
        player.setAdvantage(true);

        //When
        player.incrementPoint();

        //Then
        assertEquals(40, player.getPoint());
    }

    @Test
    void should_not_increment_point_when_player_have_40_point() {
        //Given
        var player = new Player("A", 3);

        //When
        player.incrementPoint();

        //Then
        assertEquals(40, player.getPoint());
    }
}