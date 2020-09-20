package br.com.marvy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.marvy.R
import br.com.marvy.model.CharactersData
import br.com.marvy.views.CharactersListFragmentDirections

val CharactersDataComparator = object : DiffUtil.ItemCallback<CharactersData>() {
    override fun areItemsTheSame(oldItem: CharactersData, newItem: CharactersData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharactersData, newItem: CharactersData): Boolean {
        return oldItem == newItem
    }
}


class CharactersDataViewHolder(private val view: ConstraintLayout) : RecyclerView.ViewHolder(view) {
    val mCardLayout: ConstraintLayout = view
    val name: TextView = view.findViewById<TextView>(R.id.name)
}


class CharactersListAdapter :
    PagedListAdapter<CharactersData, CharactersDataViewHolder>(CharactersDataComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.characters_list_item, parent, false) as ConstraintLayout
        return CharactersDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersDataViewHolder, position: Int) {
        with(getItem(position)!!) {
            holder.name.text = "$name -- $id"
            holder.mCardLayout.setOnClickListener { view ->
                view.findNavController().navigate(
                    CharactersListFragmentDirections.toDetail(id)
                )
            }

        }
    }
}