package aoc.cube;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CubeSolverTest {
    final String[] mockInput = new String[]{
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
    };
    final CubeSolver solver = new CubeSolver();

    @Test
    void testLineParser(){
        Game expectedGame = new Game(1);
        expectedGame.addRound(new Round(0,4,3));
        expectedGame.addRound(new Round(2, 1, 6));
        expectedGame.addRound(new Round(2, 0, 0));
        var result = solver.parseGame(mockInput[0]);
        assertThat(result).isNotNull();
        assertThat(result.getNumber()).isEqualTo(expectedGame.getNumber());
        assertThat(result.getRounds()).isEqualTo(expectedGame.getRounds());
    }

    @Test
    void testGameCounter(){
        List<Game> games = Arrays.stream(mockInput).map(solver::parseGame).toList();
        int result = solver.countValidGames(games);
        assertThat(games.get(0).isValidGame()).isEqualTo(true);
        assertThat(games.get(1).isValidGame()).isEqualTo(true);
        assertThat(games.get(2).isValidGame()).isEqualTo(false);
        assertThat(games.get(3).isValidGame()).isEqualTo(false);
        assertThat(games.get(4).isValidGame()).isEqualTo(true);
        assertThat(result).isEqualTo(8);
    }

    @Test
    void testPowers() {
        List<Game> games = Arrays.stream(mockInput).map(solver::parseGame).toList();
        int result = solver.countGamePowers(games);
        assertThat(games.get(0).getPower()).isEqualTo(48);
        assertThat(games.get(1).getPower()).isEqualTo(12);
        assertThat(games.get(2).getPower()).isEqualTo(1560);
        assertThat(games.get(3).getPower()).isEqualTo(630);
        assertThat(games.get(4).getPower()).isEqualTo(36);
        assertThat(result).isEqualTo(2286);
    }
}