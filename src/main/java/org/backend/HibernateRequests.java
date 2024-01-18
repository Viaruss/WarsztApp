package org.backend;

import org.backend.dataTypes.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateRequests {
    SessionFactory factory;
    public HibernateRequests(){
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Account.class)
                .buildSessionFactory();
    }
    public List<Account> getAccounts(){
        List<Account> testAcc;
        try{
            Session session = factory.openSession();
            session.beginTransaction();
            testAcc = session.createQuery("SELECT u FROM Account u", Account.class)
                    .list();
            session.getTransaction().commit();
        }finally {
            factory.close();
        }
        return testAcc;
    }
}
