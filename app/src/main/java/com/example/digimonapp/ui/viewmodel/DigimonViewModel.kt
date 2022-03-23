package com.example.digimonapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digimonapp.domain.models.Digimon
import com.example.digimonapp.domain.usecase.GetDigimonsUseCase
import com.example.digimonapp.domain.usecase.RemoveFavoriteUseCase
import com.example.digimonapp.domain.usecase.UpdateDigimonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DigimonViewModel @Inject constructor(
    private val getDigimonsUseCase: GetDigimonsUseCase,
    private val updateDigimonUseCase: UpdateDigimonUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) :
    ViewModel() {
    val digimons = MutableLiveData<List<Digimon>>()
    val isLoading = MutableLiveData<Boolean>()
    val favoriteDigimons = MutableLiveData<List<Digimon>>()

    init {
        loadDigimons()
    }

    fun loadDigimons() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getDigimonsUseCase()

            if (!result.isNullOrEmpty()) {
                digimons.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun loadFavoriteDigimons() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getDigimonsUseCase(true)

            if (!result.isNullOrEmpty()) {
                favoriteDigimons.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun insertFavorite(digimon: Digimon) {
        viewModelScope.launch {
            updateDigimonUseCase(digimon);
        }
    }

    fun removeFavorite(digimon: Digimon) {
        viewModelScope.launch {
            removeFavoriteUseCase(digimon);
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