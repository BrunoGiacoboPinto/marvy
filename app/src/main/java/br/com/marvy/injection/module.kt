package br.com.marvy.injection

import androidx.room.Room
import br.com.marvy.persistence.CharactersDB
import br.com.marvy.persistence.CharactersInfoDAO
import br.com.marvy.viewmodel.CharactersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val koinModules: Module = module {
    viewModel { CharactersViewModel(get()) }

    single {
        Room.databaseBuilder(androidContext(), CharactersDB::class.java, "CharactersDB")
            .build()
    }

    fun providesDao(db: CharactersDB): CharactersInfoDAO {
        return db.charactersDAO()
    }

    single { providesDao(get()) }
}