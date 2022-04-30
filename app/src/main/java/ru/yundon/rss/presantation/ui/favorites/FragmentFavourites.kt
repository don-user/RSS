package ru.yundon.rss.presantation.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.yundon.rss.presantation.adapter.RssAdapter
import ru.yundon.rss.databinding.FragmentFavouritesBinding
import ru.yundon.rss.data.database.RssDbModel
import ru.yundon.rss.domain.RssEntity
import ru.yundon.rss.presantation.ui.secondactivity.ViewModelRssNews
import ru.yundon.rss.utils.Constants.MESSAGE_IS_NOT_FAVORITES

class FragmentFavourites: Fragment() {

    private var fragmentFavorites: FragmentFavouritesBinding? = null
    private var adapterRss = RssAdapter()
    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var favoritesRssViewModel: ViewModelRssNews

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        fragmentFavorites = binding

        favoritesRssViewModel = ViewModelProvider(this)[ViewModelRssNews::class.java]
        observeListRss()
        setupRecyclerView()
        return binding.root
    }

//      override fun onFavoriteClick(item: RssEntity) {
//        favoritesRssViewModel.apply {
//            deleteFavoritesRss(item)
//            Toast.makeText(context, MESSAGE_IS_NOT_FAVORITES, Toast.LENGTH_SHORT).show()
//        }
//    }

    private fun setupRecyclerView(){
        binding.rssFavoritesRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = adapterRss
            setHasFixedSize(true)
        }
    }

    private fun observeListRss(){
//        favoritesRssViewModel.listFavoritesRss.observe(this){
//            adapterRss.updateRssList(it)
//        }
    }

}