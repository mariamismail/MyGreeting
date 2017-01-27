package com.example.dell.mygreeting.Utilits;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by DELL on 1/17/2017.
 */

public final class Utils {
    private Utils(){}

        public static boolean isNetworkConnected(Context context) {

            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            return cm.getActiveNetworkInfo() != null;
        }
    }

