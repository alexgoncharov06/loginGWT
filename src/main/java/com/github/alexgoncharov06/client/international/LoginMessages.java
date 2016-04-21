package com.github.alexgoncharov06.client.international;

import com.google.gwt.i18n.client.Messages;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 15.04.16.
 */
public interface LoginMessages extends Messages {

    @Key("loginLabel")
    String loginLabel();

    @Key("passwordLabel")
    String passwordLabel();

    @Key("submitButton")
    String submitButton();

    @Key("morning")
    String morning(String name);

    @Key("day")
    String day(String name);

    @Key("evening")
    String evening(String name);

    @Key("night")
    String night(String name);

    @Key("loginError")
    String loginError();

    @Key("logout")
    String logout();
}
