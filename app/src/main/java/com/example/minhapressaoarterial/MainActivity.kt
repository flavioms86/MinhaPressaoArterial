package com.example.minhapressaoarterial

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minhapressaoarterial.adapter.BloodAdapter
import com.example.minhapressaoarterial.database.BloodPressureApplication
import com.example.minhapressaoarterial.model.BloodPressure
import com.example.minhapressaoarterial.viewmodel.BloodPressureViewModel
import com.example.minhapressaoarterial.viewmodel.BloodPressureViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var fabAdd: FloatingActionButton

    private val newBloodPressureActivityRequestCode = 1
    private val bloodPressureViewModel: BloodPressureViewModel by viewModels {
        BloodPressureViewModelFactory((application as BloodPressureApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        fabAdd = findViewById(R.id.fabAdd)

        drawerLayout = findViewById(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open, R.string.close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val recyclerView = findViewById<RecyclerView>(R.id.rvList)
        val adapter = BloodAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        bloodPressureViewModel.allBloodPressures.observe(this) { bloodPressure ->
            bloodPressure.let { adapter.setBloodPressure(it) }
        }

        fabAdd.setOnClickListener {
            val intent = Intent(this, NewRegisterActivity::class.java)
            startActivityForResult(intent, newBloodPressureActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newBloodPressureActivityRequestCode && resultCode == Activity.RESULT_OK) {

            val sisResult = intentData?.getStringExtra(NewRegisterActivity.EXTRA_SIS)
            val diaResult = intentData?.getStringExtra(NewRegisterActivity.EXTRA_DIA)
            val pulResult = intentData?.getStringExtra(NewRegisterActivity.EXTRA_PUL)
            val spHealthSelection = intentData?.getStringExtra(NewRegisterActivity.EXTRA_SPHEALTH)
            val bloodPressure = BloodPressure(
                0,
                System.currentTimeMillis(),
                sisResult.toString().toInt(),
                diaResult.toString().toInt(),
                pulResult.toString().toInt(),
                spHealthSelection.toString()
            )

            bloodPressureViewModel.insertBloodPressure(bloodPressure)


        } else {
            Toast.makeText(
                applicationContext,
                "Não foi salvo",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.imHistory -> Toast.makeText(this, "Item 1", Toast.LENGTH_LONG).show()
            R.id.imHelp -> Toast.makeText(this, "Item 2", Toast.LENGTH_LONG).show()
            R.id.imAbout -> Toast.makeText(this, "Item 3", Toast.LENGTH_LONG).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}