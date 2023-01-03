package hr.bp.aoc.day10;

import hr.bp.aoc.BaseDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CathodeRayTube extends BaseDay {
    Integer currentCycle;
    Integer signalSum;
    Integer xRegister;
    List<Character> printedImage;

    @Override
    protected String partOne(List<String> puzzleInputRowsList) {
        executeProgram(puzzleInputRowsList);
        return String.valueOf(signalSum);
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {
        printedImage = new ArrayList<Character>();
        String imageString = "";

        executeProgram(puzzleInputRowsList);

        for (int i = 0; i < printedImage.size(); i++) {
            if ((i % 40) == 0) {
                imageString = imageString + "\n";
            }
            imageString = imageString + printedImage.get(i);
        }
        // E G J B G C F K
        return imageString;
    }

    private void executeProgram(List<String> puzzleInputRowsList) {
        currentCycle = 0;
        signalSum = 0;
        xRegister = 1;

        for (String currentCommand : puzzleInputRowsList) {
            if (currentCommand.substring(0, 4).equals("noop")) {
                commandNoop();
            }
            if (currentCommand.substring(0, 4).equals("addx")) {
                commandAddX(Integer.parseInt(currentCommand.substring(5)));
            }
        }
    }

    private void commandAddX(Integer value) {
        nextCycle();
        nextCycle();
        xRegister = xRegister + value;
    }

    private void commandNoop() {
        nextCycle();
    }

    private void nextCycle() {

        if (Objects.nonNull(printedImage)) {
            if (Math.abs((currentCycle % 40) - xRegister) < 2) {
                printedImage.add('#');
            } else {
                printedImage.add('.');
            }
        }

        currentCycle++;

        if (currentCycle == 20 ||
                currentCycle == 60 ||
                currentCycle == 100 ||
                currentCycle == 140 ||
                currentCycle == 180 ||
                currentCycle == 220) {
            signalSum = signalSum + currentCycle * xRegister;
        }

    }

}
