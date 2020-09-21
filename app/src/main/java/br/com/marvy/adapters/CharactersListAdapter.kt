package br.com.marvy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.marvy.R
import br.com.marvy.persistence.CharactersInfo
import br.com.marvy.views.CharactersListFragmentDirections
import com.squareup.picasso.Picasso

val CharactersDataComparator = object : DiffUtil.ItemCallback<CharactersInfo>() {
    override fun areItemsTheSame(oldItem: CharactersInfo, newItem: CharactersInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharactersInfo, newItem: CharactersInfo): Boolean {
        return oldItem == newItem
    }
}


class CharactersDataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val poster: ImageView = view.findViewById(R.id.characterPoster)

    fun bindTo(data: CharactersInfo?, position: Int) {
        view.setOnClickListener { view ->
            view.findNavController().navigate(
                CharactersListFragmentDirections.toDetail(position)
            )
        }

        Picasso.get()
            .load(data?.thumbnailPath)
            .into(poster)
    }
}


class CharactersListAdapter :
    PagedListAdapter<CharactersInfo, CharactersDataViewHolder>(CharactersDataComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.characters_list_item, parent, false)
        return CharactersDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersDataViewHolder, position: Int) {
        holder.bindTo(getItem(position), position)
    }
}