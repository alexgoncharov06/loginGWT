package com.github.alexgoncharov06.client.event;

import com.github.alexgoncharov06.shared.User;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 17.04.16.
 */
public class LoginEvent extends GwtEvent<LoginEventHandler> {
    public static Type<LoginEventHandler> TYPE = new Type<LoginEventHandler>();
    private final User user;

    public LoginEvent(User user) {

        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Type<LoginEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(LoginEventHandler handler) {
        handler.onLogin(this);
    }
}
