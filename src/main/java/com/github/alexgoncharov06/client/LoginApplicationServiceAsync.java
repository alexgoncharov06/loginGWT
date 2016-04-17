package com.github.alexgoncharov06.client;

import com.github.alexgoncharov06.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginApplicationServiceAsync {
    // Sample interface method of remote interface
    void getMessage(String msg, AsyncCallback<String> async);

    void tryLogin(String login, String password, AsyncCallback<User> async);

    void getCurentTypeMessage(AsyncCallback<String> async);

    void startupDatabase(AsyncCallback<Void> async);
}
