package hr.bp.aoc.day15;

import hr.bp.aoc.BaseDay;

import java.math.BigInteger;
import java.util.ArrayList;

import hr.bp.aoc.Point;

import java.util.List;

public class BeaconExclusionZone extends BaseDay {

    private List<SenzorBeaconPair> senzorBeaconPairs;
    private Character[] scanedRow;
    private int scanedRowY;
    private int scanedRowOffset;

    public class SenzorBeaconPair {
        Point senzor;

        public SenzorBeaconPair(Point senzor, Point beacon) {
            this.senzor = senzor;
            this.beacon = beacon;
        }

        Point beacon;

        protected int getManhattanDistance() {
            return Math.abs(senzor.x - beacon.x) + Math.abs(senzor.y - beacon.y);
        }

        protected void scanPositionsInRowPartOne(int row) {
            int scanWidth = Math.max(0, this.getManhattanDistance() - Math.abs(senzor.y - row) + 1);
            System.out.println("scanWidth: " + scanWidth);

            for (int x = senzor.x - scanWidth; x < senzor.x + scanWidth - 1; x++) {
                if (scanedRow[x + scanedRowOffset].equals('.')) {
                    scanedRow[x + scanedRowOffset] = 'X';
                }
            }
        }
    }

    @Override
    protected String partOne(List<String> puzzleInputRowsList) {

        initialiseSenzorBeaconPairs(puzzleInputRowsList);

        initialisePartOneScanedRow();

        for (SenzorBeaconPair senzorBeacon : senzorBeaconPairs) {
            senzorBeacon.scanPositionsInRowPartOne(scanedRowY);
        }

        return String.valueOf(getNoBeaconPositionCount());
    }

    private int getNoBeaconPositionCount() {
        int scanned = -1;

        for (Character position : scanedRow) {
            if (position.equals('X')) {
                scanned++;
            }
        }
        return scanned;
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {
        Point missingBeacon;

        initialiseSenzorBeaconPairs(puzzleInputRowsList);

        AlgorithmExecuter algorithm = new AlgorithmExecuter();

        missingBeacon = algorithm.getMissingBeaconTimed(senzorBeaconPairs);

        return String.valueOf(getTuningFrequency(missingBeacon));
    }

    private BigInteger getTuningFrequency(Point location) {

        BigInteger frequency = BigInteger.valueOf(location.x).multiply(BigInteger.valueOf(4000000));

        return frequency.add(BigInteger.valueOf(location.y));
    }

    private void initialisePartOneScanedRow() {
        scanedRowY = 2000000;
        scanedRowOffset = 2000000;
        scanedRow = new Character[10000000];
        for (int i = 0; i < 10000000; i++) {
            scanedRow[i] = '.';
        }
    }

    private void initialiseSenzorBeaconPairs(List<String> puzzleInputRowsList) {

        SenzorBeaconPair pair;
        senzorBeaconPairs = new ArrayList<>();

        for (String inputRow : puzzleInputRowsList) {

            pair = asSenzorBeaconPair(inputRow);

            senzorBeaconPairs.add(pair);

            saveSenzorAndBeaconPositionForPartOne(pair);
        }
    }

    private SenzorBeaconPair asSenzorBeaconPair(String row) {

        String[] inputNumbers;
        Point senzor;
        Point beacon;

        inputNumbers = row.replaceAll("[a-zA-Z\s=]", "").split("[,:]");

        senzor = new Point(Integer.parseInt(inputNumbers[0]), Integer.parseInt(inputNumbers[1]));
        beacon = new Point(Integer.parseInt(inputNumbers[2]), Integer.parseInt(inputNumbers[3]));

        return new SenzorBeaconPair(senzor, beacon);
    }


    private void saveSenzorAndBeaconPositionForPartOne(SenzorBeaconPair position) {

        if (scanedRowY == position.senzor.y) {
            scanedRow[position.senzor.x + scanedRowOffset] = 'S';
        }

        if (scanedRowY == position.beacon.y) {
            scanedRow[position.beacon.x + scanedRowOffset] = 'B';
        }
    }
}
