package by.test.turvo.ui.books.books

import by.test.turvo.ui.books.BaseBooksFragment
import by.test.turvo.ui.books.BaseBooksViewModel
import org.kodein.di.generic.instance

open class BooksFragment : BaseBooksFragment() {
    override fun getMyViewModel(): BaseBooksViewModel {
        val viewModel: BooksViewModel by instance()
        return viewModel
    }
}
