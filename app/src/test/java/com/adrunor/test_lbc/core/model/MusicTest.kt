package com.adrunor.test_lbc.core.model

import org.junit.Assert.*
import org.junit.Test

class MusicTest {

    private val music = Music(
        1,
        2,
        "toto",
        "https://via.placeholder.com/150/1a613d",
        "https://via.placeholder.com/600/1a613d"
    )

    @Test fun test_musicIdIsInt() {
        assertEquals(1, music.id)
        assertNotEquals("1", music.id)
        assertNotEquals(1f, music.id)
    }

    @Test fun test_musicAlbumIdIsInt() {
        assertEquals(2, music.albumId)
    }

    @Test fun test_musicTitleIsString() {
        assertEquals("toto", music.title)
    }

    @Test fun test_musicUrlIsValid() {
        assertEquals("https://via.placeholder.com/150/1a613d", music.url)
        assertTrue(music.url.substring(0, 8).equals("https://", ignoreCase = true))
    }

    @Test fun test_musicThumbnailUrlIsValid() {
        assertEquals("https://via.placeholder.com/600/1a613d", music.thumbnailUrl)
        assertTrue(music.thumbnailUrl.substring(0, 8).equals("https://", ignoreCase = true))
    }
}