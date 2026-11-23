package com.example.android_hw

import java.io.IOException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class AppsRepositoryTest {
    private fun repository(catalog: suspend () -> List<AppDto>) = AppsRepositoryImpl(object : AppsApi {
        override suspend fun getApps() = catalog()
        override suspend fun getAppDetails(id: String): AppDetailsDto = error("Unexpected details request")
    })

    @Test fun returnsEmptyCatalog() = runBlocking {
        assertTrue(repository { emptyList() }.getApps().isEmpty())
    }

    @Test fun mapsCatalogInServerOrder() = runBlocking {
        val first = AppDto("z", "First", "Text", "Tools", "icon")
        val second = first.copy(id = "a", name = "Second")
        val apps = repository { listOf(first, second) }.getApps()
        assertEquals(listOf(first.toAppInfo(), second.toAppInfo()), apps)
    }

    @Test fun propagatesApiFailure() {
        val failure = IOException("offline")
        assertSame(failure, assertThrows(IOException::class.java) {
            runBlocking { repository { throw failure }.getApps() }
        })
    }

    @Test fun loadsSequentialRatingsById() = runBlocking {
        val ratings = repository { error("Unexpected catalog request") }.loadRatingsSequentially(listOf("a", "b"))
        assertEquals(setOf("a", "b"), ratings.keys)
        assertTrue(ratings.values.all { it in 3f..5f })
    }

    @Test fun loadsParallelRatingsById() = runBlocking {
        val ratings = repository { error("Unexpected catalog request") }.loadRatingsInParallel(listOf("c", "d"))
        assertEquals(setOf("c", "d"), ratings.keys)
        assertTrue(ratings.values.all { it in 3f..5f })
    }
}
