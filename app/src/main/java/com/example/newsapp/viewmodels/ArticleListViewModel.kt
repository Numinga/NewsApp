package com.example.newsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.newsapp.domain.NewsRepository
import com.kwabenaberko.newsapilib.models.Article
import kotlinx.coroutines.Dispatchers


class ArticleListViewModel : ViewModel() {
    var downloadStatus: LiveData<Boolean> = liveData(Dispatchers.IO) {
        val status = NewsRepository.downloadData()
        emit(null == status)
    }

    fun getArticles(): List<Article> {
        return NewsRepository.articles
    }
}