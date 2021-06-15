package auth;

import model.*;
import service.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register
        extends JFrame
        implements ActionListener {

    // Components of the Form
    private Container container;
    private JLabel title;
    private JLabel email;
    private JTextField temail;
    private JLabel password;
    private JTextField tpassword;
    private JCheckBox term;
    private JButton sub;
    private JButton reset;
    private JLabel res;
    private JTextArea resadd;


    public Register()
    {
        setTitle("Registration Form");
        setBounds(300, 90, 500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(130, 30);
        container.add(title);

        email = new JLabel("Username");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(100, 20);
        email.setLocation(100, 100);
        container.add(email);

        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 15));
        temail.setSize(190, 20);
        temail.setLocation(200, 100);
        container.add(temail);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(100, 150);
        container.add(password);

        tpassword = new JTextField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setSize(190, 20);
        tpassword.setLocation(200, 150);
        container.add(tpassword);

        term = new JCheckBox("Accept Terms And Conditions");
        term.setFont(new Font("Arial", Font.PLAIN, 15));
        term.setSize(250, 20);
        term.setLocation(100, 310);
        container.add(term);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(100, 350);
        sub.addActionListener(this);
        container.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(220, 350);
        reset.addActionListener(this);
        container.add(reset);



        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 250);
        container.add(res);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        final UserService userService;

        if (e.getSource() == sub) {
            if (term.isSelected()) {

                User obj = new User(String.valueOf(temail.getText()), String.valueOf(tpassword.getText()));
                try {
                    userService = UserService.getInstance();
                    int ok = 1;
                    Set<User> usrs = userService.getUsers();
                    for( User usr:usrs){
                        if(usr.getEmail().equals(obj.getEmail())){
                            res.setText("change username");
                            ok = 0;
                            break;
                        }
                    }
                    if(ok == 1) {
                        userService.add(obj);
                        res.setText("Registration Successfully");
                        Login l = new Login();
                        super.dispose();
                    }
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            else {
                resadd.setText("");
                res.setText("Please accept the"
                        + " terms & conditions..");
            }
        }

        else if (e.getSource() == reset) {
            String def = "";
            temail.setText(def);
            tpassword.setText(def);
            res.setText(def);
            term.setSelected(false);
        }
    }
}

