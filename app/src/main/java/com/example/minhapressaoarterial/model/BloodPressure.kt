package com.example.minhapressaoarterial.model

import androidx.room.*
import java.time.OffsetDateTime

//Partial entity
data class BloodUpdate(
    @ColumnInfo(name = "sis_pressure")
    val sisPressure: Int,
    @ColumnInfo(name = "dia_pressure")
    val diaPressure: Int,
    @ColumnInfo(name = "pul_pressure")
    val pulPressure: Int,
    @ColumnInfo(name = "health_status")
    val healthStats: String
) {
    @PrimaryKey(autoGenerate = true)
    var bloodId: Int = 0
}

@Entity(tableName = "blood_pressure")
data class BloodPressure(
    @ColumnInfo(name = "register_time")
    val registerTime: OffsetDateTime? = null,
    @ColumnInfo(name = "sis_pressure")
    val sisPressure: Int,
    @ColumnInfo(name = "dia_pressure")
    val diaPressure: Int,
    @ColumnInfo(name = "pul_pressure")
    val pulPressure: Int,
    @ColumnInfo(name = "health_status")
    val healthStats: String
) {
    @PrimaryKey(autoGenerate = true)
    var bloodId: Int = 0

    @Ignore
    constructor(): this(null, 0, 0, 0,  "")
}



