package Lab22;

import java.io.*;
import java.net.*;

public class TCPClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("REGISTER LOL 0.15");
            out.println("REGISTER ARBIDOL 0.3");
            out.println("INPUT LOL 150");
            out.println("INPUT ARBIDOL 500");
            out.println("INPUT LOL 300");
            out.println("INPUT KEK 150");
            out.println("PAY ARBIDOL");
            out.println("PAY LOL");
            out.println("CORRECT LOL 160");
            out.println("INFO LOL");
            out.println("INFO ARBIDOL");

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Ответ от сервера: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

