package com.github.alexgoncharov06.client;

import com.github.alexgoncharov06.client.event.LoginEvent;
import com.github.alexgoncharov06.client.event.LoginEventHandler;
import com.github.alexgoncharov06.client.event.LogoutEvent;
import com.github.alexgoncharov06.client.event.LogoutEventHandler;
import com.github.alexgoncharov06.client.presenter.LoginPagePresenter;
import com.github.alexgoncharov06.client.presenter.MainPagePresenter;
import com.github.alexgoncharov06.client.presenter.Presenter;
import com.github.alexgoncharov06.client.view.LoginForm;
import com.github.alexgoncharov06.client.view.MainPage;
import com.github.alexgoncharov06.shared.User;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Panel;

import java.util.logging.Logger;


/**
 * Created by Aleksandr Honcharov (alexwolf) on 17.04.16.
 */
public class AppController implements Presenter, ValueChangeHandler {

    private static final Logger log = Logger.getLogger(AppController.class.getName());
    private final HandlerManager eventBus;
    private final LoginApplicationServiceAsync rpcService;
    private final MainPageApplicationServiceAsync rpcMainPageService;
    private Panel container;
    private User user;


    public AppController(LoginApplicationServiceAsync rpcService, MainPageApplicationServiceAsync rpcMainPageService, HandlerManager eventBus) {
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        this.rpcMainPageService = rpcMainPageService;
        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);
        eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
            @Override
            public void onLogin(LoginEvent event) {
                user = event.getUser();
                goLogin();
            }
        });
        eventBus.addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {
            @Override
            public void onLogout(LogoutEvent event) {
                if (user != null && user == event.getUser()) {
                    user = null;
                }
                goLogout();
            }
        });
    }

    private void goLogin() {
        History.newItem("main");
    }

    private void goLogout() {
        History.newItem("login");
    }

    @Override
    public void onValueChange(ValueChangeEvent event) {

        String token = (String) event.getValue();
        if (token != null) {
            Presenter presenter = null;
            if (token.equals("login")) {
                presenter = new LoginPagePresenter(rpcService, eventBus, new LoginForm());
            } else if (token.equals("main")) {
                presenter = new MainPagePresenter(rpcMainPageService, eventBus, new MainPage(), user);
            }
            if (presenter != null) {
                presenter.go(container);
            }
        }
    }

    @Override
    public void go(Panel panel) {
        this.container = panel;
        if (user == null) {
            History.newItem("login");
        } else if (user != null) {
            History.newItem("main");
        } else {
            History.fireCurrentHistoryState();
        }
    }
}
