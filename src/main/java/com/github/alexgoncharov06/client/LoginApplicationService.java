package com.github.alexgoncharov06.client;

import com.github.alexgoncharov06.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("LoginApplicationService")
public interface LoginApplicationService extends RemoteService {


    User tryLogin(String login, String password);


    class App {
        private static LoginApplicationServiceAsync ourInstance = GWT.create(LoginApplicationService.class);
        public static synchronized LoginApplicationService getInstance() {
            return (LoginApplicationService) ourInstance;
        }
    }
}
