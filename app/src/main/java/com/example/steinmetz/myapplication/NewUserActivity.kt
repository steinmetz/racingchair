package com.example.steinmetz.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_new_user.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class NewUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)


        btn_back_nu.setOnClickListener {
          finish()
        }


        btn_start_nu.setOnClickListener {

            var intent = Intent(this, WelcomeActivity::class.java)

            var username = edt_name.text?.toString()
            if (username.isNullOrEmpty()) edt_name.error = "Please fill this field!"
            val speed = spinner.selectedItem.toString()
            val layout = spinner2.selectedItem.toString()

            database.use {
                val result = insert("Profile",
                        "username" to username,
                        "speed" to speed,
                        "layout" to layout
                )
                if (result > 1L){
                    intent.putExtra("name", username)
                    intent.putExtra("id", result)
                    startActivity(intent)
                    finish()
                }
            }
        }




    }
}
