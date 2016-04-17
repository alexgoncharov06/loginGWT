package com.github.alexgoncharov06.server;

import com.github.alexgoncharov06.shared.User;

import java.util.List;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 17.04.16.
 */
public interface UserService {

    public User getUser(String login);

    public void saveUser(User user);

    public void deleteUser(User user);

    public List<User> getAllUsers();

}
