import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newfinal.R
import com.example.newfinal.model.BookSearchResultData


class WishlistAdapter: ListAdapter<BookSearchResultData, WishlistAdapter.MyViewHolder>(BookSearchResultDataDiffCallback()) {

    private var bookList = emptyList<BookSearchResultData>()


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.book_wishlist_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = bookList[position]
        holder.itemView.findViewById<TextView>(R.id.book_name).text = currentItem.title
        holder.itemView.findViewById<TextView>(R.id.book_publisher).text = currentItem.publisher
        holder.itemView.findViewById<ImageView>(R.id.delete_image_view).setOnClickListener {
            bookList[holder.adapterPosition]
        }
    }


    fun setData(book: List<BookSearchResultData>) {
        this.bookList = book
        notifyDataSetChanged()
    }

    interface DeleteBookInterface {
        fun onClick(book: BookSearchResultData)
    }

}

class BookSearchResultDataDiffCallback: DiffUtil.ItemCallback<BookSearchResultData>(){
    override fun areItemsTheSame(oldItem: BookSearchResultData, newItem: BookSearchResultData): Boolean {
        TODO("Not yet implemented")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: BookSearchResultData,
        newItem: BookSearchResultData
    ): Boolean {
        return oldItem==newItem
    }

}