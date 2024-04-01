package Lab22;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class TCPServer {
    private static final int PORT = 12345;
    private Map<String, Meter> meters;

    public TCPServer() {
        this.meters = new HashMap<>();
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        server.start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("TCP Сервер запущен...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключен : " + clientSocket);

                Thread clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String[] tokens = inputLine.split(" ");
                    String command = tokens[0];

                    switch (command) {
                        case "REGISTER":
                            registerMeter(tokens[1], Double.parseDouble(tokens[2]));
                            break;
                        case "INPUT":
                            inputReading(tokens[1], Double.parseDouble(tokens[2]));
                            break;
                        case "PAY":
                            payBill(tokens[1]);
                            break;
                        case "CORRECT":
                            correctReading(tokens[1], Double.parseDouble(tokens[2]));
                            break;
                        case "INFO":
                            getMeterInfo(tokens[1]);
                            break;
                        default:
                            out.println("Неизвестная команда");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                    in.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void registerMeter(String owner, double tariff) {
            meters.put(owner, new Meter(owner, tariff));
            out.println("Счетчик зарегистрирован");
        }

        private void inputReading(String owner, double reading) {
            Meter meter = meters.get(owner);
            if (meter != null) {
                meter.inputReading(reading);
                out.println("Показания введены");
            } else {
                out.println("Счетчик не найден");
            }
        }

        private void payBill(String owner) {
            Meter meter = meters.get(owner);
            if (meter != null) {
                double bill = meter.calculateBill();
                meter.reset();
                out.println("Счет оплачен. Сумма:  " + bill);
            } else {
                out.println("Счетчик не найден");
            }
        }

        private void correctReading(String owner, double reading) {
            Meter meter = meters.get(owner);
            if (meter != null) {
                meter.correctReading(reading);
                out.println("Показания откорректированы");
            } else {
                out.println("Счетчик не найден");
            }
        }

        private void getMeterInfo(String owner) {
            Meter meter = meters.get(owner);
            if (meter != null) {
                out.println(meter);
            } else {
                out.println("Счетчик не найден");
            }
        }
    }
}

class Meter {
    private String owner;
    private double tariff;
    private double previousReading;
    private double currentReading;

    public Meter(String owner, double tariff) {
        this.owner = owner;
        this.tariff = tariff;
        this.previousReading = 0.0;
        this.currentReading = 0.0;
    }

    public void inputReading(double reading) {
        previousReading = currentReading;
        currentReading = reading;
    }

    public void correctReading(double reading) {
        currentReading = reading;
    }

    public double calculateBill() {
        return (currentReading - previousReading) * tariff;
    }

    public void reset() {
        previousReading = currentReading;
    }

    @Override
    public String toString() {
        return "Счетчик{" +
                "владелец ='" + owner + '\'' +
                ", тариф =" + tariff +
                ", текущие показания =" + currentReading +
                '}';
    }
}

