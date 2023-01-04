package hr.bp.aoc.day16;

import java.util.*;

public class Node extends Valve {

    private static Node allNodes;
    private static Valve allValves;

    private TreeMap<Integer, Node> shortestPaths = new TreeMap<>();
    int flow;

    public Node(String name, int flow) {
        super(name);
        this.flow = flow;
    }

    public void calculateShortestPaths() {
        List<Valve> lastStep = new ArrayList<>();
        Set<Valve> explored = new HashSet<>();
        int minute=0;

        lastStep.add(this);

        do{

        }while ()
    }
}