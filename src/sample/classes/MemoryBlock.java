package sample.classes;

import java.util.Comparator;

public class MemoryBlock {
    int start;
    int end;

    static Comparator <MemoryBlock> byEnd = Comparator.comparingInt(o -> o.end);

    MemoryBlock(int start, int end){
        if(start > Configuration.OSMemoryVolume) {
            this.start = start;
            this.end = end;
        }
    }

    @Override
    public String toString() {
        return "{" + start + ", " + end + '}';
    }
}
