package Roulette;

import com.sun.source.tree.MemberSelectTree;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.text.MutableAttributeSet;


class Slice {
    String text;
    int value;
    Color color;

    public Slice(int value, Color color, String text) {
        this.value = value;
        this.color = color;
        this.text = text;
    }
}

class PieChart extends JPanel{

    private ArrayList<Slice> slices;
    private Integer angle = 90;

    PieChart() {
        super();
        setVisible(true);
        getSlices();
    }

    public void getSlices()
    {
        Integer[] rouletteNumebers = {0,32,15,19,4,21,2,25,17,34,6,27,13,36,11,30,8,23,10,5,24,16,33,1,20,14,31,9,22,18,29,7,28,12,35,3,26};

        ArrayList<Slice> slices = new ArrayList<Slice>();
        ///System.out.println(rouletteNumebers.length);

        ///we cannot make equal slices because there are 37 slices
        ///so we calculate the slices size be considering the black
        ///and the red slices  aprox. equal (9, 10, 18 is the best combination)

        for(int i = 0; i < rouletteNumebers.length; i++)
        {
            if (i == 0)
                slices.add(new Slice(18, Color.green, "0"));
            else if (i % 2 == 1)
                slices.add(new Slice(9, Color.red, rouletteNumebers[i].toString()));
            else
                slices.add(new Slice(10, Color.black, rouletteNumebers[i].toString()));
        }
        System.out.println(slices);
        this.slices = slices;
    }

    public void setAngle(Integer angle)
    {
        this.angle = angle;
        repaint();
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        drawPie((Graphics2D) g, getBounds());
        this.setVisible(true);
    }

    void drawPie(Graphics2D g, Rectangle area) {
        int curValue = angle;
        int startAngle = 0;

        ///find center and radius
//        float centerX = (float) (1.0 * (area.width - area.x) / 2) - 5;
//        float centerY = (float) (1.0 * (area.height - area.y) / 2) - 5;
//        double radius = Math.sqrt((area.width * area.width) + (area.height * area.height)) / 4;

        for (Slice mySlice : slices)
        {
            startAngle = curValue;
            System.out.println(mySlice.color);
            g.setColor(mySlice.color);
            g.fillArc(area.x, area.y, area.width, area.height, startAngle, mySlice.value);

//            System.out.println(area.width + " " + area.height + " " + radius);
//            double textX = centerX - ((Math.sin((double) (startAngle + (mySlice.value / 2))) * ((area.width - 50) * 1.0 / 2)));
//            double textY = centerY - ((Math.cos((double) (startAngle + (mySlice.value / 2))) * ((area.height - 50) * 1.0 / 2)));
//            g.setColor(Color.white);
//            g.drawString(mySlice.text, (float) textX, (float) textY);

//            g.setColor(Color.white);
//            g.fillOval((int)(centerX - radius + 10), (int)(centerY - radius + 10), 10, 10);
//            g.drawOval((int)(centerX - radius + 10), (int)(centerY - radius + 10), 10, 10);

            curValue += mySlice.value;
        }

//        g.setColor(Color.white);
//        g.fillOval((int)(centerX), (int)(centerY), 10, 10);
//        g.drawOval((int)(centerX), (int)(centerY), 10, 10);

    }
}

class Main {
    public static void main(String[] argv) throws InterruptedException {

        JFrame frame = new JFrame();
        frame.setSize(400, 400);

        PieChart p = new PieChart();

        frame.getContentPane().add(p);
        frame.setVisible(true);

        Integer LIMIT = 440;
        for(int i = 0; i < LIMIT; i++)
        {
            p.setAngle((i + 90) % 360);
            TimeUnit.MILLISECONDS.sleep(i / 20);
        }
    }
}
