package hr.bp.aoc.day16;

import hr.bp.aoc.BaseDay;

import java.util.*;

public class ProboscideaVolcanium extends BaseDay {

    private static final int ERUPTION_TIME = 30;
    private List<Node> nodeList = new ArrayList<>();


    @Override
    protected String partOne(List<String> puzzleInputRowsList) {

        Set<Valve> opened = new HashSet<>();
        List<Valve> valveList = initialiseValves(puzzleInputRowsList);


        return String.valueOf(getMaxPressure(valveList.get(0), opened, 0));
    }

    @Override
    protected String partTwo(List<String> puzzleInputRowsList) {
        return null;
    }


    //IGNORING POTENTIAL OF SKIPING A LOW FLOW VALVE


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
    }


    private List<Valve> initialiseValves(List<String> puzzleInputRowsList) {
        List<Valve> valveList;
        List<Valve> tunnelList;
        valveList = new ArrayList<>();

        for (String inputRow : puzzleInputRowsList) {
            valveList.add(asValve(inputRow));
        }

        setTunnels(puzzleInputRowsList, valveList);

        return valveList;
    }

    private void setTunnels(List<String> puzzleInputRowsList, List<Valve> valveList) {
        List<Valve> tunnelList;
        for (int i = 0; i < puzzleInputRowsList.size(); i++) {

            tunnelList = new ArrayList<>();

            for (String tunnel : asTunnelStrings(puzzleInputRowsList.get(i))) {
                tunnelList.add(asValve(tunnel, valveList));
            }

            valveList.get(i).tunnelList = tunnelList;
        }
    }

    private Valve asValve(String row) {

        Node node;
        String[] nameAndFlow;

        nameAndFlow = row.substring(6, 25).replaceAll("[a-z\s;]", "").split("=");

        if (nameAndFlow[1].equals("0")) {
            return new Valve(nameAndFlow[0]);
        } else {
            node = new Node(nameAndFlow[0], Integer.parseInt(nameAndFlow[1]));
            nodeList.add(node);
            return node;
        }
    }

    private String[] asTunnelStrings(String row) {

        String[] tunnels;

        tunnels = row.substring(49).replaceAll("[a-z;=\s]", "").split("[,]");

        return tunnels;
    }

    private Valve asValve(String tunnel, List<Valve> valveList) {
        for (Valve valve : valveList) {
            if (valve.name.equals(tunnel)) {
                return valve;
            }
        }
        return null;
    }
}



