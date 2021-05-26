package com.example.minhapressaoarterial.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.minhapressaoarterial.R

class UpdateRegisterActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var textToolbarTitle: TextView
    lateinit var spinner: Spinner
    lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_register)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        textToolbarTitle = findViewById(R.id.toolbar_title)
        textToolbarTitle.text = getString(R.string.new_update_toolbar_title)

        spinner = findViewById(R.id.spHealthSelectorUpdate)
        var spinnerSelection: String? = null
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinnerSelection = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //To do
            }

        }

        val sisResultUpdate = findViewById<TextView?>(R.id.tfEditSisUpdate)
        val diaResultUpdate = findViewById<TextView?>(R.id.tfEditDiaUpdate)
        val pulResultUpdate = findViewById<TextView?>(R.id.tfEditPulUpdate)
        updateButton = findViewById(R.id.button_update)

    }
}