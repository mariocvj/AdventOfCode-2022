package hr.bp.aoc.app;

import hr.bp.aoc.BaseDay;
import hr.bp.aoc.day1.CalorieCounting;
import hr.bp.aoc.day10.CathodeRayTube;
import hr.bp.aoc.day11.MonkeyInTheMiddle;
import hr.bp.aoc.day12.HillClimbingAlgorithm;
import hr.bp.aoc.day13.DistressSignal;
import hr.bp.aoc.day14.RegolithReservoir;
import hr.bp.aoc.day15.BeaconExclusionZone;
import hr.bp.aoc.day16.ProboscideaVolcanium;
import hr.bp.aoc.day2.RockPaperScissors;
import hr.bp.aoc.day3.RucksackReorganization;
import hr.bp.aoc.day4.CampCleanup;
import hr.bp.aoc.day5.SupplyStacks;
import hr.bp.aoc.day6.TuningTrouble;
import hr.bp.aoc.day7.NoSpaceLeftOnDevice;
import hr.bp.aoc.day8.TreetopTreeHouse;
import hr.bp.aoc.day9.RopeBridge;

public class Main {

    public static void main(String[] args) {
        String output;
        int day = Integer.parseInt(args[0]);
        int part = Integer.parseInt(args[1]);

        if (args.length < 3) {
            output = getDayInstance(day).getPuzzleSolution(part);
        } else {
            BaseDay instance = getDayInstance(day);
            instance.setCustomInput(args[2]);
            output = instance.getPuzzleSolution(part);
        }

        System.out.println(output);
        return;
    }

    private static BaseDay getDayInstance(int day){
        switch (day){
            case 1: return new CalorieCounting(day);
            case 2: return new RockPaperScissors(day);
            case 3: return new RucksackReorganization(day);
            case 4: return new CampCleanup(day);
            case 5: return new SupplyStacks(day);
            case 6: return new TuningTrouble(day);
            case 7: return new NoSpaceLeftOnDevice(day);
            case 8: return new TreetopTreeHouse(day);
            case 9: return new RopeBridge(day);
            case 10: return new CathodeRayTube(day);
            case 11: return new MonkeyInTheMiddle(day);
            case 12: return new HillClimbingAlgorithm(day);
            case 13: return new DistressSignal(day);
            case 14: return new RegolithReservoir(day);
            case 15: return new BeaconExclusionZone(day);
            case 16: return new ProboscideaVolcanium(day);
            case 17: return new TuningTrouble(day);
            case 18: return new TuningTrouble(day);
            case 19: return new TuningTrouble(day);
            case 20: return new TuningTrouble(day);
            case 21: return new TuningTrouble(day);
        }
        return null;
    }



}
