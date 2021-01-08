package com.adrunor.test_lbc.core.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import com.adrunor.test_lbc.ui.PlaylistViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException

class ViewModelFactoryTest {

    @MockK
    private lateinit var context: Context

    @Before
    fun setUp() = MockKAnnotations.init(this, true)

    @Test fun test_createPlaylistViewModel() {
        every { context.applicationContext } returns context
        val viewModel = ViewModelFactory(context).create(PlaylistViewModel::class.java)
        assertEquals(viewModel::class, PlaylistViewModel::class)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_fakeViewModel() {
        every { context.applicationContext } returns context
        ViewModelFactory(context).create(FakeModel::class.java)
    }

    class FakeModel : ViewModel() {

    }
}