package com.example.nekofitness.broadCastRecievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class DateChangeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == Intent.ACTION_DATE_CHANGED){

        }
    }
}