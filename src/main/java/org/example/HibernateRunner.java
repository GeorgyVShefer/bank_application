package org.example;

import org.example.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configure = new Configuration().configure();

        try (SessionFactory sessionFactory = configure.buildSessionFactory()){
            Session session = sessionFactory.openSession();
            System.out.println("Session is ok");
            session.getTransaction().begin();

           


            session.getTransaction().commit();
        }
    }
}
