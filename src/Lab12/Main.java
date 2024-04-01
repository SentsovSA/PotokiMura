package Lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        List<Integer> dataList = readDataFromFile("/Users/user/IdeaProjects/PotokiMura/src/Lab12/data.txt");

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        Producer producer = new Producer(queue, dataList);
        Consumer consumer = new Consumer(queue);

        producer.start();
        consumer.start();
    }

    private static List<Integer> readDataFromFile(String filename) {
        List<Integer> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("[\\s.,!?;:]+");
                for (String part : parts) {
                    dataList.add(Integer.parseInt(part));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
