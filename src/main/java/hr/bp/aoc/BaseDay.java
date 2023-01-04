package hr.bp.aoc;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class BaseDay {

    private final String inputHomeFolderPath = "inputs";
    private String inputFilepathExtension;

    public BaseDay(int day) {
        this.inputFilepathExtension = "day" + day;
    }

    public void setCustomInput(String path) {
        this.inputFilepathExtension = "custom/"+path;
    }

    public String getPuzzleSolution(Integer dayPart) {
        if (dayPart == 1) return partOne(getPuzzleInput(inputFilepathExtension));
        if (dayPart == 2) return partTwo(getPuzzleInput(inputFilepathExtension));
        return "ERROR with dayPart input";
    }

    protected abstract String partOne(List<String> puzzleInputRowsList);

    protected abstract String partTwo(List<String> puzzleInputRowsList);


    private List<String> getPuzzleInput(String filePathExtension){

        List<String> puzzleInputRowsList = new ArrayList<>();

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

    private Path getRootDirectory() {
        URL hrPackageDirectory = BaseDay.class.getResource("app");

        System.out.printf("HR in location %s\n", hrPackageDirectory.getPath());

        Path path = Paths.get(hrPackageDirectory.getPath());

        while (!path.endsWith("hr")) {
            path = path.getParent();
        }

        path = path.getParent();

        return path.resolve(inputHomeFolderPath);
    }


}
