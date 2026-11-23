package com.example.android_hw

import javax.inject.Inject

class LoadRatingsUseCase @Inject constructor(private val repository: AppsRepository) {
    suspend operator fun invoke(apps: List<AppInfo>, parallel: Boolean): Map<String, Float> {
        val ids = apps.map { it.id }
        return if (parallel) repository.loadRatingsInParallel(ids) else repository.loadRatingsSequentially(ids)
    }
}
