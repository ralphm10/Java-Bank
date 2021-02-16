import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Statement {

    private final String HEADER = "date || credit || debit || balance";
    public ArrayList<String> transactions = new ArrayList<>();
    public LocalDateTime now = LocalDateTime.now();

    public String printStatement(){
        if (getTransactions().size() == 0) {
            return HEADER;
        }
        return HEADER + "\n" + String.join("\n",getTransactions());
    }

    public void recordDeposit(double amount, double balance) {
        transactions.add(printDate(now) + " || " + formatAmount(amount) + " || || " + formatAmount(balance));
    }

    public void recordWithdrawal(double amount, double balance) {
        transactions.add(printDate(now) + " || || " + formatAmount(amount) + " || " + formatAmount(balance));
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public String printDate(LocalDateTime now){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dtf.format(now);
    }

    public String formatAmount(double amount) {
        return new DecimalFormat("#.00").format(amount);
    }
}
