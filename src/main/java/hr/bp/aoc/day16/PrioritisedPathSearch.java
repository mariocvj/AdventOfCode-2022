package hr.bp.aoc.day16;

import java.util.ArrayList;
import java.util.List;

public class PrioritisedPathSearch {

    int[] pathDirectionList;

    private void initialiseJunctionList(int length) {
        pathDirectionList = new int[length];

        for (int i = 0; i < length; i++) {
            pathDirectionList[i] = 0;
        }
    }

    //one call changes one direction in one junction, but simulates that for all junctions, "ammount" is number of recursive calls aka. ammount of directions changed
    private void changeDirections(int ammount) {

        if (ammount == 0) {
            getAndPrintMaxPressure();
        }

        for (Integer i = 0; i < pathDirectionList.length; i++) {

            pathDirectionList[i]++;
            changeDirections(ammount-1);
            pathDirectionList[i]--;

        }
    }


    private int getAndPrintMaxPressure() {
        return 0;
    }

}
