package hr.bp.aoc.day16;

import java.util.List;
import java.util.Set;

public class VariablesSingleton {
    public static final int ERUPTION_TIME = 30;

    public static final String START_LOCATION = "AA";

    public static List<FlowableValve> allNodes;
    public static Set<PressureValve> allValves;

    public static FlowableValve getStartingLocation() {

        for (FlowableValve node:allNodes){
            if (node.name.equals(START_LOCATION)){
                return node;
            }
        }
        return null;
    }
}
