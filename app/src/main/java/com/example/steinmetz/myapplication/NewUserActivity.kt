package com.example.steinmetz.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_user.*

class NewUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)


        btn_back_nu.setOnClickListener {
          finish()
        }


        btn_start_nu.setOnClickListener {

            var intent = Intent(this, WelcomeActivity::class.java)

            var text = edt_name.text?.toString()


            intent.putExtra("name", text)

            startActivity(intent)
            finish()
        }


    }
}
