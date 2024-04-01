package Lab21;

import java.io.IOException;
import java.net.*;

public class UDPServer {
    private static final int PORT = 9876;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("UDP Сервер запущен...");

            while (true) {
                byte[] receiveData = new byte[BUFFER_SIZE];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String word = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Слово от клиента получено: " + word);
                word = word.toLowerCase();

                boolean isPalindrome = checkPalindrome(word);

                byte[] sendData = String.valueOf(isPalindrome).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
                System.out.println("Ответ клиенту отправлен.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;

        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

