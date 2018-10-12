package karthee.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import arivista.lib.dragdrop.CustomView
import arivista.lib.dragdrop.Optionmodel

import java.util.*

class MainActivity : AppCompatActivity() {
    val data = ArrayList<Optionmodel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_main)
        val customView = findViewById<CustomView>(R.id.customView)
        data.add(Optionmodel("15th Aug", 420, 50, 250, 35))
        data.add(Optionmodel("14th Feb", 420, 340, 250, 35))
        data.add(Optionmodel("25th Dec", 420, 240, 250, 35))
        data.add(Optionmodel("4th Aug", 420, 440, 250, 35))
        data.add(Optionmodel("12 May", 420, 570, 250, 35))
        data.add(Optionmodel("14th Jan", 420, 140, 250, 35))
        customView.setInput(data,R.drawable.demoimage,500,500)
    }
}


