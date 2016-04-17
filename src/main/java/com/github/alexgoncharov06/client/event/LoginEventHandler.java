package com.github.alexgoncharov06.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 17.04.16.
 */
public interface LoginEventHandler extends EventHandler {
    void onLogin(LoginEvent event);
}
