package br.com.marvy.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.marvy.R
import br.com.marvy.adapters.CharactersListAdapter
import br.com.marvy.viewmodel.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters_list.*


class CharactersListFragment : Fragment() {
    private val mCharactersListAdapter = CharactersListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.charactersList
            .observe(viewLifecycleOwner, mCharactersListAdapter::submitList)

        with(mCharactersRecyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = mCharactersListAdapter
        }
    }


    private val viewModel: CharactersViewModel
        get() = ViewModelProviders.of(requireActivity())
            .get(CharactersViewModel::class.java)


}