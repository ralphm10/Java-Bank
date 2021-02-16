import net.time4j.PlainDate;
import net.time4j.SystemClock;
import net.time4j.format.expert.ChronoFormatter;
import net.time4j.format.expert.PatternType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Statement {

    private final String HEADER = "date || credit || debit || balance";
    public ArrayList<String> transactions = new ArrayList<>();

    public String printStatement(){
        if (getTransactions().size() == 0) {
            return HEADER;
        }
        return HEADER + "\n" + String.join("\n",getTransactions());
    }

    public void recordDeposit(double amount, double balance) {
        transactions.add(printDate() + " || " + formatAmount(amount) + " || || " + formatAmount(balance));
    }

    public void recordWithdrawal(double amount, double balance) {
        transactions.add(printDate() + " || || " + formatAmount(amount) + " || " + formatAmount(balance));
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public String printDate(){
        PlainDate today = SystemClock.inLocalView().today();

        String date =
                today.print(
                        ChronoFormatter.ofDatePattern(
                                "dd/MM/uuuu",
                                PatternType.CLDR,
                                Locale.ENGLISH
                        )
                );
        return date;
    }

    public String formatAmount(double amount) {
        return new DecimalFormat("#.00").format(amount);
    }
}
