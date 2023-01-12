package hr.bp.aoc.day17;

/**
 * @author Mario
 */
public class JetFlowProvider {
    String pattern;
    int next;

    public JetFlowProvider(String pattern) {
        this.pattern = pattern;
        this.next = 0;
    }

    public char getJetFlow() {
        char flow = pattern.charAt(next);
        next++;
        if (next == pattern.length()) {
            next = 0;
        }
        return flow;
    }
}
