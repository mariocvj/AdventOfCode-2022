package hr.bp.aoc.day4;

import hr.bp.aoc.BaseDay;

import java.util.List;

/**
 * @author Mario Cvjetojevic
 */
public class CampCleanup extends BaseDay {
    public CampCleanup(int day) {
        super(day);
    }

    @Override
    protected String partOne(List<String> puzzleInputRowsList) {

        int solution = 0;

        for (String row : puzzleInputRowsList) {
            String[] inputNumbers = row.split("-|,");

            int leftStart = Integer.valueOf(inputNumbers[0]);
            int leftEnd = Integer.valueOf(inputNumbers[1]);
            int rightStart = Integer.valueOf(inputNumbers[2]);
            int rightEnd = Integer.valueOf(inputNumbers[3]);

            if ((leftStart <= rightStart && leftEnd >= rightEnd) || (rightStart <= leftStart && rightEnd >= leftEnd)) {
                solution++;
            }
        }

        return String.valueOf(solution);
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {

        int solution = 0;

        for (String row : puzzleInputRowsList) {
            String inputNumbers[] = row.split("-|,");

            int leftStart = Integer.valueOf(inputNumbers[0]);
            int leftEnd = Integer.valueOf(inputNumbers[1]);
            int rightStart = Integer.valueOf(inputNumbers[2]);
            int rightEnd = Integer.valueOf(inputNumbers[3]);

            if (!(leftStart > rightEnd || rightStart > leftEnd)) {
                solution++;
            }
        }

        return String.valueOf(solution);
    }

}