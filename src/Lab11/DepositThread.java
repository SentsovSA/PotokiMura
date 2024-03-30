import java.util.Random;

class DepositThread extends Thread {
    private BankAccount account;
    private double depositAmount;
    private Random random = new Random();

    public DepositThread(BankAccount account, double depositAmount) {
        this.account = account;
        this.depositAmount = depositAmount;
    }

    public void run() {
        for (int i = 0; i < 5; i++) { // Число операций фиксированное
            try {
                Thread.sleep(random.nextInt(1000)); // Интервал времени между операциями задается случайным числом
                account.deposit(depositAmount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
