import java.text.DecimalFormat;

class BankAccount {
    private double balance;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println("Внесение: " + decimalFormat.format(amount) + "; баланс: " + decimalFormat.format(balance));
        notify();
    }

    public synchronized void withdraw(double amount) {
        while (balance < amount) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        balance -= amount;
        System.out.println("Списание: " + decimalFormat.format(amount) + "; баланс: " + decimalFormat.format(balance));
    }
}
