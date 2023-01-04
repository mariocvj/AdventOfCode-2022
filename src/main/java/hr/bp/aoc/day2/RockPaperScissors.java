package hr.bp.aoc.day2;

import hr.bp.aoc.BaseDay;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mario Cvjetojevic
 */
public class RockPaperScissors extends BaseDay {
    public RockPaperScissors(int day) {
        super(day);
    }

    private int score = 0;

    private enum Result {
        WIN, LOSE, DRAW
    }
    private enum Hand {
        ROCK, PAPER, SCISSORS
    }

    private static final Map<Character,Hand> charHandMap = createCharHandMap();
    private static final Map<Character,Result> charResultMap = createCharResultMap();

    private static Map<Character, Hand> createCharHandMap() {
        Map<Character, Hand> map = new HashMap<>();
        map.put('A', Hand.ROCK);
        map.put('B', Hand.PAPER);
        map.put('C', Hand.SCISSORS);
        map.put('X', Hand.ROCK);
        map.put('Y', Hand.PAPER);
        map.put('Z', Hand.SCISSORS);
        return Collections.unmodifiableMap(map);
    }
    private static Map<Character, Result> createCharResultMap() {
        Map<Character, Result> map = new HashMap<>();
        map.put('X', Result.LOSE);
        map.put('Y', Result.DRAW);
        map.put('Z', Result.WIN);
        return Collections.unmodifiableMap(map);
    }



    @Override
    protected String partOne(List<String> listOfRounds) {

        for (String roundString : listOfRounds) {
            matchScorePartOne(charHandMap.get(roundString.charAt(0)), charHandMap.get(roundString.charAt(2)));
        }
        return String.valueOf(score);
    }

    @Override
    protected String partTwo(List<String> listOfRounds) {

        for (String roundString : listOfRounds) {
            matchScorePartTwo(charHandMap.get(roundString.charAt(0)), charResultMap.get(roundString.charAt(2)));
        }

        return String.valueOf(score);
    }

    private void matchScorePartTwo(Hand left, Result right) {

        addResultScore(right);

        // add scores for Hand
        switch (right) {
            case LOSE:
                switch (left) {
                    case ROCK     -> score = score + 3;
                    case PAPER    -> score = score + 1;
                    case SCISSORS -> score = score + 2;
                }
                break;
            case DRAW:
                switch (left) {
                    case ROCK     -> score = score + 1;
                    case PAPER    -> score = score + 2;
                    case SCISSORS -> score = score + 3;
                }
                break;
            case WIN:
                switch (left) {
                    case ROCK     -> score = score + 2;
                    case PAPER    -> score = score + 3;
                    case SCISSORS -> score = score + 1;
                }
                break;
        }
    }

    private void matchScorePartOne(Hand left, Hand right) {
        switch (right) {

            case ROCK->{
                score = score + 1;
                switch (left) {
                    case ROCK     -> addResultScore(Result.DRAW);
                    case PAPER    -> addResultScore(Result.LOSE);
                    case SCISSORS -> addResultScore(Result.WIN);
                }
            }

            case PAPER -> {
                score = score + 2;
                switch (left) {
                    case ROCK     -> addResultScore(Result.WIN);
                    case PAPER    -> addResultScore(Result.DRAW);
                    case SCISSORS -> addResultScore(Result.LOSE);
                }
            }

            case SCISSORS -> {
                score = score + 3;
                switch (left) {
                    case ROCK     -> addResultScore(Result.LOSE);
                    case PAPER    -> addResultScore(Result.WIN);
                    case SCISSORS -> addResultScore(Result.DRAW);
                }
            }
        }
    }

    private void addResultScore(Result result) {
        switch (result) {
            case WIN  -> score = score + 6;
            case LOSE -> score = score + 0;
            case DRAW -> score = score + 3;
        }
    }
}
