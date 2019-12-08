package sample.classes;

import java.util.ArrayList;

public class MemoryScheduler {
    private static ArrayList<MemoryBlock> memoryBlocks = new ArrayList<>();

    public static String print() {
        String result = "[ ";
        for (MemoryBlock memoryBlock : memoryBlocks) {
            result += memoryBlock + " ";
        }
        return result + " ]";
    }

    static boolean findFreeBlock(int size, Process process) {
        boolean check = false;
        if (memoryBlocks.isEmpty()) {
            process.setMemoryBlock(new MemoryBlock(Configuration.OSMemoryVolume + 1, Configuration.OSMemoryVolume + size + 1));
            memoryBlocks.add(process.getMemoryBlock());
            check = true;
        } else {
            if ((memoryBlocks.get(memoryBlocks.size() - 1).end + size) < Configuration.memoryVolume) {
                check = add(size, process);
                System.out.println(process.getMemoryBlock());
            } else {
                if (Configuration.memoryVolume - memoryBlocks.get(memoryBlocks.size() - 1).end + size <= 0) {
                    process.setMemoryBlock(new MemoryBlock(memoryBlocks.get(memoryBlocks.size() - 1).end + 1, memoryBlocks.get(memoryBlocks.size() - 1).end + size + 1));
                    memoryBlocks.add(process.getMemoryBlock());
                    check = true;
                } else {
                    memoryBlocks.sort(MemoryBlock.byEnd);
                    ArrayList<MemoryBlock> tempMemoryBlocks = new ArrayList<>();
                    for (int i = 0; i < memoryBlocks.size() - 1; i++) {
                        if (memoryBlocks.get(i + 1).start - memoryBlocks.get(i).end > size) {
                            MemoryBlock tempMemoryBlock = new MemoryBlock(memoryBlocks.get(i).end, memoryBlocks.get(i + 1).start);
                            tempMemoryBlocks.add(tempMemoryBlock);
                        }
                    }
                    if (!tempMemoryBlocks.isEmpty()) {
                        memoryBlocks.add(new MemoryBlock(tempMemoryBlocks.get(0).start, size));
                        check = true;
                    }
                }
            }
        }
        return check;
    }

    public static void releaseMemoryBlock(MemoryBlock memoryBlock) {
        memoryBlocks.remove(memoryBlock);
    }

    public static boolean add(int size, Process process) {
        boolean check = false;
        if (memoryBlocks.get(memoryBlocks.size() - 1).end + size < Configuration.memoryVolume) {
            process.setMemoryBlock(new MemoryBlock(memoryBlocks.get(memoryBlocks.size() - 1).end + 1, memoryBlocks.get(memoryBlocks.size() - 1).end + size + 1));
            memoryBlocks.add(process.getMemoryBlock());
            check = true;
        }
        return check;
    }
}
