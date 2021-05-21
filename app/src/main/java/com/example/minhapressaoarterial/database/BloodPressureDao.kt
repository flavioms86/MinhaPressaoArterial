package com.example.minhapressaoarterial.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.minhapressaoarterial.model.BloodPressure

@Dao
interface BloodPressureDao {

    @Insert
    suspend fun insert(pressure: BloodPressure)

    @Update
    suspend fun update(pressure: BloodPressure)

    @Delete
    suspend fun delete(pressure: BloodPressure)
}