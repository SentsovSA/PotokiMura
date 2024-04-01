package Lab12;

import java.util.concurrent.BlockingQueue;

class Consumer extends Thread {
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            long product = 1;
            while (true) {
                int element = queue.take();
                if (element == Integer.MIN_VALUE) {
                    break;
                }
                if (element > 0) {
                    product *= element;
                    System.out.println("Потребитель вычислил произведение: " + product);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
