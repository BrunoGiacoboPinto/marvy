package br.com.marvy.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.marvy.R
import br.com.marvy.adapters.ComicsListAdapter
import br.com.marvy.extenstions.viewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_character_detail.*


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
            val item = viewModel.characterAt(getInt("position"))

            characterDetailName.text = item?.name?.toUpperCase()

            Picasso.get()
                .load(item?.thumbnailPath)
                .into(CharacterDetailsPoster)

            with(comicsList) {
                layoutManager = LinearLayoutManager(activity)
                adapter = ComicsListAdapter(item?.comics)
            }
        }
    }


}