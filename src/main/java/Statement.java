import net.time4j.PlainDate;
import net.time4j.SystemClock;
import net.time4j.format.expert.ChronoFormatter;
import net.time4j.format.expert.PatternType;

import java.util.ArrayList;
import java.util.Locale;

public class Statement {

    private String HEADER = "date || credit || debit || balance";
    public ArrayList<String> transactions = new ArrayList<>();

    public String printStatement(){
        if (getTransactions().size() == 0) {
            return HEADER;
        }
        return HEADER + "\n" + getTransactions().get(0);
    }

    public void recordDeposit(int amount, int balance) {
        transactions.add(printDate() + " || " + amount + " || || " + balance);
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
}
