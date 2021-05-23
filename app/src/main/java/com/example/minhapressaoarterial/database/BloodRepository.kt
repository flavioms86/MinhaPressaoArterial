package com.example.minhapressaoarterial.database

import androidx.annotation.WorkerThread
import androidx.room.Query
import com.example.minhapressaoarterial.model.BloodPressure
import kotlinx.coroutines.flow.Flow

class BloodRepository(private val bloodDao: BloodPressureDao) {

    val allBloodPressures: Flow<List<BloodPressure>> = bloodDao.getAllBloodPressure()

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