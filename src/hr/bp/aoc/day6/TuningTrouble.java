package hr.bp.aoc.day6;

import hr.bp.aoc.BaseDay;

import java.util.List;

/**
 * @author Mario Cvjetojevic
 */
public class TuningTrouble extends BaseDay {
    public static final String ERROR_INVALID_PUZZLE_INPUT_NO_SOLUTION_FOUND = "ERROR: invalid puzzle input, no solution found";
    private String inputString;
    private Integer length;

    protected String partOne(List<String> puzzleInputRowsList){
        length =2;
        this.inputString=puzzleInputRowsList.get(0);
        return findSolution();
    }
    protected String partTwo(List<String> puzzleInputRowsList){
        length =14;
        this.inputString =puzzleInputRowsList.get(0);
        return findSolution();

    }

    private String findSolution(){

        for(int i = length; i<99999; i++){
            if (!(thereAreAnyEquals(i))){
                return String.valueOf(i);
            }
        }
        return ERROR_INVALID_PUZZLE_INPUT_NO_SOLUTION_FOUND;
    }

    private boolean thereAreAnyEquals(int firstIndex){
        for(int i = firstIndex- length; i<firstIndex-1; i++){
            if (firstIsEqualToAnyOther(i,firstIndex)) return true;
        }
        return false;
    }

    private boolean firstIsEqualToAnyOther(int firstIndex, int lastIndex) {
        Character firstChar = inputString.charAt(firstIndex);
        Character currentChar;

        for(int i=firstIndex+1;i<lastIndex;i++){
            currentChar= inputString.charAt(i);

            if (firstChar.equals(currentChar)) {
                return true;
            }
        }

        return false;
    }
}
