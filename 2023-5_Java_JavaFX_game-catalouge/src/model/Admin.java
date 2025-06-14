package model;

import java.util.ArrayList;

public class Admin {

    private ArrayList<String> AdminList = new ArrayList<String>();

    public ArrayList<String> getAdminList() {
        return AdminList;
    }

    public void setAdminList(ArrayList<String> adminList) {
        AdminList = adminList;
    }

    public Admin(ArrayList<String> adminList) {
        AdminList = adminList;
    }
}
