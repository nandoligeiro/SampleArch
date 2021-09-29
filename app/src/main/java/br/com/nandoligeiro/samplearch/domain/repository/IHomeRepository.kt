package br.com.nandoligeiro.samplearch.domain.repository

import br.com.nandoligeiro.samplearch.data.repository.home.RepoData

interface IHomeRepository {
    suspend fun getRepos(): List<RepoData>
}