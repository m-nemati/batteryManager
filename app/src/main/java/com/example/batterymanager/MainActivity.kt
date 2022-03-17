package com.example.batterymanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(batteryInfoReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    private val batteryInfoReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var batteryLevel = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            var batteryPlugged = intent?.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0)
            var batteryHealth= intent?.getIntExtra(BatteryManager.EXTRA_HEALTH, 0)
            var batteryTemperature= intent?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)
                ?.div(10)
            var batteryVoltage= intent?.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0)?.div(1000)
            Log.e("3636", batteryLevel.toString())
            Log.e("3636", batteryPlugged.toString())
            Log.e("3636", batteryHealth.toString())
            Log.e("3636", batteryTemperature.toString())
        }
    }
}