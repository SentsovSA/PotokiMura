package Lab21;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9876;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        //String word = "Довод";
        String word = "пример";

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(SERVER_HOST);

            byte[] sendData = word.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);
            System.out.println("Запрос отправлен на сервер.");

            byte[] receiveData = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            boolean isPalindrome = Boolean.parseBoolean(response);
            if (isPalindrome) {
                System.out.println("'" + word + "' палиндром.");
            } else {
                System.out.println("'" + word + "' не палиндром.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

