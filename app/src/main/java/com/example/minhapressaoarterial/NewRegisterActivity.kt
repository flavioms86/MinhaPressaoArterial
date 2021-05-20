package com.example.minhapressaoarterial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class NewRegisterActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var textTitle: TextView
    lateinit var spinner: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_register)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        textTitle = findViewById(R.id.toolbar_title)
        textTitle.text = "NOVO REGISTRO"

        spinner = findViewById(R.id.spHealthSelector)
        ArrayAdapter.createFromResource(
            this,
            R.array.health_status, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}