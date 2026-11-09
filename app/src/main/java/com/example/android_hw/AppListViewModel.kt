package com.example.android_hw

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
    repository: AppsRepository,
) : ViewModel() {
    var apps: List<AppInfo> by mutableStateOf(emptyList())
        private set
    var isLoading by mutableStateOf(true)
        private set
    var error: String? by mutableStateOf(null)
        private set

    init {
        viewModelScope.launch {
            try {
                apps = repository.getApps()
            } catch (exception: IOException) {
                error = "Не удалось загрузить каталог. Проверьте подключение к интернету."
            } catch (exception: HttpException) {
                error = "Не удалось загрузить каталог: ошибка сервера."
            } finally {
                isLoading = false
            }
        }
    }
}
