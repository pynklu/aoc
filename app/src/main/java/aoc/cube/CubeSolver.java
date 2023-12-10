package aoc.cube;

import aoc.Solver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeSolver extends Solver {
    private static final Pattern digitPattern = Pattern.compile("\\D");
    private static final Pattern redPattern = Pattern.compile("(\\d*) red");
    private static final Pattern greenPattern = Pattern.compile("(\\d*) green");
    private static final Pattern bluePattern = Pattern.compile("(\\d*) blue");

    public int countValidGamesInFile() throws IOException, URISyntaxException {
        var games = readFileByLine("day2.txt").stream().map(this::parseGame).toList();
        return this.countValidGames(games);
    }
    public int countValidGames(List<Game> games){
        return games.stream()
                .filter(Game::isValidGame)
                .mapToInt(Game::getNumber)
                .sum();
    }

    public int countGamePowersInFIle() throws IOException, URISyntaxException {
        var games = readFileByLine("day2.txt").stream().map(this::parseGame).toList();
        return countGamePowers(games);
    }
    public int countGamePowers(List<Game> games){
        return games.stream()
                .mapToInt(Game::getPower)
                .sum();
    }
    public Game parseGame(String line) {
        String[] lineParts = line.split(":");
        int gameNumber = Integer.parseInt(digitPattern.matcher(lineParts[0]).replaceAll(""));
        Game game = new Game(gameNumber);
        String[] rounds = lineParts[1].split(";");
        for(var round: rounds){
            var green = getResultFromPattern(greenPattern, round);
            var red = getResultFromPattern(redPattern, round);
            var blue = getResultFromPattern(bluePattern, round);
            game.addRound(new Round(green, red, blue));
        }
        return game;
    }

    private int getResultFromPattern(Pattern pattern, String line){
        Matcher matcher = pattern.matcher(line);
        return matcher.find() ? Integer.parseInt(matcher.group(1)) : 0;
    }
}
