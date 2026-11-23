package com.example.android_hw

import org.junit.Assert.*
import org.junit.Test

class AppMapperTest {
    private val dto = AppDto("id", "Name", "Description", "Category", "icon")

    @Test fun mapsCatalogFieldsToDomain() {
        val app = dto.toAppInfo()
        assertEquals(listOf("id", "Name", "Description", "Category", "icon"),
            listOf(app.id, app.name, app.description, app.category, app.iconUrl))
    }

    @Test fun suppliesLocalIconFallback() {
        val app = dto.toAppInfo()
        assertEquals(R.drawable.ic_launcher_foreground, app.iconRes)
        assertEquals(0xFF4056C8, app.iconBackground)
    }

    @Test fun mapsEditedDomainFieldsToDto() {
        val app = dto.toAppInfo().copy(id = "new", name = "Editor", category = "Tools",
            description = "Edit files", iconUrl = "new-icon")
        assertEquals(AppDto("new", "Editor", "Edit files", "Tools", "new-icon"), app.toAppDto())
    }

    @Test fun mapsMissingIconUrlToEmptyString() {
        assertEquals("", dto.toAppInfo().copy(iconUrl = null).toAppDto().iconUrl)
    }

    @Test fun preservesDtoWithEmptyOptionalTextOnRoundTrip() {
        val original = dto.copy(description = "", iconUrl = "")
        assertEquals(original, original.toAppInfo().toAppDto())
    }
}
