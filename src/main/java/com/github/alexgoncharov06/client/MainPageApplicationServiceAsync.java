package com.github.alexgoncharov06.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MainPageApplicationServiceAsync {
    void getCurentTypeMessage(AsyncCallback<String> async);
}
