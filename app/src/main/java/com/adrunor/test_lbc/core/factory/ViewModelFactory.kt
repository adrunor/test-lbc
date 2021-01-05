package com.adrunor.test_lbc.core.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adrunor.test_lbc.ui.PlaylistViewModel
import com.adrunor.test_lbc.core.Injection
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaylistViewModel::class.java)) {
            return PlaylistViewModel(Injection.provideMusicDataSource(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}