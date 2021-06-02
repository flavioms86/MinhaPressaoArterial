package com.example.minhapressaoarterial.viewmodel

import androidx.lifecycle.*
import com.example.minhapressaoarterial.database.BloodRepository
import com.example.minhapressaoarterial.model.BloodPressure
import com.example.minhapressaoarterial.model.BloodUpdate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BloodPressureViewModel(private val repository: BloodRepository) : ViewModel() {

    val allBloodPressures: LiveData<List<BloodPressure>> = repository.allBloodPressures.asLiveData()


    fun insertBloodPressure (pressure: BloodPressure) = viewModelScope.launch {
        repository.insert(pressure)
    }

    fun updateBloodPressureSingle (pressure: BloodUpdate) = viewModelScope.launch {
        repository.updateSingle(pressure)
    }

    fun deleteBloodPressures() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun deleteBloodPressure(pressure: BloodPressure) = viewModelScope.launch {
        repository.deleteBloodPressure(pressure)
    }
}

class BloodPressureViewModelFactory(private val repository: BloodRepository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BloodPressureViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BloodPressureViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Class desconhecida")
    }
}