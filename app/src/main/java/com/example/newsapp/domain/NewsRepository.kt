package com.example.newsapp.domain

import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.NewsApiClient.ArticlesResponseCallback
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


object NewsRepository {
    private val API_KEY = "9e04dc0f18274840840954b68f1fefc7"
    private val newsApiClient = NewsApiClient(API_KEY)

    lateinit var articles: List<Article>

    suspend fun downloadData() =
        suspendCoroutine<String?> { cont ->
            newsApiClient.getEverything(EverythingRequest.Builder()
                .q("space")
                .build(), object : ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse) {
                    if (response.status.equals("ok")) {
                        articles = response.articles
                        cont.resume(null)
                    } else {
                        cont.resume("Error while downloading data.")
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    cont.resumeWithException(throwable)
                }
            })
        }
}
