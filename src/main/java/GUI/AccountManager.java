package GUI;

import org.backend.MD5;
import org.backend.SQLRequests;
import org.backend.dataTypes.Account;

import java.util.ArrayList;

public class AccountManager {
    private Account activeAccount;
    MD5 md5hash = new MD5();
    SQLRequests req = new SQLRequests();
    ArrayList<Account> accounts;
    public boolean logIn(String userLogin, String userPassword) {
        accounts = req.accountInfo();
        for (Account account : accounts){
            if (account.getLogin().equals(userLogin)){
                if (md5hash.Hash(userPassword).equals(account.getPassword())) {
                    activeAccount = account;
                    return true;
                }
            }
        }
        return false;
    }
    public Account getActiveAccount(){
        return activeAccount;
    }

    public SQLRequests getReq() {
        return req;
    }
}

