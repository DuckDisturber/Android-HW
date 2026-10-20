package com.example.android_hw

import androidx.annotation.DrawableRes

data class AppInfo(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    @param:DrawableRes val iconRes: Int,
    val iconBackground: Long,
)

val applications = listOf(
    AppInfo("telegram", "Telegram", "Сообщения, групповые чаты, каналы и звонки.", "Общение", R.drawable.ic_telegram, 0xFF229ED9),
    AppInfo("youtube", "YouTube", "Видео, прямые трансляции и любимые каналы.", "Видео", R.drawable.ic_youtube, 0xFFFFFFFF),
    AppInfo("chrome", "Google Chrome", "Браузер для поиска информации и просмотра сайтов.", "Инструменты", R.drawable.ic_chrome, 0xFFFFFFFF),
    AppInfo("whatsapp", "WhatsApp", "Переписка, голосовые сообщения и видеозвонки.", "Общение", R.drawable.ic_whatsapp, 0xFF25D366),
    AppInfo("gmail", "Gmail", "Электронная почта, поиск писем и управление перепиской.", "Работа", R.drawable.ic_gmail, 0xFFFFFFFF),
    AppInfo("maps", "Google Карты", "Карты городов, поиск мест и построение маршрутов.", "Карты и навигация", R.drawable.ic_google_maps, 0xFFFFFFFF),
    AppInfo("spotify", "Spotify", "Музыка, подкасты и персональные плейлисты.", "Музыка", R.drawable.ic_spotify, 0xFF1DB954),
    AppInfo("vk", "ВКонтакте", "Друзья, сообщества, новости, фотографии и видео.", "Социальные сети", R.drawable.ic_vk, 0xFF0077FF),
)
