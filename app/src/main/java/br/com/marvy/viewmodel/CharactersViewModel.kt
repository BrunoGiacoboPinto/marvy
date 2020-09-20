package br.com.marvy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.marvy.model.CharactersData
import br.com.marvy.paging.CharactersDataSourceFactory
import java.util.concurrent.Executors

class CharactersViewModel : ViewModel() {

    private val mExecutor = Executors.newFixedThreadPool(2)
    private var mCharactersList: LiveData<PagedList<CharactersData>>

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(1)
            .setPageSize(1)
            .build()

        val pagedListBuilder = LivePagedListBuilder(CharactersDataSourceFactory(), config).apply {
            setFetchExecutor(mExecutor)
        }

        mCharactersList = pagedListBuilder.build()
    }

    val charactersList: LiveData<PagedList<CharactersData>>
        get() = mCharactersList


    fun characterAt(position: Int): CharactersData? = mCharactersList.value?.get(position)

}