package com.example.newfinal

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(
    private val dataset: List<ListElement>
): RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val iconImage: ImageView = view.findViewById(R.id.iconImageView)
        val name: TextView = view.findViewById(R.id.nameTextView)
        val city: TextView = view.findViewById(R.id.authorTextview)
        val status: TextView = view.findViewById(R.id.statusTextView)

        fun bindData(item: ListElement): Unit{
            iconImage.setColorFilter(Color.parseColor(item.color), PorterDuff.Mode.SRC_IN)
            name.setText(item.name)
            city.setText(item.author)
            status.setText(item.status)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_element, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.bindData(item)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}