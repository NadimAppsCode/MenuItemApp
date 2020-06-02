package com.example.menuitem

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_itemdetails.*

class itemdetails : AppCompatActivity() {
    private lateinit var txtdescription: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemdetails)
        setSupportActionBar(toolbar)
        val description = intent.getStringExtra("Description")
        System.out.println("Test desc" + description)
        val txtdescription = findViewById<TextView>(R.id.itemDetails)
        txtdescription?.setText(description)

    }

}
