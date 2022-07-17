package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.newsapp.R
import com.example.newsapp.ui.adapters.ArticlesPagerAdapter
import com.example.newsapp.viewmodels.ArticleListViewModel

class ArticleContainerFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private val viewModel: ArticleListViewModel by activityViewModels()

    companion object {
        private const val keyPosition = "position"
        fun newInstance(position: Int) = ArticleContainerFragment().apply {
            arguments = Bundle().apply {
                putInt(keyPosition, position)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_article_container, container, false)
        viewPager = view.findViewById(R.id.pager)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = ArticlesPagerAdapter(requireActivity(), viewModel.getArticles())
        viewPager.setCurrentItem(arguments?.getInt(keyPosition)!!, false)
    }
}