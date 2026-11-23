package com.example.android_hw

import java.io.IOException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class LoadRatingsUseCaseTest {
    private val apps = listOf("z", "a").map { AppDto(it, "Same name", "", "", "").toAppInfo() }
    private fun useCase(
        sequential: (List<String>) -> Map<String, Float> = { error("Unexpected sequential request") },
        parallel: (List<String>) -> Map<String, Float> = { error("Unexpected parallel request") },
    ) = LoadRatingsUseCase(object : AppsRepository {
        override suspend fun getApps(): List<AppInfo> = error("Unexpected catalog request")
        override suspend fun loadRatingsSequentially(ids: List<String>) = sequential(ids)
        override suspend fun loadRatingsInParallel(ids: List<String>) = parallel(ids)
    })

    @Test fun requestsSequentialRatingsInCatalogOrder() = runBlocking {
        val requested = mutableListOf<String>()
        useCase(sequential = { requested.addAll(it); emptyMap() })(apps, parallel = false)
        assertEquals(listOf("z", "a"), requested)
    }

    @Test fun requestsParallelRatingsForDistinctIdsWithSameName() = runBlocking {
        val requested = mutableListOf<String>()
        useCase(parallel = { requested.addAll(it); emptyMap() })(apps, parallel = true)
        assertEquals(listOf("z", "a"), requested)
    }

    @Test fun returnsRatingsAssociatedWithOriginalIds() = runBlocking {
        val expected = mapOf("a" to 4.9f, "z" to 3.1f)
        assertEquals(expected, useCase(parallel = { expected })(apps, parallel = true))
    }

    @Test fun handlesEmptyCatalog() = runBlocking {
        val result = useCase(sequential = { ids -> ids.associateWith { 4f } })(emptyList(), parallel = false)
        assertTrue(result.isEmpty())
    }

    @Test fun propagatesRepositoryFailure() {
        val failure = IOException("offline")
        assertSame(failure, assertThrows(IOException::class.java) {
            runBlocking { useCase(sequential = { throw failure })(apps, parallel = false) }
        })
    }
}
