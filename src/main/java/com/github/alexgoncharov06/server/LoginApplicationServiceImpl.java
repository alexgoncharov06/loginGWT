package com.github.alexgoncharov06.server;

import com.github.alexgoncharov06.client.LoginApplicationService;
import com.github.alexgoncharov06.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LoginApplicationServiceImpl extends RemoteServiceServlet implements LoginApplicationService {

    private static final Logger log = LogManager.getLogger(LoginApplicationServiceImpl.class);

    private static UserService userService;

    public LoginApplicationServiceImpl() {

        userService = new UserServiceImpl();
        startupDatabase();


    }


    public String getMessage(String msg) {
        return msg;
    }

    @Override
    public User tryLogin(String login, String password) {
        User formLoginForm = new User(login, password);

        log.info(formLoginForm.toString());

        User userByLogin = userService.getUser(login);
        log.info(userByLogin.toString());

        if (userByLogin != null && userByLogin.equals(formLoginForm)) {
            return userByLogin;
        }

        return null;

    }

    @Override
    public String getCurentTypeMessage() {

        Calendar current = Calendar.getInstance();

        String response = "";


        if (current.get(Calendar.HOUR_OF_DAY) >= 6 && current.get(Calendar.HOUR_OF_DAY) < 9) {


            response = "morning";

        }

        if (current.get(Calendar.HOUR_OF_DAY) >= 9 && current.get(Calendar.HOUR_OF_DAY) < 19) {

            response = "day";

        }

        if (current.get(Calendar.HOUR_OF_DAY) >= 19 && current.get(Calendar.HOUR_OF_DAY) < 23) {

            response = "evening";

        }

        if (current.get(Calendar.HOUR_OF_DAY) >= 23 || current.get(Calendar.HOUR_OF_DAY) < 6) {

            response = "night";

        }

        return response;
    }


    public void startupDatabase() {

        log.info("Adding new users");
        List<User> standartUser = new ArrayList<>();
        List<User> listFromDB = userService.getAllUsers();

        User ivan = new User("ivan", "Иван", "secret");
        User john = new User("john", "John", "smith");
        standartUser.add(ivan);
        standartUser.add(john);


        for (User user : standartUser) {
            if (listFromDB == null || !(listFromDB.contains(user))) {
                userService.saveUser(user);
            }

        }

        listFromDB = userService.getAllUsers();


    }
}