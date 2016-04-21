package com.github.alexgoncharov06.client;

import com.github.alexgoncharov06.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class CompileGwtTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "com.github.alexgoncharov06.client.loginGWTJUnit";
    }

    public void testSandbox() {
        assertTrue(true);
    }


    public void testLogIn() {

        LoginApplicationServiceAsync rpcService = GWT.create(LoginApplicationService.class);
        ServiceDefTarget target = (ServiceDefTarget) rpcService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "Project/LoginApplicationService");


        delayTestFinish(10000);

        rpcService.tryLogin("ivan", "secret", new AsyncCallback<User>() {
            @Override
            public void onFailure(Throwable caught) {
                fail("Request failure: " + caught.getMessage());
            }

            @Override
            public void onSuccess(User result) {

                assertTrue(result != null);

                finishTest();

            }
        });


    }

}
