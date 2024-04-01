package Lab11;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(10);
        double depositAmount = 200;
        double withdrawalAmount = 300;
        int numDeposits = 5;
        int numWithdraws = 5;

        DepositThread depositThread = new DepositThread(account, depositAmount, numDeposits);
        WithdrawThread withdrawThread = new WithdrawThread(account, withdrawalAmount, numWithdraws);

        depositThread.start();
        withdrawThread.start();
    }
}