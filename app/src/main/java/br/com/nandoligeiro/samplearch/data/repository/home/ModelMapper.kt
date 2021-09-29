package br.com.nandoligeiro.samplearch.data.repository.home

import br.com.nandoligeiro.samplearch.domain.vo.RepoResponse

object ModelMapper {

    fun mapperHomeModel(it: List<RepoResponse>): List<RepoData> {
        val repos: ArrayList<RepoData> = arrayListOf()
        for (repoResponse in it) {
            with(repoResponse) {
                val repo = RepoData(
                    repoId,
                    repoName,
                    description,
                    owner.authorName,
                    owner.imgProfile
                )
                repos.add(repo)
            }
        }
        return repos
    }
}