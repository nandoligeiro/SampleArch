package br.com.nandoligeiro.samplearch.data.repository.home

import br.com.nandoligeiro.samplearch.data.network.GithubApi
import br.com.nandoligeiro.samplearch.domain.repository.IHomeRepository

class HomeRepository(private val githubApi: GithubApi) : IHomeRepository {

    override suspend fun getRepos(): List<RepoData> {
        val response = githubApi.getRepositories()

        return ModelMapper.mapperHomeModel(response)
    }

//    fun getRepos(): LiveData<List<Repo>> {
//        job = Job()
//        return object : LiveData<List<Repo>>() {
//            override fun onActive() {
//                super.onActive()
//                job?.let {
//                    CoroutineScope(IO + it).launch {
//                        val response = GithubApi.api.getRepositories()
//                        withContext(Main) {
//                            value = ModelMapper.mapperHomeModel(response)
//                            it.complete()
//                        }
//                    }
//                }
//
//            }
//        }
//    }
}
