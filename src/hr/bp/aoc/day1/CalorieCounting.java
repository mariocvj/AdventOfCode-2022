package hr.bp.aoc.day1;

import hr.bp.aoc.BaseDay;
import java.util.List;

/**
 * @author Mario Cvjetojevic
 */
public class CalorieCounting extends BaseDay {

    @Override
    protected String partOne(List<String> inputRows) {

        int[] top3 = getTop3Calories(inputRows);

        return String.valueOf(top3[0]);
    }

    @Override
    protected String partTwo(List<String> inputRows) {

        int[] top3 = getTop3Calories(inputRows);

        return String.valueOf(top3[0] + top3[1] + top3[2]);

    }

    private int[] getTop3Calories(List<String> inputRows) {
        int[] top3cal = {0, 0, 0};  //from the highest to third-highest
        int currentCal = 0;

        for (String row : inputRows) {
            if (row.equals("")) {

                updateTop3Calories(top3cal, currentCal);
                currentCal = 0;

            } else {
                currentCal = currentCal + Integer.parseInt(row);
            }
        }
        return top3cal;
    }

    private void updateTop3Calories(int[] top3, int newCal) {
        int helper;

        if (newCal > top3[2]) {
            top3[2] = newCal;

            if (top3[2] > top3[1]) {

                helper = top3[2] ;
                top3[2] = top3[1] ;
                top3[1]  = helper;

                if (top3[1] > top3[0]) {

                    top3[1] = top3[0] ;
                    top3[0]  = helper;
                }
            }
        }
    }
}































