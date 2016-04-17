package com.github.alexgoncharov06.client.presenter;

import com.github.alexgoncharov06.client.LoginApplicationServiceAsync;
import com.github.alexgoncharov06.client.event.LoginEvent;
import com.github.alexgoncharov06.client.international.LoginMessages;
import com.github.alexgoncharov06.client.view.LoginForm;
import com.github.alexgoncharov06.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;


/**
 * Created by Aleksandr Honcharov (alexwolf) on 17.04.16.
 */
public class LoginPagePresenter implements Presenter {


    private final Display view;
    private final HandlerManager eventBus;
    private final LoginApplicationServiceAsync rpcService;
    private final LoginMessages LANG = GWT.create(LoginMessages.class);

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LoginPagePresenter.class.getName());

    public interface Display {
        public String getLogin();

        public String getLoginLabel();

        public String getPasswordLabel();

        public HasClickHandlers getButtonSubmit();

        public String getPassword();

        public Widget asWidget();

        public void alert(String msg);

        public void setPresenter(LoginPagePresenter presenter);

        void clear();


    }


    public LoginPagePresenter(LoginApplicationServiceAsync rpcService, HandlerManager eventBus, LoginForm view) {
        this.view = view;
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        this.view.setPresenter(this);
        this.view.clear();
    }


    @Override
    public void go(Panel panel) {

        panel.clear();
        panel.add(view.asWidget());

    }


    public void login() {

        rpcService.tryLogin(view.getLogin(), view.getPassword(), new AsyncCallback<User>() {

            @Override
            public void onFailure(Throwable caught) {
                view.alert("debug: login failed, message from server: " + caught.getMessage());
            }

            @Override
            public void onSuccess(User result) {


                boolean logined;


                if (result == null) logined = false;
                else logined = true;

                if (logined) {

                    eventBus.fireEvent(new LoginEvent(result));

                } else {

                    view.alert(LANG.loginError());
                    log.info(LANG.loginError());
                }


            }
        });

    }


}
