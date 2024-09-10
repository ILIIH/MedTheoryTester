package com.example.medtheorytester.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Riddle
import com.example.domain.useCase.GetAllRiddlesUseCase
import kotlinx.coroutines.launch

class QuizViewModel(
    private val getAllRiddlesUseCase: GetAllRiddlesUseCase
) : ViewModel() {

    private val _riddlesListState = mutableStateOf<List<Riddle>>(emptyList())
    private var index = 0;
    private val _currentRiddle = mutableStateOf<Riddle?>(null)
    val currentRiddle: State<Riddle?> get() = _currentRiddle

    private val _isLoaded = mutableStateOf(false)
    val isLoaded: State<Boolean> get() = _isLoaded

    init {
        fetchRiddles()
    }
    fun next(){
        index++;
        if(_riddlesListState.value.size < index){
            _currentRiddle.value = _riddlesListState.value[index];
        }
        else {

        }
    }
    fun fetchRiddles() {
        viewModelScope.launch {
            _isLoaded.value = true
            _riddlesListState.value = getAllRiddlesUseCase.execute()
            _currentRiddle.value = _riddlesListState.value.first();
            _isLoaded.value = false
        }
    }
}