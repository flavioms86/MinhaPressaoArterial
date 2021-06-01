package com.example.minhapressaoarterial.view

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minhapressaoarterial.R
import com.example.minhapressaoarterial.adapter.BloodAdapter
import com.example.minhapressaoarterial.database.BloodPressureApplication
import com.example.minhapressaoarterial.model.BloodPressure
import com.example.minhapressaoarterial.viewmodel.BloodPressureViewModel
import com.example.minhapressaoarterial.viewmodel.BloodPressureViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var fabAdd: FloatingActionButton

    private val newBloodPressureActivityRequestCode = 1
    private val updateBloodPressureActivityRequestCode = 2

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

        val adapter = BloodAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.rvList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        bloodPressureViewModel.allBloodPressures.observe(this) { bloodPressure ->
            bloodPressure.let { adapter.setBloodPressure(it) }
        }

        fabAdd.setOnClickListener {
            val intent = Intent(this, NewRegisterActivity::class.java)
            startActivityForResult(intent, newBloodPressureActivityRequestCode)
        }

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val swipedBloodPressure = adapter.getBloodAtPosition(position)
                bloodPressureViewModel.deleteBloodPressure(swipedBloodPressure)
                Toast.makeText(applicationContext, "Registro apagado!", Toast.LENGTH_SHORT).show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)


        adapter.setOnItemClickListener(object : BloodAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val bloodPosition = adapter.getBloodAtPosition(position)
                val intent = Intent (this@MainActivity, UpdateRegisterActivity::class.java)
                intent.putExtra(UpdateRegisterActivity.EXTRA_ID, bloodPosition.bloodId)
                intent.putExtra(UpdateRegisterActivity.EXTRA_SIS, bloodPosition.sisPressure.toString())
                intent.putExtra(UpdateRegisterActivity.EXTRA_DIA, bloodPosition.diaPressure.toString())
                intent.putExtra(UpdateRegisterActivity.EXTRA_PUL, bloodPosition.pulPressure.toString())
                startActivityForResult(intent, updateBloodPressureActivityRequestCode)
            }

        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        val currentDateTime = LocalDateTime.now()

        if (requestCode == newBloodPressureActivityRequestCode && resultCode == Activity.RESULT_OK) {

            val sisResult = intentData?.getStringExtra(NewRegisterActivity.EXTRA_SIS)
            val diaResult = intentData?.getStringExtra(NewRegisterActivity.EXTRA_DIA)
            val pulResult = intentData?.getStringExtra(NewRegisterActivity.EXTRA_PUL)
            val spHealthSelection = intentData?.getStringExtra(NewRegisterActivity.EXTRA_SPHEALTH)
            val bloodPressure = BloodPressure(
                currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
                sisResult.toString().toInt(),
                diaResult.toString().toInt(),
                pulResult.toString().toInt(),
                spHealthSelection.toString()
            )

            bloodPressureViewModel.insertBloodPressure(bloodPressure)


        } else if (requestCode == updateBloodPressureActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val id = intentData?.getIntExtra(UpdateRegisterActivity.EXTRA_ID, -1)
            val sisResult = intentData?.getStringExtra(UpdateRegisterActivity.EXTRA_SIS)
            val diaResult = intentData?.getStringExtra(UpdateRegisterActivity.EXTRA_DIA)
            val pulResult = intentData?.getStringExtra(UpdateRegisterActivity.EXTRA_PUL)
            val spHealthSelection = intentData?.getStringExtra(UpdateRegisterActivity.EXTRA_SPHEALTH)
            val updateBloodPressure = BloodPressure(
                currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
                sisResult.toString().toInt(),
                diaResult.toString().toInt(),
                pulResult.toString().toInt(),
                spHealthSelection.toString()
            )
            if (id != null) {
                updateBloodPressure.bloodId = id
            }
            bloodPressureViewModel.updateBloodPressure(updateBloodPressure)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu_clear -> {
                clearAllDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun clearAllDialog() {

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(getString(R.string.delete_entries))
        dialogBuilder.setMessage(getString(R.string.clear_confirmation_message))
        dialogBuilder.setPositiveButton(
            getString(R.string.clear_yes_confirmation),
            DialogInterface.OnClickListener { dialog, id ->
                bloodPressureViewModel.deleteBloodPressures()
                Toast.makeText(
                    this,
                    getString(R.string.cleared_entries_message),
                    Toast.LENGTH_SHORT
                ).show()
                dialog.cancel()
            })
        dialogBuilder.setNegativeButton(
            getString(R.string.clear_no_confirmation),
            DialogInterface.OnClickListener { dialog, id ->
                Toast.makeText(
                    this,
                    getString(R.string.not_cleared_entries_message),
                    Toast.LENGTH_SHORT
                ).show()
                dialog.cancel()

            })
        val alert = dialogBuilder.create()
        alert.show()
    }
}