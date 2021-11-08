package meehan.matthew.spaceapp.dependencyInjection

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import meehan.matthew.spaceapp.network.ArticleAPIEndpointInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.spaceflightnewsapi.net/v3/")
            .client(okHttpClient)
            .build()

    @Provides
    fun provideUserApiService(retrofit: Retrofit): ArticleAPIEndpointInterface =
        retrofit.create(ArticleAPIEndpointInterface::class.java)
}