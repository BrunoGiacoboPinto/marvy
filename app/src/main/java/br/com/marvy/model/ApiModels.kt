package br.com.marvy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class CharactersApiResponse(
    val data: CharactersPageData,
    val attributionText: String,
    val attributionHTML: String,
    val copyright: String,
    val status: String,
    val etag: String,
    val code: Int
)

data class CharactersPageData(
    val results: List<CharactersData>,
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int
)

@Parcelize
data class CharactersData(
    val comics: CharactersComicsData,
    val thumbnail: CharactersThumbnail,
    val description: String,
    val resourceURI: String,
    val modified: String,
    val name: String,
    val id: Int
) : Parcelable

@Parcelize
data class CharactersThumbnail(
    val extension: String,
    val path: String
) : Parcelable

@Parcelize
data class CharactersComicsData(
    val items: List<ResourceURIItem>,
    val collectionURI: String,
    val available: Int
) : Parcelable

@Parcelize
data class ResourceURIItem(
    val resourceURI: String,
    val name: String
) : Parcelable