package hr.bp.aoc.day16;

import hr.bp.aoc.BaseDay;

import java.util.*;

public class ProboscideaVolcanium extends BaseDay {
    public ProboscideaVolcanium(int day) {
        super(day);
    }

    private final List<FlowableValve> nodeList = new ArrayList<>();


    @Override
    protected String partOne(List<String> puzzleInputRowsList) {

        for (FlowableValve node : nodeList) {
            node.calculateShortestPaths();
        }

        PrioritisedPathSearch algorithm = new PrioritisedPathSearch();
        algorithm.runSearchAlgorithm();

        System.out.println("dfs");
        return String.valueOf(null);
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {
        return null;
    }


/*
    //old too slow algorithm
    private int getMaxPressure(Valve location, Set<Valve> opened, int minutes) {
        int thisPressure = 0;
        int highestPressure = 0;

        if ((location.flow > 0) && (!(opened.contains(location)))) {

            minutes++;
            thisPressure = (ERUPTION_TIME - minutes) * location.flow;


            System.out.println(minutes + "  " + opened.size() + "  " + flowableValves);

            if (opened.size() == flowableValves) {
                return thisPressure;
            }

            opened.add(location);
        }

        minutes++;

        if (minutes < ERUPTION_TIME) {
            for (Valve tunnel : location.tunnelList) {
                highestPressure = Math.max(highestPressure, getMaxPressure(tunnel, new HashSet<>(opened), minutes));
            }
            return highestPressure + thisPressure;
        }
        return thisPressure;
    }*/


    private List<PressureValve> initialiseValves(List<String> puzzleInputRowsList) {
        List<PressureValve> valveList;
        valveList = new ArrayList<>();

        for (String inputRow : puzzleInputRowsList) {
            valveList.add(asValve(inputRow));
        }

        setTunnels(puzzleInputRowsList, valveList);

        VariablesSingleton.allValves = new HashSet<>(valveList);
        VariablesSingleton.allNodes = new ArrayList<>(nodeList);

        return valveList;
    }

    private void setTunnels(List<String> puzzleInputRowsList, List<PressureValve> valveList) {
        List<PressureValve> tunnelList;
        for (int i = 0; i < puzzleInputRowsList.size(); i++) {

            tunnelList = new ArrayList<>();

            for (String tunnel : asTunnelStrings(puzzleInputRowsList.get(i))) {
                tunnelList.add(asValve(tunnel, valveList));
            }

            valveList.get(i).tunnelList = tunnelList;
        }
    }

    private FlowableValve asStartingValve(String row) {

        FlowableValve start;
        String[] nameAndFlow;

        nameAndFlow = row.substring(6, 25).replaceAll("[a-z\s;]", "").split("=");

        start = new FlowableValve(nameAndFlow[0], Integer.parseInt(nameAndFlow[1]));
        nodeList.add(start);
        return start;
    }

    private PressureValve asValve(String row) {

        FlowableValve node;
        String[] nameAndFlow;

        nameAndFlow = row.substring(6, 25).replaceAll("[a-z\s;]", "").split("=");

        if (!(nameAndFlow[1].equals("0")) || (nameAndFlow[0].equals(VariablesSingleton.START_LOCATION))) {
            node = new FlowableValve(nameAndFlow[0], Integer.parseInt(nameAndFlow[1]));
            nodeList.add(node);
            return node;
        } else {
            return new PressureValve(nameAndFlow[0]);
        }
    }

    private String[] asTunnelStrings(String row) {

        String[] tunnels;

        tunnels = row.substring(49).replaceAll("[a-z;=\s]", "").split("[,]");

        return tunnels;
    }

    private PressureValve asValve(String tunnel, List<PressureValve> valveList) {
        for (PressureValve valve : valveList) {
            if (valve.name.equals(tunnel)) {
                return valve;
            }
        }
        return null;
    }
}



