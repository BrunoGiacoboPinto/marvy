package br.com.marvy.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.marvy.R
import br.com.marvy.adapters.CharactersListAdapter
import br.com.marvy.viewmodel.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class CharactersListFragment : Fragment() {

    private val mCharactersViewModel: CharactersViewModel by viewModel()
    private val mCharactersListAdapter = CharactersListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_characters_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mCharactersViewModel.charactersList.observe(
            viewLifecycleOwner,
            mCharactersListAdapter::submitList
        )

        with(mCharactersRecyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = mCharactersListAdapter
        }
    }

}