package com.example.steinmetz.myapplication

import java.io.Serializable

class Profile(val id: Int, val username: String, val speed: String, val layout: String) : Serializable {

    override fun toString(): String {
        return username
    }

}