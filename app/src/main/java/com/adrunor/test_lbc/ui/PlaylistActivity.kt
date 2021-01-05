package com.adrunor.test_lbc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrunor.test_lbc.R
import com.adrunor.test_lbc.core.factory.ViewModelFactory
import com.adrunor.test_lbc.core.model.Music
import com.adrunor.test_lbc.databinding.ActivityPlaylistBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlaylistActivity : AppCompatActivity(R.layout.activity_playlist) {

    private var _binding: ActivityPlaylistBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlaylistViewModel by viewModels { ViewModelFactory(this)}
    private val adapter = PlaylistAdapter()
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         _binding = ActivityPlaylistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val recyclerView = binding.musicRecycler
        val layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))

        getPlaylist()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    private fun getPlaylist() {
        disposable.add(
            viewModel.getPlaylist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ playlist ->
                    if (playlist.isEmpty()) {
                        fetchPlaylist()
                    } else {
                        adapter.setMusicList(playlist)
                    }
                }, {
                    throw it
                })
        )
    }

    private fun fetchPlaylist() {
        disposable.add(
            viewModel.fetchPlaylist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ playlist ->
                    if (playlist.isEmpty()) {
                        //todo change ui
                    } else {
                        savePlaylist(playlist)
                    }
                }, {
                    throw it
                })
        )
    }

    private fun savePlaylist(playlist: List<Music>) {
        disposable.add(
            viewModel.saveAll(playlist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getPlaylist()
                }, {
                    adapter.setMusicList(playlist)
                })
        )
    }

    companion object {
        private const val TAG = "PlaylistActivity"
        private const val LAYOUT_MANAGER_PARCEL = "LAYOUT_MANAGER_PARCEL"
    }
}