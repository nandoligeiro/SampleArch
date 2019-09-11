package br.com.nandoligeiro.samplearch.data.model


data class Repo(
    val repoId: Int,
    val repoName: String,
    val description: String,
    val authorName: String,
    val imgProfile: String,
    val numberOfStars: Int = 1,
    val numberOfForks: Int = 1
)