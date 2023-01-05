package hr.bp.aoc.day12;

import hr.bp.aoc.BaseDay;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class HillClimbingAlgorithm extends BaseDay {
    public HillClimbingAlgorithm(int day) {
        super(day);
    }

    private List<List<GeographicalPoint>> terrainMap;
    private GeographicalPoint startingLocation;
    private GeographicalPoint endingLocation;
    private String elevationPathingRestrictions;

    private class GeographicalPoint {

        Boolean pathDiscovered;
        Integer shortestPath;
        char elevation;
        Point2D coordinates;


        public GeographicalPoint(char elevation, int x, int y) {
            this.shortestPath = 9999;
            this.pathDiscovered = false;
            this.elevation = elevation;
            this.coordinates = new Point(x, y);
        }
    }


    @Override
    protected String partOne(List<String> puzzleInputRowsList) {
        List<GeographicalPoint> newlyDiscoveredPoints = new ArrayList<GeographicalPoint>();

        initialiseTerrainData(puzzleInputRowsList);

        // SET STARTING LOCATION FOR THE SEARCH
        startingLocation.shortestPath = 0;
        newlyDiscoveredPoints.add(startingLocation);

        elevationPathingRestrictions = "partOne";

        do {
            newlyDiscoveredPoints = calculateNextStep(newlyDiscoveredPoints);
        } while (!(endingLocation.pathDiscovered));

        System.out.println("Target location found!");
        return String.valueOf(endingLocation.shortestPath);
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {
        GeographicalPoint closestLowestElevationPoint = null;
        boolean discoveredLowestGround = false;
        List<GeographicalPoint> newlyDiscoveredPoints = new ArrayList<GeographicalPoint>();

        initialiseTerrainData(puzzleInputRowsList);

        // SET STARTING LOCATION FOR THE SEARCH
        endingLocation.shortestPath = 0;
        newlyDiscoveredPoints.add(endingLocation);

        elevationPathingRestrictions = "partTwo";

        do {
            newlyDiscoveredPoints = calculateNextStep(newlyDiscoveredPoints);
            for (GeographicalPoint newPoint : newlyDiscoveredPoints) {
                if (((Character) newPoint.elevation).equals('a')) {
                    closestLowestElevationPoint = newPoint;
                    discoveredLowestGround = true;
                }
            }
        } while (!(discoveredLowestGround));

        System.out.println("Lowest ground found!");
        return String.valueOf(closestLowestElevationPoint.shortestPath);
    }

    private List<GeographicalPoint> calculateNextStep(List<GeographicalPoint> pointsDiscoveredInLastStep) {

        List<GeographicalPoint> newlyDiscoveredPoints = new ArrayList<GeographicalPoint>();

        for (GeographicalPoint discoveredPoint : pointsDiscoveredInLastStep) {
            newlyDiscoveredPoints.addAll(StepInAllDirectionsFromPoint(discoveredPoint));
        }
        System.out.println("Step " + newlyDiscoveredPoints.get(0).shortestPath + ", " + newlyDiscoveredPoints.size() + " new points found!");

        return newlyDiscoveredPoints;
    }

    private List<GeographicalPoint> StepInAllDirectionsFromPoint(GeographicalPoint centerPoint) {

        List<GeographicalPoint> surroundingPoints = new ArrayList<GeographicalPoint>();
        List<GeographicalPoint> newlyDiscoveredPoints = new ArrayList<GeographicalPoint>();

        int x = (int) Math.round(centerPoint.coordinates.getX());
        int y = (int) Math.round(centerPoint.coordinates.getY());

        surroundingPoints.add(terrainMap.get(y).get(Math.min(x + 1, (terrainMap.get(0).size() - 1))));
        surroundingPoints.add(terrainMap.get(Math.min(y + 1, (terrainMap.size() - 1))).get(x));

        surroundingPoints.add(terrainMap.get(Math.max(y - 1, 0)).get(x));
        surroundingPoints.add(terrainMap.get(y).get(Math.max(x - 1, 0)));


        for (GeographicalPoint neighbourPoint : surroundingPoints) {
            if (stepFromPointToPoint(centerPoint, neighbourPoint)) {
                newlyDiscoveredPoints.add(neighbourPoint);
            }
        }
        return newlyDiscoveredPoints;
    }


    private boolean stepFromPointToPoint(GeographicalPoint current, GeographicalPoint destination) {

        if (!(destination.pathDiscovered)) {

            if ((elevationPathingRestrictions.equals("partOne") && (((int) destination.elevation) < (2 + (int) current.elevation))) || (elevationPathingRestrictions.equals("partTwo") && (((int) current.elevation) < (2 + (int) destination.elevation)))) {

                destination.pathDiscovered = true;

                if (destination.shortestPath > (1 + current.shortestPath)) {
                    destination.shortestPath = current.shortestPath + 1;
                }
            }
            return destination.pathDiscovered;
        } else {
            return false;
        }
    }

    private void initialiseTerrainData(List<String> puzzleInputRowsList) {

        GeographicalPoint pointToAdd;
        char pointLetter;
        terrainMap = new ArrayList<>();

        for (int y = 0; y < puzzleInputRowsList.size(); y++) {

            terrainMap.add(new ArrayList<>());

            for (int x = 0; x < puzzleInputRowsList.get(y).length(); x++) {

                pointLetter = puzzleInputRowsList.get(y).charAt(x);

                pointToAdd = new GeographicalPoint(pointLetter, x, y);

                terrainMap.get(y).add(pointToAdd);

                if (pointLetter == 'S') {
                    startingLocation = pointToAdd;
                    startingLocation.elevation = 'a';
                }
                if (pointLetter == 'E') {
                    endingLocation = pointToAdd;
                    endingLocation.elevation = 'z';
                }
            }
        }
    }

}
