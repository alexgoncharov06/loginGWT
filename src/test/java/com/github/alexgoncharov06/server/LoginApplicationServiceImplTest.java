package com.github.alexgoncharov06.server;

import com.github.alexgoncharov06.shared.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 22.04.16.
 */
@RunWith(value = Parameterized.class)
public class LoginApplicationServiceImplTest {
    private static final Logger logger = LogManager.getLogger(LoginApplicationServiceImplTest.class.getName());

    private String login;
    private String password;
    private User user;

    public LoginApplicationServiceImplTest(String login, String password, User user) {
        this.login = login;
        this.password = password;
        this.user = user;
    }

    @BeforeClass
    public static void initDB() {


        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(new User("ivan", "secret"));
            session.getTransaction().commit();
            session.beginTransaction();
            session.saveOrUpdate(new User("john", "smith"));
            session.getTransaction().commit();

        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            logger.warn(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    @Parameterized.Parameters(name = "{index}: login: {0}, password: {1}, message: {2}")
    public static Iterable<Object[]> data1() {
        return Arrays.asList(new Object[][]{
                {"ivan", "secret", new User("ivan", "secret")},
                {"test", "test", null},
                {"ivan", "seCret", null},
                {"john", "smith", new User("john", "smith")},
        });
    }


    @Test
    public void testTryLogin() throws IOException {


        logger.info("Login: " + login + ", Passworn: " + password);
        assertEquals(user, new LoginApplicationServiceImpl().tryLogin(login, password));
    }

}