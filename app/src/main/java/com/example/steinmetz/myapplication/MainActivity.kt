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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select


class MainActivity : AppCompatActivity() {

    var profiles = listOf<Profile>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_new_user.setOnClickListener {

            var intent = Intent(this, NewUserActivity::class.java)
            startActivity(intent)

        }

        database.use {

            select("Profile").exec {

                val rowParser = classParser<Profile>()
                profiles = parseList(rowParser)
                val aa = ProfilesAdapter( applicationContext, profiles)

                user_list.setAdapter(aa)

            }
        }

        user_list.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            var intent = Intent(this, WelcomeActivity::class.java)
            intent.putExtra("name", profiles[position].username)
            intent.putExtra("id", profiles[position].id)
            startActivity(intent)
            finish()

        }

    }
}
