package by.test.turvo.ui.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.test.turvo.R
import by.test.turvo.databinding.BookItemLayoutBinding
import by.test.turvo.net.response.Book
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation

class BooksAdapter(
    private val favoriteListener: (Book) -> Unit,
    private val itemClickListener: (String) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {

    var items = ArrayList<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.book_item_layout, parent, false)
        return MyViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        setBook(items[position], holder)
    }

    fun setData(list: MutableList<Book>?) {
        list?.let { it ->
            items.clear()
            items.addAll(it)
            notifyDataSetChanged()
        }
    }

    private fun setBook(item: Book, holder: MyViewHolder) {

        with(holder.binding) {
            bookItemYear.text = item.year
            bookItemName.text = item.name
            bookItemDescription.text = item.excerpt
            bookItemAuthor.text = item.author
            bookItemIsbn.text = item.isbn
            Glide.with(bookItemImage.context).load(item.image)
                .into(bookItemImage)
            bookItemLayout.setOnClickListener {
                //item click
                itemClickListener(item.id)
            }

            with(bookItemkAddToFavs) {
                isChecked = item.liked
                setOnClickListener {
                    favoriteListener(item)
                }
            }

            Glide.with(bookItemBackground.context).load(item.image)
                .apply(RequestOptions.bitmapTransform(BlurTransformation(35, 7)))
                .into(bookItemBackground)
        }
    }
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var binding = BookItemLayoutBinding.bind(view)
}
