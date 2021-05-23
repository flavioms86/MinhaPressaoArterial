package com.example.minhapressaoarterial

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.example.minhapressaoarterial.model.BloodPressure

class NewRegisterActivity : AppCompatActivity(){

    lateinit var toolbar: Toolbar
    lateinit var textToolbarTitle: TextView
    lateinit var spinner: Spinner
    lateinit var saveButton: Button
    lateinit var deleteButton: Button
    lateinit var spinnerSelection: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_register)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        textToolbarTitle = findViewById(R.id.toolbar_title)
        textToolbarTitle.text = "NOVO REGISTRO"

        spinner = findViewById(R.id.spHealthSelector)
        spinnerSelection = "nulo"
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

//        ArrayAdapter.createFromResource(
//            this,
//            R.array.health_status, android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner.adapter = adapter
//        }

        val sisResult = findViewById<TextView?>(R.id.tfEditSis)
        val diaResult = findViewById<TextView?>(R.id.tfEditDia)
        val pulResult = findViewById<TextView?>(R.id.tfEditPul)
        saveButton = findViewById(R.id.button_save)
        deleteButton = findViewById(R.id.button_delete)

        saveButton.setOnClickListener {
            val replyIntent = Intent()
            if(sisResult.text.isEmpty() || diaResult.text.isEmpty() || pulResult.text.isEmpty()) {
                Toast.makeText(this, "Por favor digite todos os dados", Toast.LENGTH_SHORT).show()
            } else {
                val sis = sisResult.text.toString()
                val dia = diaResult.text.toString()
                val pul = pulResult.text.toString()
                val spHealthStatus = spinnerSelection
                replyIntent.putExtra(EXTRA_SIS, sis)
                replyIntent.putExtra(EXTRA_DIA, dia)
                replyIntent.putExtra(EXTRA_PUL, pul)
                replyIntent.putExtra(EXTRA_SPHEALTH, spHealthStatus)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_SIS: String = "com.example.minhapressaoarterial.EXTRA_SIS"
        const val EXTRA_DIA: String = "com.example.minhapressaoarterial.EXTRA_DIA"
        const val EXTRA_PUL: String = "com.example.minhapressaoarterial.EXTRA_PUL"
        const val EXTRA_SPHEALTH: String = "com.example.minhapressaoarterial.EXTRA_SPHEALTH"
    }

}

