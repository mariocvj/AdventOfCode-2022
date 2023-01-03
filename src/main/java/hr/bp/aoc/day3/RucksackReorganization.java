package hr.bp.aoc.day3;

import hr.bp.aoc.BaseDay;

import java.util.*;
/**
 * @author Mario Cvjetojevic
 */
public class RucksackReorganization extends BaseDay {

    protected static void Day3_RucksackReorganization() {
    }

    @Override
    protected String partTwo(List<String> listOfRucksacks){

        char letter;
        int ascii, sum=0;

        for(int i=0;i<listOfRucksacks.size();i=i+3){
            String commonFromTwo = commonChars(listOfRucksacks.get(i),listOfRucksacks.get(i+1)).toString();
            ascii = letter = commonChars(commonFromTwo ,listOfRucksacks.get(i+2)).iterator().next();
            if (ascii<91) ascii = ascii-38;
            if (ascii>96) ascii = ascii-96;
            sum=sum+ascii;

        }
        System.out.println(sum);
        return String.valueOf(sum);

    }

    @Override
    protected String partOne(List<String> listOfRucksacks){

        String leftSide,rightSide;
        char letter;
        int ascii,sum=0;
        for (String rundaString : listOfRucksacks) {
            leftSide = rundaString.substring(0, rundaString.length() / 2);
            rightSide = rundaString.substring(rundaString.length() / 2, rundaString.length());

            ascii = letter = commonChars(leftSide,rightSide).iterator().next();
            if (ascii<91) ascii = ascii-38;
            if (ascii>96) ascii = ascii-96;
            sum=sum+ascii;



        }
        System.out.println(sum);
        return String.valueOf(sum);
    }

    private static Set<Character> commonChars(String s1, String s2) {
        Set<Character> set = new HashSet<>();
        for(Character c : s1.toCharArray()) {
            if(s2.indexOf(c) >= 0) {
                set.add(c);
            }
        }
        return set;
    }

}