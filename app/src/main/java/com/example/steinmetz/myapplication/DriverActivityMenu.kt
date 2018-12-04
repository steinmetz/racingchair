package com.example.steinmetz.myapplication

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_driver_menu.*
import kotlinx.android.synthetic.main.app_bar_driver_activity_menu.*
import kotlinx.android.synthetic.main.content_driver_activity_menu.*
import kotlinx.android.synthetic.main.nav_header_driver_activity_menu.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import android.bluetooth.BluetoothSocket
import android.bluetooth.BluetoothAdapter
import java.util.*
import android.bluetooth.BluetoothDevice
import android.util.Log


class DriverActivityMenu : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    var myBluetooth: BluetoothAdapter? = null
    var btSocket: BluetoothSocket? = null
    private val isBtConnected = false
    val myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_menu)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        drive_up.setOnClickListener {
            Toast.makeText(this, "up", Toast.LENGTH_SHORT).show()
            btSocket?.let { it.outputStream.write("1".toByteArray()) }
        }

        drive_down.setOnClickListener {
            Toast.makeText(this, "down", Toast.LENGTH_SHORT).show()
            btSocket?.let { it.outputStream.write("2".toByteArray()) }
        }

        drive_left.setOnClickListener {
            Toast.makeText(this, "left", Toast.LENGTH_SHORT).show()
            btSocket?.let { it.outputStream.write("3".toByteArray()) }
        }

        drive_right.setOnClickListener {
            Toast.makeText(this, "right", Toast.LENGTH_SHORT).show()
            btSocket?.let { it.outputStream.write("4".toByteArray()) }
        }

        dance.setOnClickListener {
            Toast.makeText(this, "dance", Toast.LENGTH_SHORT).show()
            btSocket?.let { it.outputStream.write("5".toByteArray()) }
        }

    }


    override fun onResume() {
        super.onResume()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.driver_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_devices -> {
                var intent = Intent(this,DevicesList::class.java )
                startActivityForResult(intent, 1000)
            }
            R.id.nav_gmaps -> {

            }
            R.id.nav_weather -> {

            }
            R.id.nav_profile -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_close -> {

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1000){
            data?.let {
                if (it.hasExtra("address"))
                    connectDevice(it.getStringExtra("address"))
            }
        }
    }

    fun connectDevice(address: String){

        doAsync {

            myBluetooth = BluetoothAdapter.getDefaultAdapter()//get the mobile bluetooth device
            val device = myBluetooth?.getRemoteDevice(address)//connects to the device's address and checks if it's available
            btSocket = device?.createInsecureRfcommSocketToServiceRecord(myUUID)//create a RFCOMM (SPP) connection
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
            btSocket?.connect()//start connection

            uiThread {

                btSocket?.let {
                    if (it.isConnected){
                        toast("Connected!").show()
                        device_status.text = "Connected"
                        bluetoothListener()
                    }
                }
            }
        }
    }

    fun bluetoothListener(){
        doAsync {
            btSocket?.let {

                while (it.isConnected){
                    try {
                        var text = it.inputStream.bufferedReader().use { it.readText() }
                        Log.i("SSS", text)
                    }catch (e : Exception){
                        e.printStackTrace()
                    }

                }
            }
        }
    }


}
