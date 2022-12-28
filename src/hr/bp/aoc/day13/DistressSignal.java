package hr.bp.aoc.day13;

import hr.bp.aoc.BaseDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DistressSignal extends BaseDay {

    String rowParser;

    @Override
    protected String partOne(List<String> puzzleInputRowsList) {
        Integer pairsSum = 0;
        Object left;
        Object right;
        Boolean compare;

        for (int pairID = 0; pairID < puzzleInputRowsList.size() / 3; pairID = pairID + 3) {

            left = parseRow(puzzleInputRowsList.get(pairID * 3));

            right = parseRow(puzzleInputRowsList.get(pairID * 3 + 1));

            compare=compareObjects(left, right);

            if ((compare!=null) && compare) {
                System.out.println("-----------------------------------------------------");
                pairsSum = pairsSum + pairID;
            }
        }
        return String.valueOf(pairsSum);
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {
        return null;
    }

    private Boolean compareObjects(Object left, Object right) {
        Boolean result = null;
        List<Object> numberToList;

        System.out.println("Comparing objects " + left + " and " + right);

        if ((left instanceof Integer) && (right instanceof Integer)) {
            result = compareIntegers((Integer) left, (Integer) right);
        } else {
            if (left instanceof Integer) {
                numberToList = new ArrayList<Object>();
                numberToList.add(left);
                left = numberToList;
                result = compareLists((List<Object>) left, (List<Object>) right);
            }
            if (right instanceof Integer) {
                numberToList = new ArrayList<Object>();
                numberToList.add(right);
                right=numberToList;
                result = compareLists((List<Object>) left, (List<Object>) right);
            }
            if ((!(left instanceof Integer)) && (!(right instanceof Integer))) {
                result = compareLists((List) left, (List) right);
            }
        }
        return result;
    }

    private Boolean compareLists(List<Object> left, List<Object> right) {
        Boolean result = null;
        Integer unDetermined=0;

        System.out.println("Comparing lists " + left+ " and " + right);

        while ((left.size() > unDetermined) && (right.size() > unDetermined) && (result == null)) {
            result = compareObjects(left.get(unDetermined), right.get(unDetermined));
            unDetermined++;
        }
        if (result == null) {
            if (left.size() == 0 && right.size() > 0) {
                result = true;
            }
            if (left.size() > 0 && right.size() == 0) {
                result = false;
            }
        }
        return result;
    }

    private Boolean compareIntegers(Integer left, Integer right) {
        Boolean result = null;

        System.out.println("Comparing integers " + left + " and " + right);

        if (left > right) {
            result = false;
        }
        if (left < right) {
            result = true;
        }
        return result;
    }

    private List<Object> parseRow(String row) {
        rowParser = row.substring(1);
        return parseSubList();
    }

    private List<Object> parseSubList() {
        List<Object> currentDepth = new ArrayList<Object>();
        Character nextCharacter;

        while (rowParser.length() > 0) {

            nextCharacter = rowParser.charAt(0);

            if (Character.isDigit(nextCharacter)) {
                if (Character.isDigit(rowParser.charAt(1))) {
                    currentDepth.add(Integer.parseInt(rowParser.substring(0, 2)));
                    rowParser = rowParser.substring(1);
                } else {
                    currentDepth.add(Integer.parseInt(nextCharacter + ""));
                }
            }

            rowParser = rowParser.substring(1);

            if (nextCharacter == '[') {
                currentDepth.add(parseSubList());
            }

            if (nextCharacter == ']') {
                break;
            }
        }

        return currentDepth;
    }


}
