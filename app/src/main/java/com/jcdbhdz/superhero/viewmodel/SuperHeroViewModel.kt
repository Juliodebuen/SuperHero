package com.jcdbhdz.superhero.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcdbhdz.superhero.data.model.*
import com.jcdbhdz.superhero.data.repository.SuperHeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperHeroViewModel @Inject constructor(
    private val superHeroRepository: SuperHeroRepository
): ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private val _characterList = MutableStateFlow<List<Character>>(emptyList())
    val characterList = _characterList.asStateFlow()

    private val _selectedCharacter = MutableStateFlow<List<Character>>(emptyList())
    val selectedCharacter = _selectedCharacter.asStateFlow()

    private val _comicList = MutableStateFlow<List<Comic>>(emptyList())
    val comicList = _comicList.asStateFlow()

    private val _eventList = MutableStateFlow<List<Event>>(emptyList())
    val eventList = _eventList.asStateFlow()

    private val _seriesList = MutableStateFlow<List<Series>>(emptyList())
    val seriesList = _seriesList.asStateFlow()

    private val _storiesList = MutableStateFlow<List<Story>>(emptyList())
    val storiesList = _storiesList.asStateFlow()



    fun getCharacterList(){
        _isLoading.value = true
        _errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            superHeroRepository.getCharacters(20, characterList.value.size)
                .catch { e ->
                    _errorMessage.value = e.message.toString()
                }.collect { data ->
                    val newList = arrayListOf<Character>()
                    newList.addAll(_characterList.value)
                    newList.addAll(data.data?.results!!)
                    _characterList.value = newList
                }
            _isLoading.value = false
        }
    }

    fun getCharacterById(id: String){
        _isLoading.value = true
        _errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            superHeroRepository.getCharacterById(id)
                .catch { e ->
                    _errorMessage.value = e.message.toString()
                }.collect { data ->
                    _selectedCharacter.value = data.data?.results!!
                }
            _isLoading.value = false
        }
    }

    fun getComicsByCharacterId(id: String){
        _isLoading.value = true
        _errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            superHeroRepository.getComicsByCharacterId(id)
                .catch { e ->
                    _errorMessage.value = e.message.toString()
                }.collect { data ->
                    _comicList.value = data.data?.results!!
                }
            _isLoading.value = false
        }
    }

    fun getEventsByCharacterId(id: String){
        _isLoading.value = true
        _errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            superHeroRepository.getEventsByCharacterId(id)
                .catch { e ->
                    _errorMessage.value = e.message.toString()
                }.collect { data ->
                    _eventList.value = data.data?.results!!
                }
            _isLoading.value = false
        }
    }

    fun getSeriesByCharacterId(id: String){
        _isLoading.value = true
        _errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            superHeroRepository.getSeriesByCharacterId(id)
                .catch { e ->
                    _errorMessage.value = e.message.toString()
                }.collect { data ->
                    _seriesList.value = data.data?.results!!
                }
            _isLoading.value = false
        }
    }

    fun getStoriesByCharacterId(id: String){
        _isLoading.value = true
        _errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            superHeroRepository.getStoriesByCharacterId(id)
                .catch { e ->
                    _errorMessage.value = e.message.toString()
                }.collect { data ->
                    _storiesList.value = data.data?.results!!
                }
            _isLoading.value = false
        }
    }


}