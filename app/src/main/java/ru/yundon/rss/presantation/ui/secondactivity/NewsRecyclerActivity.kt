package ru.yundon.rss.presantation.ui.secondactivity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.yundon.rss.databinding.ActivityNewsRecyclerBinding
import ru.yundon.rss.domain.RssEntity
import ru.yundon.rss.presantation.adapter.RssAdapter
import ru.yundon.rss.utils.Constants.EXTRA
import ru.yundon.rss.utils.Constants.MESSAGE_ERROR

class NewsRecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsRecyclerBinding
    private var adapterRss: RssAdapter = RssAdapter()
    private lateinit var viewModelRss: ViewModelRssNews

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelRss = ViewModelProvider(this)[ViewModelRssNews::class.java]

        val newsName = intent.getStringExtra(EXTRA)


        if (newsName != null) {
            requestApi(newsName)
            getRssEntityList(newsName)
        } else{
            toast("ERROR: NOT DATA")
        }

        setupRecyclerView()
        setToolbar(newsName)
        observeRssList()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
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
            layoutManager = LinearLayoutManager(this@NewsRecyclerActivity)
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

            getListRss.observe(this@NewsRecyclerActivity){
                Log.d("TAG", "NewsRecyclerActivity getListRss $it")
                adapterRss.submitList(it)
            }

            errorConnection.observe(this@NewsRecyclerActivity){if (!it) toast(MESSAGE_ERROR)}

            isLoading.observe(this@NewsRecyclerActivity){
                binding.progressBarLoading.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    private fun setToolbar(titleToolbar: String?){
        setSupportActionBar(binding.toolbarRss)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = titleToolbar
    }

    private fun toast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}