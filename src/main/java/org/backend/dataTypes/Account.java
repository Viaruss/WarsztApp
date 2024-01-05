package org.backend.dataTypes;

public class Account {
    int id;
    String login;
    String password;
    public Account(int id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
