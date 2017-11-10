package com.wsfmn.habittracker.habit;

import android.test.ActivityInstrumentationTestCase2;

import com.wsfmn.habit.Request;

/**
 * Created by Fredric on 2017-10-21.
 */

public class RequestTest  extends ActivityInstrumentationTestCase2 {

    public RequestTest(){
        super(Request.class);
    }

    public void testGetName(){
        Request request = new Request("test");
        request.setName("testName");
        assertEquals(request.getName(), "testName");
    }

}