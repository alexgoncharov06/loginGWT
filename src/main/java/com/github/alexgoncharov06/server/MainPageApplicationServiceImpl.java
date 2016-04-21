package com.github.alexgoncharov06.server;

import com.github.alexgoncharov06.client.MainPageApplicationService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 20.04.16.
 */
public class MainPageApplicationServiceImpl extends RemoteServiceServlet implements MainPageApplicationService {

    private static final Logger log = LogManager.getLogger(MainPageApplicationServiceImpl.class);
    private static Calendar current;

    public MainPageApplicationServiceImpl() {
        current = Calendar.getInstance();
    }

    public MainPageApplicationServiceImpl(Calendar current) {
        MainPageApplicationServiceImpl.current = current;
    }

    @Override
    public String getCurentTypeMessage() {

        String response = "";
        if (current.get(Calendar.HOUR_OF_DAY) >= 6 && current.get(Calendar.HOUR_OF_DAY) < 9) {
            response = "morning";
        }
        if (current.get(Calendar.HOUR_OF_DAY) >= 9 && current.get(Calendar.HOUR_OF_DAY) < 19) {
            response = "day";
        }
        if (current.get(Calendar.HOUR_OF_DAY) >= 19 && current.get(Calendar.HOUR_OF_DAY) < 23) {
            response = "evening";
        }
        if (current.get(Calendar.HOUR_OF_DAY) >= 23 || current.get(Calendar.HOUR_OF_DAY) < 6) {
            response = "night";
        }

        return response;
    }
}
