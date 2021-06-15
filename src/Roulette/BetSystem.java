package Roulette;

import javax.xml.stream.FactoryConfigurationError;
import java.awt.print.Book;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;

public class BetSystem {
    private Integer balance;
    private Integer bet = 0;

    public BetSystem(Integer balance) {
        this.balance = balance;
    }

    Integer getBalance() { return balance; }
    void setBet(Integer bet) { this.bet = bet; }

    void setBalance(Integer mulWin)
    {
        balance += bet * mulWin;
    }

    Integer calculateMulWin(RouletteNumber rouletteNumber, Map<String, Boolean> choices)
    {
        int localScore = 0;

        localScore += boolToInt(choices.get(rouletteNumber.getColor()));
        localScore += boolToInt(choices.get(rouletteNumber.getPosition()));

        return localScore;
    }

    private int boolToInt(Boolean value)
    {
        if (value.equals(true)) return 1;
//        if(value == null) return 0;
        return -1;
    }
}
