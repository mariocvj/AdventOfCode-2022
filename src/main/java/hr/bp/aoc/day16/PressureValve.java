package hr.bp.aoc.day16;

import java.util.List;

public class PressureValve implements Comparable<PressureValve>{
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