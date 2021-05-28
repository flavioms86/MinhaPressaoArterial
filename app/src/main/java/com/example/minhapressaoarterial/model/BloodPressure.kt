package com.example.minhapressaoarterial.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity(tableName = "blood_pressure")
data class BloodPressure(
    @PrimaryKey(autoGenerate = true)
    var bloodId: Int,
    @ColumnInfo(name = "register_time")
    val registerTime: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "sis_pressure")
    val sisPressure: Int,
    @ColumnInfo(name = "dia_pressure")
    val diaPressure: Int,
    @ColumnInfo(name = "pul_pressure")
    val pulPressure: Int,
    @ColumnInfo(name = "health_status")
    val healthStats: String
) {
    @Ignore constructor(): this(0, 0, 0, 0, 0, "")
}


