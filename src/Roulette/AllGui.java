package Roulette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllGui{

    private JFrame gameFrame;
    private GridBagConstraints constraints;
    private Integer width, height;

    private BetSystem betSystem;

    private PieChart wheelRoulette;
    private MyRoulette myRoulette;

    private JButton spin;
    private JLabel balance, dialogue;

    private JTextArea bet;

    private JRadioButton high, low, red, black;

    private Integer lineFollower = 0;

    private Font title = new Font("Verdana", Font.PLAIN, 24);
    private Font little = new Font("Verdana", Font.PLAIN, 16);

    public AllGui(Integer width, Integer height, BetSystem betSystem, MyRoulette myRoulette) throws InterruptedException {
        this.width = width;
        this.height = height;

        this.betSystem = betSystem;
        this.myRoulette = myRoulette;

        createWindow();
        addDialogBox();
        addBalanceBox();
        addButtons();
        addBetArea();
        addSpinButton();
        //getAllData();
    }

    public void show(Boolean value) { gameFrame.setVisible(value); }

    private void createWindow() throws InterruptedException {
        constraints = new GridBagConstraints();

        gameFrame = new JFrame();
        gameFrame.setSize(width,height);
        gameFrame.setLayout(new GridBagLayout());

        constraints.anchor = GridBagConstraints.WEST;
        //addWheel();
    }

    public void addWheel() {
        wheelRoulette = new PieChart();
        wheelRoulette.setSize(20, 20);
        //wheelRoulette.setVisible(true);
        System.out.println(wheelRoulette.getPreferredSize());

        JPanel panel = new JPanel();
        panel.setBounds(40,80,200,200);

        panel.add(wheelRoulette);

        gameFrame.add(panel);
    }

    private void addDialogBox()
    {
        dialogue = new JLabel();
        dialogue.setText("Bet a lot and spin a little!");
        dialogue.setFont(title);

        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = lineFollower++;
        constraints.weighty = 0.02;
        constraints.weightx = 0;


        gameFrame.add(dialogue, constraints);
    }

    private void addBalanceBox()
    {
        balance = new JLabel();
        balance.setText("You have " + betSystem.getBalance() + "$");
        balance.setFont(little);

        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = lineFollower++;
        constraints.weighty = 0.02;

        gameFrame.add(balance, constraints);
    }

    private void changeBalanceBox()
    {
        balance.setText("You have " + betSystem.getBalance() + "$");
    }

    private void changeDialogBox(String message)
    {
        dialogue.setText(message);
    }

    private void addButtonGroups(ArrayList<JRadioButton> buttonList, Integer line)
    {
        ButtonGroup buttonGroup = new ButtonGroup();
        Integer counter = 0;

        for(JRadioButton button : buttonList)
        {
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = counter;
            constraints.gridy = line;
            constraints.weighty = 0;

            ///initialize radio button
            buttonGroup.add(button);
            gameFrame.add(button, constraints);
            counter++;
        }
    }

    private void addButtons()
    {

        red = new JRadioButton("RED");
        red.setFont(little);
        red.setForeground(Color.red);

        black = new JRadioButton("BLACK");
        black.setFont(little);

        high = new JRadioButton("HIGH");
        high.setFont(little);
        high.setForeground(Color.orange);

        low = new JRadioButton("LOW");
        low.setFont(little);
        low.setForeground(Color.blue);

        ArrayList<JRadioButton> colorButtons = new ArrayList<>();
        colorButtons.add(red);
        colorButtons.add(black);

        ArrayList<JRadioButton> positionButton = new ArrayList<>();
        positionButton.add(low);
        positionButton.add(high);

        addButtonGroups(colorButtons, lineFollower++);
        addButtonGroups(positionButton, lineFollower++);
    }

    private Map<String, Boolean> getAllData()
    {
        Map<String, Boolean> values = new HashMap<>();
        values.put(high.getText(), high.isSelected());
        values.put(low.getText(), low.isSelected());
        values.put(red.getText(), red.isSelected());
        values.put(black.getText(), black.isSelected());

        ///System.out.println(values);

        return values;
    }

    private Integer getBet()
    {
        return Integer.parseInt(bet.getText());
    }

    private void addBetArea()
    {

        bet = new JTextArea();
        bet.setText("Here you put your bet");
        bet.setFont(little);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = lineFollower;
        constraints.weighty = 0.1;

        gameFrame.add(bet, constraints);
        constraints.gridwidth = 0;
    }

    private void addSpinButton()
    {
        spin = new JButton("SPIN");
        spin.setFont(title);
        spin.setBackground(Color.green);

        spin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                RouletteNumber rouletteNumber =  myRoulette.spin();
                changeDialogBox("It's a " + rouletteNumber.getNumber() + " " + rouletteNumber.getColor());

                if(rouletteNumber.getColor().equals("RED"))
                    dialogue.setForeground(Color.red);

                else if(rouletteNumber.getColor().equals("BLACK"))
                    dialogue.setForeground(Color.black);

                else dialogue.setForeground(Color.green);

                Integer pseudoScore = betSystem.calculateMulWin(rouletteNumber, getAllData());
                System.out.println(pseudoScore);
                betSystem.setBet(getBet());
                betSystem.setBalance(pseudoScore);

                changeBalanceBox();
                ///SOMETHING WRONG WITH THE SCORE CALCULATION
            }
        });

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = lineFollower++;
        constraints.weighty = 0.1;

        gameFrame.add(spin, constraints);
    }
}


class Test{
    public static void main(String[] args) throws InterruptedException {
       /// AllGui gameRender = new AllGui(800, 500);
        //gameRender.addButtons();
    }

}