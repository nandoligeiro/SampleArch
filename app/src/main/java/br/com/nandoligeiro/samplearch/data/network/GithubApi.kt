package br.com.nandoligeiro.samplearch.data.network

import br.com.nandoligeiro.samplearch.domain.vo.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {


    @GET("/repositories")
    suspend fun getRepositories(): List<RepoResponse>


    @GET("/search/repositories")
    fun getJavaRepositories(@Query("q") q: String,
                            @Query("sort") sort: String,
                            @Query("page") page: String)


    @GET("repos/{owner}/{repo}/pulls")
    fun getPulls(@Path("owner") owner: String, @Path("repo") repo: String)


    @GET("users/{user}")
    fun getUser(@Path("user") user: String)


}