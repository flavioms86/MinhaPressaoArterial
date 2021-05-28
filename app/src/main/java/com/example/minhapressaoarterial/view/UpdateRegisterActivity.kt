package com.example.minhapressaoarterial.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.minhapressaoarterial.R

class UpdateRegisterActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var textToolbarTitle: TextView
    lateinit var spinner: Spinner
    lateinit var updateButton: Button
    private lateinit var sisResultUpdate: TextView
    private lateinit var diaResultUpdate: TextView
    private lateinit var pulResultUpdate: TextView

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

            }

        }

        sisResultUpdate = findViewById(R.id.tfEditSisUpdate)
        diaResultUpdate = findViewById(R.id.tfEditDiaUpdate)
        pulResultUpdate = findViewById(R.id.tfEditPulUpdate)
        sisResultUpdate.text = intent.getStringExtra(EXTRA_SIS)
        diaResultUpdate.text = intent.getStringExtra(EXTRA_DIA)
        pulResultUpdate.text = intent.getStringExtra(EXTRA_PUL)

        updateButton = findViewById(R.id.button_update)

        updateButton.setOnClickListener {
            val replyIntent = Intent()
            if(sisResultUpdate.text.isEmpty() || diaResultUpdate.text.isEmpty() || pulResultUpdate.text.isEmpty() || spinnerSelection.equals("Selecionar")) {
                Toast.makeText(this, getString(R.string.type_all_fields_message), Toast.LENGTH_SHORT).show()
            } else {
                val id: Int = intent.getIntExtra(EXTRA_ID, 1)
                val sis = sisResultUpdate.text.toString()
                val dia = diaResultUpdate.text.toString()
                val pul = pulResultUpdate.text.toString()
                val spHealthStatus = spinnerSelection
                replyIntent.putExtra(EXTRA_SIS, sis)
                replyIntent.putExtra(EXTRA_DIA, dia)
                replyIntent.putExtra(EXTRA_PUL, pul)
                replyIntent.putExtra(EXTRA_SPHEALTH, spHealthStatus)
                replyIntent.putExtra(EXTRA_ID, id)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }

        }

    }

    companion object {
        const val EXTRA_ID: String = "com.example.minhapressaoarterial.EXTRA_ID"
        const val EXTRA_SIS: String = "com.example.minhapressaoarterial.EXTRA_SIS"
        const val EXTRA_DIA: String = "com.example.minhapressaoarterial.EXTRA_DIA"
        const val EXTRA_PUL: String = "com.example.minhapressaoarterial.EXTRA_PUL"
        const val EXTRA_SPHEALTH: String = "com.example.minhapressaoarterial.EXTRA_SPHEALTH"
    }
}