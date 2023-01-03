package hr.bp.aoc;

import java.util.List;

public abstract class BaseDay {

    protected String executeDay(Integer dayPart, List<String> puzzleInputRowsList){
        if (dayPart==1) return partOne(puzzleInputRowsList);
        if (dayPart==2) return partTwo(puzzleInputRowsList);
        return "ERROR with dayPart input";
    }

    protected abstract String partOne(List<String> puzzleInputRowsList);
    protected abstract String partTwo(List<String> puzzleInputRowsList);
}
