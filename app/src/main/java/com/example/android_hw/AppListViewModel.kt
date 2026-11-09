package com.example.android_hw

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
    repository: AppsRepository,
) : ViewModel() {
    val apps: List<AppInfo> = repository.getApps()
}
