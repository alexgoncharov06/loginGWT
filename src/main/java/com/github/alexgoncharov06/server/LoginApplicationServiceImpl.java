package com.github.alexgoncharov06.server;

import com.github.alexgoncharov06.client.LoginApplicationService;
import com.github.alexgoncharov06.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginApplicationServiceImpl extends RemoteServiceServlet implements LoginApplicationService {

    private static final Logger log = LogManager.getLogger(LoginApplicationServiceImpl.class);
    private static UserService userService;

    public LoginApplicationServiceImpl() {

        userService = new UserServiceImpl();

    }

    @Override
    public User tryLogin(String login, String password) {
        User formLoginForm = new User(login, password);
        User userByLogin = userService.getUser(login);
        if (userByLogin != null && userByLogin.equals(formLoginForm)) {

            return userByLogin;
        }

        return null;

    }

}