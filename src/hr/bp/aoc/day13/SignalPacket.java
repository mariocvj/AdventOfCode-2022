package hr.bp.aoc.day13;

import java.util.ArrayList;
import java.util.List;

public class SignalPacket implements Comparable<SignalPacket> {

    private final List<Object> content;

    protected SignalPacket(List<Object> content) {
        this.content = content;
    }

    protected SignalPacket(int dividerPacket) {

        this.content = new ArrayList<>();
        List<Object> inner = new ArrayList<>();

        inner.add(dividerPacket);
        this.content.add(new SignalPacket(inner));
    }

    private int length() {
        return this.content.size();
    }

    private void convertIntegerToSignalPacket(int index) {
        List<Object> numberAsList;
        Object number = this.content.get(index);

        if (number instanceof Integer) {

            numberAsList = new ArrayList<>();
            numberAsList.add(number);
            this.content.set(index, new SignalPacket(numberAsList));
        }
    }

    @Override
    public int compareTo(SignalPacket other) {
        Object left;
        Object right;
        int result = 0;
        int i = 0;

        while ((this.length() > i) && (other.length() > i) && (result == 0)) {

            left = this.content.get(i);
            right = other.content.get(i);

            if ((left instanceof Integer) && (right instanceof Integer)) {

                result = Integer.compare((Integer) left, ((Integer) right));

            } else {

                this.convertIntegerToSignalPacket(i);
                other.convertIntegerToSignalPacket(i);

                result = ((SignalPacket) this.content.get(i)).compareTo((SignalPacket) other.content.get(i));
            }
            i++;
        }

        if (result == 0) {
            result = Integer.compare(this.length(), other.length());
        }

        return result;
    }
}
