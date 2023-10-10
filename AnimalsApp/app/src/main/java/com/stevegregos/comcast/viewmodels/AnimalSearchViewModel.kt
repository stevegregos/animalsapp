package com.stevegregos.comcast.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevegregos.comcast.models.Animal
import com.stevegregos.comcast.models.Bird
import com.stevegregos.comcast.models.Bug
import com.stevegregos.comcast.models.Dog
import com.stevegregos.comcast.requests.AnimalApi
import com.stevegregos.comcast.requests.RetrofitClient
import com.stevegregos.comcast.viewmodels.AnimalSearchViewModel.State.Companion.ERROR_STATE
import kotlinx.coroutines.launch

class AnimalSearchViewModel constructor(
    private val animalApi: AnimalApi = RetrofitClient.animalApi,
) : ViewModel() {
    private val _stateMutableLiveData = MutableLiveData<State>()

    private lateinit var currentState: State

    val stateLiveData: LiveData<State> get() = _stateMutableLiveData

    init {
        fetchAnimals()
    }

    fun fetchAnimals() {
        val allAnimals = mutableListOf<Animal>()

        viewModelScope.launch {
            val result = animalApi.searchAnimal("dog")
            if (result.isSuccessful) {
                allAnimals.addAll(result.body() as List<Dog>)
                currentState = State(allAnimals, false, "")
            } else {
                //Error handling when the search is not successful would go here, and an error state would be emitted
            }

            val bugResult = animalApi.searchAnimal("bug")
            if (bugResult.isSuccessful) {
                allAnimals.addAll(bugResult.body() as List<Bug>)
                currentState = State(allAnimals, false, "")
            } else {
                //Error handling when the search is not successful would go here, and an error state would be emitted
            }

            val birdResult = animalApi.searchAnimal("bird")
            if (birdResult.isSuccessful) {
                allAnimals.addAll(birdResult.body() as List<Bird>)
                currentState = State(allAnimals, false, "")
            } else {
                //Error handling when the search is not successful would go here, and an error state would be emitted
            }

            if (allAnimals.isEmpty()) {
                currentState = ERROR_STATE
            }

            updateState()
        }
    }


    private fun updateState() {
        _stateMutableLiveData.postValue(currentState)
    }

    data class State(
        val animals: List<Animal>?,
        val isLoading: Boolean,
        val errorMessage: String,
    ) {
        companion object {
            val DEFAULT_STATE: State = State(
                animals = emptyList(),
                isLoading = false,
                errorMessage = "",
            )

            val ERROR_STATE: State = State(
                animals = emptyList(),
                isLoading = false,
                errorMessage = "No animals returned",
            )
        }
    }
}