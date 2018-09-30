package com.example.meenal.petconnect;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;

public class Model {
    private static final Model _instance = new Model();

    /**
     * Returns a singleton instance of the model
     * @return an instance of the model
     */
    public static Model getInstance() {
        return _instance;
    }

    /**
     * holds the list of all users, lost & found items
     */
    private ArrayList<User> _users;

    private ArrayList<User> _bannedUsers;

    /**
     * the currently selected user, defaults to the first one
     */
    private User _currentUser;


    private final User theNullUser = new User("Null", "No", "Name", "passw0rd", "null@gatech.edu", "0000000000", User.accountType.get(1));



    /**
     * makes a new model
     */
    private Model() {
        _users = new ArrayList<>();

        _bannedUsers = new ArrayList<>();

    }


    /**
     * get the users
     *
     * @return a list of the users in the app
     */
    public List<User> getUsers() {
        return _users;
    }

    /**
     * returns a list of users whose accounts have been locked
     * @return a list of banned users
     */
    public List<User> getBannedUsers() {
        return _bannedUsers;
    }

    /**
     * add a banned user to the app.  checks if the user is already entered
     * <p>
     * uses O(n) linear search for course
     *
     * @param user the user to be added
     */
    public void addBannedUser(User user) {
        for (User c : _bannedUsers) {
            if (c.equals(user)) return;
        }
        _bannedUsers.add(user);
    }


    /**
     * removes a banned user.  checks if the user is already entered
     * <p>
     * uses O(n) linear search for course
     *
     * @param user the user to be removed
     */
    public void removeBannedUser(User user) {
        for (User c : _bannedUsers) {
            if (c.equals(user)){
                _bannedUsers.remove(c);
                return;
            }
        }
    }

    /**
     * returns true if the user is banned, false otherwise
     * @param user the user to check its lockout status
     * @return true if user banned else false
     */
    public boolean isBannedUser(User user) {
        for (User c : _bannedUsers) {
            if (c.equals(user)) return true;
        }
        return false;
    }
    /**
     * getter for null user
     * @return null user
     */
    public User getNullUser() {
        return theNullUser;
    }


    /**
     * add a user to the app.  checks if the user is already entered
     * <p>
     * uses O(n) linear search for course
     *
     * @param user the user to be added
     */
    public void addUser(User user) {
        for (User c : _users) {
            if (c.equals(user)) return;
        }
        _users.add(user);
    }


    /**
     * @return the currently selected course
     */
    public User getCurrentUser() {
        return _currentUser;
    }

    /**
     * sets the current user
     *
     * @param user the current user in the model
     */
    public void setCurrentUser(User user) {
        _currentUser = user;
    }


    /**
     * Return a user that has matching number.
     * This uses an O(n) linear search.
     *
     * @param username the username of the user to find
     * @return the user with that username or the NullUser if no such user exists.
     */
    public User getUserByUsername(String username) {
        for (User c : _users) {
            if (c.getUsername().equals(username)) return c;
        }
        return theNullUser;
    }


    /**
     * Completely wipes everything in the model
     */
    public void nuclearMeltdown() {
        Model model = Model.getInstance();
        _users = new ArrayList<>();
        _bannedUsers = new ArrayList<>();

    }




}
