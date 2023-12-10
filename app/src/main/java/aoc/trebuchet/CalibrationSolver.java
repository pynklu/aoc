/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package aoc.trebuchet;

import aoc.Solver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class CalibrationSolver extends Solver {
    public int getSimpleCalibrationValue() throws IOException, URISyntaxException {
        return readFileByLine("day1.txt").stream().mapToInt(this::getSimpleNumberFromLine).sum();
    }

    public int getSimpleNumberFromLine(String line) {
        boolean firstFound = false, lastFound = false;
        int firstIndex = -1, lastIndex = -1;
        for (var i = 0; i < line.length(); i++) {
            if(!firstFound) {
                if(Character.isDigit(line.charAt(i))) {
                    firstFound = true;
                    firstIndex = i;
                }
            }
            if(!lastFound) {
                if(Character.isDigit(line.charAt(line.length() - i - 1))) {
                    lastFound = true;
                    lastIndex = line.length() - i -1;
                }
            }
            if(firstFound && lastFound){
                return Integer.parseInt("" + line.charAt(firstIndex) + line.charAt(lastIndex));
            }
        }  return -1;
    }

    public int getComplexCalibrationValue() throws IOException, URISyntaxException {
        return readFileByLine("day1.txt").stream().map(this::getComplexNumberFromLine).reduce(0, Integer::sum);
    }

    public int getComplexNumberFromLine(String line) {
        var numbers = new String[]{
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };
        var lowestIndex = line.length();
        var highestIndex = -1;
        var firstNumber = "";
        var lastNumber = "";
        for (String curr : numbers) {
            var firstIndex = line.indexOf(curr);
            if (firstIndex != -1 && firstIndex < lowestIndex) {
                lowestIndex = firstIndex;
                firstNumber = curr;
            }

            var lastIndex = line.lastIndexOf(curr);
            if (lastIndex > highestIndex) {
                highestIndex = lastIndex;
                lastNumber = curr;
            }
        }

        firstNumber = firstNumber.length() > 1 ?
                String.valueOf(List.of(numbers).indexOf(firstNumber) + 1)
                : firstNumber;
        lastNumber = lastNumber.length() > 1 ?
                String.valueOf(List.of(numbers).indexOf(lastNumber) + 1)
                : lastNumber;
        return Integer.parseInt(firstNumber + lastNumber);
    }
}