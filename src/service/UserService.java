package service;

import model.*;
import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class UserService {

    private static UserService instance;
    private Set<User> users = new HashSet<User> ();
    private User currentUser;
    private static final String PATH = "src/data/User.csv";
    private static final String HEADER = "userID,email,password,currency";

    public UserService() throws IOException {
    }

    public static UserService getInstance() throws IOException {
        if(instance == null)
            instance = new UserService();
        return instance;
    }

        public final Set<User> loadUsers() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH));

        String line;
        reader.readLine();
        while((line = reader.readLine()) != null){
            final String[] fields = line.split("\\s*,");
            if(fields.length > 0){
                User x = new User(fields[1], fields[2]);
                x.setUserID(Integer.parseInt(fields[0]));
                x.setCurrency(Integer.parseInt(fields[3]));
                users.add(x);
            }
        }
        reader.close();
        return users;
    }


    public Set<User> getUsers() throws IOException {
        return Collections.unmodifiableSet(users);
    }

    public User getLogged() throws IOException {
        return currentUser;
    }

    public void setLogged(User x) throws IOException {
         currentUser = x;
    }

    public void add(final User user) throws IOException {
        users.add(user);
        FileWriter writer = new FileWriter(PATH);
        writer.write(HEADER + "\n");
        for(final User usr:users){

            writer.append(String.valueOf(usr.getUserID())).append(",");
            writer.append(String.valueOf(usr.getEmail())).append(",");
            writer.append(String.valueOf(usr.getPassword())).append(",");
            writer.append(String.valueOf(usr.getCurrency()));
            writer.append("\n");

        }
        writer.flush();
    }

    public void remove(final User user) throws IOException {
        users.remove(user);
    }

}
