package hr.bp.aoc.day17;

import hr.bp.aoc.BaseDay;

import java.util.ArrayList;
import java.util.List;

public class PyroclasticFlow extends BaseDay {

    List<Character>[] caveChamber;

    public PyroclasticFlow(int day) {
        super(day);
    }

    @Override
    protected String partOne(List<String> puzzleInputRowsList) {
        int highestRock = 0;
        int spawnHeight = 0;
        int rockCounter = 0;
        FallingRock rock;
        String directions = puzzleInputRowsList.get(0);
        caveChamber = initialiseCaveChamber();

        do {
            if (caveChamber[0].size() < spawnHeight + 9) {
                extendCaveChamber(10);
            }


            rock = getNextRockAfterItFell(directions, spawnHeight);
            rock.addRockToChamber(caveChamber);


            if (rock.getPeak() > spawnHeight) {
                highestRock = highestRock + (rock.getPeak() - spawnHeight);
                spawnHeight = rock.getPeak();
            }


            if (spawnHeight > 50) {
                deleteBottomRowOfChamber(spawnHeight - 50);
                spawnHeight = 50;
            }

            rockCounter++;

        } while (rockCounter < 2022);


        return String.valueOf(highestRock);
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {
        return null;
    }


    private FallingRock getNextRockAfterItFell(String directions, int currentFloor) {
        FallingRock rock = new FallingRock(currentFloor);

        for (int i = 0; i < directions.length(); i++) {
            if (!(rock.hasJetFlowGap(caveChamber, directions.charAt(i)))) {
                return rock;
            }
            if (!(rock.hasGapBellow(caveChamber))) {
                return rock;
            }
        }
        return null;
    }

    private List<Character>[] initialiseCaveChamber() {
        caveChamber = new ArrayList[7];

        for (int x = 0; x < 7; x++) {
            caveChamber[x] = new ArrayList<>();
        }
        return caveChamber;
    }

    private void extendCaveChamber(int ammount) {
        for (int y = 0; y < ammount; y++) {
            for (int x = 0; x < 7; x++) {
                caveChamber[x].add('.');
            }
        }
    }

    private void deleteBottomRowOfChamber(int rows) {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < 7; x++) {
                caveChamber[x].remove(0);
            }
        }
    }


}
