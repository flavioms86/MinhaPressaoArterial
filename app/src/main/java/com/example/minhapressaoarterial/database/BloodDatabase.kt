package com.example.minhapressaoarterial.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.minhapressaoarterial.model.BloodPressure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [BloodPressure::class], version = 2, exportSchema = false)
abstract class BloodDatabase : RoomDatabase() {

    abstract fun bloodPressureDao(): BloodPressureDao

    companion object {

        @Volatile
        private var INSTANCE: BloodDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): BloodDatabase {
            //Retorna se a instancia nao for nula,
            //Se for nula, cria o banco de dados
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BloodDatabase::class.java,
                    "blood_pressure_history_database"
                )
                    .fallbackToDestructiveMigration()
                    //.addCallback(BloodDatabaseCallBack(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

//        private class BloodDatabaseCallBack(
//            private val scope: CoroutineScope
//        ) : RoomDatabase.Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                INSTANCE?.let { database ->
//                    scope.launch(Dispatchers.IO) {
//
//                    }
//                }
//            }
//        }

    }
}