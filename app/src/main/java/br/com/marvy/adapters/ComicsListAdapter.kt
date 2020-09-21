package br.com.marvy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.marvy.R

class ComicsItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val headline: TextView = view.findViewById(R.id.comicsHeadline)

    fun bind(content: String?) {
        headline.text = content
    }
}

class ComicsListAdapter(private val mComicsList: List<String>?) :
    RecyclerView.Adapter<ComicsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comics_list_item, parent, false)
        return ComicsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicsItemViewHolder, position: Int) {
        holder.bind(mComicsList?.get(position))
    }

    override fun getItemCount(): Int {
        return mComicsList?.size!!
    }
}