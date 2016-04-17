package com.github.alexgoncharov06.client;

import com.github.alexgoncharov06.client.international.LoginMessages;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ProjectEntryPoint implements EntryPoint {


    private LoginMessages messages =
            GWT.create(LoginMessages.class);



    @Override
    public void onModuleLoad() {

        LoginApplicationServiceAsync rpcService = GWT.create(LoginApplicationService.class);
        HandlerManager eventBus = new HandlerManager(null);
        AppController appViewer = new AppController(rpcService, eventBus);
        appViewer.go(RootPanel.get());



    }

}
