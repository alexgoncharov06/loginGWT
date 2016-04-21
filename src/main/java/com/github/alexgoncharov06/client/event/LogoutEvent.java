package com.github.alexgoncharov06.client.event;

import com.github.alexgoncharov06.shared.User;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 17.04.16.
 */
public class LogoutEvent extends GwtEvent<LogoutEventHandler> {
    public static Type<LogoutEventHandler> TYPE = new Type<LogoutEventHandler>();
    private final User user;

    public LogoutEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


    public Type<LogoutEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(LogoutEventHandler handler) {
        handler.onLogout(this);
    }
}
