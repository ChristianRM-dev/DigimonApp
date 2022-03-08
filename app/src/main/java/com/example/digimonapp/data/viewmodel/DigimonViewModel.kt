package com.example.digimonapp.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digimonapp.data.repository.DigimonRepository
import com.example.digimonapp.models.Digimon

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DigimonViewModel@Inject constructor(private val repository: DigimonRepository) : ViewModel() {
    private val countryLiveData = MutableLiveData<ArrayList<Digimon>?>()

    fun getCountry() = countryLiveData

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            val countries = repository.getDigimons()
            when (countries.isSuccessful) {
                true -> {
                    countryLiveData.postValue(countries.body())
                    }

                else -> {
                    Timber.e(countries.message())
                }
            }
        }
    }
}