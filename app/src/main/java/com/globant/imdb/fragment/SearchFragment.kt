package com.globant.imdb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.globant.imdb.components.adapter.MovieCardAdapter
import com.globant.imdb.databinding.SearchFragmentBinding
import com.globant.imdb.viewmodel.MainActivityVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment() {
    private lateinit var binding: SearchFragmentBinding
    val vm: MainActivityVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        val adapter = MovieCardAdapter()
        binding.searchBar.clearFocus()
        binding.moviesList.adapter = adapter
        vm.movies.observe(viewLifecycleOwner){movies ->
            adapter.setData(movies)
        }
        vm.loadMovies()
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
        return binding.root
    }

    companion object{
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}