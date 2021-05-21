package com.example.minhapressaoarterial.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minhapressaoarterial.database.BloodRepository
import java.lang.IllegalArgumentException

class BloodPressureViewModelFactory(private val repository: BloodRepository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BloodPressureViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BloodPressureViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Class desconhecida")
    }
}