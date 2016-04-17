package com.github.alexgoncharov06.client.presenter;

import com.github.alexgoncharov06.client.LoginApplicationServiceAsync;
import com.github.alexgoncharov06.client.event.LogoutEvent;
import com.github.alexgoncharov06.client.international.LoginMessages;
import com.github.alexgoncharov06.client.view.MainPage;
import com.github.alexgoncharov06.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import java.util.logging.Logger;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 17.04.16.
 */
public class MainPagePresenter implements Presenter {

    private final Display view;

    private final LoginApplicationServiceAsync rpcService;
    private final HandlerManager eventBus;
    private User user;
    private static final Logger log = Logger.getLogger(MainPagePresenter.class.getName());

    private final LoginMessages LANG = GWT.create(LoginMessages.class);

    public MainPagePresenter(LoginApplicationServiceAsync rpcService, HandlerManager eventBus, MainPage mainPage, User user) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.user = user;
        this.view = mainPage;
        this.view.setPresenter(this);

        bind();


    }


    public void bind() {


        this.setHelloUserMessage(user);


    }

    public interface Display {

        void setHelloUserMessage(String message);

        public HasClickHandlers getLinkClick();

        public void alert(String msg);


        public Widget asWidget();

        public void setPresenter(MainPagePresenter presenter);


    }


    @Override
    public void go(Panel panel) {

        Panel parentPanel = (Panel) panel.getParent();
        panel.clear();
        panel.add(view.asWidget());
    }


    public void logOut() {

        eventBus.fireEvent(new LogoutEvent(user));

    }


    public void setHelloUserMessage(final User user) {


        rpcService.getCurentTypeMessage(new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                view.alert("Failed get current message");
            }

            @Override
            public void onSuccess(String result) {

                String message = "";


                switch (result) {
                    case "morning":
                        message = LANG.morning(user.getName());
                        break;
                    case "day":
                        message = LANG.day(user.getName());
                        break;
                    case "evening":
                        message = LANG.evening(user.getName());
                        break;
                    case "night":
                        message = LANG.night(user.getName());
                        break;
                }

                view.setHelloUserMessage(message);
            }
        });


    }

}
