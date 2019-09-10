package br.com.nandoligeiro.samplearch.data.repository.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.nandoligeiro.samplearch.data.model.Repo
import br.com.nandoligeiro.samplearch.data.repository.datasource.network.GithubApi
import br.com.nandoligeiro.samplearch.data.vo.RepoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {

    fun getRepositories(): MutableLiveData<List<Repo>> {

        val data = MutableLiveData<List<Repo>>()
        val repos: MutableList<Repo> = mutableListOf()

        GithubApi.api.getRepositories().enqueue(object: Callback<List<RepoResponse>> {
            override fun onFailure(call: Call<List<RepoResponse>>, t: Throwable) {
                t
            }

            override fun onResponse(call: Call<List<RepoResponse>>, response: Response<List<RepoResponse>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        for (repoResponse in it){
                            with(repoResponse){
                                val repo = Repo(repoId, repoName, description, owner.authorName, owner.imgProfile)
                                repos.add(repo)
                            }

                        }

                    }

                    data.value = repos
                }
            }

        })


        return data
    }
}