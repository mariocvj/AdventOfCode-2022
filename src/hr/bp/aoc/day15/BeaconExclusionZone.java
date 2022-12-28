package hr.bp.aoc.day15;

import hr.bp.aoc.BaseDay;

import java.math.BigInteger;
import java.util.ArrayList;

import hr.bp.aoc.Point;

import java.util.List;

public class BeaconExclusionZone extends BaseDay {

    private List<SenzorBeaconPair> senzorBeaconPairs;
    private Character scanedRow[];
    private Integer areaSize;
    private Integer scanedRowY;
    private Integer scanedRowOffset;

    public class SenzorBeaconPair {
        Point senzor;

        public SenzorBeaconPair(Point senzor, Point beacon) {
            this.senzor = senzor;
            this.beacon = beacon;
        }

        Point beacon;

        protected Integer getManhattanDistance() {
            return Math.abs(senzor.x - beacon.x) + Math.abs(senzor.y - beacon.y);
        }

        protected void scanPositionsInRowPartOne(Integer row) {
            Integer scanWidth = Math.max(0, this.getManhattanDistance() - Math.abs(senzor.y - row) + 1);
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
        Integer scanned = 0;

        initialiseSenzorBeaconPairs(puzzleInputRowsList);

        initialisePartOneScanedRow();

        for(SenzorBeaconPair senzorBeacon: senzorBeaconPairs){
            senzorBeacon.scanPositionsInRowPartOne(2000000);
        }


        return String.valueOf(scanned);
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
        String inputNumbers[];
        Point senzor;
        Point beacon;
        senzorBeaconPairs = new ArrayList<SenzorBeaconPair>();

        for (String inputRow : puzzleInputRowsList) {
            inputNumbers = inputRow.replaceAll("[[a-zA-Z] =]", "").split("[,:]");

            senzor = new Point(Integer.parseInt(inputNumbers[0]), Integer.parseInt(inputNumbers[1]));
            beacon = new Point(Integer.parseInt(inputNumbers[2]), Integer.parseInt(inputNumbers[3]));

            senzorBeaconPairs.add(new SenzorBeaconPair(senzor, beacon));

            if (scanedRowY != null) {
                if (scanedRowY == senzor.y) {
                    scanedRow[senzor.x + scanedRowOffset] = 'S';
                }
                if (scanedRowY == beacon.y) {
                    scanedRow[beacon.x + scanedRowOffset] = 'B';
                }
            }
        }
    }
}
