package com.github.alexgoncharov06.client;

import com.github.alexgoncharov06.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginApplicationServiceAsync {

    void tryLogin(String login, String password, AsyncCallback<User> async);


}
