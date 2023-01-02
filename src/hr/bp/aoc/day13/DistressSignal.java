package hr.bp.aoc.day13;

import hr.bp.aoc.BaseDay;

import java.util.ArrayList;
import java.util.List;

public class DistressSignal extends BaseDay {

    private String rowParser;

    @Override
    protected String partOne(List<String> puzzleInputRowsList) {
        Integer pairsSum = 0;
        List<Object> left;
        List<Object> right;

        for (int pairID = 0; pairID <= puzzleInputRowsList.size() / 3; pairID++) {

            left = parseRow(puzzleInputRowsList.get(pairID * 3));

            right = parseRow(puzzleInputRowsList.get(pairID * 3 + 1));

            if (isOrderedOkList(left, right)) {
                System.out.println("RIGHT ORDER\n");
                pairsSum = pairsSum + (pairID + 1);
            } else {
                System.out.println("NOT RIGHT ORDER\n");
            }
        }
        return String.valueOf(pairsSum);
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {
        return null;
    }

    private Boolean isOrderedOkList(List<Object> left, List<Object> right) {
        Boolean result = null;
        int i = 0;

        System.out.println("Comparing lists " + left + " and " + right);

        while ((left.size() > i) && (right.size() > i) && (result == null)) {

            if ((left.get(i) instanceof Integer) && (right.get(i) instanceof Integer)) {

                result = isOrderedOkInt((Integer) left.get(i), ((Integer) right.get(i)));

            } else {

                convertIntegerToList(left, i);
                convertIntegerToList(right, i);

                result = isOrderedOkList((List<Object>) left.get(i), (List<Object>) right.get(i));
            }
            i++;
        }

        if (result == null) {
            result = isLeftShorter(left, right);
        }

        return result;
    }

    private static void convertIntegerToList(List<Object> container, int index) {
        List<Object> numberAsList;

        if (container.get(index) instanceof Integer) {
            numberAsList = new ArrayList<>();
            numberAsList.add(container.get(index));
            container.set(index, numberAsList);
        }
    }

    private Boolean isLeftShorter(List<Object> left, List<Object> right) {

        if (left.size() < right.size()) {
            return true;
        }
        if (left.size() > right.size()) {
            return false;
        }
        return null;
    }


    private Boolean isOrderedOkInt(Integer left, Integer right) {

        System.out.println("Comparing integers " + left + " and " + right);

        if (left > right) {
            return false;
        }
        if (left < right) {
            return true;
        }
        return null;
    }

    private List<Object> parseRow(String row) {
        rowParser = row.substring(1);
        return parseSubList();
    }

    private List<Object> parseSubList() {
        List<Object> subList = new ArrayList<>();
        char nextChar;

        while (rowParser.length() > 0) {

            nextChar = rowParser.charAt(0);

            if (Character.isDigit(nextChar)) {
                parseNumber(subList, nextChar);
            }

            rowParser = rowParser.substring(1);

            if (nextChar == '[') {
                subList.add(parseSubList());
            }

            if (nextChar == ']') {
                break;
            }
        }

        return subList;
    }

    private void parseNumber(List<Object> list, char number) {
        if (Character.isDigit(rowParser.charAt(1))) {

            list.add(Integer.parseInt(rowParser.substring(0, 2)));
            rowParser = rowParser.substring(1);

        } else {
            list.add(Integer.parseInt(number + ""));
        }
    }
}
