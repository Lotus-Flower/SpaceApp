package meehan.matthew.spaceapp.ui.articleList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import meehan.matthew.spaceapp.repository.ArticleRepository
import meehan.matthew.spaceapp.data.ArticleResponseItem
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _state: MutableStateFlow<ArticleListViewState> = MutableStateFlow(
        ArticleListViewState.Loading
    )
    val state: StateFlow<ArticleListViewState> = _state

    init {
        viewModelScope.launch {
            val response = articleRepository.getArticles()

            if (response.isSuccessful) {
                _state.value = ArticleListViewState.Loaded(
                    data = response.body()?.toList().orEmpty()
                )
            }
        }
    }

    fun toArticleDetails(articleResponseItem: ArticleResponseItem) {
        // TODO
        Log.d("Navigation", "Navigate to article: ${articleResponseItem.id}'")
    }
}