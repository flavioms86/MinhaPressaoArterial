package com.example.minhapressaoarterial.database

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BloodPressureApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {BloodDatabase.getDatabase(this, applicationScope)}
    val repository by lazy {BloodRepository(database.bloodPressureDao())}
}