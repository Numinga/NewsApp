package com.example.newsapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsapp.ui.ArticleFragment
import com.kwabenaberko.newsapilib.models.Article

class ArticlesPagerAdapter(
    activity: FragmentActivity,
    private val articles: List<Article>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun createFragment(position: Int): Fragment {
        return ArticleFragment.newInstance(position)
    }

}