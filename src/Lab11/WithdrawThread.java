import java.util.Random;

class WithdrawThread extends Thread {
    private BankAccount account;
    private double withdrawalAmount;
    private Random random = new Random();

    public WithdrawThread(BankAccount account, double withdrawalAmount) {
        this.account = account;
        this.withdrawalAmount = withdrawalAmount;
    }

    public void run() {
        for (int i = 0; i < 5; i++) { // Число операций фиксированное
            try {
                Thread.sleep(random.nextInt(1000)); // Интервал времени между операциями задается случайным числом
                account.withdraw(withdrawalAmount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
