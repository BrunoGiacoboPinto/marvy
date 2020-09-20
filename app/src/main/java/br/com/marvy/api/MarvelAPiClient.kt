package br.com.marvy.api

import br.com.marvy.model.CharactersApiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.security.MessageDigest
import java.util.*

const val marvelApiUrl = "https://gateway.marvel.com/"

private val algorithm = MessageDigest.getInstance("MD5")

private fun ByteArray.toHex() = joinToString("") { "%02x".format(it) }

private fun String.md5(): String {
    algorithm.reset()
    algorithm.update(this.encodeToByteArray())
    return algorithm.digest().toHex()
}


interface MarvelAPiClient {

    @GET("v1/public/characters")
    fun characters(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("ts") ts: Long,
    ): Call<CharactersApiResponse>

    companion object {
        val instance: MarvelAPiClient by lazy {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(marvelApiUrl)
                .build()
                .create(MarvelAPiClient::class.java)
        }
    }

}

// TODO: Hide sensitive stuff
fun getMarvelCharacters(offset: Int): Call<CharactersApiResponse> {
    val apikey = "4ea2906d9617b23441d1073993a504bf"
    val ts = Date().time
    val hash = "${ts}6af9386d70941abddc2b63181d16a60414dbfab3${apikey}".md5()

    return MarvelAPiClient.instance.characters(
        apikey = apikey,
        limit = 100,
        offset = offset,
        hash = hash,
        ts = ts
    )
}