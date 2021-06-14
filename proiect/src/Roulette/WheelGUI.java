package Roulette;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class WheelGUI{

    private PieChart roulette;
    private Integer lowerBound = 0;
    private Integer upperBound;


    WheelGUI() throws InterruptedException {
        roulette = new PieChart();
    }

    public PieChart getRoulette() {
        return roulette;
    }

    public void spinRoulette() throws InterruptedException {

        int rand = (int) (Math.random() * 360);
        upperBound = lowerBound + 360 + rand;

        for(int i = lowerBound; i < upperBound; i++)
        {
            roulette.setAngle((i + 90) % 360);
            TimeUnit.MILLISECONDS.sleep(i / 20);
        }

        lowerBound = upperBound % 360;
    }
}
