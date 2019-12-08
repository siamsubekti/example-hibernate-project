package com.enigmacamp.hbm.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.enigmacamp.friends.db.entities.Category;
import com.enigmacamp.friends.db.entities.Gender;
import com.enigmacamp.friends.db.entities.Person;
import com.enigmacamp.friends.db.entities.Post;

public class HibernateConfig {
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            
            configuration.configure();
            configuration.addAnnotatedClass(Gender.class);
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(Post.class);
            configuration.addAnnotatedClass(Category.class);
            
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            System.out.println("Session failed" + e);
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}
