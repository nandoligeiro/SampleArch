package br.com.nandoligeiro.samplearch.data.vo

import androidx.annotation.Nullable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoResponse(
    @Json(name = "id")val repoId: Int,
    @Json(name = "name")val repoName: String,
    @Json( name = "description")@Nullable val description: String = "",
    @Json(name = "owner")val owner: Owner
)

@JsonClass(generateAdapter = true)
data class Owner(
    @Json(name = "login")val authorName: String,
    @Json(name = "avatar_url")val imgProfile: String
)