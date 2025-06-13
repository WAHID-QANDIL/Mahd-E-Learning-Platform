package org.mahd_e_learning_platform.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState

    init {
        viewModelScope.launch {
            _uiState.update {
                HomeUiState(
                    //TODO: fetch from data source
                )
            }
        }
    }




}