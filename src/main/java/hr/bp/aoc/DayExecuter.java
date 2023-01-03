package hr.bp.aoc;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

import hr.bp.aoc.day1.CalorieCounting;
import hr.bp.aoc.day11.MonkeyInTheMiddle;
import hr.bp.aoc.day12.HillClimbingAlgorithm;
import hr.bp.aoc.day13.DistressSignal;
import hr.bp.aoc.day14.RegolithReservoir;
import hr.bp.aoc.day15.BeaconExclusionZone;
import hr.bp.aoc.day2.RockPaperScissors;
import hr.bp.aoc.day3.RucksackReorganization;
import hr.bp.aoc.day4.CampCleanup;
import hr.bp.aoc.day5.SupplyStacks;
import hr.bp.aoc.day6.TuningTrouble;
import hr.bp.aoc.day7.NoSpaceLeftOnDevice;
import hr.bp.aoc.day8.TreetopTreeHouse;
import hr.bp.aoc.day9.RopeBridge;
import hr.bp.aoc.day10.CathodeRayTube;

public class DayExecuter {

    private BaseDay dayInstance;
    private String filePathExtension;
    private String inputHomeFolderPath = "inputs";

    public void DayExecuter(){
    }

    public String executeDayPart(Integer dayNumber,Integer dayPart){
        loadResources(dayNumber,dayPart);
        return dayInstance.executeDay(dayPart,loadPuzzleInput(filePathExtension));
    }

    public String executeDayPart(Integer dayNumber,Integer dayPart, String puzzleInput){
        loadResources(dayNumber,dayPart);
        return dayInstance.executeDay(dayPart,parseCustomPuzzleInput(puzzleInput));
    }

    //is used only for testing
    public void printFileInput(Integer dayNumber,Integer dayPart){
        loadResources(dayNumber,dayPart);
        List<String> fileRowList = loadPuzzleInput(filePathExtension);
        for (String row : fileRowList){
            System.out.println(row);
        }
    }


    private void loadResources(Integer dayNumber,Integer dayPart){
        switch (dayNumber){
            case 1:
                dayInstance = new CalorieCounting();
                filePathExtension = "day1";
                break;
            case 2:
                dayInstance = new RockPaperScissors();
                filePathExtension = "day2";
                break;
            case 3:
                dayInstance = new RucksackReorganization();
                filePathExtension = "day3";
                break;
            case 4:
                dayInstance = new CampCleanup();
                filePathExtension = "day4";
                break;
            case 5:
                dayInstance = new SupplyStacks();
                filePathExtension = "day5";
                break;
            case 6:
                dayInstance = new TuningTrouble();
                filePathExtension = "day6";
                break;
            case 7:
                dayInstance = new NoSpaceLeftOnDevice();
                filePathExtension = "day7";
                break;
            case 8:
                dayInstance = new TreetopTreeHouse();
                filePathExtension = "day8";
                break;
            case 9:
                dayInstance = new RopeBridge();
                filePathExtension = "day9";
                break;
            case 10:
                dayInstance = new CathodeRayTube();
                filePathExtension = "day10";
                break;
            case 11:
                dayInstance = new MonkeyInTheMiddle();
                filePathExtension = "day11";
                break;
            case 12:
                dayInstance = new HillClimbingAlgorithm();
                filePathExtension = "day12";
                break;
            case 13:
                dayInstance = new DistressSignal();
                filePathExtension = "day13";
                break;
            case 14:
                dayInstance = new RegolithReservoir();
                filePathExtension = "day14";
                break;
            case 15:
                dayInstance = new BeaconExclusionZone();
                filePathExtension = "day15";
                break;
            case 16:
                dayInstance = new TuningTrouble();
                filePathExtension = "day16";
                break;
            case 17:
                dayInstance = new TuningTrouble();
                filePathExtension = "day17";
                break;
            case 18:
                dayInstance = new TuningTrouble();
                filePathExtension = "day18";
                break;
            case 19:
                dayInstance = new TuningTrouble();
                filePathExtension = "day19";
                break;
            case 20:
                dayInstance = new TuningTrouble();
                filePathExtension = "day20";
                break;
            case 21:
                dayInstance = new TuningTrouble();
                filePathExtension = "day21";
                break;
        }
    }


    private List<String> loadPuzzleInput(String filePathExtension){

        List<String> puzzleInputRowsList = new ArrayList<String>();

        try {
			Path rootDirectoryPath = getRootDirectory();

			Path dayInputPath = rootDirectoryPath.resolve(filePathExtension);

            Scanner myReader = new Scanner(dayInputPath.toFile());

			while (myReader.hasNextLine()) {
                puzzleInputRowsList.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No puzzle input file at a given path locaton.");
            e.printStackTrace();
        }

        return puzzleInputRowsList;
    }


    private List<String> parseCustomPuzzleInput(String customPuzzleInput){
        return new ArrayList<String>(Arrays.asList(customPuzzleInput.split("\n")));
    }

	private Path getRootDirectory() {
		URL hrPackageDirectory = DayExecuter.class.getResource("app");

		System.out.printf("HR in location %s\n", hrPackageDirectory.getPath());

		Path path = Paths.get(hrPackageDirectory.getPath());

		while (!path.endsWith("hr")) {
			path = path.getParent();
		}

		path = path.getParent();

		return path.resolve(inputHomeFolderPath);
	}



}
