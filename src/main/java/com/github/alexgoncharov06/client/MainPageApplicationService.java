package com.github.alexgoncharov06.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 20.04.16.
 */
@RemoteServiceRelativePath("MainPageApplicationService")
public interface MainPageApplicationService extends RemoteService {

    String getCurentTypeMessage();

    class App {
        private static MainPageApplicationServiceAsync ourInstance = GWT.create(MainPageApplicationService.class);

        public static synchronized MainPageApplicationService getInstance() {
            return (MainPageApplicationService) ourInstance;
        }
    }
}
