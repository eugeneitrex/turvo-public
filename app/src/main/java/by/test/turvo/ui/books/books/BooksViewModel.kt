package by.test.turvo.ui.books.books

import by.test.turvo.BooksService
import by.test.turvo.net.response.Book
import by.test.turvo.ui.books.BaseBooksViewModel

class BooksViewModel(booksService: BooksService) : BaseBooksViewModel(booksService) {

    override fun filterBooks(books: ArrayList<Book>): ArrayList<Book> {
        return books
    }
}
