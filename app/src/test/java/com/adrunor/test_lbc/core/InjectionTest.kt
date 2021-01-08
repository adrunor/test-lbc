package com.adrunor.test_lbc.core

import android.content.Context
import com.adrunor.test_lbc.core.repository.MusicRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class InjectionTest {

    @MockK private lateinit var context: Context

    @Before fun setUp() = MockKAnnotations.init(this, true)

    @Test fun test_provideMusicDataSource() {
        every { context.applicationContext } returns context
        val repo = Injection.provideMusicDataSource(context)
        assertEquals(repo::class, MusicRepository::class)
    }
}