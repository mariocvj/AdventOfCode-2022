package hr.bp.aoc.day7;

import java.util.List;

public class Day7Directory extends Day7BaseObject {
    protected List<Day7BaseObject> content;

    public Day7Directory(String name, Day7Directory location, List<Day7BaseObject> content) {
        super(name, location);
        this.content = content;
    }

    protected List<Day7BaseObject> getContent() {
        return content;
    }

    protected Day7BaseObject setChild(Day7BaseObject newChild){
        Day7BaseObject setedChild = null;
        Boolean existedBefore = false;

        for( Day7BaseObject existingChild : content){
            if (existingChild.getName() == newChild.getName()){
                existedBefore = true;
                setedChild = existingChild;
            }
        }

        if (!(existedBefore)){
            content.add(newChild);
            setedChild=newChild;

        }
        return setedChild;
    }

    @Override
    protected Double getSize() {
        Double size = Double.valueOf(0);

        for(Day7BaseObject childInstance : content){
            size = size + childInstance.getSize();
        }

        return size;
    }

    @Override
    protected Double getSumOfPartOneDirectories(){

        Double partOneSum = this.getSize();
        if(partOneSum > 100000 ) {
            partOneSum = Double.valueOf(0);
        }

        for(Day7BaseObject childInstance : content){
            partOneSum = partOneSum + childInstance.getSumOfPartOneDirectories();
        }

        return partOneSum;
    }

    @Override
    protected Day7Directory getBestPartTwoDirectory(Double missingSpace) {
        Day7Directory bestDirectoryOfThis = null;
        Day7Directory bestDirectoryOfChild;

        if ( this.getSize() >= missingSpace){
            bestDirectoryOfThis = this;

            for (Day7BaseObject child : content){
                bestDirectoryOfChild = child.getBestPartTwoDirectory(missingSpace);

                if( ! (bestDirectoryOfChild == null) ){

                    if (  bestDirectoryOfThis.getSize()  >  bestDirectoryOfChild.getSize()  ){
                        bestDirectoryOfThis = bestDirectoryOfChild;
                    }
                }
            }
        }
        return bestDirectoryOfThis;
    }
}
