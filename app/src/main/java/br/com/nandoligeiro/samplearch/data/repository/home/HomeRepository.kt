package br.com.nandoligeiro.samplearch.data.repository.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.nandoligeiro.samplearch.data.model.Repo
import br.com.nandoligeiro.samplearch.data.repository.datasource.network.GithubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeRepository {

    fun getRepositories(): MutableLiveData<List<Repo>> {

        val data = MutableLiveData<List<Repo>>()
        val repos: MutableList<Repo> = mutableListOf()

        GlobalScope.launch(Dispatchers.Main) {
            val response = GithubApi.api.getRepositoriesAsync().await()

            if (response.isSuccessful) {
                response.body()?.let {
                    for (repoResponse in it) {
                        with(repoResponse) {
                            val repo = Repo(
                                repoId,
                                repoName,
                                description,
                                owner.authorName,
                                owner.imgProfile
                            )
                            repos.add(repo)
                        }

                    }

                    data.value = repos
                }

            } else {
                Log.d("GET REPOSITORIES", "Error ${response.code()}")
            }
        }


        return data
    }
}