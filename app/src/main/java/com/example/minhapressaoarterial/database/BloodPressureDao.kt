package com.example.minhapressaoarterial.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.minhapressaoarterial.model.BloodPressure
import com.example.minhapressaoarterial.model.BloodUpdate
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodPressureDao {

    @Insert
    suspend fun insert(pressure: BloodPressure)

    //Using partial entity for update only selected fields. Check BloodPressure
    @Update(entity = BloodPressure::class)
    suspend fun updateSingle(pressure: BloodUpdate)
    
    @Delete
    suspend fun deleteBloodPressure(pressure: BloodPressure)

    @Query("DELETE FROM blood_pressure")
    suspend fun deleteAll()    

    @Query("SELECT * FROM blood_pressure ORDER BY bloodId DESC")
    fun getAllBloodPressure(): Flow<List<BloodPressure>>
}