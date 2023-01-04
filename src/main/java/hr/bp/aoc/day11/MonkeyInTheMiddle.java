package hr.bp.aoc.day11;

import hr.bp.aoc.BaseDay;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MonkeyInTheMiddle extends BaseDay {
    public MonkeyInTheMiddle(int day) {
        super(day);
    }

    private List<Monkey> allMonkeys;
    private String puzzlePart;
    private BigInteger sharedMultiplier;


    private class Monkey {
        List<BigInteger> heldItems;

        Integer divisionTest;
        Integer trueTarget;
        Integer falseTarget;
        Integer inspections;

        public Monkey(List<BigInteger> heldItems, Integer divisionTest, Integer trueTarget, Integer falseTarget) {
            this.heldItems = heldItems;
            this.divisionTest = divisionTest;
            this.trueTarget = trueTarget;
            this.falseTarget = falseTarget;
            this.inspections = 0;
        }

        public Integer getInspections() {
            return inspections;
        }

        private Integer monkeyID() {
            return allMonkeys.indexOf(this);
        }

        private void takeTurn() {
            while (heldItems.size() > 0) {
                this.inspectAndThrowNextItem();
            }
        }

        private BigInteger optimiseBigWorryLevels(BigInteger item) {
            return item.mod(sharedMultiplier);
        }

        private void inspectAndThrowNextItem() {
            BigInteger item = heldItems.get(0);
            heldItems.remove(0);
            item = this.monkeyOperation(item);

            if (puzzlePart.equals("partOne")) {
                item = BigDecimal.valueOf(Math.floor(item.floatValue() / 3.0)).toBigInteger();
            }

            if (puzzlePart.equals("partTwo")) {
                item = optimiseBigWorryLevels(item);
            }

            if ((item.mod(BigInteger.valueOf(divisionTest)).intValue() == 0)) {
                allMonkeys.get(trueTarget).heldItems.add(item);
            } else {
                allMonkeys.get(falseTarget).heldItems.add(item);
            }
            this.inspections++;
        }

        private BigInteger monkeyOperation(BigInteger oldValue) {
            BigInteger newValue = null;

            switch (this.monkeyID()) {
                case 0->  newValue = oldValue.multiply(BigInteger.valueOf(11));
                case 1->  newValue = oldValue.add(BigInteger.valueOf(4));
                case 2->                    newValue = oldValue.multiply(oldValue);
                case 3->                    newValue = oldValue.add(BigInteger.valueOf(2));
                case 4->                    newValue = oldValue.add(BigInteger.valueOf(3));
                case 5->                    newValue = oldValue.add(BigInteger.valueOf(1));
                case 6->  newValue = oldValue.add(BigInteger.valueOf(5));
                case 7->                    newValue = oldValue.multiply(BigInteger.valueOf(19));
            }
            return newValue;
        }


    }

    @Override
    protected String partOne(List<String> puzzleInputRowsList) {
        List<Integer> inspectionAmmounts;
        initialiseMonkeyData(puzzleInputRowsList);
        puzzlePart = "partOne";

        for (int i = 0; i < 20; i++) {
            simulateRound();
        }

        inspectionAmmounts = allMonkeys.stream().map(Monkey::getInspections).sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        return String.valueOf(inspectionAmmounts.get(0) * inspectionAmmounts.get(1));
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {
        List<Integer> inspectionAmmounts;
        initialiseMonkeyData(puzzleInputRowsList);
        puzzlePart = "partTwo";

        for (int i = 0; i < 10000; i++) {
            System.out.println("Simulating round " + i);
            simulateRound();
        }

        inspectionAmmounts = allMonkeys.stream().map(Monkey::getInspections).sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        return String.valueOf(BigInteger.valueOf(inspectionAmmounts.get(0)).multiply(BigInteger.valueOf(inspectionAmmounts.get(1))));
    }


    private void simulateRound() {
        for (Monkey monkey : allMonkeys) {
            monkey.takeTurn();
        }
    }

    private void initialiseMonkeyData(List<String> puzzleInputRowsList) {
        int divisionTest;
        int trueTarget;
        int falseTarget;
        List<BigInteger> heldItems;
        String heldItemsInput;

        allMonkeys = new ArrayList<>();
        sharedMultiplier = BigInteger.valueOf(1);

        for (int i = 0; i < puzzleInputRowsList.size(); i = i + 7) {

            heldItemsInput = puzzleInputRowsList.get(i + 1).replaceAll("[a-zA-Z: ]", "");
            heldItems = Arrays.stream(heldItemsInput.split(",")).map(Integer::parseInt).map(BigInteger::valueOf).collect(Collectors.toList());

            divisionTest = Integer.parseInt(puzzleInputRowsList.get(i + 3).replaceAll("[a-zA-Z: ]", ""));

            trueTarget = Integer.parseInt(puzzleInputRowsList.get(i + 4).replaceAll("[a-zA-Z: ]", ""));

            falseTarget = Integer.parseInt(puzzleInputRowsList.get(i + 5).replaceAll("[a-zA-Z: ]", ""));

            allMonkeys.add(new Monkey(heldItems, divisionTest, trueTarget, falseTarget));

            sharedMultiplier = sharedMultiplier.multiply(BigInteger.valueOf(divisionTest));
        }
    }
}






























