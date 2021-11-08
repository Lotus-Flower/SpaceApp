package meehan.matthew.spaceapp.network

import meehan.matthew.spaceapp.data.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET

interface ArticleAPIEndpointInterface {
    @GET("articles")
    suspend fun getArticles(): Response<ArticleResponse>
}