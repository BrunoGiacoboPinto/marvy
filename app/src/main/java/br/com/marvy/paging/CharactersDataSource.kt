package br.com.marvy.paging

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import br.com.marvy.api.getMarvelCharacters
import br.com.marvy.model.CharactersApiResponse
import br.com.marvy.model.CharactersData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CharactersDataSource : PageKeyedDataSource<Int, CharactersData>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharactersData>
    ) {

        getMarvelCharacters(0)
            .enqueue(object : Callback<CharactersApiResponse> {
                override fun onResponse(
                    call: Call<CharactersApiResponse>,
                    response: Response<CharactersApiResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let { data ->
                            callback.onResult(data.results, 0, data.count, 0, data.offset + 100)
                        }
                    }
                }

                override fun onFailure(call: Call<CharactersApiResponse>, t: Throwable) {
                    Log.d("_____", "CAll failded")
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CharactersData>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CharactersData>) {
        // need to implement TODO
        Log.d("_____", "Will load next page with offet = ${params.key}")

        getMarvelCharacters(params.key)
            .enqueue(object : Callback<CharactersApiResponse> {
                override fun onResponse(
                    call: Call<CharactersApiResponse>,
                    response: Response<CharactersApiResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let { data ->
                            callback.onResult(data.results, params.key + 100)
                        }
                    }
                }

                override fun onFailure(call: Call<CharactersApiResponse>, t: Throwable) {
                    Log.d("_____", "CAll failded")
                }
            })

    }
}

class CharactersDataSourceFactory : DataSource.Factory<Int, CharactersData>() {
    override fun create(): DataSource<Int, CharactersData> {
        return CharactersDataSource()
    }
}