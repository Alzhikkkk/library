package com.example.newfinal.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newfinal.ListElement
import com.example.newfinal.R

class ElementAdapter: ListAdapter<ListElement, ElementAdapter.ItemViewHolder>(ListElementDiffCallback()){

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val iconImage: ImageView = view.findViewById(R.id.iconImageView)
        val name: TextView = view.findViewById(R.id.nameTextView)
        val city: TextView = view.findViewById(R.id.authorTextview)
        val status: TextView = view.findViewById(R.id.statusTextView)

        fun bindData(item: ListElement): Unit{
            bindImage(iconImage, item.color)
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
     //   val item = dataset[position]
        holder.bindData(getItem(position))
    }

}
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

class ListElementDiffCallback: DiffUtil.ItemCallback<ListElement>(){
    override fun areItemsTheSame(oldItem: ListElement, newItem: ListElement): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ListElement, newItem: ListElement): Boolean {
        return oldItem==newItem
    }

}