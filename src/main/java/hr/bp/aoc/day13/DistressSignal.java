package hr.bp.aoc.day13;

import hr.bp.aoc.BaseDay;

import java.util.*;

public class DistressSignal extends BaseDay {
    public DistressSignal(int day) {
        super(day);
    }

    private String rowParser;


    @Override
    protected String partOne(List<String> puzzleInputRowsList) {
        Integer pairsSum = 0;
        SignalPacket left;
        SignalPacket right;

        for (int pairID = 0; pairID <= puzzleInputRowsList.size() / 3; pairID++) {

            left = parseRow(puzzleInputRowsList.get(pairID * 3));

            right = parseRow(puzzleInputRowsList.get(pairID * 3 + 1));

            if (left.compareTo(right) < 0) {
                pairsSum = pairsSum + (pairID + 1);
            }
        }
        return String.valueOf(pairsSum);
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {

        SortedSet<SignalPacket> packets = new TreeSet<>();

        for (String row : puzzleInputRowsList) {
            if (!(row.equals(""))) {
                packets.add(parseRow(row));
            }
        }
        return String.valueOf(getDecoderKey(packets));
    }

    private int getDecoderKey(SortedSet<SignalPacket> packets) {
        List<SignalPacket> list;
        SignalPacket divider1 = new SignalPacket(2);
        SignalPacket divider2 = new SignalPacket(6);

        packets.add(divider1);
        packets.add(divider2);

        list = new ArrayList<>(packets);

        return (list.indexOf(divider1) + 1) * (list.indexOf(divider2) + 1);
    }

    private SignalPacket parseRow(String row) {
        rowParser = row.substring(1);
        return parseSignalPacket();
    }

    private SignalPacket parseSignalPacket() {
        List<Object> subList = new ArrayList<>();
        char nextChar;

        while (rowParser.length() > 0) {

            nextChar = rowParser.charAt(0);

            if (Character.isDigit(nextChar)) {
                parseNumber(subList, nextChar);
            }

            rowParser = rowParser.substring(1);

            if (nextChar == '[') {
                subList.add(parseSignalPacket());
            }

            if (nextChar == ']') {
                break;
            }
        }

        return new SignalPacket(subList);
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
