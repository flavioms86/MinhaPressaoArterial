package com.example.minhapressaoarterial.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import com.example.minhapressaoarterial.R
import com.example.minhapressaoarterial.database.BloodPressureApplication
import com.example.minhapressaoarterial.database.BloodRepository
import com.example.minhapressaoarterial.viewmodel.BloodPressureViewModel
import com.example.minhapressaoarterial.viewmodel.BloodPressureViewModelFactory

class UpdateRegisterActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var textToolbarTitle: TextView
    lateinit var spinner: Spinner
    lateinit var updateButton: Button


    private val bloodPressureViewModel: BloodPressureViewModel by viewModels {
        BloodPressureViewModelFactory((application as BloodPressureApplication).repository)
    }

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

        //Testing here
        var currentBloodPressure: Int? = null

        currentBloodPressure = intent.getIntExtra("EXTRA_Blood_ID", -1)
        var bloodRepository = BloodRepository.getBloodPressureById(currentBloodPressure)

        val sisResultUpdate = findViewById<TextView?>(R.id.tfEditSisUpdate)
        val diaResultUpdate = findViewById<TextView?>(R.id.tfEditDiaUpdate)
        val pulResultUpdate = findViewById<TextView?>(R.id.tfEditPulUpdate)
        updateButton = findViewById(R.id.button_update)
//
//        updateButton.setOnClickListener {
//            val replyIntent = Intent()
//            if(sisResultUpdate.text.isEmpty() || diaResultUpdate.text.isEmpty() || pulResultUpdate.text.isEmpty()) {
//                Toast.makeText(this, getString(R.string.type_all_fields_message), Toast.LENGTH_SHORT).show()
//            } else {
//                val sis = sisResultUpdate.text.toString()
//                val dia = diaResultUpdate.text.toString()
//                val pul = pulResultUpdate.text.toString()
//                val spHealthStatus = spinnerSelection
//                replyIntent.putExtra(EXTRA_SIS_UPDATE, sis)
//                replyIntent.putExtra(EXTRA_DIA_UPDATE, dia)
//                replyIntent.putExtra(EXTRA_PUL_UPDATE, pul)
//                replyIntent.putExtra(EXTRA_SPHEALTH_UPDATE, spHealthStatus)
//                setResult(Activity.RESULT_OK, replyIntent)
//                finish()
//            }
//
//        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_SIS_UPDATE: String = "com.example.minhapressaoarterial.EXTRA_SIS_UPDATE"
        const val EXTRA_DIA_UPDATE: String = "com.example.minhapressaoarterial.EXTRA_DIA_UPDATE"
        const val EXTRA_PUL_UPDATE: String = "com.example.minhapressaoarterial.EXTRA_PUL_UPDATE"
        const val EXTRA_SPHEALTH_UPDATE: String = "com.example.minhapressaoarterial.EXTRA_SPHEALTH_UPDATE"
    }
}