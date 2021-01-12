package by.test.turvo.ui.books.favorites

import by.test.turvo.BooksService
import by.test.turvo.net.response.Book
import by.test.turvo.ui.books.BaseBooksViewModel

class FavoritesViewModel(booksService: BooksService) : BaseBooksViewModel(booksService) {

    override fun filterBooks(books: ArrayList<Book>): MutableList<Book> {
        return books.filter { it.liked }.toMutableList()
    }
}
