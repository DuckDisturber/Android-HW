package com.example.android_hw.appdetails

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.android_hw.AppInfo
import com.example.android_hw.AppItem
import com.example.android_hw.applications

@Composable
fun AppDetailsScreen(
    app: AppInfo,
    onBackClick: () -> Unit,
    onShowcaseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var descriptionExpanded by rememberSaveable(app.id) { mutableStateOf(false) }
    val context = LocalContext.current
    val underDevelopment = {
        Toast.makeText(context, "В процессе разработки", Toast.LENGTH_SHORT).show()
    }
    Column(
        modifier.fillMaxSize().safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp),
    ) {
        Toolbar(
            onBackClick = onBackClick,
            onShareClick = {
                context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "${app.name}: ${app.description}")
                }, "Поделиться"))
            },
        )
        Spacer(Modifier.height(8.dp))
        AppDetailsHeader(app, Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(16.dp))
        InstallButton(underDevelopment, Modifier.fillMaxWidth().padding(horizontal = 16.dp))
        Spacer(Modifier.height(12.dp))
        ScreenshotsList(app.screenshots, PaddingValues(horizontal = 16.dp))
        Spacer(Modifier.height(12.dp))
        AppDescription(
            description = app.description,
            collapsed = descriptionExpanded,
            onReadMoreClick = { descriptionExpanded = true },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        )
        HorizontalDivider(Modifier.padding(16.dp))
        Developer(app.developer, underDevelopment, Modifier.fillMaxWidth().padding(start = 16.dp))
        HorizontalDivider(Modifier.padding(16.dp))
        Showcase(onAppClick = onShowcaseClick)
    }
}

@Composable
private fun Showcase(onAppClick: () -> Unit) {
    val pages = remember { applications.chunked(3) }
    val pagerState = rememberPagerState { 3 }
    Column {
        Text(
            text = "Витрина",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(16.dp))
        HorizontalPager(
            state = pagerState,
            verticalAlignment = androidx.compose.ui.Alignment.Top,
            pageSpacing = 16.dp,
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier.fillMaxWidth(),
        ) { page ->
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                pages[page].forEach { app ->
                    AppItem(app, Modifier.clickable(onClick = onAppClick))
                }
            }
        }
        Text(
            text = "${pagerState.currentPage + 1} / 3",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        )
    }
}
