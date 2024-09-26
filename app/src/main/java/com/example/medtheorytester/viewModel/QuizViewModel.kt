package com.example.medtheorytester.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Riddle
import com.example.domain.useCase.GetAllRiddlesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait

const val  BATCH_SIZE = 4;
class QuizViewModel(
    private val getAllRiddlesUseCase: GetAllRiddlesUseCase
) : ViewModel() {

    private val _riddlesListState = mutableStateOf<List<Riddle>>(emptyList())
    private val _currentRiddle = mutableStateOf<Riddle?>(null)
    val currentRiddle: State<Riddle?> get() = _currentRiddle

    private val _isLoading = mutableStateOf(false)

    private var index = 0;
    private var batch_limit = 0;

    val isLoading: State<Boolean> get() = _isLoading

    init {
        fetchRiddles(batch_limit)
    }
    fun next(){
        index++;
        if(_riddlesListState.value.size > index){
            _currentRiddle.value = _riddlesListState.value[index];
        }
        else {
            fetchRiddles(batch_limit)
            batch_limit += BATCH_SIZE
            index = 0
        }
    }
    private fun fetchRiddles(index:Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _riddlesListState.value = getAllRiddlesUseCase.execute(index)
            _currentRiddle.value = _riddlesListState.value.first();
            _isLoading.value = false
        }
    }
}