package com.example.meenal.petconnect;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Meenal on 9/7/2018.
 */

public class UserAccount extends AppCompatActivity {

    private static String useraccount;

    public static String getUserAccount() {
        return useraccount;
    }

    public static void setUserAccount(String someVariable) {
        useraccount = someVariable;
    }
}
