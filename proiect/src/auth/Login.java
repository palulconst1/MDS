package auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import menu.Menu;
import model.*;
import service.*;
import java.io.IOException;
import java.util.Set;

public class Login
        extends JFrame
        implements ActionListener {

    private Container container;
    private JLabel title;
    private JLabel email;
    private JTextField temail;
    private JLabel password;
    private JTextField tpassword;
    private JCheckBox term;
    private JButton login;
    private JButton register;
    private JLabel res;

    public Login()
    {
        setTitle("Login Form");
        setBounds(300, 90, 500, 330);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Login Form");
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

        login = new JButton("Log In");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setSize(100, 20);
        login.setLocation(100, 240);
        login.addActionListener(this);
        container.add(login);

        register = new JButton("Register");
        register.setFont(new Font("Arial", Font.PLAIN, 15));
        register.setSize(100, 20);
        register.setLocation(250, 240);
        register.addActionListener(this);
        container.add(register);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 200);
        container.add(res);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == login) {
            String mail = String.valueOf(temail.getText());
            String pass = String.valueOf(tpassword.getText());

            final UserService userService;
            try {
                userService = UserService.getInstance();
                userService.loadUsers();
                Set<User> usrs = userService.getUsers();
                for( User usr:usrs){
                    if(usr.getEmail().equals(mail)  && usr.getPassword().equals(pass)){
                        res.setText("Login Successfully");
                        UserService.getInstance().setLogged(usr);
                        menu.Menu m = new Menu();
                        super.dispose();
                        break;
                    }
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
        else if (e.getSource() == register) {
            Register r = new Register();
        }

        else {
            res.setText("err");
        }
    }
}

