import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newfinal.ListElement
import com.example.newfinal.R
import com.example.newfinal.bindImage
import com.example.newfinal.model.BookSearchResultData


class WishlistAdapter: ListAdapter<BookSearchResultData, WishlistAdapter.MyViewHolder>(BookSearchResultDataDiffCallback()) {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView.rootView) {
        val book_name: TextView = itemView.findViewById(R.id.book_name)
        val book_publisher: TextView = itemView.findViewById(R.id.book_publisher)
        val delete : ImageView = itemView.findViewById(R.id.delete_image_view);
        fun bindData(item: BookSearchResultData): Unit{
            Log.e("gdeTy", item.title)
            book_name.setText(item.title)
            book_publisher.setText(item.publisher)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.book_wishlist_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    interface DeleteBookInterface {
        fun onClick(book: BookSearchResultData)
    }

}

class BookSearchResultDataDiffCallback: DiffUtil.ItemCallback<BookSearchResultData>(){
    override fun areItemsTheSame(oldItem: BookSearchResultData, newItem: BookSearchResultData): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(
        oldItem: BookSearchResultData,
        newItem: BookSearchResultData
    ): Boolean {
        return oldItem==newItem
    }

}