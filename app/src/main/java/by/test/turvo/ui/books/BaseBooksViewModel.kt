package by.test.turvo.ui.books

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.test.turvo.BooksService
import by.test.turvo.R
import by.test.turvo.net.response.Book
import by.test.turvo.ui.BaseViewModel

abstract class BaseBooksViewModel(private val booksService: BooksService) : BaseViewModel() {

    private val _booksLiveData = MutableLiveData<ArrayList<Book>>()
    val booksLiveData: LiveData<ArrayList<Book>> = _booksLiveData
    var isBusy = MutableLiveData(false)

    init {
        getBooks()
    }

    abstract fun filterBooks(books: ArrayList<Book>): MutableList<Book>

    /**
     * Navigate to details screen
     *
     * @param args Bundle for details screen
     */
    fun itemClicked(args: Bundle) {
        //navigate to book details screen
        navigate(R.id.nav_book_details, args)
    }

    /**
     * Changes favorite status
     *
     * @param book item to change
     */
    fun favoriteClicked(book: Book) {
        booksService.favoriteChanged(book)
        getBooks()
    }

    private fun getBooks() {
        booksService.getBooks {
            _booksLiveData.value = filterBooks(it) as ArrayList<Book>
        }
    }
}
