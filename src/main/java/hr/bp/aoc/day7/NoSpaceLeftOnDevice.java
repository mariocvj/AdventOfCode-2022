package hr.bp.aoc.day7;

import hr.bp.aoc.BaseDay;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mario Cvjetojevic
 */
public class NoSpaceLeftOnDevice extends BaseDay {
    public NoSpaceLeftOnDevice(int day) {
        super(day);
    }

    @Override
    protected String partOne(List<String> puzzleInputRowsList) {

        Day7Root rootInstance = directoryTreeBuilder(puzzleInputRowsList);

        return String.valueOf(rootInstance.sumOfPartOneDirectories());

    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {

        Day7Root rootInstance = directoryTreeBuilder(puzzleInputRowsList);

        Double missingSpace = 30000000 - (70000000 - rootInstance.getSize());

        Day7Directory bestDirectoryToDelete = rootInstance.bestPartTwoDirectory(missingSpace);

        return String.valueOf(bestDirectoryToDelete.getSize().intValue());
    }

    private Day7Root directoryTreeBuilder(List<String> puzzleInputRowsList) {


        Day7Directory currentLocation;
        Day7Root rootInstance = new Day7Root();
        String[] rowData;
        Day7Directory createNewDir;
        Day7File createNewFile;

        currentLocation = rootInstance;

        for (String inputRow : puzzleInputRowsList) {

            switch (inputRow) {
                case "$ cd /":
                    currentLocation = rootInstance;
                    continue;
                case "$ cd ..":
                    if (!(currentLocation.getLocation() == null)) {
                        currentLocation = currentLocation.getLocation();
                    }
                    continue;
                case "$ ls":
                    continue;
            }

            rowData = inputRow.split(" ");


            if (rowData[0].equals("$")) {  //TRUE IF ROW IS A CD INTO DEEPER DIRECTORY

                createNewDir = new Day7Directory(rowData[2], currentLocation, new ArrayList<>());

                currentLocation = (Day7Directory) currentLocation.setChild(createNewDir);
                continue;
            }

            if (rowData[0].equals("dir")) {    //TRUE IF ROW IS A DIR

                createNewDir = new Day7Directory(rowData[1], currentLocation, new ArrayList<>());

                currentLocation.setChild(createNewDir);
                continue;
            }


            if (rowData[0].matches("[0-9]+")) {  //TRUE IF ROW IS A FILE

                createNewFile = new Day7File(rowData[1], currentLocation, Integer.parseInt(rowData[0]));

                currentLocation.setChild(createNewFile);
            }
        }
        return rootInstance;
    }
}
