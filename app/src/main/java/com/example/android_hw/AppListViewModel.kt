package com.example.android_hw

import androidx.lifecycle.ViewModel

class AppListViewModel : ViewModel() {
    val apps: List<AppInfo> = applications.toList()
}
