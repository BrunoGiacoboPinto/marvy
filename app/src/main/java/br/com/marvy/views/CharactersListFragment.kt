package br.com.marvy.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.marvy.R
import kotlinx.android.synthetic.main.fragment_characters_list.*


class CharactersListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listBtn.setOnClickListener { btn ->
            btn.findNavController()
                .navigate(R.id.to_detail)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharactersListFragment()
    }
}