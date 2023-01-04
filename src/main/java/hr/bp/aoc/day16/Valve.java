package hr.bp.aoc.day16;

import java.util.List;

public class Valve implements Comparable<Valve>{
    String name;
    List<Valve> tunnelList;

    public Valve(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Valve other) {
        if (this.name.equals(other.name)){
            return 0;
        }
        return -1;
    }
}