package sample.classes;


public class ReadyQueue {
    public static Queue queue;

    public ReadyQueue() {
        queue = new Queue();
        AddQueue addQueue = new AddQueue();
        addQueue.run();
    }

    public Queue getQueue() {
        return queue;
    }

    static void addQueue() {
        queue.add(5);
        queue.addReadyQueue();
//        if (queue.getReadyQueue().get(0) != null) {
//            queue.removeReadyQueue(0);
//            MemoryScheduler.releaseMemoryBlock(queue.getReadyQueue().get(0).getMemoryBlock());
//        }
    }

    @Override
    public String toString() {
        return "Queue{" +
                "queue = \n" + queue.toString() +
                '}';
    }
}
