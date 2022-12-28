package hr.bp.aoc.day1;

import hr.bp.aoc.BaseDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mario Cvjetojevic
 */
public class CalorieCounting extends BaseDay {

    private String solution;

    protected static void Day1_CalorieCounting() {
    }

    @Override
    protected String partOne(List<String> listOfCalories){

        List<List<Integer>> listOfElfs= new ArrayList<>();
        ArrayList<Integer> currentElf = new ArrayList<Integer>();

        for (int i=0;i<listOfCalories.size();i++) {
            if (listOfCalories.get(i) == "") {
                listOfElfs.add(currentElf);
                currentElf = new ArrayList<Integer>();
            } else currentElf.add(Integer.parseInt(listOfCalories.get(i) ) );
        }

        int highestCalories=0, currentElfCalories;

        for (List<Integer> elf : listOfElfs) {
            currentElfCalories=0;
            //System.out.println("Sljedeci:\n");
            for (Integer mealCalories : elf){
                //System.out.println(mealCalories);
                currentElfCalories=currentElfCalories+mealCalories;

            }
            if (currentElfCalories>highestCalories) highestCalories=currentElfCalories;
        }
        return String.valueOf(highestCalories);
    }

    @Override
    protected String partTwo(List<String> listOfCalories){

        List<List<Integer>> listOfElfs= new ArrayList<>();
        ArrayList<Integer> currentElf = new ArrayList<Integer>();

        for (int i=0;i<listOfCalories.size();i++) {
            if (listOfCalories.get(i) == "") {
                listOfElfs.add(currentElf);
                currentElf = new ArrayList<Integer>();
            } else currentElf.add(Integer.parseInt(listOfCalories.get(i) ) );
        }

        int firstCalories=0, secondCalories=0, thirdCalories=0,currentElfCalories;

        for (List<Integer> elf : listOfElfs) {
            currentElfCalories=0;
            //System.out.println("Sljedeci:\n");
            for (Integer mealCalories : elf){
                //System.out.println(mealCalories);
                currentElfCalories=currentElfCalories+mealCalories;

            }
            if (currentElfCalories>firstCalories){
                thirdCalories=secondCalories;
                secondCalories=firstCalories;
                firstCalories=currentElfCalories;
            }
        }
        /*System.out.println(thirdCalories);
        System.out.println(secondCalories);
        System.out.println(firstCalories);
        System.out.println(firstCalories+secondCalories+thirdCalories);*/

        return String.valueOf(firstCalories+secondCalories+thirdCalories);

    }
}































