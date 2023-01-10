package hr.bp.aoc.day16;

import java.util.*;

public class FlowableValve extends PressureValve {
    private final List<ShortestPath> shortestPaths = new ArrayList<>();
    int flow;

    public FlowableValve(String name, int flow) {
        super(name);
        this.flow = flow;
    }

    class ShortestPath implements Comparable<ShortestPath> {
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

    public ShortestPath getDirection(int priority, Set<FlowableValve> unOpened){

        for (ShortestPath direction:shortestPaths){  //shortestPaths is sorted by custom priority, defined above
            if (unOpened.contains(direction.destination)){
                priority--;
                if (priority<0){
                    return direction;
                }
            }
        }
        return null;
    }

    public void calculateShortestPaths() {
        List<PressureValve> lastStep = new ArrayList<>();
        List<PressureValve> nextStep = new ArrayList<>();
        Set<PressureValve> explored = new HashSet<>();
        int distance = 0;

        lastStep.add(this);
        explored.add(this);

        //dijkstra algorithm
        do {
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

        } while ((explored.size() < VariablesSingleton.allValves.size()) && (this.shortestPaths.size() < (VariablesSingleton.allNodes.size() - 1)) && (distance < VariablesSingleton.ERUPTION_TIME));

        Collections.sort(shortestPaths);
    }


}