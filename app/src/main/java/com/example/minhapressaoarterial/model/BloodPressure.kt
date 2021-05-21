package com.example.minhapressaoarterial.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blood_pressure")
data class BloodPressure (

    @PrimaryKey(autoGenerate = true)
    val bloodId: Int? = null,
    @ColumnInfo(name = "sis_pressure")
    val sisPressure: Int,
    @ColumnInfo(name = "dia_pressure")
    val diaPressure: Int,
    @ColumnInfo(name = "pul_pressure")
    val pulPressure: Int,
    @ColumnInfo(name = "register_time")
    val registerTime: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "health_status")
    val healthStats: String
)