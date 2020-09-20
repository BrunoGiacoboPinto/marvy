package br.com.marvy.injection

import br.com.marvy.viewmodel.CharactersViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val koinModules: Module = module {
    viewModel { CharactersViewModel() }
}