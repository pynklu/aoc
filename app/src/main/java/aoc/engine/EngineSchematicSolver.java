package aoc.engine;

import aoc.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class EngineSchematicSolver extends Solver {
    //input naar 2d array
    //input naar 2d array met visited status
    //rij per rij symbolen zoeken
    //symbool --> add omringende cijfers if not visited & zet visited cijfers op true

    private final Pattern symbolPatern = Pattern.compile("[^\\d\\.]");
    private List<String> schematic = new ArrayList<>();
    private boolean[][] visited;
    private int maxX = 0, maxY = 0;
    private final List<Integer> partNumbers = new ArrayList<>();
    private final List<Integer> gearRatios = new ArrayList<>();

    public void initialiseMatrix(List<String> lines) {
        this.schematic = lines;
        visited = new boolean[lines.size()][lines.get(0).length()];
        //visited = lines.stream().map(line -> new boolean[line.length()]).toList();
        this.maxY = lines.size() - 1;
        this.maxX = lines.get(0).length() - 1;
    }

    public List<Integer> getSymbolsInLine(String line){
        Matcher matcher = symbolPatern.matcher(line);
        var out = new ArrayList<Integer>();
        while (matcher.find()) {
            out.add(matcher.start());
        } return out;
    }

    private int[] rangeWithMax(int baseValue,int maxValue) {
        return IntStream.rangeClosed(
                baseValue == 0 ? 0 : baseValue - 1,
                baseValue == maxValue ? maxValue : baseValue + 1
        ).toArray();
    }
    public void checkNeighboursForPosition(int x, int y) {
        var xToCheck = rangeWithMax(x, maxX);
        var yToCheck = rangeWithMax(y, maxY);
        boolean isGear = '*' == (schematic.get(y).charAt(x));
        var numbers = new ArrayList<Integer>();
        for(int xpos : xToCheck){
            for(int ypos: yToCheck){
                if(visited[ypos][xpos] || (xpos == x && ypos == y)) continue;
                if( Character.isDigit(schematic.get(ypos).charAt(xpos))){
                    numbers.add(buildFullNumber(xpos, ypos));
                }
                visited[ypos][xpos] = true;
            }
        }
        if(isGear && numbers.size() == 2){
            gearRatios.add(numbers.get(0) * numbers.get(1));
        }
        partNumbers.addAll(numbers);
    }

    public int buildFullNumber(int x, int y){
        char[] chars = schematic.get(y).toCharArray();
        int startIndex = x, endIndex = x;
        while(startIndex > 0 && Character.isDigit(chars[startIndex-1])) {
            startIndex--;
        }
        while(endIndex < chars.length-1  && Character.isDigit(chars[endIndex+1])) {
            endIndex++;
        }
        for(int i = startIndex; i <= endIndex; i++){
            visited[y][i] = true;
        }
        return Integer.parseInt(schematic.get(y).substring(startIndex, endIndex + 1));
    }

    public int solveSchematic() {
        for (int yPos = 0; yPos < schematic.size(); yPos++) {
            for(int xPos: getSymbolsInLine(schematic.get(yPos))){
                checkNeighboursForPosition(xPos, yPos);
            }
        }
        return this.partNumbers.stream().mapToInt(Integer::intValue).sum();
    }

    public int solveGearRatios() {
        for (int yPos = 0; yPos < schematic.size(); yPos++) {
            for(int xPos: getSymbolsInLine(schematic.get(yPos))){
                checkNeighboursForPosition(xPos, yPos);
            }
        }
        return this.gearRatios.stream().mapToInt(Integer::intValue).sum();
    }
}
