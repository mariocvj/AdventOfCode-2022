package hr.bp.aoc.day9;

import hr.bp.aoc.BaseDay;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RopeBridge extends BaseDay {
    public RopeBridge(int day) {
        super(day);
    }

    private HashSet<Point> visitedPositions;
    private List<Point> partTwoRopePosition;
    private Point partOneTailPosition;
    private Point partOneHeadPosition;


    @Override
    protected String partOne(List<String> puzzleInputRowsList) {
        initialiseData();

        for (String inputRow : puzzleInputRowsList) {
            executeOneRowOfStepsPartOne(inputRow.charAt(0), Integer.parseInt(inputRow.substring(2)));
        }

        return String.valueOf(visitedPositions.size());
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {
        initialiseData();

        for (String inputRow : puzzleInputRowsList) {
            executeOneRowOfStepsPartTwo(inputRow.charAt(0), Integer.parseInt(inputRow.substring(2)));
        }

        return String.valueOf(visitedPositions.size());
    }

    private void executeOneRowOfStepsPartOne(Character direction, Integer ammount) {
        for (int i = 0; i < ammount; i++) {
            visitedPositions.add(executeOneStep(direction, partOneHeadPosition, partOneTailPosition));
        }
    }

    private void executeOneRowOfStepsPartTwo(Character direction, Integer ammount) {

        for (int i = 0; i < ammount; i++) {

            executeOneStep(direction, partTwoRopePosition.get(0), partTwoRopePosition.get(1));

            for (int y = 1; y < 9; y++) {
                if (moveRopeJoint(partTwoRopePosition.get(y), partTwoRopePosition.get(y + 1))) {
                    break;
                }

            }
            visitedPositions.add(new Point(partTwoRopePosition.get(9)));
            System.out.println(partTwoRopePosition.get(9));
        }
    }

    private boolean moveRopeJoint(Point headPosition, Point tailPosition) {
        int changeX;
        int changeY;

        if (headPosition.distance(tailPosition) >= 2) {

            changeX = (int) Math.signum(headPosition.x - tailPosition.x);
            changeY = (int) Math.signum(headPosition.y - tailPosition.y);

            tailPosition.setLocation(tailPosition.x + changeX, tailPosition.y + changeY);
            return false;
        }
        return true;
    }


    private Point executeOneStep(Character direction, Point headPosition, Point tailPosition) {
        int changeX;
        int changeY;

        switch (direction) {
            case 'U' -> headPosition.setLocation(headPosition.x, headPosition.y + 1);
            case 'D' -> headPosition.setLocation(headPosition.x, headPosition.y - 1);
            case 'L' -> headPosition.setLocation(headPosition.x - 1, headPosition.y);
            case 'R' -> headPosition.setLocation(headPosition.x + 1, headPosition.y);
        }

        if (headPosition.distance(tailPosition) >= 2) {

            changeX = (int) Math.signum(headPosition.x - tailPosition.x);
            changeY = (int) Math.signum(headPosition.y - tailPosition.y);

            tailPosition.setLocation(tailPosition.x + changeX, tailPosition.y + changeY);
        }

        return new Point(tailPosition.x, tailPosition.y);
    }

    private void initialiseData() {
        visitedPositions = new HashSet<>();
        this.partOneTailPosition = new Point(0, 0);
        this.partOneHeadPosition = new Point(0, 0);
        partTwoRopePosition = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            partTwoRopePosition.add(new Point(0, 0));
        }
    }
}
