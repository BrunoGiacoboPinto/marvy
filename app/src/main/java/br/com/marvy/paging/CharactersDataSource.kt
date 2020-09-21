package br.com.marvy.paging

import android.util.Log
import androidx.paging.PagedList
import br.com.marvy.api.getMarvelCharacters
import br.com.marvy.model.CharactersApiResponse
import br.com.marvy.persistence.CharactersInfo
import br.com.marvy.persistence.CharactersInfoDAO
import br.com.marvy.persistence.transformCharactersData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService


class CharactersBoundaryLoader(val mSource: CharactersInfoDAO, val mExecutor: ExecutorService) :
    PagedList.BoundaryCallback<CharactersInfo>() {

    override fun onZeroItemsLoaded() {
        getMarvelCharacters(0)
            .enqueue(object : Callback<CharactersApiResponse> {
                override fun onResponse(
                    call: Call<CharactersApiResponse>,
                    response: Response<CharactersApiResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let { data ->
                            mExecutor.submit {
                                val charInfoList = data.results.map {
                                    transformCharactersData(it, 100)
                                }

                                mSource.insertAll(charInfoList)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<CharactersApiResponse>, t: Throwable) {
                    Log.d("_____", "CAll failded")
                }
            })
    }

    override fun onItemAtEndLoaded(itemAtEnd: CharactersInfo) {
        val nexPageIndex = itemAtEnd.pageIndex + 100

        Log.d("______", "Will load page = $nexPageIndex")

        getMarvelCharacters(nexPageIndex)
            .enqueue(object : Callback<CharactersApiResponse> {
                override fun onResponse(
                    call: Call<CharactersApiResponse>,
                    response: Response<CharactersApiResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let { data ->
                            mExecutor.submit {
                                val charInfoList = data.results.map {
                                    transformCharactersData(it, nexPageIndex)
                                }

                                mSource.insertAll(charInfoList)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<CharactersApiResponse>, t: Throwable) {
                    Log.d("_____", "CAll failded")
                }
            })
    }


}