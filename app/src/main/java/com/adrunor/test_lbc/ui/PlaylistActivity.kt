package com.adrunor.test_lbc.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.text.isDigitsOnly
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
        setSupportActionBar(binding.toolbar)

        val recyclerView = binding.musicRecycler
        val layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))

        getPlaylist()

        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        menu?.apply {
            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            val menuItem = this.findItem(R.id.search)
            menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    getPlaylist()
                    return true
                }

            })
            (menuItem.actionView as SearchView).apply {
                this.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            }
        }

        return true
    }



    override fun onNewIntent(intent: Intent?) {
        setIntent(intent)
        intent?.let {
            handleIntent(it)
        }
        super.onNewIntent(intent)
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
                        binding.musicRecycler.visibility = View.GONE
                        binding.emptyState.root.visibility = View.VISIBLE
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

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                searchInPlaylist(query)
            }
        }
    }

    private fun searchInPlaylist(query: String) {
        Log.d(TAG, query)
        disposable.add(
                viewModel.searchMusic(if (query.isDigitsOnly()) query else "%$query%")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ playlist ->
                            if (playlist.isEmpty()) {
                                binding.musicRecycler.visibility = View.GONE
                                binding.emptyState.root.visibility = View.VISIBLE
                            } else {
                                adapter.setMusicList(playlist)
                            }
                        }, {
                            throw it
                        })

        )
    }

    companion object {
        private const val TAG = "PlaylistActivity"
    }
}