package hr.bp.aoc.day16;

import java.util.List;
import java.util.Set;

public class PressureValve implements Comparable<PressureValve>{

    protected static Set<FlowableValve> allNodes;
    protected static Set<PressureValve> allValves;
    public void setAllNodes(Set<FlowableValve> allNodes) {
        this.allNodes = allNodes;
    }
    public void setAllValves(Set<PressureValve> allValves) {
        this.allValves = allValves;
    }
    String name;
    List<PressureValve> tunnelList;

    public PressureValve(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(PressureValve other) {
        if (this.name.equals(other.name)){
            return 0;
        }
        return -1;
    }
}