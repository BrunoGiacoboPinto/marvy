package br.com.marvy.extenstions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.marvy.viewmodel.CharactersViewModel

val Fragment.viewModel: CharactersViewModel
    get() = ViewModelProviders.of(requireActivity())
        .get(CharactersViewModel::class.java)