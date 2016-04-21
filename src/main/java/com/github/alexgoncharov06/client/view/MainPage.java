package com.github.alexgoncharov06.client.view;

import com.github.alexgoncharov06.client.international.LoginMessages;
import com.github.alexgoncharov06.client.presenter.MainPagePresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;

import static com.github.alexgoncharov06.client.presenter.MainPagePresenter.Display;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 17.04.16.
 */
public class MainPage extends Composite implements Display {

    public static final LoginMessages LANG = GWT.create(LoginMessages.class);
    private static MainPageUiBinder ourUiBinder = GWT.create(MainPageUiBinder.class);
    @UiField
    Label helloUser;
    @UiField
    Hyperlink logOutLink;
    private MainPagePresenter presenter;

    public MainPage() {
        initWidget(ourUiBinder.createAndBindUi(this));
        logOutLink.setText(LANG.logout());
        logOutLink.setTargetHistoryToken("login");
    }

    @Override
    public void setHelloUserMessage(String message) {
        helloUser.setText(message);
    }

    @Override
    public void setPresenter(MainPagePresenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("logOutLink")
    public void onClick(ClickEvent e) {
        presenter.logOut();
    }

    public void alert(String msg) {
        Window.alert(msg);
    }

    interface MainPageUiBinder extends UiBinder<HTMLPanel, MainPage> {
    }
}