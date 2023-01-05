package hr.bp.aoc.day7;

public class Day7File extends Day7BaseObject {
    protected Double size;

    public Day7File(String name, Day7Directory location, Integer size) {
        super(name, location);
        this.size = Double.valueOf(size);
        System.out.println(size);
    }

    @Override
    protected Double getSize() {
        return size;
    }

    @Override
    protected Double getSumOfPartOneDirectories() {
        return Double.valueOf(0);
    }

    @Override
    protected Day7Directory getBestPartTwoDirectory(Double missingSpace) {
        return null;
    }
}
