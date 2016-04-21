package com.github.alexgoncharov06.server;

import com.github.alexgoncharov06.shared.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;


/**
 * Created by Aleksandr Honcharov (alexwolf) on 17.04.16.
 */
public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    private SessionFactory sessionFactory;
    private Session session;

    public UserServiceImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public User getUser(String login) {
        User user = null;

        try {
            user = (User) sessionFactory.openSession()
                    .createCriteria(User.class)
                    .add(Restrictions.eq("login", login)).uniqueResult();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return user;
    }
}
