package ru.yundon.rss.presantation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.yundon.rss.R
import ru.yundon.rss.databinding.ActivityMainBinding
import ru.yundon.rss.utils.TypeOfNews

class MainActivity: AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        buttonsClick()

    }

    private fun buttonsClick() = with(binding){
        breakingButton.setOnClickListener {
            launchActivity(TypeOfNews.BREAKING_NEWS.newsName)
        }

        hardwareButton.setOnClickListener {
            launchActivity(TypeOfNews.HARDWARE_NEWS.newsName)
        }
        gadgetsButton.setOnClickListener {
            launchActivity(TypeOfNews.GADGETS_NEWS.newsName)
        }

        softwareButton.setOnClickListener {
            launchActivity(TypeOfNews.SOFTWARE_NEWS.newsName)
        }

        gamesButton.setOnClickListener {
            launchActivity(TypeOfNews.GAMES_NEWS.newsName)
        }
    }

    private fun launchActivity(newsName: String){
        startActivity(NewsActivity.newIntentNews(this@MainActivity, newsName) )
    }

}

