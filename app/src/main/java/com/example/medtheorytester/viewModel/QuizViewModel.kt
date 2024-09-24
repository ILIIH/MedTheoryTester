package com.example.medtheorytester.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Riddle
import com.example.domain.useCase.GetAllRiddlesUseCase
import kotlinx.coroutines.launch

const val  BATCH_SIZE = 5;
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
        if(index%BATCH_SIZE != 0){
            _currentRiddle.value = _riddlesListState.value[index];
        }
        else {
            fetchRiddles(index)
        }
    }
    fun fetchRiddles(index:Int  = 0 ) {
        viewModelScope.launch {
            _isLoaded.value = true
            _riddlesListState.value = getAllRiddlesUseCase.execute(0)
            _currentRiddle.value = _riddlesListState.value.first();
            _isLoaded.value = false
        }
    }
}