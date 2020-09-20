package br.com.marvy.model

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

data class CharactersData(
    val comics: CharactersComicsData,
    val thumbnail: CharactersThumbnail,
    val description: String,
    val resourceURI: String,
    val modified: String,
    val name: String,
    val id: Int
)

data class CharactersThumbnail(
    val extension: String,
    val path: String
)

data class CharactersComicsData(
    val items: List<ResourceURIItem>,
    val collectionURI: String,
    val available: Int
)

data class ResourceURIItem(
    val resourceURI: String,
    val name: String
)