package meehan.matthew.spaceapp.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import meehan.matthew.spaceapp.network.ArticleAPIEndpointInterface
import meehan.matthew.spaceapp.data.ArticleResponse
import meehan.matthew.spaceapp.IoDispatcher
import retrofit2.Response
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val articleAPIEndpointInterface: ArticleAPIEndpointInterface
) {
    suspend fun getArticles(): Response<ArticleResponse> = withContext(dispatcher) {
        articleAPIEndpointInterface.getArticles()
    }
}