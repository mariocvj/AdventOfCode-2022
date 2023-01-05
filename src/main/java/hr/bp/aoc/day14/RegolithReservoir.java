package hr.bp.aoc.day14;

import hr.bp.aoc.BaseDay;
import hr.bp.aoc.Point;

import java.util.Arrays;
import java.util.List;

public class RegolithReservoir extends BaseDay {
    public RegolithReservoir(int day) {
        super(day);
    }

    private Character[][] caveMap;
    private Integer highestY;
    private Integer caveSize;


    @Override
    protected String partOne(List<String> puzzleInputRowsList) {
        Point sandParticle = new Point();
        int restingSand = 0;
        caveSize = 600;

        initialiseEmptyCave(caveSize);
        createCaveStructures(puzzleInputRowsList);

        outerLoop:
        do {
            sandParticle.setLocation(500, 0);
            while (!(fallToNextPosition(sandParticle))) {
                if (sandParticle.y == (caveSize / 3 - 1)) {
                    break outerLoop;
                }
            }
            restingSand++;
        } while (true);

        return String.valueOf(restingSand);
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {
        Point sandSource = new Point(500, 0);
        Point sandParticle = new Point();
        Integer restingSand = 0;
        caveSize = 1000;

        initialiseEmptyCave(caveSize);
        createCaveStructures(puzzleInputRowsList);
        createCaveFloor();

        do {
            sandParticle.setLocation(sandSource);
            while (!(fallToNextPosition(sandParticle))) {
            }
            restingSand++;
        } while (!(sandParticle.equals(sandSource)));

        return String.valueOf(restingSand);
    }

    private void createCaveStructures(List<String> puzzleInputRowsList) {
        List<String> rockPathStrings;
        int x;
        int y;
        boolean firstLoop;
        Point previousRock = new Point();
        Point nextRock = new Point();
        highestY = 0;

        for (String inputRow : puzzleInputRowsList) {
            rockPathStrings = Arrays.asList(inputRow.split(" -> "));
            firstLoop = true;

            for (String coordinates : rockPathStrings) {
                x = Integer.parseInt(coordinates.split(",")[0]);
                y = Integer.parseInt(coordinates.split(",")[1]);

                if (y > highestY) {
                    highestY = y;
                }

                nextRock.setLocation(x, y);
                if (!(firstLoop)) {
                    createRockLine(previousRock, nextRock);
                }
                previousRock.setLocation(nextRock);
                firstLoop = false;
            }
        }
    }

    private void createCaveFloor() {
        createRockLine(
                new Point(0, highestY + 2),
                new Point(caveSize - 1, highestY + 2));
    }

    private void initialiseEmptyCave(Integer caveSize) {

        caveMap = new Character[caveSize][caveSize / 3];

        for (int x = 0; x < caveSize; x++) {
            for (int y = 0; y < caveSize / 3; y++) {
                caveMap[x][y] = '.';
            }
        }
    }

    private void createRockLine(Point start, Point end) {
        int currentX;
        int currentY;
        int xChange = (int) Math.signum(end.x - start.x);
        int yChange = (int) Math.signum(end.y - start.y);
        int counter = 0;

        do {
            currentX = start.x + xChange * counter;
            currentY = start.y + yChange * counter;

            caveMap[currentX][currentY] = '#';
            counter++;
        } while ((currentX != end.x) || (currentY != end.y));//(!(currentX.equals(end.x)) || !(currentY.equals(end.y)));
    }

    private Boolean fallToNextPosition(Point sand) {
        boolean blocked = false;

        if (caveMap[sand.x][sand.y + 1].equals('.')) {
            sand.setLocation(sand.x, sand.y + 1);
        } else {
            if (caveMap[sand.x - 1][sand.y + 1].equals('.')) {
                sand.setLocation(sand.x - 1, sand.y + 1);
            } else {
                if (caveMap[sand.x + 1][sand.y + 1].equals('.')) {
                    sand.setLocation(sand.x + 1, sand.y + 1);
                } else {
                    caveMap[sand.x][sand.y] = 'S';
                    blocked = true;
                }
            }
        }
        return blocked;
    }

    private Boolean move(Point sand) {
        if (hasGapUnder(sand)) {
            sand.setLocation(sand.x, sand.y + 1);

            return false;
        }

        if (hasGapLeft(sand)) {
            sand.setLocation(sand.x - 1, sand.y + 1);

            return false;
        }

        if (caveMap[sand.x + 1][sand.y + 1].equals('.')) {
            sand.setLocation(sand.x + 1, sand.y + 1);

            return false;
        }

        caveMap[sand.x][sand.y] = 'S';

        return true;
    }

    private boolean hasGapUnder(Point sand) {
        if (caveMap[sand.x][sand.y + 1].equals('.')) {
            return true;
        }
        return false;
    }

    private boolean hasGapLeft(Point sand) {
        if (caveMap[sand.x - 1][sand.y + 1].equals('.')) {
            return true;
        }

        return false;
    }
}
