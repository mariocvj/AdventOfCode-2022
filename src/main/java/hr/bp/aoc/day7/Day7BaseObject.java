package hr.bp.aoc.day7;

public abstract class Day7BaseObject {
    private String name;
    private Day7Directory location;

    public Day7BaseObject(String name, Day7Directory location) {
        this.name = name;
        this.location = location;
    }

    protected String getName() {
        return name;
    }

    protected Day7Directory getLocation() {
        return location;
    }

    protected abstract Double getSize();

    protected abstract Double getSumOfPartOneDirectories();

    protected abstract Day7Directory getBestPartTwoDirectory(Double missingSpace);
}
