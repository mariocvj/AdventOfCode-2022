package hr.bp.aoc.day15;
class RowSegment implements Comparable<RowSegment> {
    protected int startPoint;
    protected int endPoint;

    public RowSegment(int startPoint, int endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public int compareTo(RowSegment segment) {
        if (this.endPoint <= segment.startPoint) {
            return -1;
        }
        if (this.startPoint >= segment.endPoint) {
            return 1;
        }
        return 0;
    }

    protected void setPoints(int value) {
        this.startPoint = value;
        this.endPoint = value;
    }
}