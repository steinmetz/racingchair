package com.example.steinmetz.myapplication

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.bluetooth.BluetoothSocket



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_new_user.setOnClickListener {

            var intent = Intent(this, NewUserActivity::class.java)
            startActivity(intent)

        }

    }
}
