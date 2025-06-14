package model;

import java.util.ArrayList;

public class User {

    private ArrayList<String> Userlist = new ArrayList<String>();

    public ArrayList<String> getUserlist() {
        return Userlist;
    }

    public void setUserlist(ArrayList<String> userlist) {
        Userlist = userlist;
    }

    public User(ArrayList<String> userlist) {
        Userlist = userlist;
    }
}
