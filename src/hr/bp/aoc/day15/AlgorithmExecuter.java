package hr.bp.aoc.day15;

import hr.bp.aoc.Point;
import hr.bp.aoc.day15.BeaconExclusionZone.SenzorBeaconPair;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

class AlgorithmExecuter {

    // EXECUTION TIME SENSITIVE CLASS
    private int manhattanDistance;
    private int scanHalfWidth;
    private SegmentedRow firstRow;
    private SegmentedRow lastRow;
    private RowSegment firstColumn;
    private RowSegment lastColumn;
    private List<SegmentedRow> senzorRows;
    private List<RowSegment> scanedSegments;

    TreeSet<SegmentedRow> searchArea;


    public Point getMissingBeaconTimed(List<SenzorBeaconPair> senzorBeaconPairs) {
        Point beacon;
        long time = System.currentTimeMillis();
        beacon = this.getMissingBeacon(senzorBeaconPairs);
        System.out.println("Algorithm execution time: " + (System.currentTimeMillis() - time) + "ms");
        return beacon;
    }

    public Point getMissingBeacon(List<SenzorBeaconPair> senzorBeaconPairs) {

        initialiseSearchArea();

        scanSearchArea(senzorBeaconPairs);

        return new Point(searchArea.first().unExploredSegments.first().startPoint, searchArea.first().y);
    }

    private void initialiseSearchArea() {

        searchArea = new TreeSet<SegmentedRow>();

        int areaSize = 4000000;

        // COMPARISON-DUMMY VARIABLES
        firstRow = new SegmentedRow();
        lastRow = new SegmentedRow();
        firstColumn = new RowSegment(0, 0);
        lastColumn = new RowSegment(areaSize - 1, areaSize - 1);

        for (int y = 0; y < areaSize; y++) {
            searchArea.add(new SegmentedRow(y, areaSize));
        }
    }

    private void scanSearchArea(List<SenzorBeaconPair> senzorBeaconPairs) {

        for (SenzorBeaconPair senzorBeacon : senzorBeaconPairs) {

            System.out.println("Next Senzor");

            calculateSenzorRows(senzorBeacon);

            //scanSenzorRows(senzorBeacon);

            for (SegmentedRow row : senzorRows) {
                scanSenzorRow(senzorBeacon,row);
            }
        }
    }

    private void calculateSenzorRows(SenzorBeaconPair senzorBeacon) {

        manhattanDistance = senzorBeacon.getManhattanDistance();

        firstRow.y = senzorBeacon.senzor.y - manhattanDistance;
        lastRow.y = senzorBeacon.senzor.y + manhattanDistance;

        senzorRows = new ArrayList<SegmentedRow>(searchArea.subSet(firstRow, true, lastRow, true));
    }

    private void scanSenzorRow(SenzorBeaconPair senzorBeacon, SegmentedRow row) {
        scanRowWithSenzor(row, senzorBeacon);

        updateRowSegments(row);

        if (row.unExploredSegments.isEmpty()) {
            searchArea.remove(row);
        }
    }

    private void scanSenzorRows(SenzorBeaconPair senzorBeacon) {

        for (SegmentedRow row : senzorRows) {

            scanRowWithSenzor(row, senzorBeacon);

            updateRowSegments(row);

            if (row.unExploredSegments.isEmpty()) {
                searchArea.remove(row);
            }
        }
    }

    private void scanRowWithSenzor(SegmentedRow row, SenzorBeaconPair senzorBeacon) {

        scanHalfWidth = manhattanDistance - Math.abs(row.y - senzorBeacon.senzor.y);

        firstColumn.setPoints(senzorBeacon.senzor.x - scanHalfWidth);

        lastColumn.setPoints(senzorBeacon.senzor.x + scanHalfWidth);

        scanedSegments = new ArrayList<RowSegment>(row.unExploredSegments.subSet(firstColumn, true, lastColumn, true));
    }

    private void updateRowSegments(SegmentedRow row) {

        if (scanedSegments.size() != 0) {

            row.removeSegments(scanedSegments);

            row.addBackLeftSegmentSlice(scanedSegments.get(0), firstColumn);

            row.addBackRightSegmentSlice(scanedSegments.get(scanedSegments.size() - 1), lastColumn);
        }
    }
}