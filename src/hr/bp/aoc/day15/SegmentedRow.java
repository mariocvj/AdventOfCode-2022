package hr.bp.aoc.day15;

import java.util.List;
import java.util.TreeSet;

class SegmentedRow implements Comparable<SegmentedRow> {
    int y;
    TreeSet<RowSegment> unExploredSegments;

    public SegmentedRow(int y, int areaSize) {
        this.y = y;
        this.unExploredSegments = new TreeSet<RowSegment>();
        this.unExploredSegments.add(new RowSegment(0, areaSize));
    }

    public SegmentedRow() {
        this.y = 0;
    }

    public boolean equals(SegmentedRow row) {
        return row.y == this.y;
    }

    protected void removeSegments(List<RowSegment> senzorSegments) {
        for (RowSegment segment : senzorSegments) {
            this.unExploredSegments.remove(segment);
        }
    }

    protected void addBackLeftSegmentSlice(RowSegment segment, RowSegment split) {
        if ((segment.startPoint < split.endPoint) && (segment.startPoint < split.endPoint)) {
            this.unExploredSegments.add(new RowSegment(segment.startPoint, split.endPoint));
        }
    }

    protected void addBackRightSegmentSlice(RowSegment segment, RowSegment split) {
        if ((segment.endPoint > split.startPoint) && ((split.startPoint + 1) < segment.endPoint)) {
            this.unExploredSegments.add(new RowSegment(split.startPoint + 1, segment.endPoint));
        }
    }

    @Override
    public int compareTo(SegmentedRow row) {
        if (this.y < row.y) {
            return -1;
        }
        if (this.y > row.y) {
            return 1;
        }
        return 0;
    }
}