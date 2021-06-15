package slots;
import java.awt.*;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import service.*;
import model.*;
import java.util.Set;

public class Slots {

    private JButton btnCash, btnSpin;
    private JFrame frmFrame;
    private JLabel lblCredits, lblLost, lblMatchThree, lblMatchTwo, lblMoney, lblReel1, lblReel2, lblReel3, lblStatus, lblWon;
    private JPanel backgroundSquare, backgroundSquare1, backgroundSquare2, backgroundSquare3;
    private JProgressBar prgbarCheatUnlocker;
    private JSeparator sepCheats, sepStats, sepStats2, sepStatus;
    private int credits = 100, boughtCredits = 100, bet = 15, match1, match2, win, lost;
    private double payout = 25.0, askedCredits = 10.0, funds;
    private int reel1 = 7, reel2 = 7, reel3 = 7;
    private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
    private DecimalFormat df = new DecimalFormat("0.00");

    public Slots() throws IOException {
        UserService us = new UserService().getInstance();
        Integer balance = us.getLogged().getCurrency();
        funds = balance;
        createForm();
        imagesList();
        addFields();
        addButtons();
        layoutFrame();
        layoutReels();
        layoutOther();

    }

    private void createForm() {

        frmFrame = new JFrame();
        frmFrame.setSize(768,256);
        frmFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frmFrame.setTitle("Slots");
        frmFrame.setResizable(false);
        frmFrame.setVisible(true);

        backgroundSquare = new JPanel();
        backgroundSquare.setBorder(BorderFactory.createEtchedBorder());

        backgroundSquare1 = new JPanel();
        backgroundSquare1.setBackground(new Color(255, 215, 0));
        backgroundSquare1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        backgroundSquare2 = new JPanel();
        backgroundSquare2.setBackground(new Color(255, 216, 0));
        backgroundSquare2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        backgroundSquare3 = new JPanel();
        backgroundSquare3.setBackground(new java.awt.Color(255, 215, 0));
        backgroundSquare3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));

    }

    private void addFields() {

        lblReel1 = new JLabel();
        lblReel2 = new JLabel();
        lblReel3 = new JLabel();

        sepStats = new JSeparator();
        lblMatchTwo = new JLabel();
        lblMatchTwo.setText("Matched Two: ");
        lblMatchThree = new JLabel();
        lblMatchThree.setText("Matched Three: ");
        lblWon = new JLabel();
        lblWon.setText("Won: ");

        sepStats2 = new JSeparator();
        sepStats2.setOrientation(SwingConstants.VERTICAL);
        lblCredits = new JLabel();
        lblCredits.setText("Credits: "+credits);
        lblMoney = new JLabel();
        lblMoney.setText("Money: £"+df.format(funds));
        lblLost = new JLabel();
        lblLost.setText("Lost: ");

        sepStatus = new JSeparator();
        lblStatus = new JLabel();
        lblStatus.setBackground(new Color(255, 255, 255));
        lblStatus.setFont(new Font("Arial", 1, 14));
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        sepCheats = new JSeparator();
        prgbarCheatUnlocker = new JProgressBar();
        prgbarCheatUnlocker.setToolTipText("Fill the bar to unlock the cheat menu.");

        lblReel1.setIcon(images.get(reel1));
        lblReel2.setIcon(images.get(reel2));
        lblReel3.setIcon(images.get(reel3));

    }

    private void addButtons() {

        btnSpin = new JButton();
        btnSpin.setBackground(new Color(50, 255, 50));
        btnSpin.setText("Spin");
        btnSpin.setToolTipText("Click to spin the reels!");
        btnSpin.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        btnSpin.setInheritsPopupMenu(true);
        btnSpin.setMaximumSize(new Dimension(200, 50));
        btnSpin.setMinimumSize(new Dimension(200, 50));
        btnSpin.addActionListener(new spinClick());

        btnCash = new JButton();
        btnCash.setText("Buy Credits");
        btnCash.setToolTipText("£"+df.format(bet)+" converts to "+boughtCredits+" credits.");
        btnCash.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCash.addActionListener(new buyCredits());


    }

    private void layoutFrame() {

        GroupLayout frameLayout = new GroupLayout(frmFrame.getContentPane());
        frmFrame.getContentPane().setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
                frameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        frameLayout.setVerticalGroup(
                frameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
    }

    private void layoutReels() {

        GroupLayout backgroundSquareLayout = new GroupLayout(backgroundSquare);
        backgroundSquare.setLayout(backgroundSquareLayout);
        backgroundSquareLayout.setHorizontalGroup(
                backgroundSquareLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundSquareLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(backgroundSquare1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(backgroundSquare2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(backgroundSquare3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundSquareLayout.setVerticalGroup(
                backgroundSquareLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundSquareLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(backgroundSquareLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(backgroundSquare2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(backgroundSquare1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(backgroundSquare3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout backgroundSquare1Layout = new GroupLayout(backgroundSquare1);
        backgroundSquare1.setLayout(backgroundSquare1Layout);
        backgroundSquare1Layout.setHorizontalGroup(
                backgroundSquare1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundSquare1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblReel1)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundSquare1Layout.setVerticalGroup(
                backgroundSquare1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundSquare1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblReel1)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout backgroundSquare2Layout = new GroupLayout(backgroundSquare2);
        backgroundSquare2.setLayout(backgroundSquare2Layout);
        backgroundSquare2Layout.setHorizontalGroup(
                backgroundSquare2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundSquare2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblReel2)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundSquare2Layout.setVerticalGroup(
                backgroundSquare2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundSquare2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblReel2)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout backgroundSquare3Layout = new GroupLayout(backgroundSquare3);
        backgroundSquare3.setLayout(backgroundSquare3Layout);
        backgroundSquare3Layout.setHorizontalGroup(
                backgroundSquare3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundSquare3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblReel3)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundSquare3Layout.setVerticalGroup(
                backgroundSquare3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundSquare3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblReel3)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

    }

    private void layoutOther() {

        GroupLayout layout = new GroupLayout(frmFrame.getContentPane());
        frmFrame.getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(sepCheats)
                                                        .addComponent(prgbarCheatUnlocker, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addGap(18, 18, 18)
                                                                        .addGap(18, 18, 18)
                                                                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                )
                                                                .addComponent(btnSpin, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(backgroundSquare, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(sepStats, GroupLayout.Alignment.TRAILING)
                                                                .addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(lblMatchTwo, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(lblWon, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(lblMatchThree, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addComponent(sepStats2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(lblLost, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(lblCredits, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(lblMoney, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(btnCash)
                                                                .addComponent(sepStatus, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap())))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(backgroundSquare, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnSpin, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(sepStats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblWon, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(lblMatchTwo, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(lblMatchThree, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                                        .addComponent(sepStats2)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblLost, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(lblCredits, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(lblMoney, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(btnCash, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(sepStatus, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(sepCheats, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(prgbarCheatUnlocker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)

                                )
                                .addContainerGap())
        );

        frmFrame.pack();

    }

    class buyCredits implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            buyCredits();
        }
    }

    public void buyCredits() {
        if (askedCredits <= funds) {
            funds -= askedCredits;
            lblMoney.setText("Money: £"+df.format(funds));
            credits += boughtCredits;
            lblCredits.setText("Credits: "+credits);
            lblStatus.setText("+"+boughtCredits+" credits added! -£"+df.format(askedCredits));
            try {
                UserService userService = new UserService().getInstance();
                Set<User> usrs = userService.getUsers();;
                for( User usr:usrs) {
                    if (usr.getEmail().equals(userService.getLogged().getEmail())) {
                        userService.remove(usr);
                        int x = usr.getUserID();
                        usr = new User(usr.getEmail(), usr.getPassword());
                        usr.setCurrency((int) funds);
                        usr.setUserID(x);
                        userService.add(usr);
                        break;
                    }
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            lblStatus.setText("Insufficient funds!");
        }
    }



    class spinClick implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if ( credits < bet &&  askedCredits > funds) {
                ;
            } else if ((credits - bet) >= 0) {
                generateNumbers();
                matchCheck();
            } else {
                lblStatus.setText("The bet is "+bet+" credits!");
            }
        }
    }

    public void generateNumbers() {
        Random rand = new Random();

        reel1 = rand.nextInt(images.size());
        reel2 = rand.nextInt(images.size());
        reel3 = rand.nextInt(images.size());

        setReelIcon(reel1, reel2, reel3);
    }

    public void setReelIcon(int ico1, int ico2, int ico3) {
        lblReel1.setIcon(images.get(ico1));
        lblReel2.setIcon(images.get(ico2));
        lblReel3.setIcon(images.get(ico3));
    }

    public void matchCheck() {
        if (reel1 == reel2 && reel2 == reel3) {
            lblStatus.setText("You matched 3 symbols ("+images.get(reel1).getDescription()+")! +£"+df.format(getPrize(payout))+"!");
            lblMatchThree.setText("Matched Three: "+matchThree());

        } else if (reel1 == reel2 || reel1 == reel3) {
            lblStatus.setText("You matched 2 symbols ("+images.get(reel1).getDescription()+")! +£"+df.format(getPrize(payout))+"!");
            lblMatchTwo.setText("Matched 2: "+matchTwo());

        } else if (reel2 == reel3) {
            lblStatus.setText("You matched 2 symbols ("+images.get(reel2).getDescription()+")! +£"+df.format(getPrize(payout))+"!");
            lblMatchTwo.setText("Matched 2: "+matchTwo());

        } else {
            lblStatus.setText("Sorry, you didn't match any symbols. -"+bet+" credits!");
            lblLost.setText("Lost: "+lose());
        }
        lblCredits.setText("Credits: "+(credits -= bet));
        lblMoney.setText("Money: £"+df.format((funds += getPrize(payout))));
        lblWon.setText("Wins: "+win());
    }


    public double getPrize(double prize) {
        if (reel1 == reel2 && reel2 == reel3) {

            prize = payout;
        }
        else if (reel1 == reel2 || reel1 == reel3 || reel2 == reel3) {

            prize = payout / 5;
        }
        else {
            prize = 0;
        }
        return prize;
    }


    public void imagesList() {
        images.add(createImageIcon("../slots/images/Banana.png", "Banana"));
        images.add(createImageIcon("../slots/images/Bar.png", "Bar"));
        images.add(createImageIcon("../slots/images/Bell.png", "Bell"));
        images.add(createImageIcon("../slots/images/Cherry.png", "Cherry"));
        images.add(createImageIcon("../slots/images/Clover.png", "Clover"));
        images.add(createImageIcon("../slots/images/Diamond.png", "Diamond"));
        images.add(createImageIcon("../slots/images/Plum.png", "Plum"));
        images.add(createImageIcon("../slots/images/Seven.png", "Seven"));
        images.add(createImageIcon("../slots/images/Watermelon.png", "Watermelon"));
    }
    public ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public int matchThree() {
        match1++;
        return match1;
    }

    public int matchTwo() {
        match2++;
        return match2;
    }

    public int lose() {
        lost++;
        return lost;
    }

    public int win() {
        win = match1 + match2;
        return win;
    }

//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                new Slots();
//            }
//        });
//
//    }

}