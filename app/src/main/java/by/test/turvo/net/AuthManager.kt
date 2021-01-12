package by.test.turvo.net

import by.test.turvo.net.response.BooksResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class AuthManager(private val api: Api) {

    /**
     * Fetch list of books
     */
    fun getBooks(
        observer: DisposableObserver<BooksResponse>
    ): Disposable {
        return api.getBooks()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(observer)
    }
}
