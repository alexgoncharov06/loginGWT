package com.github.alexgoncharov06.server;

import com.github.alexgoncharov06.shared.User;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 17.04.16.
 */
public interface UserService {

    User getUser(String login);

}