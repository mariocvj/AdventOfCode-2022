package hr.bp.aoc.app;

import hr.bp.aoc.DayExecuter;

public class Main {

    public static void main(String[] args) {

		String day = args[0];
		String part = args[1];

		String output, inputString = "customInputString";
        DayExecuter adventOfCode = new DayExecuter();

        output = adventOfCode.executeDayPart(Integer.parseInt(day),Integer.parseInt(part));
        //adventOfCode.printFileInput(5,1);

        System.out.println(output);
        return;


}}
