package org.backend.dataTypes;

import jakarta.persistence.*;

@Entity
@Table(name = "dane_kont")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Konta")
    int id;
    @Column(name = "Login")
    String login;
    @Column(name = "Haslo")
    String password;

    public Account() {
    }

    public Account(int id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
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
