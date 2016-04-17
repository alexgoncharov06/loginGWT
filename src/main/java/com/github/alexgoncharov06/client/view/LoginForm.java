package com.github.alexgoncharov06.client.view;

import com.github.alexgoncharov06.client.international.LoginMessages;
import com.github.alexgoncharov06.client.presenter.LoginPagePresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;


/**
 * Created by Aleksandr Honcharov (alexwolf) on 15.04.16.
 */
public class LoginForm extends Composite implements LoginPagePresenter.Display {

    private LoginPagePresenter loginPagePresenter;


    public static final LoginMessages LANG = GWT.create(LoginMessages.class);



    interface LoginFormUiBinder extends UiBinder<HTMLPanel, LoginForm> {
    }

    private static LoginFormUiBinder ourUiBinder = GWT.create(LoginFormUiBinder.class);

    @UiField
    Panel loginForm;

    @UiField
    Label labelLogin;

    @UiField
    Label labelPassword;

    @UiField
    Button buttonSubmit;

    @UiField
    TextBox loginBox;

    @UiField
    PasswordTextBox passwordBox;


    public LoginForm() {

        initWidget(ourUiBinder.createAndBindUi(this));


        labelLogin.setText(LANG.loginLabel());
        labelPassword.setText(LANG.passwordLabel());
        buttonSubmit.setText(LANG.submitButton());




    }


    public String getLogin() {
        return  loginBox.getText();
    }

    public String getLoginLabel() {
        return labelLogin.getText();
    }

    public String getPasswordLabel() {
        return labelPassword.getText();
    }

    public HasClickHandlers getButtonSubmit() {
        return buttonSubmit;
    }

    public String getPassword() {
        return passwordBox.getText();
    }


    public void alert(String msg){

        Window.alert(msg);
    }

    @UiHandler("buttonSubmit")
    public void onClick(ClickEvent e) {

        loginPagePresenter.login();


    }


    @UiHandler("loginBox")
    public void onKeyDown(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

            loginPagePresenter.login();
        }
    }


    public Widget asWidget() {
        return this;
    }

    @Override
    public void setPresenter(LoginPagePresenter presenter) {

        this.loginPagePresenter = presenter;

    }

    @Override
    public void clear() {
        loginBox.setText("");
        passwordBox.setText("");
    }


}