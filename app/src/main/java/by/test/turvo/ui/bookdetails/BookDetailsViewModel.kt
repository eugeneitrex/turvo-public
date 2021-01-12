package by.test.turvo.ui.bookdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.test.turvo.BooksService
import by.test.turvo.net.response.Book
import by.test.turvo.ui.BaseViewModel

class BookDetailsViewModel(private val booksService: BooksService) : BaseViewModel() {

    private var _bookLiveData = MutableLiveData<Book>()
    var bookLiveData: LiveData<Book> = _bookLiveData

    /**
     * Setup book on a screen
     * @param bookId id of book from args
     */
    fun init(bookId: String) {
        _bookLiveData.value = booksService.booksLiveData.value?.first {
            it.id == bookId
        }
    }

    /**
     * Changes 'favorite' flag
     * @param book object to change
     */
    fun favoriteChanged(book: Book) {
        booksService.favoriteChanged(book)
    }
}
