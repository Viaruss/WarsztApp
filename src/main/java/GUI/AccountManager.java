package GUI;

import org.backend.HibernateRequests;
import org.backend.MD5;
import org.backend.dataTypes.Account;
import java.util.List;

public class AccountManager {
    private Account activeAccount;
    MD5 md5hash = new MD5();
    HibernateRequests req = new HibernateRequests();
    List<Account> accounts;
    public boolean logIn(String userLogin, String userPassword) {
        accounts = req.getAccounts();
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
}

