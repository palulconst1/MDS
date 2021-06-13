package Roulette;

public class main {
    public static void main(String[] args) throws InterruptedException {
        Integer balance = 1000;
        BetSystem betSystem = new BetSystem(balance);
        MyRoulette rouletteSystem = new MyRoulette();

        AllGui systemGUI = new AllGui(800, 500, betSystem, rouletteSystem);

        systemGUI.show(true);
    }
}
