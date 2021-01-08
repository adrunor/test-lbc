package com.adrunor.test_lbc

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adrunor.test_lbc.ui.PlaylistActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistActivityTest {

    @Test fun testCreateActivity() {
        val scenario = ActivityScenario.launch(PlaylistActivity::class.java)
        scenario.moveToState(Lifecycle.State.CREATED)
    }
}