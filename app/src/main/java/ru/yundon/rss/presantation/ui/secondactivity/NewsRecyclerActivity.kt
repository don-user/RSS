package ru.yundon.rss.presantation.ui.secondactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.yundon.rss.presantation.adapter.RssAdapter
import ru.yundon.rss.databinding.ActivityNewsRecyclerBinding
import ru.yundon.rss.data.room.database.RssEntity
import ru.yundon.rss.utils.Constants.EXTRA
import ru.yundon.rss.utils.Constants.MESSAGE_ERROR
import ru.yundon.rss.utils.Constants.MESSAGE_IS_FAVORITES
import ru.yundon.rss.utils.Constants.MESSAGE_IS_NOT_FAVORITES

class NewsRecyclerActivity : AppCompatActivity(), RssAdapter.ItemClickListener {

    private lateinit var binding: ActivityNewsRecyclerBinding
    private var adapterRss: RssAdapter = RssAdapter(this)
    private lateinit var newsRecyclerActivityViewModel: ViewModelNewsRecyclerActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsRecyclerActivityViewModel = ViewModelProvider(this, NewsRecyclerActivityViewModelFactory(application))[ViewModelNewsRecyclerActivity::class.java]

        val newsName = intent.getStringExtra(EXTRA)

        newsRecyclerActivityViewModel.getListRssEntity(newsName)

        observeRssList()
        setupRecyclerView()
        setToolbar(newsName)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }


    override fun onFavoriteClick(item: RssEntity) {
        newsRecyclerActivityViewModel.apply {
            updateListForRecycle(item)
            favorites.observe(this@NewsRecyclerActivity){
                if (!it) toast(MESSAGE_IS_NOT_FAVORITES)
                else toast(MESSAGE_IS_FAVORITES)
            }
        }
    }

    private fun setupRecyclerView(){
        binding.rssRecycler.apply {
            layoutManager = LinearLayoutManager(this@NewsRecyclerActivity)
            adapter = adapterRss
            setHasFixedSize(true)
        }
    }

    private fun observeRssList(){
        newsRecyclerActivityViewModel.apply {
            listEntityRss.observe(this@NewsRecyclerActivity){
                if (it.isNotEmpty()) adapterRss.updateRssList(it)
                else toast(MESSAGE_ERROR)
            }
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