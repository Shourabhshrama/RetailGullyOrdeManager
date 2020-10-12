package com.mcsandroid.myapplication.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkAvailablity {

    fun checkNetworkStatus(context: Context): Boolean {
        var HaveConnectedWifi = false
        var HaveConnectedMobile = false

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.allNetworkInfo

        for (ni in netInfo) {

            if (ni.typeName.equals("WIFI", ignoreCase = true))
                if (ni.isConnected)
                    HaveConnectedWifi = true
            if (ni.typeName.equals("MOBILE", ignoreCase = true))
                if (ni.isConnected)
                    HaveConnectedMobile = true
        }

        return HaveConnectedWifi || HaveConnectedMobile
    }
}