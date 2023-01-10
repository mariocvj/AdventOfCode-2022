package hr.bp.aoc.day17;

import hr.bp.aoc.Point;

import java.util.ArrayList;
import java.util.List;

public class FallingRock {

    static int shape = 0;
    Point location;
    List<Point> partOffsets = new ArrayList<>();
    private int peak = 0;

    public FallingRock(int floor) {
        this.location = new Point(2, floor + 3);

        initialisePartOffsets();

        shape++;
        if (shape > 4) {
            shape = 0;
        }
    }

    private void initialisePartOffsets() {
        switch (shape) {
            case 0: {
                partOffsets.add(new Point(0, 0));
                partOffsets.add(new Point(1, 0));
                partOffsets.add(new Point(2, 0));
                partOffsets.add(new Point(3, 0));
                break;
            }
            case 1: {
                partOffsets.add(new Point(0, 1));
                partOffsets.add(new Point(1, 1));
                partOffsets.add(new Point(2, 1));
                partOffsets.add(new Point(1, 0));
                partOffsets.add(new Point(1, 2));
                break;
            }
            case 2: {
                partOffsets.add(new Point(0, 0));
                partOffsets.add(new Point(1, 0));
                partOffsets.add(new Point(2, 0));
                partOffsets.add(new Point(2, 1));
                partOffsets.add(new Point(2, 2));
                break;
            }
            case 3: {
                partOffsets.add(new Point(0, 0));
                partOffsets.add(new Point(0, 1));
                partOffsets.add(new Point(0, 2));
                partOffsets.add(new Point(0, 3));
                break;
            }
            case 4: {
                partOffsets.add(new Point(0, 0));
                partOffsets.add(new Point(1, 0));
                partOffsets.add(new Point(0, 1));
                partOffsets.add(new Point(1, 1));
                break;
            }
        }
    }

    public void addRockToChamber(List<Character>[] chamber) {
        for (Point part : partOffsets) {
            chamber[location.x + part.x].set(location.y + part.y, '#');
        }
    }

    public int getPeak() {
        if (peak == 0) {
            for (Point part : partOffsets) {
                peak = Math.max(peak, location.y + part.y);
            }
        }
        return peak;
    }

    public boolean hasJetFlowGap(List<Character>[] chamber, char jetFlow) {
        try {
            if (jetFlow == '<') {
                return hasGapLeft(chamber);
            }
            if (jetFlow == '>') {
                return hasGapRight(chamber);
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            return false;// this is intended behaviour when rock hits cavern wall
        }
        return false;
    }

    private boolean hasGapLeft (List<Character>[] chamber) throws ArrayIndexOutOfBoundsException{
        for (Point part : partOffsets) {
            if (chamber[location.x + part.x - 1].get(location.y + part.y).equals('#')) {
                return false;
            }
        }
        location.x--;
        return true;
    }

    private boolean hasGapRight(List<Character>[] chamber) throws ArrayIndexOutOfBoundsException{
        for (Point part : partOffsets) {
            if (chamber[location.x + part.x + 1].get(location.y + part.y).equals('#')) {
                return false;
            }
        }
        location.x++;
        return true;
    }

    public boolean hasGapBellow(List<Character>[] chamber) {
        for (Point part : partOffsets) {
            if (chamber[location.x + part.x].get(location.y + part.y - 1).equals('#')) {
                return false;
            }
        }
        location.y--;
        return true;
    }


}
