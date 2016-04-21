package com.github.alexgoncharov06.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * Created by Aleksandr Honcharov (alexwolf) on 16.04.16.
 */
public class HibernateUtil {

    private static final ThreadLocal<Session> threadLocal = new ThreadLocal();
    private static final Logger log = LogManager.getLogger(HibernateUtil.class);
    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = configureSessionFactory();
        } catch (Exception e) {
            log.warn("%%%% Error Creating SessionFactory %%%%");
            log.warn(e.getMessage());
        }
    }


    private HibernateUtil() {
    }

    private static SessionFactory configureSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        } catch (HibernateException e) {
            log.warn("** Exception in SessionFactory **");
            log.warn(e.getMessage());
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() throws HibernateException {
        Session session = threadLocal.get();

        if (session == null || !session.isOpen()) {
            if (sessionFactory == null) {
                rebuildSessionFactory();
            }
            session = (sessionFactory != null) ? sessionFactory.openSession() : null;
            threadLocal.set(session);
        }

        return session;
    }

    public static void rebuildSessionFactory() {
        try {
            sessionFactory = configureSessionFactory();
        } catch (Exception e) {
            log.warn("%%%% Error Creating SessionFactory %%%%");
            log.warn(e.getMessage());
        }
    }

    public static void closeSession() throws HibernateException {
        Session session = threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }

}
