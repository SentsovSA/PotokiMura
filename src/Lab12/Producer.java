package Lab12;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

class Producer extends Thread {
    private BlockingQueue<Integer> queue;
    private List<Integer> dataList;
    private Random random = new Random();

    public Producer(BlockingQueue<Integer> queue, List<Integer> dataList) {
        this.queue = queue;
        this.dataList = dataList;
    }

    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(dataList.size());
                int element = dataList.get(index);
                queue.put(element);
                System.out.println("Производитель выбрал: " + element);
                Thread.sleep(random.nextInt(1000));
            }
            queue.put(Integer.MIN_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
