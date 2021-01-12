package by.test.turvo

import androidx.lifecycle.MutableLiveData
import by.test.turvo.net.AuthManager
import by.test.turvo.net.response.Book
import by.test.turvo.net.response.BooksResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class BooksService(private val compositeDisposable: CompositeDisposable, private val netManager: AuthManager) {

    /**
     * stores list of books from server
     */
    var booksLiveData = MutableLiveData<MutableList<Book>>()

    /**
     * Updates list of books and notifies observers
     * @param book item to change 'favorite' flag
     */
    fun favoriteChanged(book: Book) {
        booksLiveData.value?.let {list ->
            booksLiveData.value = (list.map {
                if (it.id == book.id) {
                    it.liked = it.liked.not()
                }
                it
            }).toMutableList()
        }
    }

    fun getBooks(function: (ArrayList<Book>) -> Unit) {
        if (booksLiveData.value.isNullOrEmpty().not()) {
            function(booksLiveData.value as ArrayList<Book>)
            return
        }

        compositeDisposable.add(netManager.getBooks(object : DisposableObserver<BooksResponse>() {
            override fun onComplete() {}

            override fun onNext(t: BooksResponse) {
                booksLiveData.value = t.books
                function(t.books)
            }

            override fun onError(e: Throwable) {}
        }))
    }
}
