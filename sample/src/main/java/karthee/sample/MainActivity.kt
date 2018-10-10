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
        data.add(Optionmodel("Freedom to eat as I wish", 136, 72, 380, 35))
        data.add(Optionmodel("15th Aug", 136, 108, 380, 35))
        data.add(Optionmodel("25th Dec", 136, 144, 380, 35))
        data.add(Optionmodel("12 May", 136, 181, 380, 35))
        data.add(Optionmodel("14th Feb", 136, 218, 380, 35))
        customView.setInput(data,R.drawable.p7)
    }
}


