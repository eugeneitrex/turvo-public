package by.test.turvo.net

import by.test.turvo.net.response.BooksResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {
    @GET("books.json")
    fun getBooks(): Observable<BooksResponse>
}
