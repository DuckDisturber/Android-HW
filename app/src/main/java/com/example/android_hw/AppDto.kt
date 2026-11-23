package com.example.android_hw

data class AppDto(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String,
)

fun AppDto.toAppInfo(): AppInfo = AppInfo(
    id = id,
    name = name,
    description = description,
    category = category,
    iconRes = R.drawable.ic_launcher_foreground,
    iconBackground = 0xFF4056C8,
    iconUrl = iconUrl,
)

fun AppInfo.toAppDto(): AppDto = AppDto(
    id = id,
    name = name,
    description = description,
    category = category,
    iconUrl = iconUrl.orEmpty(),
)
