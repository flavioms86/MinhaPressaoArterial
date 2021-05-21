package com.example.minhapressaoarterial.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.minhapressaoarterial.model.BloodPressure

@Database(entities = [BloodPressure::class], version = 1, exportSchema = false)
abstract class BloodDatabase : RoomDatabase() {

    abstract val bloodPressureDao: BloodPressureDao

    companion object {

        @Volatile
        private var INSTANCE: BloodDatabase? = null

        fun getInstance(context: Context): BloodDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BloodDatabase::class.java,
                        "blood_pressure_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance

                }

                return instance
            }
        }
    }
}