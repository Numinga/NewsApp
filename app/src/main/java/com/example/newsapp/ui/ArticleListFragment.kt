package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.ui.adapters.ArticlesListAdapter
import com.example.newsapp.viewmodels.ArticleListViewModel

class ArticleListFragment : Fragment(), ArticlesListAdapter.ItemClickListener {

    private lateinit var list: RecyclerView
    private lateinit var progress: ProgressBar
    private val viewModel: ArticleListViewModel by activityViewModels()

    companion object {
        fun newInstance() = ArticleListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_article_list, container, false)
        progress = view.findViewById(R.id.progress)
        list = view.findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(activity)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.downloadStatus.observe(viewLifecycleOwner) {
            if (it == null || !it) {
                progress.visibility = View.VISIBLE
                list.visibility = View.GONE
            } else {
                progress.visibility = View.GONE
                list.visibility = View.VISIBLE
                if (list.adapter == null) {
                    val adapter =
                        ArticlesListAdapter(requireContext(), viewModel.getArticles(), this)
                    list.adapter = adapter
                }
            }
        }
    }

    override fun onItemClick(position: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, ArticleContainerFragment.newInstance(position))
            .addToBackStack(this.javaClass.name).commit()
    }
}