package hr.bp.aoc.day16;

import java.util.HashSet;
import java.util.Set;

import static hr.bp.aoc.day16.VariablesSingleton.ERUPTION_TIME;

public class PrioritisedPathSearch {

    int[] pathDirectionList;
    int maxPressure = 0;

    public void runSearchAlgorithm() {
        int i=0;

        initialiseJunctionList();

        while (true){
            changeDirections(i);
            i++;
        }
    }

    private void initialiseJunctionList() {
        int length = VariablesSingleton.allNodes.size();
        pathDirectionList = new int[length];

        for (int i = 0; i < length; i++) {
            pathDirectionList[i] = 0;
        }
    }

    //one call changes one direction in one junction, but simulates that for all junctions, "ammount" is number of recursive calls aka. ammount of directions changed
    private void changeDirections(int ammount) {

        if (ammount == 0) {
            setMaxPressure(getPathPressure());
            System.out.println(maxPressure);
            return;
        }

        for (int i = 0; i < pathDirectionList.length; i++) {

            pathDirectionList[i]++;
            changeDirections(ammount - 1);
            pathDirectionList[i]--;

        }
    }

    private synchronized void setMaxPressure(int pressure){
        if (maxPressure < pressure) {
            maxPressure = pressure;
        }
    }


    private int getPathPressure() {
        int minute = 0;
        int junction = 0;
        int pressure = 0;
        FlowableValve.ShortestPath nextDirection;

        FlowableValve location = VariablesSingleton.getStartingLocation();
        Set<FlowableValve> unOpened = new HashSet<>(VariablesSingleton.allNodes);
        unOpened.remove(location);//because starting valve has 0 flow rate


        do {

            nextDirection = location.getDirection(pathDirectionList[junction], unOpened);
            unOpened.remove(location);
            if (nextDirection!=null){
                location = nextDirection.destination;
                minute = minute + nextDirection.distance + 1;
            }else {
                return pressure;
            }

            pressure = pressure + (ERUPTION_TIME - minute) * location.flow;

        } while ((minute < ERUPTION_TIME) && (!(unOpened.isEmpty())));

        return pressure;
    }

}
