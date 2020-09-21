package br.com.marvy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.marvy.paging.CharactersBoundaryLoader
import br.com.marvy.persistence.CharactersInfo
import br.com.marvy.persistence.CharactersInfoDAO
import java.util.concurrent.Executors

class CharactersViewModel(mSource: CharactersInfoDAO) : ViewModel() {

    private val mExecutor = Executors.newFixedThreadPool(2)
    private var mCharactersList: LiveData<PagedList<CharactersInfo>>

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(1)
            .setPageSize(1)
            .build()

        val pagedListBuilder = LivePagedListBuilder(mSource.dataSource(), config).apply {
            setBoundaryCallback(CharactersBoundaryLoader(mSource, mExecutor))
        }

        mCharactersList = pagedListBuilder.build()
    }

    val charactersList: LiveData<PagedList<CharactersInfo>>
        get() = mCharactersList


    fun characterAt(position: Int): CharactersInfo? = mCharactersList.value?.get(position)

}