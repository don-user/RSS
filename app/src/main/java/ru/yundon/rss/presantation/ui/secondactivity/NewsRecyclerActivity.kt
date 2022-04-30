package ru.yundon.rss.presantation.ui.secondactivity

import android.os.Bundle
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

class NewsRecyclerActivity : AppCompatActivity(), RssAdapter.ItemClickListener {

    private lateinit var binding: ActivityNewsRecyclerBinding
    private var adapterRss: RssAdapter = RssAdapter(this)
    private lateinit var viewModelRss: ViewModelRssNews

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelRss = ViewModelProvider(this)[ViewModelRssNews::class.java]

        val newsName = intent.getStringExtra(EXTRA)


        if (newsName != null) {
            observeRssList(newsName)
        } else{
            toast("ERROR: NOT DATA")
        }
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
//        viewModelRss.apply {
//            updateListForRecycle(item)
//            favorites.observe(this@NewsRecyclerActivity){
//                if (!it) toast(MESSAGE_IS_NOT_FAVORITES)
//                else toast(MESSAGE_IS_FAVORITES)
//            }
//        }
    }

    private fun setupRecyclerView(){
        binding.rssRecycler.apply {
            layoutManager = LinearLayoutManager(this@NewsRecyclerActivity)
            adapter = adapterRss
            setHasFixedSize(true)
        }
    }

    private fun observeRssList(newsName: String){

        viewModelRss.apply {

            loadRssInfo(newsName)
            getListRssEntity(newsName)

            getListRss.observe(this@NewsRecyclerActivity){
                adapterRss.updateRssList(it)
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