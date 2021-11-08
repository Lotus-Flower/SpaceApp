package meehan.matthew.spaceapp.ui.articleList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import meehan.matthew.spaceapp.R
import meehan.matthew.spaceapp.databinding.FragmentArticleListBinding

@AndroidEntryPoint
class ArticleListFragment : Fragment(R.layout.fragment_article_list) {

    private val viewModel: ArticleListViewModel by viewModels()
    private lateinit var binding: FragmentArticleListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.spaceListRecycler.apply {
            setHasFixedSize(true)
            adapter = ArticleAdapter { article ->
                viewModel.toArticleDetails(article)
            }
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state) {
                    ArticleListViewState.Loading -> {
                        binding.spaceListRecycler.visibility = View.GONE
                        binding.loadingView.visibility = View.VISIBLE
                    }
                    is ArticleListViewState.Loaded -> {
                        binding.spaceListRecycler.visibility = View.VISIBLE
                        binding.loadingView.visibility = View.GONE
                        (binding.spaceListRecycler.adapter as ArticleAdapter).submitList(state.data)
                    }
                }
            }
        }
    }
}