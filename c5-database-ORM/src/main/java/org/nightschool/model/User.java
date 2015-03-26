package org.nightschool.model;

/**
 * Created by Thoughtworks on 3/4/15.
 */
public class User {
    private String user_name;
    private String password;
    private String type;

    public String getPassword() { return password; }
    public String getType() {
        return type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
