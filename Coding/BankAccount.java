public class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount (String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        else {return false;}    
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        else {return false;}
    }

    public static void main(String[] args) {
        BankAccount a = new BankAccount("1234", 0);
        a.deposit(10);
        a.withdraw(5);
        System.out.println(a.getAccountNumber());
        System.out.println(a.getBalance());
    }


    

    
    
}
