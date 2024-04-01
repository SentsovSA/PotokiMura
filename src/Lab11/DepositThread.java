package Lab11;

import java.util.Random;

class DepositThread extends Thread {
    private BankAccount account;
    private double depositAmount;
    private Random random = new Random();
    private int numDeposits;

    public DepositThread(BankAccount account, double depositAmount, int numDeposits) {
        this.account = account;
        this.depositAmount = depositAmount;
        this.numDeposits = numDeposits;
    }

    public void run() {
        for (int i = 0; i < numDeposits; i++) {
            try {
                Thread.sleep(random.nextInt(1000)); // Интервал времени между операциями задается случайным числом
                account.deposit(depositAmount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
