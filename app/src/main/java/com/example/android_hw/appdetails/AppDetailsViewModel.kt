package com.example.android_hw.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface InstallState {
    data object NotInstalled : InstallState
    data class Installing(val progress: Int) : InstallState
    data object Installed : InstallState
}

sealed interface AppDetailsState {
    data class Content(val installState: InstallState = InstallState.NotInstalled) : AppDetailsState
}

class AppDetailsViewModel : ViewModel() {
    private val _state = MutableStateFlow(AppDetailsState.Content())
    val state = _state.asStateFlow()
    private var installJob: Job? = null

    fun onInstallClick() {
        when (_state.value.installState) {
            InstallState.NotInstalled -> installJob = viewModelScope.launch { emulateInstall() }
            is InstallState.Installing -> {
                installJob?.cancel()
                _state.update { it.copy(installState = InstallState.NotInstalled) }
            }
            InstallState.Installed -> Unit
        }
    }

    private suspend fun emulateInstall() {
        _state.update { it.copy(installState = InstallState.Installing(0)) }
        for (progress in 5..100 step 5) {
            delay(150)
            _state.update { it.copy(installState = InstallState.Installing(progress)) }
        }
        _state.update { it.copy(installState = InstallState.Installed) }
    }
}
