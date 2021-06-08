package com.example.minhapressaoarterial.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.minhapressaoarterial.R

class HelpActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var textToolbarTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        textToolbarTitle = findViewById(R.id.toolbar_title)
        textToolbarTitle.text = getString(R.string.help_title)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}