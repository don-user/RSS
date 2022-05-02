package ru.yundon.rss.presantation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.yundon.rss.databinding.FragmentNewsBinding
import ru.yundon.rss.presantation.adapter.RssAdapter
import ru.yundon.rss.presantation.viewmodel.ViewModelRssNews
import ru.yundon.rss.utils.Constants
import ru.yundon.rss.utils.Constants.EXCEPTION_MESSAGE_PARAM

class FragmentNews: Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding ?: throw RuntimeException(EXCEPTION_MESSAGE_PARAM)

    private var adapterRss: RssAdapter = RssAdapter()
    private lateinit var viewModelRss: ViewModelRssNews


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        viewModelRss = ViewModelProvider(this)[ViewModelRssNews::class.java]

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val newsName = requireArguments().getString("KEY_ARGS")

        if (newsName != null) {
            requestApi(newsName)
            getRssEntityList(newsName)
        } else{
            toast("ERROR: NOT DATA")
        }

        setupRecyclerView()
//        setToolbar(newsName)
        observeRssList()

    }

    private fun requestApi(newsName: String) = with(viewModelRss){
        loadRssInfo(newsName)
    }

    private fun getRssEntityList(newsName: String) = with(viewModelRss){
        Log.d("TAG", "NewsRecyclerActivity getRssEntityList")
        getListRssEntity(newsName)
    }

    private fun setupRecyclerView(){
        binding.rssRecycler.apply {
            adapter = adapterRss
        }
        setupClickFavorites()

    }

    private fun setupClickAdapterItem(){
        adapterRss.itemClickListener = {
        }

    }

    private fun setupClickFavorites(){
        adapterRss.itemFavoritesListener = {
            Log.d("TAG", "NewsRecyclerActivity setupClickFavorites $it")
            viewModelRss.setFavoritesStatus(it)
        }
    }

    private fun observeRssList(){

        viewModelRss.apply {

            getListRss.observe(viewLifecycleOwner){
                Log.d("TAG", "NewsRecyclerActivity getListRss $it")
                adapterRss.submitList(it)
            }

            errorConnection.observe(viewLifecycleOwner){if (!it) toast(Constants.MESSAGE_ERROR)}

            isLoading.observe(viewLifecycleOwner){
                binding.progressBarLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    private fun toast(text: String){
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}

