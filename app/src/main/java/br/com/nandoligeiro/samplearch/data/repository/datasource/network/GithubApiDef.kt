package br.com.nandoligeiro.samplearch.data.repository.datasource.network

import br.com.nandoligeiro.samplearch.data.vo.RepoResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiDef {

    @GET("/repositories")
    fun getRepositoriesAsync(): Deferred<Response<List<RepoResponse>>>


    @GET("/search/repositories")
    fun getJavaRepositories(@Query("q") q: String,
                            @Query("sort") sort: String,
                            @Query("page") page: String)


    @GET("repos/{owner}/{repo}/pulls")
    fun getPulls(@Path("owner") owner: String, @Path("repo") repo: String)


    @GET("users/{user}")
     fun getUser(@Path("user") user: String)


}