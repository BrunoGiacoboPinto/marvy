package br.com.marvy.extenstions

import androidx.fragment.app.Fragment
import br.com.marvy.MainActivity
import br.com.marvy.viewmodel.CharactersViewModel

val Fragment.viewModel: CharactersViewModel
    get() = (activity as MainActivity).mCharactersViewModel