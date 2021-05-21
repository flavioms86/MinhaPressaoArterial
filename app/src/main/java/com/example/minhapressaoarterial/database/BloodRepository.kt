package com.example.minhapressaoarterial.database

import androidx.annotation.WorkerThread
import com.example.minhapressaoarterial.model.BloodPressure

class BloodRepository(private val bloodDao: BloodPressureDao) {

    @WorkerThread
    suspend fun insert(pressure: BloodPressure) {
        bloodDao.insert(pressure)
    }

    @WorkerThread
    suspend fun update(pressure: BloodPressure) {
        bloodDao.update(pressure)
    }

    @WorkerThread
    suspend fun delete(pressure: BloodPressure) {
        bloodDao.delete(pressure)
    }

}