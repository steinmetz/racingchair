package com.example.steinmetz.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.bluetooth.BluetoothDevice
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_devices_list.*
import org.jetbrains.anko.toast


class DevicesList : AppCompatActivity() {

    data class MyDevice(var name: String, var address: String) {
        override fun toString(): String = name + "\n" + address
    }


    private var myBluetooth: BluetoothAdapter? = null
    private var pairedDevices: Set<BluetoothDevice>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices_list)

        myBluetooth = BluetoothAdapter.getDefaultAdapter();

        myBluetooth?.let {
            if (!it.isEnabled) {
                //Ask to the user turn the bluetooth on
                val turnBTon = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(turnBTon, 1)
            }else{
                fillList()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1) {
            myBluetooth?.let {
                if (!it.isEnabled) {
                    fillList()
                }else{
                    toast("Bluetooth is off").show()
                }
            }
        }
    }

    fun fillList(){

        myBluetooth?.let {
            val list = ArrayList<MyDevice>()

            it.bondedDevices.forEach {
                list.add(MyDevice(it.name, it.address))
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
            dev_list.adapter = adapter

            dev_list.setOnItemClickListener { parent, view, position, id ->
                val device = list[position]

                setResult(1000, intent.putExtra("address", device.address))
                finish()

            }
        }
    }
}
