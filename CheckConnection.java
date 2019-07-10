package com.example.peace;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by SAM12345 on 13/05/2016.
 */
public class CheckConnection {

    public static boolean isNetworkAvailable(Context context) {
        // TODO Auto-generated method stub
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        try {

            ConnectivityManager cm = (ConnectivityManager) ((Activity)context).getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            for (NetworkInfo ni : netInfo) {
                if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                    if (ni.isConnected())
                        haveConnectedWifi = true;
                if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                    if (ni.isConnected())
                        haveConnectedMobile = true;

            }
//	 	    Log.i("network connectivity",haveConnectedWifi+"/"+haveConnectedMobile);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

}
