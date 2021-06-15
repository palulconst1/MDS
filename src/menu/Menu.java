package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Roulette.AllGui;
import Roulette.BetSystem;
import Roulette.MyRoulette;
import service.*;
import slots.Slots;

import java.io.IOException;

public class Menu
        extends JFrame
        implements ActionListener {

    private Container container;
    private JLabel title;
    private JButton login;
    private JButton slots;
    private JButton blackjack;
    private JButton roulette;

    public Menu()
    {
        setTitle("menu");
        setBounds(300, 90, 500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("menu");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(200, 30);
        container.add(title);

        slots = new JButton("Slots");
        slots.setFont(new Font("Arial", Font.PLAIN, 35));
        slots.setSize(200, 50);
        slots.setLocation(130, 140);
        slots.addActionListener(this);
        container.add(slots);

        blackjack = new JButton("Blackjack");
        blackjack.setFont(new Font("Arial", Font.PLAIN, 35));
        blackjack.setSize(200, 50);
        blackjack.setLocation(130, 200);
        blackjack.addActionListener(this);
        container.add(blackjack);

        roulette = new JButton("Roulette");
        roulette.setFont(new Font("Arial", Font.PLAIN, 35));
        roulette.setSize(200, 50);
        roulette.setLocation(130, 260);
        roulette.addActionListener(this);
        container.add(roulette);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == blackjack) {
            try {
                UserService us = new UserService().getInstance();
                Blackjack alpha = new Blackjack(us.getLogged().getEmail(), us.getLogged().getCurrency());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if (e.getSource() == roulette) {
            try {
                UserService us = new UserService().getInstance();
                Integer balance = us.getLogged().getCurrency();
                BetSystem betSystem = new BetSystem(balance);
                MyRoulette rouletteSystem = new MyRoulette();
                AllGui systemGUI = null;
                systemGUI = new AllGui(800, 500, betSystem, rouletteSystem);
                systemGUI.show(true);
            } catch (InterruptedException | IOException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
            if (e.getSource() == slots) {
                try {
                    UserService us = new UserService().getInstance();
                    Slots s = new Slots();
                } catch (IOException interruptedException) {
                    interruptedException.printStackTrace();
                }
        }
        else {

        }
    }
}

