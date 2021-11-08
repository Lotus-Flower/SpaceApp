package meehan.matthew.spaceapp.ui.articleList

import meehan.matthew.spaceapp.data.ArticleResponseItem

sealed class ArticleListViewState {
    object Loading: ArticleListViewState()
    data class Loaded(val data: List<ArticleResponseItem>): ArticleListViewState()
}