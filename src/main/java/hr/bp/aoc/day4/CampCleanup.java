package hr.bp.aoc.day4;

import hr.bp.aoc.BaseDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author Mario Cvjetojevic
 */
public class CampCleanup extends BaseDay {

    protected static void CampCleanup() {
    }


    @Override
    protected String partOne(List<String> puzzleInputRowsList) {

        Integer solution = 0;

        for (String row : puzzleInputRowsList){
            String inputNumbers[] = row.split("-|,");

            Integer leftStart = Integer.valueOf(inputNumbers[0]);
            Integer leftEnd = Integer.valueOf(inputNumbers[1]);
            Integer rightStart = Integer.valueOf(inputNumbers[2]);
            Integer rightEnd = Integer.valueOf(inputNumbers[3]);

            if ((leftStart <= rightStart && leftEnd >= rightEnd) || (rightStart <= leftStart && rightEnd >= leftEnd)){
                solution++;
            }
        }

        return solution.toString();
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {

        Integer solution = 0;

        for (String row : puzzleInputRowsList){
            String inputNumbers[] = row.split("-|,");

            Integer leftStart = Integer.valueOf(inputNumbers[0]);
            Integer leftEnd = Integer.valueOf(inputNumbers[1]);
            Integer rightStart = Integer.valueOf(inputNumbers[2]);
            Integer rightEnd = Integer.valueOf(inputNumbers[3]);

            if (!(leftStart > rightEnd || rightStart > leftEnd)){
                solution++;
            }
        }

        return solution.toString();
    }

}