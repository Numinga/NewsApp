package com.example.newsapp.ui

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.viewmodels.ArticleListViewModel
import java.text.SimpleDateFormat


class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding
    private val viewModel: ArticleListViewModel by activityViewModels()

    companion object {
        private val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        private const val dateFormat = "yyyy.MM.dd hh:mm"
        private const val keyPosition = "position"

        fun newInstance(position: Int) = ArticleFragment().apply {
            arguments = Bundle().apply {
                putInt(keyPosition, position)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = viewModel.getArticles()[arguments?.getInt(keyPosition)!!]
        binding.article = article
        binding.date.text = parseDate(article.publishedAt)

        Glide.with(requireContext())
            .load(article.urlToImage)
            .placeholder(R.drawable.placeholder)
            .into(binding.image)
    }

    private fun parseDate(date: String): String {
        return DateFormat.format(
            dateFormat, formatter.parse(date)
        ).toString()
    }
}