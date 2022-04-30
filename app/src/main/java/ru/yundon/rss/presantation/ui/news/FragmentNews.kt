package ru.yundon.rss.presantation.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.yundon.rss.databinding.FragmentNewsBinding
import ru.yundon.rss.presantation.ui.secondactivity.NewsRecyclerActivity
import ru.yundon.rss.utils.Constants.EXCEPTION_MESSAGE_PARAM
import ru.yundon.rss.utils.Constants.EXTRA
import ru.yundon.rss.utils.TypeOfNews

class FragmentNews: Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding ?: throw RuntimeException(EXCEPTION_MESSAGE_PARAM)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        requireActivity().run {
            startActivity(Intent(this, NewsRecyclerActivity:: class.java).putExtra(EXTRA, newsName))
        }
    }
}

