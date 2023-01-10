package hr.bp.aoc.day5;

import hr.bp.aoc.BaseDay;

import java.util.*;

/**
 * @author Mario Cvjetojevic
 */
public class SupplyStacks extends BaseDay {
    public SupplyStacks(int day) {
        super(day);
    }

    private List<Stack<Character>> cratePositions;
    private int puzzleInputHeight = 0;


    @Override
    protected String partOne(List<String> puzzleInputRowsList) {
        List<String> moveCommand;

        readCratePositions(puzzleInputRowsList);

        for (int i = puzzleInputHeight + 2; i < puzzleInputRowsList.size(); i++) {
            moveCommand = new ArrayList<>(Arrays.asList(puzzleInputRowsList.get(i).split(" ")));
            System.out.println(moveCommand);

            moveStackOfCrates(
                    Integer.parseInt(moveCommand.get(1)),
                    Integer.parseInt(moveCommand.get(3)),
                    Integer.parseInt(moveCommand.get(5)));
        }

        return readTopOfAllStacks();
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {
        List<String> moveCommand;

        readCratePositions(puzzleInputRowsList);

        for (int i = puzzleInputHeight + 2; i < puzzleInputRowsList.size(); i++) {
            moveCommand = new ArrayList<>(Arrays.asList(puzzleInputRowsList.get(i).split(" ")));
            System.out.println(moveCommand);

            moveStackOfCratesAtOnce(
                    Integer.parseInt(moveCommand.get(1)),
                    Integer.parseInt(moveCommand.get(3)),
                    Integer.parseInt(moveCommand.get(5)));
        }

        return readTopOfAllStacks();
    }


    private String readTopOfAllStacks() {
        String topCrates = "";
        for (Stack<Character> stack : cratePositions) {
            topCrates = topCrates + stack.pop();
        }
        return topCrates;
    }


    private void moveStackOfCrates(int crateAmmount, int sourceStack, int destinationStack) {
        for (int i = 0; i < crateAmmount; i++) {
            cratePositions.get(destinationStack - 1).push(cratePositions.get(sourceStack - 1).pop());
        }
    }


    private void moveStackOfCratesAtOnce(int crateAmmount, int sourceStack, int destinationStack) {
        LinkedList<Character> movedCrates = new LinkedList<>();
        for (int i = 0; i < crateAmmount; i++) {
            movedCrates.addFirst(cratePositions.get(sourceStack - 1).pop());
        }
        for (char crate : movedCrates) {
            cratePositions.get(destinationStack - 1).push(crate);
        }
    }


    private void readCratePositions(List<String> puzzleInputRowsList) {
        int puzzleInputWidth;
        int stackNumber = 1;
        cratePositions = new ArrayList<>();
        char crate;

        //read highest stack height
        for (String row : puzzleInputRowsList) {
            if (row.contains("1")) {
                break;
            }
            puzzleInputHeight++;
        }
        puzzleInputWidth = puzzleInputRowsList.get(puzzleInputHeight - 1).length();

        //read the number of stacks
        for (int i = 0; i < puzzleInputWidth - 1; i++) {
            if (puzzleInputRowsList.get(puzzleInputHeight).charAt(i) == (char) (stackNumber + '0')) {
                cratePositions.add(new Stack<>());
                stackNumber++;
            }
        }

        //load crate positions
        for (int i = puzzleInputHeight - 1; i >= 0; i--) {
            int z = 0;
            for (int y = 1; y < puzzleInputWidth; y = y + 4) {

                crate = puzzleInputRowsList.get(i).charAt(y);
                if (Character.isUpperCase(crate)) {
                    cratePositions.get(z).push(crate);
                }
                z++;
            }
        }
    }


}
