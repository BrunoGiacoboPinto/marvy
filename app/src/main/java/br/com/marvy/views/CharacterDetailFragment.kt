package br.com.marvy.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.marvy.R
import br.com.marvy.viewmodel.CharactersViewModel


class CharacterDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.run {
            val position = getInt("position")

            val item = viewModel.characterAt(getInt("position"))

            Log.d(
                "_____",
                "Will load position $position and item = ${item?.name}}"
            )
        }

    }

    private val viewModel: CharactersViewModel
        get() = ViewModelProviders.of(requireActivity())
            .get(CharactersViewModel::class.java)
}