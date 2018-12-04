package com.example.steinmetz.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_user.*
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {


    var i : Intent? = null
    var name : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        name = intent.getStringExtra("name")

        txt_welcome.text = "Welcome "+name

        i= Intent(this, DriverActivityMenu::class.java)

        i?.putExtra("name", name)


    }

    override fun onResume() {
        super.onResume()


        Thread {
            Thread.sleep(2000)
            startActivity(i)
            finish()
        }.start()


    }

}
