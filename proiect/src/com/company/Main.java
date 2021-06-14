package com.company;

import Roulette.AllGui;
import Roulette.*;
import Roulette.MyRoulette;
import model.User;
import service.*;
import auth.*;
import java.io.IOException;
import java.util.Set;
import slots.*;

import menu.*;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        final UserService userService = UserService.getInstance();
//        userService.loadUsers();
//        Set<User> usrs = userService.getUsers();
//        for( User usr:usrs) {
//            if (usr.getEmail().equals("c")) {
//                System.out.println(usr);
//                userService.remove(usr);
//                System.out.println(userService.getUsers());
//                int x = usr.getUserID();
//                usr = new User(usr.getEmail(), usr.getPassword());
//                usr.setCurrency(111);
//                usr.setUserID(x);
//                userService.add(usr);
//                System.out.println(userService.getUsers());
//                break;
//            }
//        }


//        System.out.println(userService.getUsers());
//        Register f = new Register();
//        userService.loadUsers();
        Login l = new Login();






    }
}
