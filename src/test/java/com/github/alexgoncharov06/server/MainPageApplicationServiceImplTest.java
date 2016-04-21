package com.github.alexgoncharov06.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by Aleksandr Honcharov (alexwolf) on 22.04.16.
 */

@RunWith(value = Parameterized.class)
public class MainPageApplicationServiceImplTest {

    private static final Logger log = LogManager.getLogger(MainPageApplicationServiceImpl.class);

    private int currentHours;
    private String response;

    public MainPageApplicationServiceImplTest(int currentHours, String response) {
        this.currentHours = currentHours;
        this.response = response;
    }


    @Parameterized.Parameters(name = "{index}: currentHours: {0}, response: {1}")
    public static Iterable<Object[]> data1() {
        return Arrays.asList(new Object[][]{
                {6, "morning"},
                {9, "day"},
                {19, "evening"},
                {23, "night"}
        });
    }

    @Test
    public void testGetCurentTypeMessage() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, currentHours);

        assertEquals(response, new MainPageApplicationServiceImpl(calendar).getCurentTypeMessage());
    }
}