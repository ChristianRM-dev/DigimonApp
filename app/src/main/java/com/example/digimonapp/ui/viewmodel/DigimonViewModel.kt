package com.example.digimonapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digimonapp.data.database.entities.DigimonEntity
import com.example.digimonapp.data.DigimonRepository
import com.example.digimonapp.domain.models.Digimon
import com.example.digimonapp.domain.models.GetDigimonsUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DigimonViewModel @Inject constructor(private val getDigimonsUseCase: GetDigimonsUseCase) :
    ViewModel() {
    val digimonModel = MutableLiveData<Digimon>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getDigimonsUseCase()

            if (!result.isNullOrEmpty()) {
                digimonModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    /*private val countryLiveData = MutableLiveData<ArrayList<Digimon>?>()

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

    fun insertFavorite(digimon: Digimon) {
        viewModelScope.launch {
            val digimonEntity: DigimonEntity =
                DigimonEntity(digimon.name, digimon.img, digimon.isFavorite)
            repository.insertDigimon(digimonEntity)
        }
    }*/

}