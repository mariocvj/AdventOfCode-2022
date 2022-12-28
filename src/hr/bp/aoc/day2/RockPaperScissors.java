package hr.bp.aoc.day2;

import hr.bp.aoc.BaseDay;

import java.util.List;
/**
 * @author Mario Cvjetojevic
 */
public class RockPaperScissors extends BaseDay {

    private static Integer score=0;

    protected static void Day2_RockPaperScissors () {
    }

    @Override
    protected String partOne(List<String> listOfRounds){

        for (String roundString : listOfRounds) {
            matchScorePartOne(roundString.charAt(0),roundString.charAt(2));
        }
        System.out.print(score);
        return String.valueOf(score);
    }

    @Override
    protected String partTwo(List<String> listOfRounds){

        for (String roundString : listOfRounds) {
            matchScorePartTwo(roundString.charAt(0),roundString.charAt(2));
        }
        System.out.print(score);
        return String.valueOf(score);
    }




    private static void matchScorePartTwo(Character leftChar, Character rightChar){
        switch (rightChar){
            case 'X':
                addResultScore("LOSE");
                switch (leftChar){
                    case 'A':
                        score=score+3;
                        break;
                    case 'B':
                        score=score+1;
                        break;
                    case 'C':
                        score=score+2;
                        break;
                }
                break;
            case 'Y':
                addResultScore("DRAW");
                switch (leftChar){
                    case 'A':
                        score=score+1;
                        break;
                    case 'B':
                        score=score+2;
                        break;
                    case 'C':
                        score=score+3;
                        break;
                }
                break;
            case 'Z':
                addResultScore("WIN");
                switch (leftChar){
                    case 'A':
                        score=score+2;
                        break;
                    case 'B':
                        score=score+3;
                        break;
                    case 'C':
                        score=score+1;
                        break;
                }
                break;
        }
    }

    private static void matchScorePartOne(Character leftChar, Character rightChar){
        switch (rightChar){
            case 'X':
                score=score+1;
                switch (leftChar){
                    case 'A':
                        addResultScore("DRAW");
                        break;
                    case 'B':
                        addResultScore("LOSE");
                        break;
                    case 'C':
                        addResultScore("WIN");
                        break;
                }
                break;
            case 'Y':
                score=score+2;
                switch (leftChar){
                    case 'A':
                        addResultScore("WIN");
                        break;
                    case 'B':
                        addResultScore("DRAW");
                        break;
                    case 'C':
                        addResultScore("LOSE");
                        break;
                }
                break;
            case 'Z':
                score=score+3;
                switch (leftChar){
                    case 'A':
                        addResultScore("LOSE");
                        break;
                    case 'B':
                        addResultScore("WIN");
                        break;
                    case 'C':
                        addResultScore("DRAW");
                        break;
                }
                break;
        }
    }

    private static void addResultScore(String result){
        switch (result){
            case "WIN":
                score=score+6;
                break;
            case "LOSE":
                score=score+0;
                break;
            case "DRAW":
                score=score+3;
                break;
        }
    }
}
