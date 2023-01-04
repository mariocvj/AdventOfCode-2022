package hr.bp.aoc.day16;

import java.util.*;

public class FlowableValve extends PressureValve {

    private static final int ERUPTION_TIME = 30;
    private List<ShortestPath> shortestPaths = new ArrayList<>();
    int flow;

    public FlowableValve(String name, int flow) {
        super(name);
        this.flow = flow;
    }

    private class ShortestPath implements Comparable<ShortestPath> {
        FlowableValve destination;
        int distance;
        public ShortestPath(FlowableValve destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }
        @Override
        public int compareTo(ShortestPath other) {
            int left = this.destination.flow / this.distance;
            int right = other.destination.flow / other.distance;
            return Integer.compare(left,right);
        }
    }


    public void calculateShortestPaths() {
        List<PressureValve> lastStep = new ArrayList<>();
        List<PressureValve> nextStep = new ArrayList<>();
        Set<PressureValve> explored = new HashSet<>();
        int distance = 0;

        lastStep.add(this);
        explored.add(this);

        do {
            //implement dijkstra algorithm here
            distance++;
            for (PressureValve valve : lastStep) {
                for (PressureValve tunnel : valve.tunnelList) {
                    if (!(explored.contains(tunnel))) {

                        explored.add(tunnel);
                        nextStep.add(tunnel);
                        if ((tunnel instanceof FlowableValve) && (tunnel != this)) {
                            shortestPaths.add(new ShortestPath((FlowableValve) tunnel,distance));
                        }
                    }
                }
            }
            lastStep = nextStep;
            nextStep = new ArrayList<>();

        } while ((explored.size() < allValves.size()) && (this.shortestPaths.size() < (allNodes.size() - 1)) && (distance < ERUPTION_TIME));

        Collections.sort(shortestPaths);
    }
}