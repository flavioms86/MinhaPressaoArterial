package com.example.minhapressaoarterial.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.minhapressaoarterial.model.BloodPressure
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodPressureDao {

    @Insert
    suspend fun insert(pressure: BloodPressure)

    @Update
    suspend fun update(pressure: BloodPressure)

    @Delete
    suspend fun delete(pressure: BloodPressure)

    @Query("SELECT * FROM blood_pressure")
    fun getAllBloodPressure(): Flow<List<BloodPressure>>
}