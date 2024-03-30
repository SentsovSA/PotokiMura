public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        double depositAmount = 200;
        double withdrawalAmount = 300;

        DepositThread depositThread = new DepositThread(account, depositAmount);
        WithdrawThread withdrawThread = new WithdrawThread(account, withdrawalAmount);

        depositThread.start();
        withdrawThread.start();
    }
}