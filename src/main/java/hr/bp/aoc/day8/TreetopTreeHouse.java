package hr.bp.aoc.day8;

import hr.bp.aoc.BaseDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreetopTreeHouse extends BaseDay {
    public TreetopTreeHouse(int day) {
        super(day);
    }

    List<List<ForestTree>> forestMap;


    private class ForestTree {
        Boolean isVisible;
        Integer height;

        public ForestTree(Integer height) {
            this.isVisible = false;
            this.height = height;
        }
    }

    @Override
    protected String partOne(List<String> puzzleInputRowsList) {
        Integer highestTreeInLineOfSight;

        initialiseForestData(puzzleInputRowsList);

        //CHECK VISIBILITY FROM LEFT
        for (int y = 0; y < forestMap.size(); y++) {
            highestTreeInLineOfSight = -1;
            for (int x = 0; x < forestMap.get(0).size(); x++) {
                highestTreeInLineOfSight = checkTreeVisibility(x, y, highestTreeInLineOfSight);
            }
        }

        //CHECK VISIBILITY FROM RIGHT
        for (int y = 0; y < forestMap.size(); y++) {
            highestTreeInLineOfSight = -1;
            for (int x = forestMap.get(0).size() - 1; x >= 0; x--) {
                highestTreeInLineOfSight = checkTreeVisibility(x, y, highestTreeInLineOfSight);
            }
        }

        //CHECK VISIBILITY FROM TOP
        for (int x = 0; x < forestMap.get(0).size(); x++) {
            highestTreeInLineOfSight = -1;
            for (int y = 0; y < forestMap.size(); y++) {
                highestTreeInLineOfSight = checkTreeVisibility(x, y, highestTreeInLineOfSight);
            }
        }

        //CHECK VISIBILITY FROM BOTTOM
        for (int x = 0; x < forestMap.get(0).size(); x++) {
            highestTreeInLineOfSight = -1;
            for (int y = forestMap.size() - 1; y >= 0; y--) {
                highestTreeInLineOfSight = checkTreeVisibility(x, y, highestTreeInLineOfSight);

            }
        }
        return String.valueOf(getVisibleTreeAmmount());
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {
        int highestScenicScore = 0;
        int currentScenicScore;

        initialiseForestData(puzzleInputRowsList);

        for (int y = 0; y < forestMap.size(); y++) {
            for (int x = 0; x < forestMap.get(0).size(); x++) {

                currentScenicScore = calculateTreeScenicScore(x, y);
                if (highestScenicScore < currentScenicScore) {
                    highestScenicScore = currentScenicScore;
                }
            }
        }
        return String.valueOf(highestScenicScore);
    }

    private Integer calculateTreeScenicScore(Integer treeX, Integer treeY) {
        ForestTree treeToCalculate = forestMap.get(treeY).get(treeX);
        int lineOfSightLength = 0;
        int scenicScore = 1;
        ForestTree currentTree;


        //CHECK VISIBILITY TOWARDS BOTTOM
        do {
            lineOfSightLength++;
            currentTree = getForestTree(treeX, treeY + lineOfSightLength);
            if (Objects.isNull(currentTree)) {
                lineOfSightLength--;
                break;
            }
        } while (treeToCalculate.height > currentTree.height);
        scenicScore = scenicScore * lineOfSightLength;
        lineOfSightLength = 0;

        //CHECK VISIBILITY TOWARDS TOP
        do {
            lineOfSightLength++;
            currentTree = getForestTree(treeX, treeY - lineOfSightLength);
            if (Objects.isNull(currentTree)) {
                lineOfSightLength--;
                break;
            }
        } while (treeToCalculate.height > currentTree.height);
        scenicScore = scenicScore * lineOfSightLength;
        lineOfSightLength = 0;


        //CHECK VISIBILITY TOWARDS RIGHT
        do {
            lineOfSightLength++;
            currentTree = getForestTree(treeX + lineOfSightLength, treeY);
            if (Objects.isNull(currentTree)) {
                lineOfSightLength--;
                break;
            }
        } while (treeToCalculate.height > currentTree.height);
        scenicScore = scenicScore * lineOfSightLength;
        lineOfSightLength = 0;


        //CHECK VISIBILITY TOWARDS LEFT
        do {
            lineOfSightLength++;
            currentTree = getForestTree(treeX - lineOfSightLength, treeY);
            if (Objects.isNull(currentTree)) {
                lineOfSightLength--;
                break;
            }
        } while (treeToCalculate.height > currentTree.height);
        scenicScore = scenicScore * lineOfSightLength;

        return scenicScore;
    }

    private Integer getVisibleTreeAmmount() {
        Integer visibleTrees = 0;

        for (List<ForestTree> forestRow : forestMap) {
            for (int x = 0; x < forestMap.get(0).size(); x++) {
                if (forestRow.get(x).isVisible) {
                    visibleTrees++;
                }
            }
        }
        return visibleTrees;
    }


    private ForestTree getForestTree(Integer treeX, Integer treeY) {
        try {
            return forestMap.get(treeY).get(treeX);
        } catch (IndexOutOfBoundsException e) {
            // THIS IS INTENDED PROGRAM BEHAVIOUR
            return null;
        }
    }

    private Integer checkTreeVisibility(Integer treeX, Integer treeY, Integer heightOfTreesInBetween) {
        ForestTree tree = forestMap.get(treeY).get(treeX);
        //System.out.println(treeX + "  " + treeY);

        if (tree.height > heightOfTreesInBetween) {
            tree.isVisible = true;
            return tree.height;
        } else {
            return heightOfTreesInBetween;
        }
    }

    private void initialiseForestData(List<String> puzzleInputRowsList) {
        ForestTree newTree;

        forestMap = new ArrayList<>();

        for (int y = 0; y < puzzleInputRowsList.size(); y++) {

            forestMap.add(new ArrayList<>());

            for (int x = 0; x < puzzleInputRowsList.get(0).length(); x++) {

                newTree = new ForestTree(Integer.parseInt(puzzleInputRowsList.get(y).substring(x, x + 1)));
                forestMap.get(y).add(newTree);
            }
        }
    }
}
