package com.example.batterymanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.batterymanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        registerReceiver(batteryInfoReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    private val batteryInfoReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var batteryLevel = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            var batteryTechnology = intent?.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)
            var batteryPlugged = intent?.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0)
            var batteryHealth= intent?.getIntExtra(BatteryManager.EXTRA_HEALTH, 0)
            var batteryTemperature= intent?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)
                ?.div(10)
            var batteryVoltage= intent?.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0)?.div(1000)

            binding.txtTemperature.text = batteryTemperature.toString() + " °C"
            binding.txtTechnology.text = batteryTechnology
            binding.txtVoltage.text = batteryVoltage.toString() + " volte"
            if (batteryPlugged == 0)
                binding.txtPlug.text = "plug-out"
            else
                binding.txtPlug.text = "plug-in"

            binding.circularProgressBar.progressMax = 100f // max of battery charge is 100%
            if (batteryLevel != null) {
                binding.circularProgressBar.setProgressWithAnimation(batteryLevel.toFloat())
            }
            binding.txtCharge.text = batteryLevel.toString() + "%"
        }
    }
}