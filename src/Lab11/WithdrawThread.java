package Lab11;

import java.util.Random;

class WithdrawThread extends Thread {
    private BankAccount account;
    private double withdrawalAmount;
    private Random random = new Random();
    private int numWithdraws;

    public WithdrawThread(BankAccount account, double withdrawalAmount, int numWithdraws) {
        this.account = account;
        this.withdrawalAmount = withdrawalAmount;
        this.numWithdraws = numWithdraws;
    }

    public void run() {
        for (int i = 0; i < numWithdraws; i++) {
            try {
                Thread.sleep(random.nextInt(1000));
                account.withdraw(withdrawalAmount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
