package com.example.minhapressaoarterial.viewmodel

import androidx.lifecycle.ViewModel
import com.example.minhapressaoarterial.database.BloodRepository
import com.example.minhapressaoarterial.model.BloodPressure
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BloodPressureViewModel(private val repository: BloodRepository) : ViewModel() {

    fun insert (pressure: BloodPressure) = GlobalScope.launch {
        repository.insert(pressure)
    }

    fun update (pressure: BloodPressure) = GlobalScope.launch {
        repository.update(pressure)
    }

    fun delete(pressure: BloodPressure) = GlobalScope.launch {
        repository.delete(pressure)
    }

}